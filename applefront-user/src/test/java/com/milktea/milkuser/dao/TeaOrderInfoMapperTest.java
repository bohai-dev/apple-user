package com.milktea.milkuser.dao;



import com.apple.appleuser.Application;
import com.apple.appleuser.dao.TeaOrderInfoMapper;
import com.apple.appleuser.domain.TeaOrderInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Cteated by cxy on 2018/9/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TeaOrderInfoMapperTest {

    @Autowired
    TeaOrderInfoMapper teaOrderInfoMapper;

    @Test
    public void selectByOrderNo(){
        TeaOrderInfo teaOrderInfo=teaOrderInfoMapper.selectByPrimaryKey("20180822_A_837");
        Assert.assertNotNull(teaOrderInfo);

    }
}
