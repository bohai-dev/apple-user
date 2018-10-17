package com.milktea.milkuser.com.milktea.milkteauser.utils;



import com.apple.appleuser.Application;
import com.apple.appleuser.dao.TeaPayInfoMapper;
import com.apple.appleuser.domain.TeaPayInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.apple.appleuser.service.SmsService;
import com.apple.appleuser.vo.ResponseBody;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SmsServiceTest {

   @Autowired
   SmsService smsService;

    @Autowired
    TeaPayInfoMapper teaPayInfoMapper;
   
   @Test
   public void sendSms(){
	   
	   ResponseBody<String> res= smsService.sendVerCodeSMS("17621503621","SMS_111715045");
	   System.out.println(res.getData());
   }

   @Test
    public void  insertPayRecord(){
       TeaPayInfo teaPayInfo=new TeaPayInfo();
       teaPayInfo.setPayId(teaPayInfoMapper.generateClassId());
       teaPayInfo.setOrderNo("0000000002");
       teaPayInfo.setPayStatus("0");
       teaPayInfo.setPayTime(new Date());
       teaPayInfoMapper.insertSelective(teaPayInfo);
   }
}
