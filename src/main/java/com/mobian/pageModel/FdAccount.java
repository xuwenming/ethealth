package com.mobian.pageModel;

import com.mobian.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FdAccount implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Long userId;	
	private java.lang.String bankAccount;	
	private java.lang.String bankPhone;	
	private java.lang.String bankIdNo;	
	private java.lang.String bankCode;
	private java.lang.String bankName;
	private java.lang.String bankCard;
	private java.lang.String alipay;	

	public String getBankCodeZh() {
		return Application.getString(bankCode);
	}

	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
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

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
