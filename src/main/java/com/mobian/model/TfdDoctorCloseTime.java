/*
 * @author John
 * @date - 2017-12-13
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_doctor_close_time")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdDoctorCloseTime implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdDoctorCloseTime";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_DOCTOR_ID = "医生id";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "是否删除 0 否 1 是";
	public static final String ALIAS_CLOSE_DATE = "停诊日期";
	public static final String ALIAS_TIME = "0不限 1 上午 2 下午 3 夜班";
	
	//date formats
	public static final String FORMAT_CLOSE_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer doctorId;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//@Max(127)
	private Integer status;
	//
	private java.util.Date closeDate;
	//
	private java.lang.Integer time;
	//columns END


		public TfdDoctorCloseTime(){
		}
		public TfdDoctorCloseTime(Integer id) {
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
	
	@Column(name = "doctor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
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
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	

	@Column(name = "close_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getCloseDate() {
		return this.closeDate;
	}
	
	public void setCloseDate(java.util.Date closeDate) {
		this.closeDate = closeDate;
	}
	
	@Column(name = "time", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTime() {
		return this.time;
	}
	
	public void setTime(java.lang.Integer time) {
		this.time = time;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DoctorId",getDoctorId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("CloseDate",getCloseDate())
			.append("Time",getTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdDoctorCloseTime == false) return false;
		if(this == obj) return true;
		FdDoctorCloseTime other = (FdDoctorCloseTime)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

