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
@Table(name = "fd_member_consultation_expire")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberConsultationExpire implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberConsultationExpire";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_USER_ID = "病人id";
	public static final String ALIAS_DOCTOR_ID = "医生id";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1 是 0 否";
	public static final String ALIAS_EXPIRE_DATE = "到期时间";
	
	//date formats
	public static final String FORMAT_EXPIRE_DATE = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Integer doctorId;
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
	//
	private java.util.Date expireDate;
	//columns END


		public TfdMemberConsultationExpire(){
		}
		public TfdMemberConsultationExpire(Integer id) {
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
	
	@Column(name = "doctor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
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
	

	@Column(name = "expire_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getExpireDate() {
		return this.expireDate;
	}
	
	public void setExpireDate(java.util.Date expireDate) {
		this.expireDate = expireDate;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("DoctorId",getDoctorId())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("ExpireDate",getExpireDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberConsultationExpire == false) return false;
		if(this == obj) return true;
		FdMemberConsultationExpire other = (FdMemberConsultationExpire)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

