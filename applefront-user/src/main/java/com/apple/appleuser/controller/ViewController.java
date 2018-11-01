package com.apple.appleuser.controller;


import com.apple.appleuser.exception.MilkTeaErrorConstant;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.task.TicketTask;
import com.apple.appleuser.util.Utils;
import com.apple.appleuser.vo.ResponseBody;
import com.milktea.milkteauser.wxpay.WXPayConstants;
import com.milktea.milkteauser.wxpay.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class ViewController {


    @RequestMapping("/testStripe")
    public String testStripe() {

        return "stripepaytest";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }




}
