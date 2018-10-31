package com.apple.appleuser.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apple.appleuser.domain.AppUserInfor;

public interface AppUserInforMapper {
    int deleteByPrimaryKey(BigDecimal inforNo);

    int insert(AppUserInfor record);

    int insertSelective(AppUserInfor record);

    AppUserInfor selectByPrimaryKey(BigDecimal inforNo);

    int updateByPrimaryKeySelective(AppUserInfor record);

    int updateByPrimaryKey(AppUserInfor record);
    
    @Select("select * from APP_USER_INFOR where USER_NO = #{userNo}")
    List<AppUserInfor> findUserPostAddress(@Param("userNo") String userNo);
    
    @Select("select APP_USER_INFOR_ID.nextval from dual")
    BigDecimal getAppUserInforId();
    
    @Update("update APP_USER_INFOR set DEFAULT_ADDRESS = '' ")
    void nullDefaultAddress();
    
    @Update("update APP_USER_INFOR set DEFAULT_ADDRESS = '1' where INFOR_NO = #{inforNo}")
    void setDefaultAddress(@Param("inforNo") BigDecimal inforNo);
}