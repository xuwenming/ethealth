package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdCashLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Boolean status;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.String handleStatus;	
	private java.lang.String handleLoginId;	
	private java.lang.String handleRemark;	
	private Date handleTime;			
	private java.lang.Integer userId;	
	private java.lang.Integer amount;	
	private java.lang.String refType;	
	private java.lang.String content;	
	private java.lang.String bankAccount;	
	private java.lang.String bankPhone;	
	private java.lang.String bankIdNo;	
	private java.lang.String bankName;	
	private java.lang.String bankCard;	
	private java.lang.String alipay;	

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	public void setHandleStatus(java.lang.String handleStatus) {
		this.handleStatus = handleStatus;
	}
	
	public java.lang.String getHandleStatus() {
		return this.handleStatus;
	}
	public void setHandleLoginId(java.lang.String handleLoginId) {
		this.handleLoginId = handleLoginId;
	}
	
	public java.lang.String getHandleLoginId() {
		return this.handleLoginId;
	}
	public void setHandleRemark(java.lang.String handleRemark) {
		this.handleRemark = handleRemark;
	}
	
	public java.lang.String getHandleRemark() {
		return this.handleRemark;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	public Date getHandleTime() {
		return this.handleTime;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setAmount(java.lang.Integer amount) {
		this.amount = amount;
	}
	
	public java.lang.Integer getAmount() {
		return this.amount;
	}
	public void setRefType(java.lang.String refType) {
		this.refType = refType;
	}
	
	public java.lang.String getRefType() {
		return this.refType;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setBankAccount(java.lang.String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public java.lang.String getBankAccount() {
		return this.bankAccount;
	}
	public void setBankPhone(java.lang.String bankPhone) {
		this.bankPhone = bankPhone;
	}
	
	public java.lang.String getBankPhone() {
		return this.bankPhone;
	}
	public void setBankIdNo(java.lang.String bankIdNo) {
		this.bankIdNo = bankIdNo;
	}
	
	public java.lang.String getBankIdNo() {
		return this.bankIdNo;
	}
	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setBankCard(java.lang.String bankCard) {
		this.bankCard = bankCard;
	}
	
	public java.lang.String getBankCard() {
		return this.bankCard;
	}
	public void setAlipay(java.lang.String alipay) {
		this.alipay = alipay;
	}
	
	public java.lang.String getAlipay() {
		return this.alipay;
	}

}
