package com.mobian.pageModel;

import com.mobian.listener.Application;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class FdWithdrawLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.String withdrawNo;	
	private java.lang.Integer amount;	
	private java.lang.Integer serviceAmt;
	private java.lang.String userId;
	private java.lang.String content;	
	private java.lang.String bankAccount;	
	private java.lang.String bankCode;	
	private java.lang.String bankName;	
	private java.lang.String bankCard;	
	private java.lang.String handleStatus;	
	private java.lang.String handleLoginId;	
	private java.lang.String handleRemark;	
	private Date handleTime;			
	private java.lang.String paymentNo;	
	private java.lang.Integer cmmsAmt;
	private java.lang.String refType;
	private java.lang.String applyLoginIp;

	private java.lang.Long createTimeStart;
	private java.lang.Long createTimeEnd;
	private Date createTimeStartDate;
	private Date createTimeEndDate;
	private String userName;
	private String userMobile;
	private String handleLoginName;

	public String getBankCodeZh() {
		return Application.getString(bankCode);
	}

	public String getHandleStatusZh() {
		return Application.getString(handleStatus);
	}

	public String getRefTypeZh() {
		return Application.getString(refType);
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
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
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setWithdrawNo(java.lang.String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}
	
	public java.lang.String getWithdrawNo() {
		return this.withdrawNo;
	}
	public void setAmount(java.lang.Integer amount) {
		this.amount = amount;
	}
	
	public java.lang.Integer getAmount() {
		return this.amount;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
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
	public void setBankCode(java.lang.String bankCode) {
		this.bankCode = bankCode;
	}
	
	public java.lang.String getBankCode() {
		return this.bankCode;
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
	public void setPaymentNo(java.lang.String paymentNo) {
		this.paymentNo = paymentNo;
	}
	
	public java.lang.String getPaymentNo() {
		return this.paymentNo;
	}
	public void setRefType(java.lang.String refType) {
		this.refType = refType;
	}
	
	public java.lang.String getRefType() {
		return this.refType;
	}
	public void setApplyLoginIp(java.lang.String applyLoginIp) {
		this.applyLoginIp = applyLoginIp;
	}
	
	public java.lang.String getApplyLoginIp() {
		return this.applyLoginIp;
	}

	public Long getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Long createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getCreateTimeStartDate() {
		return createTimeStartDate;
	}

	public void setCreateTimeStartDate(Date createTimeStartDate) {
		this.createTimeStartDate = createTimeStartDate;
	}

	public Date getCreateTimeEndDate() {
		return createTimeEndDate;
	}

	public void setCreateTimeEndDate(Date createTimeEndDate) {
		this.createTimeEndDate = createTimeEndDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getHandleLoginName() {
		return handleLoginName;
	}

	public void setHandleLoginName(String handleLoginName) {
		this.handleLoginName = handleLoginName;
	}

	public Integer getServiceAmt() {
		return serviceAmt;
	}

	public void setServiceAmt(Integer serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public Integer getCmmsAmt() {
		return cmmsAmt;
	}

	public void setCmmsAmt(Integer cmmsAmt) {
		this.cmmsAmt = cmmsAmt;
	}

	public String getCreateTimeStr() {
		if(createTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(createTime);
			return DateUtil.format(c.getTime(), Constants.DATE_FORMAT);
		}
		return null;
	}
}
