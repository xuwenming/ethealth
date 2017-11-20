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
@Table(name = "fd_balance_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdBalanceLog implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdBalanceLog";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADMIN_ID = "adminId";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_BALANCE_NO = "钱包流水号";
	public static final String ALIAS_REF_ID = "业务id";
	public static final String ALIAS_REF_TYPE = "业务类型";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_AMOUNT = "金额";
	public static final String ALIAS_AMOUNT_LOG = "钱包期末余额";
	public static final String ALIAS_NOTE = "备注";
	public static final String ALIAS_STATUS = "是否删除1:是 0:否";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Long id;
	//
	private java.lang.Long adminId;
	//@NotNull 
	private java.lang.Long userId;
	//@Length(max=64)
	private java.lang.String balanceNo;
	//@Length(max=64)
	private java.lang.String refId;
	//@NotBlank @Length(max=10)
	private java.lang.String refType;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//@NotNull 
	private java.lang.Float amount;
	//@NotNull 
	private java.lang.Float amountLog;
	//@Length(max=65535)
	private java.lang.String note;
	//
	private java.lang.Boolean status;
	//columns END


		public TfdBalanceLog(){
		}
		public TfdBalanceLog(Long id) {
			this.id = id;
		}
	

	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "admin_id", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAdminId() {
		return this.adminId;
	}
	
	public void setAdminId(java.lang.Long adminId) {
		this.adminId = adminId;
	}
	
	@Column(name = "user_id", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "balance_no", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getBalanceNo() {
		return this.balanceNo;
	}
	
	public void setBalanceNo(java.lang.String balanceNo) {
		this.balanceNo = balanceNo;
	}
	
	@Column(name = "ref_id", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRefId() {
		return this.refId;
	}
	
	public void setRefId(java.lang.String refId) {
		this.refId = refId;
	}
	
	@Column(name = "ref_type", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.String getRefType() {
		return this.refType;
	}
	
	public void setRefType(java.lang.String refType) {
		this.refType = refType;
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
	
	@Column(name = "amount", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Float getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.lang.Float amount) {
		this.amount = amount;
	}
	
	@Column(name = "amount_log", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Float getAmountLog() {
		return this.amountLog;
	}
	
	public void setAmountLog(java.lang.Float amountLog) {
		this.amountLog = amountLog;
	}
	
	@Column(name = "note", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String note) {
		this.note = note;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AdminId",getAdminId())
			.append("UserId",getUserId())
			.append("BalanceNo",getBalanceNo())
			.append("RefId",getRefId())
			.append("RefType",getRefType())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Amount",getAmount())
			.append("AmountLog",getAmountLog())
			.append("Note",getNote())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdBalanceLog == false) return false;
		if(this == obj) return true;
		FdBalanceLog other = (FdBalanceLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

