package com.milktea.milkuser.com.milktea.milkteauser.utils;



import com.apple.appleuser.service.PayInfoService;
import com.apple.appleuser.vo.IOTBean;
import com.apple.appleuser.vo.IotResponseBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UtilsTest {

  @Test
  public void test(){
      IotResponseBean iotResponseBean=new IotResponseBean();
      iotResponseBean.setPayOrderId("11111");
      iotResponseBean.setAmount(100);
      iotResponseBean.setMchId("11561");
      iotResponseBean.setCurrency("CAD");

      System.out.println(iotResponseBean.toString());

  }

    @Test
    public void getTimeStamp(){
      String timestamp=(System.currentTimeMillis()+"").substring(0,10);
      System.out.println(timestamp);
    }
}
