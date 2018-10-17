package com.apple.appleuser.dao;



import com.apple.appleuser.domain.TeaAdmin;

public interface TeaAdminMapper {
    int insert(TeaAdmin record);

    int insertSelective(TeaAdmin record);
}