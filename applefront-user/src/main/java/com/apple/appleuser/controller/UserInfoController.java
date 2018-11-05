package com.apple.appleuser.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apple.appleuser.domain.AppUserInfor;
import com.apple.appleuser.domain.TeaAdmin;
import com.apple.appleuser.domain.TeaOrderInfo;
import com.apple.appleuser.domain.TeaUserInfo;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.UserInfoService;
import com.apple.appleuser.vo.ResponseBody;
import com.apple.appleuser.vo.ResponseHeader;



@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ResponseHeader  insert(TeaUserInfo teaUserInfo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.insert(teaUserInfo);
		return header;
	}
	
	
	@RequestMapping(value="/selectAll", method = RequestMethod.POST)
	public ResponseBody<List<TeaUserInfo>> selectAll() throws MilkTeaException{
		ResponseBody<List<TeaUserInfo>> responseBody = new ResponseBody<>();
		responseBody.setData(this.userInfoService.selectAll());
		return responseBody;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseHeader update(TeaUserInfo teaUserInfo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.update(teaUserInfo);
		return header;
	}
	
	@RequestMapping(value="/selectByUserId", method = RequestMethod.POST)
	public ResponseBody<TeaUserInfo> selectByUserId(String userNo) throws MilkTeaException{
		ResponseBody<TeaUserInfo> responseBody = new ResponseBody<>();
		responseBody.setData(this.userInfoService.selectByUserId(userNo));
		return responseBody;
	}

	@RequestMapping("/getauser")
	public TeaAdmin  findOne(){
		TeaAdmin teaAdmin=new TeaAdmin();
		teaAdmin.setUserName("cxy");
		teaAdmin.setPasswd("123456");
		return  teaAdmin;
	}
	
	@RequestMapping(value="/findUserPostAddress", method = RequestMethod.GET)
	public ResponseBody<List<AppUserInfor>> findUserPostAddress(@RequestParam("userNo") String userNo) throws MilkTeaException{
		ResponseBody<List<AppUserInfor>> responseBody = new ResponseBody<>();
		responseBody.setData(this.userInfoService.findUserPostAddress(userNo));
		return responseBody;
	}
	
	@RequestMapping(value="/insertUserPostAddress", method = RequestMethod.POST)
	public ResponseHeader insertUserPostAddress(@RequestBody AppUserInfor appUserInfor) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.insertUserPostAddress(appUserInfor);
		return header;
	}
	
	@RequestMapping(value="/deleteUserPostAddress", method = RequestMethod.POST)
	public ResponseHeader deleteUserPostAddress(@RequestBody AppUserInfor appUserInfor) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.deleteUserPostAddress(appUserInfor);
		return header;
	}
	
	@RequestMapping(value="/updateUserPostAddress", method = RequestMethod.POST)
	public ResponseHeader updateUserPostAddress(@RequestBody AppUserInfor appUserInfor) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.updateUserPostAddress(appUserInfor);
		return header;
	}
	
	@RequestMapping(value="/updateUserDefaultPostAddress", method = RequestMethod.POST)
	public ResponseHeader updateUserDefaultPostAddress(@RequestBody AppUserInfor appUserInfor) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.updateUserDefaultPostAddress(appUserInfor);
		return header;
	}
	
	@RequestMapping(value="/getPendingDeliveryInfo", method = RequestMethod.GET)
	public ResponseBody<List<TeaOrderInfo>> getPendingDeliveryInfo(@RequestParam("userNo") String userNo,@RequestParam("orderStatus") String orderStatus,@RequestParam("payStatus") String payStatus) throws MilkTeaException{
		ResponseBody<List<TeaOrderInfo>> responseBody = new ResponseBody<>();
		responseBody.setData(this.userInfoService.getPendingDeliveryInfo(userNo,orderStatus,payStatus));
		return responseBody;
	}
	
	@RequestMapping(value="/setFinishDeliveryInfo", method = RequestMethod.GET)
	public ResponseHeader setFinishDeliveryInfo(@RequestParam("orderNo") String orderNo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.setFinishDeliveryInfo(orderNo);
		return header;
	}
	
	
}
