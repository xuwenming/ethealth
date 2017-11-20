package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdPaymentLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String paymentId;	
	private java.lang.String type;	
	private java.lang.String status;	
	private java.lang.Long totalFee;	
	private java.lang.String refId;	
	private java.lang.String refundNo;	
	private java.lang.String refRefundNo;	
	private java.lang.Long refundFee;	
	private Date refundDate;			
	private Date createDate;			
	private java.lang.String remark;	

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setPaymentId(java.lang.String paymentId) {
		this.paymentId = paymentId;
	}
	
	public java.lang.String getPaymentId() {
		return this.paymentId;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setTotalFee(java.lang.Long totalFee) {
		this.totalFee = totalFee;
	}
	
	public java.lang.Long getTotalFee() {
		return this.totalFee;
	}
	public void setRefId(java.lang.String refId) {
		this.refId = refId;
	}
	
	public java.lang.String getRefId() {
		return this.refId;
	}
	public void setRefundNo(java.lang.String refundNo) {
		this.refundNo = refundNo;
	}
	
	public java.lang.String getRefundNo() {
		return this.refundNo;
	}
	public void setRefRefundNo(java.lang.String refRefundNo) {
		this.refRefundNo = refRefundNo;
	}
	
	public java.lang.String getRefRefundNo() {
		return this.refRefundNo;
	}
	public void setRefundFee(java.lang.Long refundFee) {
		this.refundFee = refundFee;
	}
	
	public java.lang.Long getRefundFee() {
		return this.refundFee;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	
	public Date getRefundDate() {
		return this.refundDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}

}
