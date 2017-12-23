/*
 * @author John
 * @date - 2017-12-18
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_account")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdAccount implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdAccount";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_BANK_ACCOUNT = "开户名";
	public static final String ALIAS_BANK_PHONE = "开户预留手机号";
	public static final String ALIAS_BANK_ID_NO = "开户身份证号";
	public static final String ALIAS_BANK_NAME = "开户行支行";
	public static final String ALIAS_BANK_CARD = "银行卡号";
	public static final String ALIAS_ALIPAY = "支付宝账号";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Long userId;
	//@Length(max=18)
	private java.lang.String bankAccount;
	//@Length(max=18)
	private java.lang.String bankPhone;
	//@Length(max=18)
	private java.lang.String bankIdNo;
	//@Length(max=36)
	private java.lang.String bankCode;
	private java.lang.String bankName;
	//@Length(max=36)
	private java.lang.String bankCard;
	//@Length(max=100)
	private java.lang.String alipay;
	//columns END


		public TfdAccount(){
		}
		public TfdAccount(Long userId) {
		this.userId = userId;
	}
	

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 19)
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	@Column(name = "bank_account", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getBankAccount() {
		return this.bankAccount;
	}
	
	public void setBankAccount(java.lang.String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Column(name = "bank_phone", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getBankPhone() {
		return this.bankPhone;
	}
	
	public void setBankPhone(java.lang.String bankPhone) {
		this.bankPhone = bankPhone;
	}
	
	@Column(name = "bank_id_no", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getBankIdNo() {
		return this.bankIdNo;
	}
	
	public void setBankIdNo(java.lang.String bankIdNo) {
		this.bankIdNo = bankIdNo;
	}

	@Column(name = "bank_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "bank_name", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}
	
	@Column(name = "bank_card", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getBankCard() {
		return this.bankCard;
	}
	
	public void setBankCard(java.lang.String bankCard) {
		this.bankCard = bankCard;
	}
	
	@Column(name = "alipay", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAlipay() {
		return this.alipay;
	}
	
	public void setAlipay(java.lang.String alipay) {
		this.alipay = alipay;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("BankAccount",getBankAccount())
			.append("BankPhone",getBankPhone())
			.append("BankIdNo",getBankIdNo())
			.append("BankName",getBankName())
			.append("BankCard",getBankCard())
			.append("Alipay",getAlipay())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdAccount == false) return false;
		if(this == obj) return true;
		FdAccount other = (FdAccount)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}*/
}

