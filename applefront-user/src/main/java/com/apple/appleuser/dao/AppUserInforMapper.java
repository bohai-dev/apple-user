package com.apple.appleuser.dao;

import com.apple.appleuser.domain.AppUserInfor;

public interface AppUserInforMapper {
    int insert(AppUserInfor record);

    int insertSelective(AppUserInfor record);
}