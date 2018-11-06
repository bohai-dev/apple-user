package com.apple.appleuser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apple.appleuser.domain.AppRejected;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.RejectedService;
import com.apple.appleuser.vo.ResponseHeader;

@Controller
public class RejectedController {

	@Autowired
	RejectedService rejectedService;


    @RequestMapping("/setNewRejected")
    public ResponseHeader updateRejected(@RequestBody AppRejected appRejected) throws MilkTeaException{
        ResponseHeader responseHeader=new ResponseHeader();

        rejectedService.setNewRejected(appRejected);

        return  responseHeader;

    }


}
