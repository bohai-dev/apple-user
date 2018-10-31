package com.apple.appleuser.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.apple.appleuser.domain.TeaOrderDetails;



/**
 * 商品信息
 * @author caoxx
 *
 */
public class PostInfoVo {
    
    //客户下单信息
	
	private String orderNo;
	
	private String postAddress;
	
	
	private String postTel;
	
	private String postName;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String getPostTel() {
		return postTel;
	}

	public void setPostTel(String postTel) {
		this.postTel = postTel;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	
}
