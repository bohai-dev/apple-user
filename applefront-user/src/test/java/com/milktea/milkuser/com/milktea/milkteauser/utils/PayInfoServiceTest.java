package com.milktea.milkuser.com.milktea.milkteauser.utils;



import com.apple.appleuser.Application;
import com.apple.appleuser.service.PayInfoService;
import com.apple.appleuser.vo.IOTBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Cteated by cxy on 2018/9/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PayInfoServiceTest {

    @Autowired
    PayInfoService payInfoService;

    @Test
    public void iotPayTest(){
        try {
            IOTBean iotBean=new IOTBean();
            iotBean.setMchOrderNo("100000004");
            iotBean.setChannelId("ALIPAY_PC");
            iotBean.setCurrency("CAD");
            iotBean.setAmount(100);
            iotBean.setSubject("奶茶");
            iotBean.setBody("奶茶一杯");
            iotBean.setExtra("{}");


            payInfoService.iotPay(iotBean);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
