package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdPaymentBase implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String orderNo;	
	private java.lang.String type;	
	private java.lang.Integer price;	
	private java.lang.String status;	
	private java.lang.String remark;	
	private Date expiryDate;			
	private Date createDate;			
	private Date updateDate;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setOrderNo(java.lang.String orderNo) {
		this.orderNo = orderNo;
	}
	
	public java.lang.String getOrderNo() {
		return this.orderNo;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setPrice(java.lang.Integer price) {
		this.price = price;
	}
	
	public java.lang.Integer getPrice() {
		return this.price;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public Date getExpiryDate() {
		return this.expiryDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}

}
