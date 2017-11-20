/*
 * @author John
 * @date - 2017-11-18
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_payment_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdPaymentLog implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdPaymentLog";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PAYMENT_ID = "支付id";
	public static final String ALIAS_TYPE = "支付类型（微信/支付宝）";
	public static final String ALIAS_STATUS = "支付状态（0 待支付 1 支付中 2 完成支付 3 退款 9 取消）";
	public static final String ALIAS_TOTAL_FEE = "总金额";
	public static final String ALIAS_REF_ID = "第三方支付订单号";
	public static final String ALIAS_REFUND_NO = "退款单号";
	public static final String ALIAS_REF_REFUND_NO = "第三方退款单号";
	public static final String ALIAS_REFUND_FEE = "退款金额";
	public static final String ALIAS_REFUND_DATE = "退款日期";
	public static final String ALIAS_CREATE_DATE = "创建日期";
	public static final String ALIAS_REMARK = "备注";
	
	//date formats
	public static final String FORMAT_REFUND_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_CREATE_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=50)
	private java.lang.String id;
	//@Length(max=50)
	private java.lang.String paymentId;
	//@Length(max=16)
	private java.lang.String type;
	//@Length(max=16)
	private java.lang.String status;
	//
	private java.lang.Long totalFee;
	//@Length(max=64)
	private java.lang.String refId;
	//@Length(max=64)
	private java.lang.String refundNo;
	//@Length(max=64)
	private java.lang.String refRefundNo;
	//
	private java.lang.Long refundFee;
	//
	private java.util.Date refundDate;
	//
	private java.util.Date createDate;
	//@Length(max=512)
	private java.lang.String remark;
	//columns END


		public TfdPaymentLog(){
		}
		public TfdPaymentLog(String id) {
			this.id = id;
		}
	

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "payment_id", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPaymentId() {
		return this.paymentId;
	}
	
	public void setPaymentId(java.lang.String paymentId) {
		this.paymentId = paymentId;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "total_fee", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getTotalFee() {
		return this.totalFee;
	}
	
	public void setTotalFee(java.lang.Long totalFee) {
		this.totalFee = totalFee;
	}
	
	@Column(name = "ref_id", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRefId() {
		return this.refId;
	}
	
	public void setRefId(java.lang.String refId) {
		this.refId = refId;
	}
	
	@Column(name = "refund_no", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRefundNo() {
		return this.refundNo;
	}
	
	public void setRefundNo(java.lang.String refundNo) {
		this.refundNo = refundNo;
	}
	
	@Column(name = "ref_refund_no", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRefRefundNo() {
		return this.refRefundNo;
	}
	
	public void setRefRefundNo(java.lang.String refRefundNo) {
		this.refRefundNo = refRefundNo;
	}
	
	@Column(name = "refund_fee", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getRefundFee() {
		return this.refundFee;
	}
	
	public void setRefundFee(java.lang.Long refundFee) {
		this.refundFee = refundFee;
	}
	

	@Column(name = "refund_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getRefundDate() {
		return this.refundDate;
	}
	
	public void setRefundDate(java.util.Date refundDate) {
		this.refundDate = refundDate;
	}
	

	@Column(name = "create_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("PaymentId",getPaymentId())
			.append("Type",getType())
			.append("Status",getStatus())
			.append("TotalFee",getTotalFee())
			.append("RefId",getRefId())
			.append("RefundNo",getRefundNo())
			.append("RefRefundNo",getRefRefundNo())
			.append("RefundFee",getRefundFee())
			.append("RefundDate",getRefundDate())
			.append("CreateDate",getCreateDate())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdPaymentLog == false) return false;
		if(this == obj) return true;
		FdPaymentLog other = (FdPaymentLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

