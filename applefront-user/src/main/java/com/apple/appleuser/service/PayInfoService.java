package com.apple.appleuser.service;

import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.vo.IOTBean;
import com.apple.appleuser.vo.IotResponseBean;
import com.apple.appleuser.vo.ResponseBody;
import com.apple.appleuser.vo.StripeBean;

public interface PayInfoService {

    void  stripePay(StripeBean stripeBean) throws MilkTeaException;
    ResponseBody<String> iotPay(IOTBean iotBean) throws MilkTeaException;
    String iotNotify(IotResponseBean iotResponseBean) throws MilkTeaException;
}
