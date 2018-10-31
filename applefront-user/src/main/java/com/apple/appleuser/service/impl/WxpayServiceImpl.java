package com.apple.appleuser.service.impl;

import com.apple.appleuser.config.WXPayConfiguration;
import com.apple.appleuser.dao.TeaOrderInfoMapper;
import com.apple.appleuser.dao.TeaPayInfoMapper;
import com.apple.appleuser.dao.TeaUserInfoMapper;
import com.apple.appleuser.domain.TeaOrderInfo;
import com.apple.appleuser.domain.TeaPayInfo;
import com.apple.appleuser.domain.TeaUserInfo;
import com.apple.appleuser.exception.MilkTeaErrorConstant;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.WXPayService;
import com.apple.appleuser.util.Utils;
import com.apple.appleuser.vo.WXPayVo;
import com.milktea.milkteauser.wxpay.WXPay;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Cteated by cxy on 2018/10/30
 */
@Service
public class WxpayServiceImpl implements WXPayService {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxpayServiceImpl.class);
   //TODO:定义自己的服务器ip
    private static final String SERVER_IP="39.106.145.242";
    //TODO:定义通知url
    private static final String NOTIFY_URL="http://www.95cfun.top/wxpay/notify";

    @Autowired
    TeaOrderInfoMapper teaOrderInfoMapper;
    @Autowired
    TeaUserInfoMapper teaUserInfoMapper;

    @Autowired
    TeaPayInfoMapper teaPayInfoMapper;

    /**
     * 统一下单
     */
    public  Map<String, String>  unifiedorder(WXPayVo wxPayVo) throws MilkTeaException{
        //获取订单编号
        String orderNO=wxPayVo.getOrderNO();
        //获取用户id
        String userNo=wxPayVo.getUserId();

        if (StringUtils.isBlank(orderNO)){
            throw new MilkTeaException(MilkTeaErrorConstant.ORDER_NO_REQUIRED);
        }

        if (StringUtils.isBlank(userNo)){
            throw new MilkTeaException(MilkTeaErrorConstant.USER_ID_REQUIRED);
        }

        //TODO:查询订单信息
        TeaOrderInfo teaOrderInfo=teaOrderInfoMapper.selectByPrimaryKey(orderNO);
        if (teaOrderInfo==null){
            throw new MilkTeaException(MilkTeaErrorConstant.ORDERINFO_REQUIRED);
        }
        //订单状态不是0
        if (teaOrderInfo.getOrderStatus().equals("1")){
            throw new MilkTeaException(MilkTeaErrorConstant.ORDER_STATUS_ERROR);
        }
        //TODO:查询用户信息
        TeaUserInfo teaUserInfo=teaUserInfoMapper.selectByPrimaryKey(userNo);

        if (teaUserInfo==null ){
            throw new MilkTeaException(MilkTeaErrorConstant.USER_OPENID_ERROR);
        }
        String openId=teaUserInfo.getWeixinOpenid();

        if (StringUtils.isBlank(openId)){
            throw new MilkTeaException(MilkTeaErrorConstant.USER_OPENID_ERROR);
        }



        WXPay wxpay = null;
        try {
            WXPayConfiguration config = new WXPayConfiguration();
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION);
        }


        TeaPayInfo teaPayInfo=new TeaPayInfo();
        String payId=Utils.getRandomWithTime(4);
        teaPayInfo.setPayId(payId);
        teaPayInfo.setOrderNo(orderNO);
        teaPayInfo.setPayStatus("0");
        teaPayInfo.setPayTime(new Date());
        //设置支付方式为"微信支付"
        teaPayInfo.setPayType("WXPay");



        Map<String, String> data = new HashMap<String, String>();
        String body=wxPayVo.getBody();
        if (StringUtils.isBlank(body)){
            body="农产品购买";
        }
        data.put("body", body);
        //支付号
        data.put("out_trade_no", payId);
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        //金额 单位分
        int totalFee=teaOrderInfo.getOrderPrice().multiply(new BigDecimal(100)).intValue();
        data.put("total_fee", totalFee+"");
        //服务器ip
        data.put("spbill_create_ip",SERVER_IP);
        //通知url
        data.put("notify_url", NOTIFY_URL);
        data.put("trade_type", "JSAPI ");  // 此处指定为公众号支付
        //data.put("product_id", "12");      //trade_type=NATIVE时（即扫码支付），此参数必传
        //TODO:修改为用户openid
        data.put("openid",openId);

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            LOGGER.info("微信支付返回内容:"+resp);
            System.out.println(resp);
            //保存支付记录
            teaPayInfoMapper.insertSelective(teaPayInfo);
            return  resp;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MilkTeaException(MilkTeaErrorConstant.WXPAY_ERROR);
        }
    }






}
