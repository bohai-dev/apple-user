package com.apple.appleuser.controller;


import com.apple.appleuser.vo.StripeBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @RequestMapping("/testStripe")
    public String testStripe(){

        return  "stripepaytest";
    }
    @RequestMapping( "/index")
    public String index(){
        return "index";
    }
}
