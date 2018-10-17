package com.apple.appleuser.dao;



import com.apple.appleuser.domain.TeaClassInfo;

public interface TeaClassInfoMapper {
    int deleteByPrimaryKey(String classId);

    int insert(TeaClassInfo record);

    int insertSelective(TeaClassInfo record);

    TeaClassInfo selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(TeaClassInfo record);

    int updateByPrimaryKey(TeaClassInfo record);
}