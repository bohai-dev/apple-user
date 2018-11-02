package com.apple.appleuser.task;

import com.apple.appleuser.dao.TeaGlobalTokenMapper;
import com.apple.appleuser.domain.TeaGlobalToken;
import com.apple.appleuser.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Cteated by cxy on 2018/11/1
 */
@Service
public class TicketTask {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketTask.class);
    @Autowired
    TeaGlobalTokenMapper teaGlobalTokenMapper;

    private String ticket;



    //两个小时执行一次
    @Scheduled(initialDelay = 5000, fixedRate = 7000*1000)
    public void RequireTicket(){
        //1、获取token
        TeaGlobalToken token= teaGlobalTokenMapper.getGlobalToken();

        String httpUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&"+"access_token="+token.getToken();

        try {
            //2、获取jsapi_ticket
            String content=HttpUtil.get(httpUrl);
            JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
            int errorCode=jsonObject.get("errcode").getAsInt();
            if (errorCode==0){
                String ticket=jsonObject.get("ticket").getAsString();
                this.ticket=ticket;
                LOGGER.info("获取到ticket:"+ticket);
            }else{
                String errMsg=jsonObject.get("errmsg").getAsString();
                LOGGER.error("获取ticket错误：errCode:"+errorCode+",errMsg:"+errMsg);
            }




        } catch (Exception e) {
            LOGGER.error("获取ticket错误："+e.toString());
        }

    }

    public String getTicket() {
        return ticket;
    }
}
