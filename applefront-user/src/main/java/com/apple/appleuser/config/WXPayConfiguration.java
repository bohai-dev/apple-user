package com.apple.appleuser.config;



import com.milktea.milkteauser.wxpay.IWXPayDomain;
import com.milktea.milkteauser.wxpay.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 微信支付配置类
 *TODO:配置
 */
public class WXPayConfiguration  extends WXPayConfig {

    private byte[] certData;

    public WXPayConfiguration() throws Exception {
        //TODO:API证书 涉及资金会回滚会使用
        /*
        String certPath = "/path/to/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();*/
    }

    //公众账号ID
    @Override
    public String getAppID() {
        return "wxbac9e1b7d8104470";
    }

    //商户号
    @Override
    public String getMchID() {
        return "1493631082";
    }

    @Override
    public String getKey() {
        return "E0E1954FB86B4CDDA3DB69E13629AAA7";
    }

    @Override
    public InputStream getCertStream() {
       /* ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;*/
       return null;

    }

    @Override
    public IWXPayDomain getWXPayDomain() {
       WxPayDomin wxPayDomin=new WxPayDomin();
       return  wxPayDomin;
    }

    class WxPayDomin implements IWXPayDomain{
        @Override
        public void report(String domain, long elapsedTimeMillis, Exception ex) {

        }

        @Override
        public DomainInfo getDomain(WXPayConfig config) {
            String domain="api.mch.weixin.qq.com";
            DomainInfo domainInfo=new DomainInfo(domain,true);
            return domainInfo;
        }
    }
}
