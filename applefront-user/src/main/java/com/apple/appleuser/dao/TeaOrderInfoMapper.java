package com.apple.appleuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apple.appleuser.domain.TeaOrderInfo;

public interface TeaOrderInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(TeaOrderInfo record);

    int insertSelective(TeaOrderInfo record);

    TeaOrderInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(TeaOrderInfo record);

    int updateByPrimaryKey(TeaOrderInfo record);
    
    @Select("select to_char(sysdate,'YYYYMMDD_') || 'A_' ||TEA_CUSTORDER_SEQ.nextval from dual")
    String getCustOrderSeq();
    
    @Select("select * from TEA_ORDER_INFO where TELEPHONE = #{telephone} and ORDER_STATUS = #{flag}")
    List<TeaOrderInfo> findOrderByTelephone(@Param("telephone") String telephone,@Param("flag") String flag);


    @Update("update TEA_ORDER_INFO set ORDER_STATUS = #{orderStatus},UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int modifyOrderStatus(@Param("orderNo") String orderNo,@Param("orderStatus") String orderStatus);
    
    @Update("update TEA_ORDER_INFO set BOOK_TIME = to_date(#{orderTime},'yyyy/mm/dd hh24:mi:ss'),REMARK = #{remark},ORDER_TYPE = #{orderType} where ORDER_NO = #{orderNo}")
    int finishPayModfiyOrder1(@Param("orderNo") String orderNo,@Param("remark") String remark,@Param("orderTime") String orderTime,@Param("orderType") String orderType);
    
    @Update("update TEA_ORDER_INFO set REMARK = #{remark},ORDER_TYPE = #{orderType} where ORDER_NO = #{orderNo}")
    int finishPayModfiyOrder2(@Param("orderNo") String orderNo,@Param("remark") String remark,@Param("orderType") String orderType);

    //更新订单的支付状态
    @Update("update TEA_ORDER_INFO set PAY_STATUS = #{payStatus},UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int updatePayStatus(@Param("orderNo")String orderNo,@Param("payStatus")String payStatus);
    
    //更新收货人电话地址
    @Update("update TEA_ORDER_INFO set POST_ADDRESS = #{postAddress},POST_TEL = #{postTel},POST_NAME = #{postName},UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int updatePostInfo(@Param("orderNo")String orderNo,@Param("postAddress")String postAddress,@Param("postTel")String postTel,@Param("postName")String postName);

    //带支付状态查询
    @Select("select * from TEA_ORDER_INFO where USER_NO = #{userNo} and ORDER_STATUS = #{orderStatus} and PAY_STATUS = #{payStatus} order by ORDER_TIME desc")
    List<TeaOrderInfo> getPendingDeliveryInfo(@Param("userNo")String userNo,@Param("orderStatus")String orderStatus,@Param("payStatus")String payStatus);
    
    //不带支付状态查询
    @Select("select * from TEA_ORDER_INFO where USER_NO = #{userNo} and ORDER_STATUS = #{orderStatus} order by ORDER_TIME desc")
    List<TeaOrderInfo> getPendingDeliveryInfo2(@Param("userNo")String userNo,@Param("orderStatus")String orderStatus);
    
    //支付状态为“1”,不带订单状态，查询
    @Select("select * from TEA_ORDER_INFO where USER_NO = #{userNo} order by ORDER_TIME desc ")
    List<TeaOrderInfo> getPendingDeliveryInfo3(@Param("userNo")String userNo);
    
    //用户确认收货设置 默认为设置orderStatus = '3'
    @Update("update TEA_ORDER_INFO set ORDER_STATUS = '3',UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int setFinishDeliveryInfo(@Param("orderNo")String orderNo);
    
}
