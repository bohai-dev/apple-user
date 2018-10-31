package com.apple.appleuser.service;

import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.vo.WXPayVo;

import java.util.Map;

/**
 * Cteated by cxy on 2018/10/30
 */
public interface WXPayService {

    Map<String, String> unifiedorder(WXPayVo wxPayVo) throws MilkTeaException;
}
