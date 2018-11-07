package com.apple.appleuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.appleuser.dao.AppRejectedMapper;
import com.apple.appleuser.dao.TeaOrderInfoMapper;
import com.apple.appleuser.domain.AppRejected;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.RejectedService;


@Service("RejectedService")
public  class RejectedServiceImpl implements RejectedService {

	@Autowired
	AppRejectedMapper appRejectedMapper;
	
	@Autowired
	TeaOrderInfoMapper teaOrderInfoMapper;
	
	
	@Override
	public Integer setNewRejected(AppRejected appRejected) throws MilkTeaException {
		//退货表插入
		appRejected.setId(appRejectedMapper.getSeqId());
		
		appRejectedMapper.insertSelective(appRejected);
		
		//客户订单表 变更状态为2
		teaOrderInfoMapper.setRejectedFlag(appRejected.getOrderId());
		
		return null;
	}

	
	
    
}
