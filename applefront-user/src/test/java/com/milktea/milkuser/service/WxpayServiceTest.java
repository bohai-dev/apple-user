package com.milktea.milkuser.service;

import com.apple.appleuser.Application;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.UserLoginService;
import com.apple.appleuser.service.UserRegisterService;
import com.apple.appleuser.service.impl.UserLoginServiceImpl;
import com.apple.appleuser.service.impl.WxpayServiceImpl;
import com.apple.appleuser.vo.WXPayVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Cteated by cxy on 2018/10/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WxpayServiceTest {

    @Autowired
    WxpayServiceImpl wxpayService;
    @Autowired
    UserRegisterService userRegisterService;

    @Autowired
    UserLoginServiceImpl userLoginService;

    @Test
    public void unifiedorderTest(){
        WXPayVo wxPayVo=new WXPayVo();
        wxPayVo.setBody("购买商品信息");
        wxPayVo.setOrderNO("20181029_A_2073");
        wxPayVo.setUserId("21");
        try {
            wxpayService.unifiedorder(wxPayVo);
        } catch (MilkTeaException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendSMS(){
        try {
            int result = userRegisterService.createPollCode("17621503621");
        } catch (MilkTeaException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest(){
        try {
            userLoginService.saveUserInfo("4541","8788");
        } catch (MilkTeaException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStr(){
        String str="https://api.weixin.qq.com/sns/userinfo?access_token=4541&openid=8788&lang=zh_CN &";
        System.out.println(str.length());
    }
}
