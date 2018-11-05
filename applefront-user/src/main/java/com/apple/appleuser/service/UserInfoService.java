package com.apple.appleuser.service;

import java.math.BigDecimal;
import java.util.List;

import com.apple.appleuser.domain.AppUserInfor;
import com.apple.appleuser.domain.TeaOrderInfo;
import com.apple.appleuser.domain.TeaUserInfo;
import com.apple.appleuser.exception.MilkTeaException;




public interface UserInfoService {
	
	/**
     * 添加用户信息
     * @param figureVo
     * @throws MilkTeaException
     */
   public Integer insert(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   
   
   /**
    * 根据ID查用户信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public TeaUserInfo selectByUserId(String userNo) throws MilkTeaException;
   
   /**
    * 修改用户信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  update(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   /**
    * 查询所有用户信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public List<TeaUserInfo> selectAll() throws MilkTeaException;
   
   
   /**
     * 根据用户号 改变积分
	 * @param USER_NO
	 * @param point
	 * @return
	 * @throws MilkTeaException
	 */
   public int modifyPoint(String USER_NO,BigDecimal point) throws MilkTeaException;
   
   /**
    * 根据ID查用户邮寄信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public List<AppUserInfor> findUserPostAddress(String userNo) throws MilkTeaException;
   
   
   /**
    * 插入用户邮寄信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  insertUserPostAddress(AppUserInfor appUserInfor) throws MilkTeaException;
   
   /**
    * 删除用户邮寄信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  deleteUserPostAddress(AppUserInfor appUserInfor) throws MilkTeaException;
   
   /**
    * 更新用户邮寄信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  updateUserPostAddress(AppUserInfor appUserInfor) throws MilkTeaException;
   
   /**
    * 更新用户默认地址
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  updateUserDefaultPostAddress(AppUserInfor appUserInfor) throws MilkTeaException;
   
   
   /**
    * 用户订单查询接口
    * @param figureVo
    * @throws MilkTeaException
    */
   public List<TeaOrderInfo>  getPendingDeliveryInfo(String userNo,String orderStatus,String payStatus) throws MilkTeaException;
   
   /**
    * 用户确认收货设置
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  setFinishDeliveryInfo(String orderNo) throws MilkTeaException;
   
   
}
