package com.apple.appleuser.service;

import com.apple.appleuser.domain.AppRejected;
import com.apple.appleuser.exception.MilkTeaException;



public interface RejectedService {
	
	/**
	    * 根据ID查用户信息
	    * @param figureVo
	    * @throws MilkTeaException
	    */
	   public Integer setNewRejected(AppRejected appRejected) throws MilkTeaException;
   
}
