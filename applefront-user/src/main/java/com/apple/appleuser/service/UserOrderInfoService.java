package com.apple.appleuser.service;

import java.util.List;

import com.apple.appleuser.domain.TeaOrderInfo;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.vo.CustOrderInfoVo;
import com.apple.appleuser.vo.PostInfoVo;




public interface UserOrderInfoService {
	
	
 /**
  * 客户下单操作
 * @return CustOrderInfoVo
 * @throws MilkTeaException
 */
public CustOrderInfoVo userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException;

/**
 * 更新订单状态
* @param orderStatus
* @return Integer
* @throws MilkTeaException
*/
public Integer modifyOrderStatus(String orderNo,String orderStatus) throws MilkTeaException;

Integer updatePayStatus(String orderNo,String payStatus)throws MilkTeaException;
  
/**
 * 支付后状态改变
 * @param orderNo
 * @param remark
 * @param orderTime
 * @return
 * @throws MilkTeaException
 */
public Integer finishPayModfiyOrder(String orderNo,String remark,String orderTime) throws MilkTeaException;


/**
 * 根据电话号码查询订单
 * @param telephone
 * @param flag
 * @throws MilkTeaException
 */
public List<CustOrderInfoVo> findOrderByTelephone(String telephone,String flag) throws MilkTeaException;


/**
 * 根据订单号查询订单
 * @param telephone
 * @param flag
 * @throws MilkTeaException
 */
public TeaOrderInfo findOrderByOrderNo(String orderNo) throws MilkTeaException;


/**
 * 在结算时根据订单插入邮寄地址收货人电话
 * @param telephone
 * @param flag
 * @throws MilkTeaException
 */
public void updateOrderSetPostInfo (PostInfoVo postInfoVo) throws MilkTeaException;


}
