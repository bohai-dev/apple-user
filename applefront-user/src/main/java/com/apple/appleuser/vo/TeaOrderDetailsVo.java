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
public class TeaOrderDetailsVo {
    
	//订单详情表
	private String orderDetailId;

    private String orderNo;

    private String goodsId;

    private BigDecimal origPrice;

    private BigDecimal attrPrice;

    private BigDecimal discount;

    private BigDecimal orderPrice;

    private String updateTime;
    
    private String standardId;
    
    private BigDecimal standardNum;
    
    //详细规格表
    private String name;

    private String description;

    private BigDecimal price;
    
    //商品信息表
    
    private String goodsName;

    private String goodsIntroduction;
    
    private String goodsPictureBig;

    
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsIntroduction() {
		return goodsIntroduction;
	}

	public void setGoodsIntroduction(String goodsIntroduction) {
		this.goodsIntroduction = goodsIntroduction;
	}

	public String getGoodsPictureBig() {
		return goodsPictureBig;
	}

	public void setGoodsPictureBig(String goodsPictureBig) {
		this.goodsPictureBig = goodsPictureBig;
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}

	public BigDecimal getAttrPrice() {
		return attrPrice;
	}

	public void setAttrPrice(BigDecimal attrPrice) {
		this.attrPrice = attrPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getStandardId() {
		return standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public BigDecimal getStandardNum() {
		return standardNum;
	}

	public void setStandardNum(BigDecimal standardNum) {
		this.standardNum = standardNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
    
	
}
