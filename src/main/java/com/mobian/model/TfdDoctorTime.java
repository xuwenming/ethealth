/*
 * @author John
 * @date - 2017-11-08
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_doctor_time")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdDoctorTime implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdDoctorTime";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_WEEK = "门诊时间 周";
	public static final String ALIAS_TIME = "1 上午 2 下午 3 夜班";
	public static final String ALIAS_VISIT_TYPE = "1 专家门诊 2 特需门诊";
	public static final String ALIAS_ADDRESS = "门诊地址";
	public static final String ALIAS_NUMBER = "门诊数量";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1 是 0 否";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Integer week;
	//
	private java.lang.Integer time;
	//
	private java.lang.Integer visitType;
	//@Length(max=200)
	private java.lang.String address;
	//
	private java.lang.Integer number;
	//
	private java.lang.Integer createBy;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Integer updateBy;
	//
	private java.lang.Long updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//columns END


		public TfdDoctorTime(){
		}
		public TfdDoctorTime(Integer id) {
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
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "week", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getWeek() {
		return this.week;
	}
	
	public void setWeek(java.lang.Integer week) {
		this.week = week;
	}
	
	@Column(name = "time", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTime() {
		return this.time;
	}
	
	public void setTime(java.lang.Integer time) {
		this.time = time;
	}
	
	@Column(name = "visit_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getVisitType() {
		return this.visitType;
	}
	
	public void setVisitType(java.lang.Integer visitType) {
		this.visitType = visitType;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	@Column(name = "number", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNumber() {
		return this.number;
	}
	
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}
	
	@Column(name = "create_by", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateBy() {
		return this.createBy;
	}
	
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_by", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateBy() {
		return this.updateBy;
	}
	
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
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
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("Week",getWeek())
			.append("Time",getTime())
			.append("VisitType",getVisitType())
			.append("Address",getAddress())
			.append("Number",getNumber())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdDoctorTime == false) return false;
		if(this == obj) return true;
		FdDoctorTime other = (FdDoctorTime)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

