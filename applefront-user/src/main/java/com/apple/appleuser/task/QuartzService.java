package com.apple.appleuser.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apple.appleuser.util.Constants;
import com.apple.appleuser.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apple.appleuser.dao.TeaGlobalTokenMapper;
import com.apple.appleuser.domain.TeaGlobalToken;
import com.apple.appleuser.exception.MilkTeaErrorConstant;


@Component
public class QuartzService {
    static Logger log = LoggerFactory.getLogger(QuartzService.class);

    //public static String weiXinAppid = "wxbac9e1b7d8104470";

    //public static String weiXinSecret = "08695399b120b9ed523db01ddd51d38d";

    public static String weiXinGrantType = "authorization_code";

    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    public String param;

    @Autowired
    TeaGlobalTokenMapper teaGlobalTokenMapper;


    @Scheduled(fixedRate = 7000 * 1000)
    public void getToken() {

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", Constants.APPID);
        params.put("secret", Constants.APP_SECRET);

        try {
            String jsonStr = HttpUtil.get(TOKEN_URL, params);
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            String errorCode = jsonObject.getString("errcode");
            if (errorCode != null) {
                //获取token错误
                String errMsg = jsonObject.getString("errmsg");
                log.error("获取全局token错误,errCode:" + errorCode + ",errMsg:" + errMsg);
            } else {
                //获取token
                String token = jsonObject.getString("access_token");
                log.info("获取到全局token:" + token);

                //入库
                teaGlobalTokenMapper.deleteAll();

                TeaGlobalToken teaGlobalToken = new TeaGlobalToken();
                teaGlobalToken.setToken(token);
                teaGlobalToken.setInsertTime(new Date());

                teaGlobalTokenMapper.insert(teaGlobalToken);


            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }


}
