package com.apple.appleuser.service;

import com.apple.appleuser.domain.TeaLoginWeixin;
import com.apple.appleuser.domain.TeaSaveContactVo;
import com.apple.appleuser.exception.MilkTeaException;




public interface UserLoginService {





   TeaLoginWeixin weixinLogin(String code) throws MilkTeaException;


/**
 * 保存网页联系人信息
 * @param teaSaveContactVo
 * @throws MilkTeaException
 */
public void saveContact(TeaSaveContactVo teaSaveContactVo) throws MilkTeaException;
   
   
}
