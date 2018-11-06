package com.apple.appleuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.appleuser.dao.AppRejectedMapper;
import com.apple.appleuser.domain.AppRejected;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.service.RejectedService;


@Service("RejectedService")
public  class RejectedServiceImpl implements RejectedService {

	@Autowired
	AppRejectedMapper appRejectedMapper;
	
	
	@Override
	public Integer setNewRejected(AppRejected appRejected) throws MilkTeaException {
		appRejected.setId(appRejectedMapper.getSeqId());
		
		appRejectedMapper.insertSelective(appRejected);
		
		
		return null;
	}

	
	
    
}
