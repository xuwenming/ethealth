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
@Table(name = "fd_payment_base")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdPaymentBase implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdPaymentBase";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ORDER_NO = "对应订单号";
	public static final String ALIAS_TYPE = "支付类型（微信/支付宝）";
	public static final String ALIAS_PRICE = "支付价格";
	public static final String ALIAS_STATUS = "支付状态（0 待支付 1 支付中 2 完成支付 3 退款 9 取消）";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_EXPIRY_DATE = "有效期";
	public static final String ALIAS_CREATE_DATE = "创建日期";
	public static final String ALIAS_UPDATE_DATE = "更新日期";
	
	//date formats
	public static final String FORMAT_EXPIRY_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_CREATE_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATE_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=50)
	private java.lang.String id;
	//@Length(max=50)
	private java.lang.String orderNo;
	//@Length(max=16)
	private java.lang.String type;
	//
	private java.lang.Integer price;
	//@Length(max=16)
	private java.lang.String status;
	//@Length(max=512)
	private java.lang.String remark;
	//
	private java.util.Date expiryDate;
	//
	private java.util.Date createDate;
	//
	private java.util.Date updateDate;
	//columns END


		public TfdPaymentBase(){
		}
		public TfdPaymentBase(String id) {
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
	
	@Column(name = "order_no", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderNo(java.lang.String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.Integer price) {
		this.price = price;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	

	@Column(name = "expiry_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getExpiryDate() {
		return this.expiryDate;
	}
	
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	

	@Column(name = "create_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	

	@Column(name = "update_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}
	
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderNo",getOrderNo())
			.append("Type",getType())
			.append("Price",getPrice())
			.append("Status",getStatus())
			.append("Remark",getRemark())
			.append("ExpiryDate",getExpiryDate())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdPaymentBase == false) return false;
		if(this == obj) return true;
		FdPaymentBase other = (FdPaymentBase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

