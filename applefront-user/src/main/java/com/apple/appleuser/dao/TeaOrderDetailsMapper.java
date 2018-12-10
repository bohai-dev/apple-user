package com.apple.appleuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.apple.appleuser.domain.TeaOrderDetails;
import com.apple.appleuser.vo.TeaOrderDetailsVo;

public interface TeaOrderDetailsMapper {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(TeaOrderDetails record);

    int insertSelective(TeaOrderDetails record);

    TeaOrderDetails selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(TeaOrderDetails record);

    int updateByPrimaryKey(TeaOrderDetails record);
    
    @Select("select TEA_ORDERDETAILS_SEQ.nextval from dual")
    String getOrderDetailsSeq();
    
    @Select("select  A.ORDER_DETAIL_ID as ORDER_DETAIL_ID ,A.ORDER_NO as ORDER_NO ,A.GOODS_ID as GOODS_ID ,A.ORIG_PRICE as ORIG_PRICE ,A.ATTR_PRICE as ATTR_PRICE ,A.DISCOUNT as DISCOUNT ,A.ORDER_PRICE as ORDER_PRICE ,A.UPDATE_TIME as UPDATE_TIME ,A.STANDARD_ID as STANDARD_ID ,A.STANDARD_NUM as STANDARD_NUM ,B.NAME as NAME,B.DESCRIPTION as DESCRIPTION,B.PRICE as PRICE,C.CN_GOODS_NAME as GOODS_NAME,C.CN_GOODS_INTRODUCTION as GOODS_INTRODUCTION,C.CN_GOODS_PICTURE_BIG as GOODS_PICTURE_BIG  from TEA_ORDER_DETAILS A,APP_STANDARD B,TEA_GOODS_INFO C where A.STANDARD_ID = B.ID and A.GOODS_ID = C.GOODS_ID and a.ORDER_NO = #{orderNo}")
    List<TeaOrderDetailsVo> getListTeaOrderDetailsVo(@Param("orderNo")String orderNo);
}