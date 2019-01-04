package com.apple.appleuser.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apple.appleuser.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.apple.appleuser.controller.UserLoginController;
import com.apple.appleuser.dao.TeaGlobalTokenMapper;
import com.apple.appleuser.dao.TeaLoginWeixinMapper;
import com.apple.appleuser.domain.TeaGlobalToken;
import com.apple.appleuser.domain.TeaLoginWeixin;
import com.apple.appleuser.domain.TeaSaveContactVo;
import com.apple.appleuser.exception.MilkTeaErrorConstant;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.UserLoginService;
import com.apple.appleuser.util.HttpUtil;
import com.apple.appleuser.vo.ResponseBody;

import javax.print.DocFlavor;


@Service("userLoginService")
public  class UserLoginServiceImpl implements UserLoginService {

	
	public String url = "https://api.weixin.qq.com/sns/oauth2/access_token";

	
	public String param;

	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo";


	@Autowired
	TeaLoginWeixinMapper teaLoginWeixinMapper;
	

	
	
	private static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);


	@Override
	public TeaLoginWeixin weixinLogin(String code) throws MilkTeaException{

		Map<String,String> params=new HashMap<>();
		params.put("appid",Constants.APPID);
		params.put("secret",Constants.APP_SECRET);
		params.put("code",code);
		params.put("grant_type","authorization_code");

		TeaLoginWeixin  teaLoginWeixin;
		try {
			String accessResult=HttpUtil.get(ACCESS_TOKEN_URL,params);
			logger.info("accessResult="+accessResult);
			JSONObject jsonObject=JSON.parseObject(accessResult);
			String errCode=jsonObject.getString("errcode");
			if (errCode==null){
			  	String accessToken=jsonObject.getString("access_token");
			  	String openId=jsonObject.getString("openid");
			  	//根据openId查询用户微信登录信息
				teaLoginWeixin=teaLoginWeixinMapper.selectByPrimaryKey(openId);
				if (teaLoginWeixin==null){
					//拉取并保存用户信息
					teaLoginWeixin=saveUserInfo(accessToken,openId);
				}

				return teaLoginWeixin;


			}else{
				String accessErrorMsg=jsonObject.getString("errmsg");
				throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_LOGIN_ERROR.getErrorCode(),"获取accessToken错误:"+accessErrorMsg,"获取accessToken错误:"+accessErrorMsg);

			}

		} catch (Exception e) {
			e.printStackTrace();

			if(e instanceof  MilkTeaException){
				throw (MilkTeaException)e;
			}else {
				throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_LOGIN_ERROR.getErrorCode(),"网络连接出错","网络连接出错");
			}


		}

	}

	public TeaLoginWeixin  saveUserInfo(String accessToken,String openId) throws MilkTeaException{
		Map<String,String> infoParams=new HashMap<>();
		infoParams.put("access_token",accessToken);
		infoParams.put("openid",openId);
		infoParams.put("lang","zh_CN");

		try {
			String infoResult=HttpUtil.get(USER_INFO_URL,infoParams);
			logger.info("UserinfoResult="+infoResult);
			JSONObject infoObject=JSON.parseObject(infoResult);

            String errorCode=infoObject.getString("errcode");
            if (errorCode==null){

            	String nickName=infoObject.getString("nickname");
            	String sex=infoObject.getString("sex");
            	String province=infoObject.getString("province");
            	String city=infoObject.getString("city");
            	String country=infoObject.getString("country");
            	String headimgurl=infoObject.getString("headimgurl");

            	TeaLoginWeixin teaLoginWeixin=new TeaLoginWeixin();
            	teaLoginWeixin.setWeixinOpenid(openId);
            	teaLoginWeixin.setCity(city);
            	teaLoginWeixin.setCountry(country);
            	teaLoginWeixin.setHeadimgurl(headimgurl);
            	teaLoginWeixin.setWeixinNickname(nickName);
            	teaLoginWeixin.setWeixinSex(sex);
            	teaLoginWeixin.setWeixinProvince(province);

            	teaLoginWeixinMapper.insertSelective(teaLoginWeixin);

            	return teaLoginWeixin;
			}else {
            	String errorMsg=infoObject.getString("errmsg");
                throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_LOGIN_ERROR.getErrorCode(),"拉取用户信息错误:"+errorMsg,"拉取用户信息错误:"+errorMsg);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_LOGIN_ERROR.getErrorCode(),"网络连接出错","网络连接出错");
		}


	}










	@Override
	public void saveContact(TeaSaveContactVo teaSaveContactVo) throws MilkTeaException {

		
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		String path = "http://localhost:8089/saveContact";
		try {

			HttpUtil HttpUtil = new HttpUtil();
			Map<String,String> mapParam = new HashMap<String,String>();
			mapParam.put("name", teaSaveContactVo.getName());
			mapParam.put("email",teaSaveContactVo.getEmail());
			mapParam.put("telephone", teaSaveContactVo.getTelephone());
			mapParam.put("context", teaSaveContactVo.getContext());
			String retStr = HttpUtil.post(path, mapParam);
			System.out.println(retStr);
			JSONObject json = JSON.parseObject(retStr);
			String retCode = json.getString("rspCode");
			if(!"00000".equals(retCode)){
				logger.error(MilkTeaErrorConstant.SAVEUSERINFOR_ERROR.getCnErrorMsg());
	            throw new MilkTeaException(MilkTeaErrorConstant.SAVEUSERINFOR_ERROR);
			}
	      
	        
          
		} catch (Exception e) {
			logger.error(MilkTeaErrorConstant.SAVEUSERINFOR_ERROR.getCnErrorMsg(),e);
            throw new MilkTeaException(MilkTeaErrorConstant.SAVEUSERINFOR_ERROR,e);
		}  
		
	}
	
	
	

	
	
	
	
    
}
