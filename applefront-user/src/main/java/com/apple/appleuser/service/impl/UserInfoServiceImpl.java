package com.apple.appleuser.service.impl;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apple.appleuser.controller.UserLoginController;
import com.apple.appleuser.dao.AppUserInforMapper;
import com.apple.appleuser.dao.TeaOrderDetailsMapper;
import com.apple.appleuser.dao.TeaOrderInfoMapper;
import com.apple.appleuser.dao.TeaUserInfoMapper;
import com.apple.appleuser.domain.AppUserInfor;
import com.apple.appleuser.domain.TeaOrderInfo;
import com.apple.appleuser.domain.TeaUserInfo;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.UserInfoService;
import com.apple.appleuser.util.HttpUtil;
import com.apple.appleuser.vo.ResponseBody;
import com.apple.appleuser.vo.TeaOrderDetailsVo;
import com.google.gson.JsonObject;


@Service
public  class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	TeaUserInfoMapper teaUserInfoMapper;
	
	@Autowired
	AppUserInforMapper appUserInforMapper;
	
	@Autowired
	TeaOrderInfoMapper teaOrderInfoMapper;
	
	@Autowired
	TeaOrderDetailsMapper teaOrderDetailsMapper;

	@Override
	public Integer insert(TeaUserInfo teaUserInfo) throws MilkTeaException {
		String custNoSeq = teaUserInfoMapper.getNewCustSeq();
		teaUserInfo.setUserNo(custNoSeq);
		teaUserInfo.setRegisterDate(new Date());
		teaUserInfoMapper.insertSelective(teaUserInfo);
		return 1;
	}

	@Override
	public TeaUserInfo selectByUserId(String userNo) throws MilkTeaException {
		TeaUserInfo retTeaUserInfo = new TeaUserInfo();
		retTeaUserInfo = teaUserInfoMapper.selectByPrimaryKey(userNo);
		return retTeaUserInfo;
		
	}

	@Override
	public Integer update(TeaUserInfo teaUserInfo) throws MilkTeaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeaUserInfo> selectAll() throws MilkTeaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyPoint(String userNo, BigDecimal point) throws MilkTeaException {
		
		teaUserInfoMapper.modifyPoint(userNo,point);
		return 0;
	}

	@Override
	public List<AppUserInfor> findUserPostAddress(String userNo) throws MilkTeaException {
		
		//根据用户USERNO 会返回多条记录
		List<AppUserInfor> listAppUserInfo = new ArrayList<AppUserInfor>();
		listAppUserInfo = appUserInforMapper.findUserPostAddress(userNo);
		return listAppUserInfo;
	}

	@Override
	public Integer insertUserPostAddress(AppUserInfor appUserInfor) throws MilkTeaException {
		
		//插入的一条是设定默认地址
		if("1".equals(appUserInfor.getDefaultAddress())) {
			//先清空数据库中的DEFAULT
			appUserInforMapper.nullDefaultAddress();
		}
		appUserInfor.setInforNo(appUserInforMapper.getAppUserInforId());
		appUserInforMapper.insert(appUserInfor);
		return null;
	}

	@Override
	public Integer deleteUserPostAddress(AppUserInfor appUserInfor) throws MilkTeaException {
		appUserInforMapper.deleteByPrimaryKey(appUserInfor.getInforNo());
		return null;
	}

	@Override
	public Integer updateUserPostAddress(AppUserInfor appUserInfor) throws MilkTeaException {
		appUserInforMapper.updateByPrimaryKeySelective(appUserInfor);
		return null;
	}

	@Override
	public Integer updateUserDefaultPostAddress(AppUserInfor appUserInfor) throws MilkTeaException {
		//先清空数据库中的DEFAULT
		appUserInforMapper.nullDefaultAddress();
		//再设置新的DEFAULT
		appUserInforMapper.setDefaultAddress(appUserInfor.getInforNo());
		
		return null;
	}

	@Override
	public List<TeaOrderInfo> getPendingDeliveryInfo(String userNo, String orderStatus, String payStatus)
			throws MilkTeaException {
		//orderStatus:0下单成功，待发货 ；1已发货，待收货 ；2用户退换货；3用户确认收货，本单完成；4系统确认收货（15天后由客服联系用户后在后台确认收货），本单完成; 5用户取消订单
		//payStatus:支付状态 0:待支付 1:支付成功 2:支付失败
		List<TeaOrderInfo> listTeaOrderInfo = new ArrayList<TeaOrderInfo>();
		
		//待支付查询
		if("0".equals(orderStatus) && "0".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo(userNo, orderStatus, payStatus);
		}
		
		//待发货查询
		if("0".equals(orderStatus) && "1".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo(userNo, orderStatus, payStatus);
		}
		
		//待收货查询
		if("1".equals(orderStatus) && "1".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo(userNo, orderStatus, payStatus);
		}
		
		//退款查询
		if("2".equals(orderStatus) && "1".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo(userNo, orderStatus, payStatus);
		}
		
		//用户取消订单查询
		if("5".equals(orderStatus) && "99".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo2(userNo, orderStatus);
		}
		
		//用户确认收货查询
		if("3".equals(orderStatus) && "99".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo2(userNo, orderStatus);
		}
		
		//系统确认收货查询
		if("4".equals(orderStatus) && "99".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo2(userNo, orderStatus);
		}		
		
		//查询全部订单
		//支付状态为“1”,不带订单状态，查询
		if("99".equals(orderStatus) && "99".equals(payStatus)){
			listTeaOrderInfo = teaOrderInfoMapper.getPendingDeliveryInfo3(userNo);
		}
		
		return listTeaOrderInfo;
		
		
		
	}

	@Override
	public Integer setFinishDeliveryInfo(String orderNo) throws MilkTeaException {
		
		//用户确认收货设置 默认为设置orderStatus = '3'
		teaOrderInfoMapper.setFinishDeliveryInfo(orderNo);
		
		
		return null;
	}

	@Override
	public List<TeaOrderDetailsVo> getOrderDetailInfo(String orderNo) throws MilkTeaException {
		
		List<TeaOrderDetailsVo> listTeaOrderDetailsVo = new ArrayList<TeaOrderDetailsVo>();
		
		listTeaOrderDetailsVo = teaOrderDetailsMapper.getListTeaOrderDetailsVo(orderNo);
		
		return listTeaOrderDetailsVo;
	}

	
    
}
