package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdBalanceLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Long id;	
	private java.lang.Long adminId;	
	private java.lang.Long userId;	
	private java.lang.String balanceNo;	
	private java.lang.String refId;	
	private java.lang.String refType;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.Float amount;	
	private java.lang.Float amountLog;	
	private java.lang.String note;	
	private java.lang.Boolean status;	

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}

	
	public void setAdminId(java.lang.Long adminId) {
		this.adminId = adminId;
	}
	
	public java.lang.Long getAdminId() {
		return this.adminId;
	}
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	public void setBalanceNo(java.lang.String balanceNo) {
		this.balanceNo = balanceNo;
	}
	
	public java.lang.String getBalanceNo() {
		return this.balanceNo;
	}
	public void setRefId(java.lang.String refId) {
		this.refId = refId;
	}
	
	public java.lang.String getRefId() {
		return this.refId;
	}
	public void setRefType(java.lang.String refType) {
		this.refType = refType;
	}
	
	public java.lang.String getRefType() {
		return this.refType;
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
	public void setAmount(java.lang.Float amount) {
		this.amount = amount;
	}
	
	public java.lang.Float getAmount() {
		return this.amount;
	}
	public void setAmountLog(java.lang.Float amountLog) {
		this.amountLog = amountLog;
	}
	
	public java.lang.Float getAmountLog() {
		return this.amountLog;
	}
	public void setNote(java.lang.String note) {
		this.note = note;
	}
	
	public java.lang.String getNote() {
		return this.note;
	}
	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}

}
