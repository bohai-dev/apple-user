package com.apple.appleuser.controller;



import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.WXPayService;
import com.apple.appleuser.vo.ResponseBody;
import com.apple.appleuser.vo.WXPayVo;
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
import java.util.Map;

@RequestMapping("/wxpay")
public class WxPayController {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    WXPayService wxPayService;

    @RequestMapping("/notify")
    //异步接收微信支付通知
    public void  notify(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        LOGGER.info("微信支付异步接收通知");
        StringBuilder sb = new StringBuilder();
        try {
            out=response.getWriter();
            BufferedReader reader=request.getReader();
            String line=null;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            LOGGER.info("微信支付接收到消息："+sb.toString());
            //需要验证签名


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //发起支付
    @RequestMapping("/pay")
    public  ResponseBody<Map<String,String>>  wxPay(@RequestBody WXPayVo wxPayVo) throws MilkTeaException {
        ResponseBody<Map<String,String>> responseBody=new ResponseBody<>();
        Map<String,String> resultMap=wxPayService.unifiedorder(wxPayVo);

        return responseBody;
    }



}
