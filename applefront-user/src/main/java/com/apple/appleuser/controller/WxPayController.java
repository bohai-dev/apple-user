package com.apple.appleuser.controller;


import com.apple.appleuser.config.WXPayConfiguration;
import com.apple.appleuser.dao.TeaOrderInfoMapper;
import com.apple.appleuser.dao.TeaPayInfoMapper;
import com.apple.appleuser.domain.TeaOrderInfo;
import com.apple.appleuser.domain.TeaPayInfo;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.WXPayService;
import com.apple.appleuser.vo.ResponseBody;
import com.apple.appleuser.vo.WXPayVo;
import com.milktea.milkteauser.wxpay.WXPay;
import com.milktea.milkteauser.wxpay.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/wxpay")
public class WxPayController {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    WXPayService wxPayService;
    @Autowired
    TeaPayInfoMapper TeaPayInfoMapper;
    @Autowired
    TeaOrderInfoMapper  teaOrderInfoMapper;


    @RequestMapping("/notify")
    //异步接收微信支付通知
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        LOGGER.info("微信支付异步接收通知");
        StringBuilder sb = new StringBuilder();
        try {
            out = response.getWriter();

            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String notifyData = sb.toString();
            LOGGER.info("微信支付接收到通知：" + notifyData);
            //需要验证签名
            WXPayConfiguration config = new WXPayConfiguration();
            WXPay wxpay = new WXPay(config);
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);  // 转换成map

            Map<String,String> resultMap=new HashMap<>();
            resultMap.put("return_code","SUCCESS");
            resultMap.put("return_msg","OK");
            String resultXml=WXPayUtil.mapToXml(resultMap);
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                if (notifyMap.get("return_code").equals("SUCCESS")) {
                    //获取支付单号
                    String  payId = notifyMap.get("out_trade_no");
                    TeaPayInfo newTeaPayInfo=new TeaPayInfo();
                    newTeaPayInfo.setPayId(payId);

                    TeaOrderInfo teaOrderInfo=new TeaOrderInfo();

                    //查询支付信息
                    TeaPayInfo teaPayInfo = TeaPayInfoMapper.selectByPrimaryKey(payId);
                    if (teaPayInfo != null){
                        String orderNo=teaPayInfo.getOrderNo();
                        teaOrderInfo.setOrderNo(orderNo);

                        if (notifyMap.get("result_code").equals("SUCCESS")){
                            //处理订单
                            //获取订单状态
                            String payStatus = teaPayInfo.getPayStatus();
                            if (payStatus.equals("1")) {
                                //TODO:已经处理过，直接返回 "SUCCESS"
                                out.print(resultXml);
                            } else {

                                //更新订单 支付状态：成功
                                teaOrderInfo.setPayStatus("1");   //状态"1"成功

                                //更新支付信息
                                newTeaPayInfo.setPayStatus("1");  //状态"1"成功

                            }
                        }else {
                            String errCode=notifyMap.get("err_code");
                            String errMsg= notifyMap.get("err_code_des");

                            LOGGER.info("微信支付接收通知遇错,err_code:" + errCode + ",err_code_des:" + errMsg);
                            //更新支付信息 状态：失败
                            newTeaPayInfo.setPayStatus("2");
                            newTeaPayInfo.setErrorMsg("errCode:"+errCode+",errMsg:"+errMsg);

                            //更新订单  支付状态：失败
                            teaOrderInfo.setPayStatus("2");
                        }
                        //更新支付信息
                        newTeaPayInfo.setUpdateTime(new Date());
                        TeaPayInfoMapper.updateByPrimaryKeySelective(newTeaPayInfo);
                        //更新订单
                        teaOrderInfo.setUpdateTime(new Date());
                        teaOrderInfoMapper.updateByPrimaryKeySelective(teaOrderInfo);

                        //回复微信服务器 成功
                        out.print(resultXml);
                    }




                } else {
                    LOGGER.info("微信支付接收通知遇错：" + notifyMap.get("return_msg"));
                }

            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                LOGGER.info("微信支付接收通知：" + "签名错误");
            }


        } catch (Exception e) {
            LOGGER.error("微信支付接收通知发生错误：" + e.toString());
        }

    }

    //发起支付
    @RequestMapping("/pay")
    public ResponseBody<Map<String, String>> wxPay(@RequestBody WXPayVo wxPayVo) throws MilkTeaException {
        ResponseBody<Map<String, String>> responseBody = new ResponseBody<>();
        Map<String, String> resultMap = wxPayService.unifiedorder(wxPayVo);

        return responseBody;
    }


}
