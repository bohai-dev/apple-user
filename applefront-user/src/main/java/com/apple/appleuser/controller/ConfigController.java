package com.apple.appleuser.controller;

import com.apple.appleuser.exception.MilkTeaErrorConstant;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.task.TicketTask;
import com.apple.appleuser.util.Utils;
import com.apple.appleuser.vo.ResponseBody;
import com.milktea.milkteauser.wxpay.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Cteated by cxy on 2018/11/1
 */
@RestController
public class ConfigController {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    TicketTask ticketTask;



    @RequestMapping("/getconfig")
    public ResponseBody<Map<String,String>> getConfig(@RequestParam("pageUrl") String pageUrl)  throws MilkTeaException {
        ResponseBody<Map<String,String>> responseBody=new ResponseBody();
        //获取ticket
        String ticket = ticketTask.getTicket();
        //签名
        Map<String, String> dataMap = new HashMap<>();

        String nonceStr = Utils.getRandomWithTime(6);
        String timeStamp = System.currentTimeMillis() + "";

        dataMap.put("noncestr", nonceStr);
        dataMap.put("jsapi_ticket", ticket);
        dataMap.put("timestamp", timeStamp);
        dataMap.put("url", pageUrl);

        String signature = getSignature(dataMap);

        Map<String,String> resultMap=new HashMap<>();
        resultMap.put("appId","wxbac9e1b7d8104470");
        resultMap.put("timestamp",timeStamp);
        resultMap.put("nonceStr",nonceStr);
        resultMap.put("signature",signature);

        responseBody.setData(resultMap);

        return responseBody;

    }

    private String getSignature(Map<String, String> data) throws MilkTeaException {

        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k) != null && data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        String content=sb.toString();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] array = md.digest(content.getBytes("UTF-8"));
            StringBuilder sign = new StringBuilder();
            for (byte item : array) {
                sign.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }

            return sign.toString();
        } catch (Exception e) {
            LOGGER.error("签名错误:"+e.toString());
            throw new MilkTeaException(MilkTeaErrorConstant.SIGN_ERROR);
        }


    }


}
