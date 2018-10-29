package com.apple.appleuser.domain;

import java.math.BigDecimal;

public class AppUserInfor {
    private BigDecimal inforNo;

    private String userNo;

    private String acceptName;

    private String acceptTel;

    private String accessAddress;

    private String defaultAddress;

    public BigDecimal getInforNo() {
        return inforNo;
    }

    public void setInforNo(BigDecimal inforNo) {
        this.inforNo = inforNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName == null ? null : acceptName.trim();
    }

    public String getAcceptTel() {
        return acceptTel;
    }

    public void setAcceptTel(String acceptTel) {
        this.acceptTel = acceptTel == null ? null : acceptTel.trim();
    }

    public String getAccessAddress() {
        return accessAddress;
    }

    public void setAccessAddress(String accessAddress) {
        this.accessAddress = accessAddress == null ? null : accessAddress.trim();
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress == null ? null : defaultAddress.trim();
    }
}