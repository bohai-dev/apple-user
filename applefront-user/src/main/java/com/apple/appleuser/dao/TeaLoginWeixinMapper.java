package com.apple.appleuser.dao;



import com.apple.appleuser.domain.TeaLoginWeixin;
import org.apache.ibatis.annotations.Mapper;


public interface TeaLoginWeixinMapper {
    int deleteByPrimaryKey(String weixinOpenid);

    int insert(TeaLoginWeixin record);

    int insertSelective(TeaLoginWeixin record);

    TeaLoginWeixin selectByPrimaryKey(String weixinOpenid);

    int updateByPrimaryKeySelective(TeaLoginWeixin record);

    int updateByPrimaryKey(TeaLoginWeixin record);
}