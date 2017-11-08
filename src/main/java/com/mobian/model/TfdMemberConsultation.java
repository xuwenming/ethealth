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
@Table(name = "fd_member_consultation")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberConsultation implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberConsultation";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "病人id";
	public static final String ALIAS_DOCTOR_ID = "医生id";
	public static final String ALIAS_CONSULTATION_MESSAGE = "咨询问题";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 0 是 1 否";
	public static final String ALIAS_COUPON_ID = "优惠券id";
	public static final String ALIAS_PICS = "病例图片";
	public static final String ALIAS_FILE = "文档";
	public static final String ALIAS_CONSULTATION_STATUS = "咨询状态 0 未回复 1 已回复 2 取消";
	public static final String ALIAS_DOCTOR_REPLY = "医生回复";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Integer doctorId;
	//@Length(max=2000)
	private java.lang.String consultationMessage;
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
	private java.lang.Integer couponId;
	//@Length(max=255)
	private java.lang.String pics;
	//
	private java.lang.Integer file;
	//@Length(max=2)
	private java.lang.String consultationStatus;
	//@Length(max=2000)
	private java.lang.String doctorReply;
	//columns END


		public TfdMemberConsultation(){
		}
		public TfdMemberConsultation(Integer id) {
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
	
	@Column(name = "consultation_message", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getConsultationMessage() {
		return this.consultationMessage;
	}
	
	public void setConsultationMessage(java.lang.String consultationMessage) {
		this.consultationMessage = consultationMessage;
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
	
	@Column(name = "coupon_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCouponId() {
		return this.couponId;
	}
	
	public void setCouponId(java.lang.Integer couponId) {
		this.couponId = couponId;
	}
	
	@Column(name = "pics", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPics() {
		return this.pics;
	}
	
	public void setPics(java.lang.String pics) {
		this.pics = pics;
	}
	
	@Column(name = "file", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFile() {
		return this.file;
	}
	
	public void setFile(java.lang.Integer file) {
		this.file = file;
	}
	
	@Column(name = "consultation_status", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getConsultationStatus() {
		return this.consultationStatus;
	}
	
	public void setConsultationStatus(java.lang.String consultationStatus) {
		this.consultationStatus = consultationStatus;
	}
	
	@Column(name = "doctor_reply", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getDoctorReply() {
		return this.doctorReply;
	}
	
	public void setDoctorReply(java.lang.String doctorReply) {
		this.doctorReply = doctorReply;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("DoctorId",getDoctorId())
			.append("ConsultationMessage",getConsultationMessage())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("CouponId",getCouponId())
			.append("Pics",getPics())
			.append("File",getFile())
			.append("ConsultationStatus",getConsultationStatus())
			.append("DoctorReply",getDoctorReply())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberConsultation == false) return false;
		if(this == obj) return true;
		FdMemberConsultation other = (FdMemberConsultation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

