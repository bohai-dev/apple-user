package com.apple.appleuser.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.appleuser.dao.AppUserInforMapper;
import com.apple.appleuser.dao.TeaUserInfoMapper;
import com.apple.appleuser.domain.AppUserInfor;
import com.apple.appleuser.domain.TeaUserInfo;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.UserInfoService;


@Service
public  class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	TeaUserInfoMapper teaUserInfoMapper;
	
	@Autowired
	AppUserInforMapper appUserInforMapper;

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
	
	
	
	
    
}
