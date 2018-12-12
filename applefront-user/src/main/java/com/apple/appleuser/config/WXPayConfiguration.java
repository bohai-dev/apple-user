package com.apple.appleuser.config;



import com.apple.appleuser.util.Constants;
import com.milktea.milkteauser.wxpay.IWXPayDomain;
import com.milktea.milkteauser.wxpay.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 微信支付配置类
 *
 */
public class WXPayConfiguration  {

    private String appId=Constants.APPID;  //公众账号ID
    private String mchId="1493631082";   //商户号
    private String key="E0E1954FB86B4CDDA3DB69E13629AAA7";  //key
    private String unifiedUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";



   /* public WXPayConfiguration()  {

    }

    //公众账号ID
    public String getAppID() {
        return "wxbac9e1b7d8104470";
    }

    //商户号
    public String getMchID() {
        return "1493631082";
    }


    public String getKey() {
        return "E0E1954FB86B4CDDA3DB69E13629AAA7";
    }*/

    public String getAppId() {
        return appId;
    }

    public String getMchId() {
        return mchId;
    }

    public String getKey() {
        return key;
    }

    public String getUnifiedUrl() {
        return unifiedUrl;
    }
}
