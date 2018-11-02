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
import com.apple.appleuser.util.HttpUtil;
import com.apple.appleuser.util.Utils;
import com.apple.appleuser.vo.WXPayVo;
import com.milktea.milkteauser.wxpay.WXPay;
import com.milktea.milkteauser.wxpay.WXPayConstants;
import com.milktea.milkteauser.wxpay.WXPayUtil;
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
    private static final String NOTIFY_URL="https://www.95cfun.top/wxpay/notify";

    @Autowired
    TeaOrderInfoMapper teaOrderInfoMapper;
    @Autowired
    TeaUserInfoMapper teaUserInfoMapper;

    @Autowired
    TeaPayInfoMapper teaPayInfoMapper;

    /**
     * 统一下单
     */
    @Override
    public Map<String, String> unifiedorder(WXPayVo wxPayVo) throws MilkTeaException{
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




        WXPayConfiguration config;
        try {
            config = new WXPayConfiguration();
           // wxpay = new WXPay(config);
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


        //统一下单
        Map<String, String> data = new HashMap<String, String>();

        String timeStamp=(System.currentTimeMillis()+"").substring(0,10);
        String nonceStr=Utils.getRandomWithTime(6);

        String body=wxPayVo.getBody();
        if (StringUtils.isBlank(body)){
            body="农产品购买";
        }
        data.put("appid",config.getAppId());
        data.put("mch_id",config.getMchId());
        data.put("nonce_str",nonceStr);

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
        //用户openid
        data.put("openid",openId);

        try {
            Map<String,String> resultMap=new HashMap<>();

            //签名
           String  signature=WXPayUtil.generateSignature(data,config.getKey(),WXPayConstants.SignType.MD5);
           data.put("sign",signature);
           //请求统一下单接口
            String xmlParams=WXPayUtil.mapToXml(data);
            String responeXml=HttpUtil.postText(config.getUnifiedUrl(),xmlParams);
            LOGGER.info("统一下单返回内容:"+responeXml);
            Map<String, String> resp = WXPayUtil.xmlToMap(responeXml);


            String returnCode=resp.get("return_code");
            String resultCode=resp.get("result_code");
            if (returnCode.equals("SUCCESS") && resultCode.equals("SUCCESS")){

               String prepayId=resp.get("prepay_id");
               //获取支付签名
                WXPayConfiguration wxPayConfig=new WXPayConfiguration();
                Map<String,String> signMap=new HashMap<>();

                String packageStr="prepay_id="+prepayId;
                String signType="MD5";

                signMap.put("timeStamp",timeStamp);
                signMap.put("nonceStr",nonceStr);
                signMap.put("package",packageStr);
                signMap.put("signType",signType);
                signMap.put("appId",config.getAppId());
                LOGGER.info("支付签名字符串："+signMap);
                String sign=WXPayUtil.generateSignature(signMap,wxPayConfig.getKey(),WXPayConstants.SignType.MD5);

                LOGGER.info("支付签名："+sign);



                resultMap.put("timestamp",timeStamp);
                resultMap.put("nonceStr",nonceStr);
                resultMap.put("package",packageStr);
                resultMap.put("signType",signType);
                resultMap.put("paySign",sign);




            }else {
                throw new MilkTeaException(MilkTeaErrorConstant.WXPAY_ERROR);
            }



            //保存支付记录
            teaPayInfoMapper.insertSelective(teaPayInfo);
            return resultMap;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MilkTeaException(MilkTeaErrorConstant.WXPAY_ERROR);
        }
    }








}
