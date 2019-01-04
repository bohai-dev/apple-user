package com.milktea.milkuser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.apple.appleuser.Application;
import com.apple.appleuser.task.QuartzService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	
	@Autowired
	private QuartzService quartzService;

    @Test
    public void contextLoads() {
    	//this.quartzService.timerToNow();
    }

}
