package com.apple.appleuser.controller;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.apple.appleuser.dao.TeaLoginWeixinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apple.appleuser.domain.TeaLoginWeixin;
import com.apple.appleuser.domain.TeaSaveContactVo;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.UserLoginService;
import com.apple.appleuser.util.HttpUtil;
import com.apple.appleuser.vo.ResponseBody;
import com.apple.appleuser.vo.ResponseHeader;
import com.apple.appleuser.vo.WeixinVo;
import com.google.gson.JsonObject;







@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

	
	public String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public String param;
	
	@Autowired
    private UserLoginService userLoginService;

	
	@RequestMapping(value="/saveContact", method = RequestMethod.POST)
	public ResponseHeader saveContact(@RequestBody TeaSaveContactVo teaSaveContactVo) throws MilkTeaException{
		ResponseHeader responseHeader = new ResponseHeader();
		this.userLoginService.saveContact(teaSaveContactVo);
		return responseHeader;
	}
	
	
	//微信客户登入
	@RequestMapping(value="/weixin", method = RequestMethod.POST)
	public ResponseBody<TeaLoginWeixin>  userInfoLogin(@RequestBody WeixinVo weixinVo) throws MilkTeaException{

		ResponseBody<TeaLoginWeixin> responseBody = new ResponseBody<TeaLoginWeixin>();

		String code=weixinVo.getCode();
		TeaLoginWeixin teaLoginWeixin=userLoginService.weixinLogin(code);

		responseBody.setData(teaLoginWeixin);

		return responseBody;
	}
	
	//按照区分得到店铺LIST
	@RequestMapping(value="/queryClassStore", method = RequestMethod.GET)
	public ResponseBody<JSONObject>  queryClassStore() throws MilkTeaException{
		
		BufferedReader in = null;
		String result = "";
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
		JSONObject jsonObject = new JSONObject();
		//调用商品后台，取得默认登入商铺内的商品及所有商铺
        
        //所有商铺 
//		param = "appid=" + weiXinAppid +"&" + "secret=" + weiXinSecret + "&" + "code=" + code + "&" + "grant_type=authorization_code";
        String url = "http://localhost:8089/queryClassStore";
        String urlNameString = "" ;
        
        urlNameString = url ;
    
//		String path = "http://localhost:8089/queryStores";
        try {

			HttpUtil HttpUtil = new HttpUtil();
			Map<String,String> mapParam = new HashMap<String,String>();
//			mapParam.put("lang", lang);

			String retStr = HttpUtil.get(urlNameString);
			System.out.println(retStr);
			jsonObject = JSON.parseObject(retStr);
	        responseBody.setData(jsonObject);
			
		
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return responseBody;
	
	
	}
	
	
	//得到所有店铺LIST
	@RequestMapping(value="/storelist", method = RequestMethod.GET)
	public ResponseBody<JSONObject>  getStoreList() throws MilkTeaException{
		BufferedReader in = null;
		String result = "";
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
		JSONObject jsonObject = new JSONObject();
		//调用商品后台，取得默认登入商铺内的商品及所有商铺
        
        //所有商铺 
//		param = "appid=" + weiXinAppid +"&" + "secret=" + weiXinSecret + "&" + "code=" + code + "&" + "grant_type=authorization_code";
        String url = "http://localhost:8089/queryStores";
        String urlNameString = "" ;
       
        urlNameString = url ;
    
//		String path = "http://localhost:8089/queryStores";
        try {

			HttpUtil HttpUtil = new HttpUtil();
			Map<String,String> mapParam = new HashMap<String,String>();
//			mapParam.put("lang", lang);

			String retStr = HttpUtil.get(urlNameString);
			System.out.println(retStr);
			jsonObject = JSON.parseObject(retStr);
	        responseBody.setData(jsonObject);
			
		
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return responseBody;
	}
	
	//取得店铺内的商品
	@RequestMapping(value="/getClassGoods")
	public ResponseBody<JSONObject>  getClassGoods(@RequestParam("storeNo") String storeNo,@RequestParam(value = "classType",required = false) String classType) throws MilkTeaException{
		BufferedReader in = null;
		String result = "";
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
		JSONObject jsonObject = new JSONObject();
		JsonObject message = new JsonObject();
		PrintWriter out = null;
		String path = "http://localhost:8089/queryClassGoodsNational";
	        
        

		try {

			HttpUtil HttpUtil = new HttpUtil();
			Map<String,String> mapParam = new HashMap<String,String>();
			mapParam.put("storeNo", storeNo);
			mapParam.put("classType", classType);

			String retStr = HttpUtil.post(path, mapParam);
			System.out.println(retStr);
			jsonObject = JSON.parseObject(retStr);
	        responseBody.setData(jsonObject);
			
		
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
		return responseBody;
	}
	
	//取得轮播图
		@RequestMapping(value="/getCarouselFigure")
		public ResponseBody<JSONObject>  getCarouselFigure(@RequestParam("storeNo") String storeNo) throws MilkTeaException{
			BufferedReader in = null;
			String result = "";
			Logger logger = LoggerFactory.getLogger(UserLoginController.class);
			ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
			JSONObject jsonObject = new JSONObject();
			JsonObject message = new JsonObject();
			PrintWriter out = null;
			String path = "http://localhost:8089/queryCarouselFigureNation";
		        
			try {

				HttpUtil HttpUtil = new HttpUtil();
				Map<String,String> mapParam = new HashMap<String,String>();
				mapParam.put("storeNo", storeNo);
				String retStr = HttpUtil.post(path, mapParam);
				
				System.out.println(retStr);
				jsonObject = JSON.parseObject(retStr);
		        responseBody.setData(jsonObject);
			
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        
			return responseBody;
		}
		
//		http://localhost:8089/queryPromotionByStoreNoNation
		@RequestMapping(value="/queryPromotionByStoreNoNation", method = RequestMethod.GET)
		public ResponseBody<JSONObject>  getPromotionByStoreNoNation(@RequestParam("storeNo") String storeNo) throws MilkTeaException{
			BufferedReader in = null;
			String result = "";
			Logger logger = LoggerFactory.getLogger(UserLoginController.class);
			ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
			JSONObject jsonObject = new JSONObject();
			JsonObject message = new JsonObject();
			PrintWriter out = null;
			String path = "http://localhost:8089/queryPromotionByStoreNoNation";
		        
			try {

				HttpUtil HttpUtil = new HttpUtil();
				Map<String,String> mapParam = new HashMap<String,String>();
				mapParam.put("storeNo", storeNo);

				String retStr = HttpUtil.post(path, mapParam);
				
				System.out.println(retStr);
				jsonObject = JSON.parseObject(retStr);
		        responseBody.setData(jsonObject);
			
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        
			return responseBody;
			
		}
	
	
	
	
}
