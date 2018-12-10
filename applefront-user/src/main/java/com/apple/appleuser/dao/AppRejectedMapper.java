package com.apple.appleuser.dao;

import org.apache.ibatis.annotations.Select;

import com.apple.appleuser.domain.AppRejected;

public interface AppRejectedMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppRejected record);

    int insertSelective(AppRejected record);

    AppRejected selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppRejected record);

    int updateByPrimaryKey(AppRejected record);
    
    @Select("select APPLE_REJECTED_ID_SEQ.nextval from dual ")
    String getSeqId();
}