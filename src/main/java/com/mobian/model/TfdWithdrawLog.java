/*
 * @author John
 * @date - 2018-06-02
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_withdraw_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdWithdrawLog implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdWithdrawLog";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "是否删除 0 否 1 是";
	public static final String ALIAS_WITHDRAW_NO = "提现流水号";
	public static final String ALIAS_AMOUNT = "提现金额";
	public static final String ALIAS_USER_ID = "申请人ID";
	public static final String ALIAS_CONTENT = "申请备注";
	public static final String ALIAS_BANK_ACCOUNT = "开户名";
	public static final String ALIAS_BANK_CODE = "银行编号";
	public static final String ALIAS_BANK_NAME = "开户行支行";
	public static final String ALIAS_BANK_CARD = "银行卡号";
	public static final String ALIAS_HANDLE_STATUS = "处理状态";
	public static final String ALIAS_HANDLE_LOGIN_ID = "处理人";
	public static final String ALIAS_HANDLE_REMARK = "处理结果";
	public static final String ALIAS_HANDLE_TIME = "处理时间";
	public static final String ALIAS_PAYMENT_NO = "流水号";
	public static final String ALIAS_REF_TYPE = "业务类型";
	public static final String ALIAS_APPLY_LOGIN_IP = "提现IP";
	
	//date formats
	public static final String FORMAT_HANDLE_TIME = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//@Length(max=64)
	private java.lang.String withdrawNo;
	//@NotNull 
	private java.lang.Integer amount;
	//@Length(max=36)
	private java.lang.String userId;
	//@Length(max=512)
	private java.lang.String content;
	//@Length(max=18)
	private java.lang.String bankAccount;
	//@Length(max=10)
	private java.lang.String bankCode;
	//@Length(max=36)
	private java.lang.String bankName;
	//@Length(max=36)
	private java.lang.String bankCard;
	//@Length(max=4)
	private java.lang.String handleStatus;
	//@Length(max=36)
	private java.lang.String handleLoginId;
	//@Length(max=512)
	private java.lang.String handleRemark;
	//
	private java.util.Date handleTime;
	//@Length(max=64)
	private java.lang.String paymentNo;
	//@Length(max=10)
	private java.lang.String refType;
	//@Length(max=64)
	private java.lang.String applyLoginIp;
	//columns END


		public TfdWithdrawLog(){
		}
		public TfdWithdrawLog(Integer id) {
			this.id = id;
		}
	

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "withdraw_no", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getWithdrawNo() {
		return this.withdrawNo;
	}
	
	public void setWithdrawNo(java.lang.String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}
	
	@Column(name = "amount", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.lang.Integer amount) {
		this.amount = amount;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "bank_account", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getBankAccount() {
		return this.bankAccount;
	}
	
	public void setBankAccount(java.lang.String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Column(name = "bank_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getBankCode() {
		return this.bankCode;
	}
	
	public void setBankCode(java.lang.String bankCode) {
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
	
	@Column(name = "handle_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getHandleStatus() {
		return this.handleStatus;
	}
	
	public void setHandleStatus(java.lang.String handleStatus) {
		this.handleStatus = handleStatus;
	}
	
	@Column(name = "handle_login_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getHandleLoginId() {
		return this.handleLoginId;
	}
	
	public void setHandleLoginId(java.lang.String handleLoginId) {
		this.handleLoginId = handleLoginId;
	}
	
	@Column(name = "handle_remark", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getHandleRemark() {
		return this.handleRemark;
	}
	
	public void setHandleRemark(java.lang.String handleRemark) {
		this.handleRemark = handleRemark;
	}
	

	@Column(name = "handle_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getHandleTime() {
		return this.handleTime;
	}
	
	public void setHandleTime(java.util.Date handleTime) {
		this.handleTime = handleTime;
	}
	
	@Column(name = "payment_no", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getPaymentNo() {
		return this.paymentNo;
	}
	
	public void setPaymentNo(java.lang.String paymentNo) {
		this.paymentNo = paymentNo;
	}
	
	@Column(name = "ref_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getRefType() {
		return this.refType;
	}
	
	public void setRefType(java.lang.String refType) {
		this.refType = refType;
	}
	
	@Column(name = "apply_login_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getApplyLoginIp() {
		return this.applyLoginIp;
	}
	
	public void setApplyLoginIp(java.lang.String applyLoginIp) {
		this.applyLoginIp = applyLoginIp;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("WithdrawNo",getWithdrawNo())
			.append("Amount",getAmount())
			.append("UserId",getUserId())
			.append("Content",getContent())
			.append("BankAccount",getBankAccount())
			.append("BankCode",getBankCode())
			.append("BankName",getBankName())
			.append("BankCard",getBankCard())
			.append("HandleStatus",getHandleStatus())
			.append("HandleLoginId",getHandleLoginId())
			.append("HandleRemark",getHandleRemark())
			.append("HandleTime",getHandleTime())
			.append("PaymentNo",getPaymentNo())
			.append("RefType",getRefType())
			.append("ApplyLoginIp",getApplyLoginIp())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdWithdrawLog == false) return false;
		if(this == obj) return true;
		FdWithdrawLog other = (FdWithdrawLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

