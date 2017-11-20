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
@Table(name = "fd_member_appointment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberAppointment implements java.io.Serializable, IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberAppointment";
	public static final String ALIAS_TIME = "1：上午 2：下午";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "病人id";
	public static final String ALIAS_DOCTOR_ID = "医生id";
	public static final String ALIAS_APPOINT_TIME = "预约时间";
	public static final String ALIAS_SECOND_TIME = "备选时间";
	public static final String ALIAS_APPOINT_NAME = "病人姓名";
	public static final String ALIAS_LINK_NAME = "联系人";
	public static final String ALIAS_LINK_WAY = "联系方式";
	public static final String ALIAS_APPOINT_MESSAGE = "预约留言";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 0 是 1 否";
	public static final String ALIAS_COUPON_ID = "优惠券id";
	public static final String ALIAS_PICS = "病例图片";
	public static final String ALIAS_APPOINT_STATUS = "预约状态 0 待确认 1 医生已确认 2 用户确认完成 3 已取消";
	public static final String ALIAS_ADJUSTMENT = "调整方案";
	public static final String ALIAS_REFUSE_REASON = "拒绝理由";
	public static final String ALIAS_CONFIRM_TIME = "确认时间";
	public static final String ALIAS_FIRST_CONFIRM = "初步诊断";
	public static final String ALIAS_FILE = "附件id";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer time;
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Integer doctorId;
	//@Length(max=200)
	private java.lang.String appointTime;
	//@Length(max=200)
	private java.lang.String secondTime;
	//@Length(max=100)
	private java.lang.String appointName;
	//@Length(max=100)
	private java.lang.String linkName;
	//@Length(max=100)
	private java.lang.String linkWay;
	//@Length(max=200)
	private java.lang.String appointMessage;
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
	//@Length(max=2)
	private java.lang.String appointStatus;
	//@Length(max=200)
	private java.lang.String adjustment;
	//@Length(max=200)
	private java.lang.String refuseReason;
	//@Length(max=200)
	private java.lang.String confirmTime;
	//@Length(max=200)
	private java.lang.String firstConfirm;
	//
	private java.lang.Integer file;

	private java.lang.String sourse;
	private java.lang.String appointmentNo;
	//columns END


		public TfdMemberAppointment(){
		}
		public TfdMemberAppointment(Integer id) {
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
	
	@Column(name = "time", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTime() {
		return this.time;
	}
	
	public void setTime(java.lang.Integer time) {
		this.time = time;
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
	
	@Column(name = "appoint_time", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAppointTime() {
		return this.appointTime;
	}
	
	public void setAppointTime(java.lang.String appointTime) {
		this.appointTime = appointTime;
	}
	
	@Column(name = "second_time", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getSecondTime() {
		return this.secondTime;
	}
	
	public void setSecondTime(java.lang.String secondTime) {
		this.secondTime = secondTime;
	}
	
	@Column(name = "appoint_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAppointName() {
		return this.appointName;
	}
	
	public void setAppointName(java.lang.String appointName) {
		this.appointName = appointName;
	}
	
	@Column(name = "link_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLinkName() {
		return this.linkName;
	}
	
	public void setLinkName(java.lang.String linkName) {
		this.linkName = linkName;
	}
	
	@Column(name = "link_way", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLinkWay() {
		return this.linkWay;
	}
	
	public void setLinkWay(java.lang.String linkWay) {
		this.linkWay = linkWay;
	}
	
	@Column(name = "appoint_message", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAppointMessage() {
		return this.appointMessage;
	}
	
	public void setAppointMessage(java.lang.String appointMessage) {
		this.appointMessage = appointMessage;
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
	
	@Column(name = "appoint_status", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getAppointStatus() {
		return this.appointStatus;
	}
	
	public void setAppointStatus(java.lang.String appointStatus) {
		this.appointStatus = appointStatus;
	}
	
	@Column(name = "adjustment", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAdjustment() {
		return this.adjustment;
	}
	
	public void setAdjustment(java.lang.String adjustment) {
		this.adjustment = adjustment;
	}
	
	@Column(name = "refuse_reason", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getRefuseReason() {
		return this.refuseReason;
	}
	
	public void setRefuseReason(java.lang.String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
	@Column(name = "confirm_time", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getConfirmTime() {
		return this.confirmTime;
	}
	
	public void setConfirmTime(java.lang.String confirmTime) {
		this.confirmTime = confirmTime;
	}
	
	@Column(name = "first_confirm", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getFirstConfirm() {
		return this.firstConfirm;
	}
	
	public void setFirstConfirm(java.lang.String firstConfirm) {
		this.firstConfirm = firstConfirm;
	}
	
	@Column(name = "file", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFile() {
		return this.file;
	}
	
	public void setFile(java.lang.Integer file) {
		this.file = file;
	}

	@Column(name = "sourse", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public String getSourse() {
		return sourse;
	}

	public void setSourse(String sourse) {
		this.sourse = sourse;
	}

	@Column(name = "appointment_no", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Time",getTime())
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("DoctorId",getDoctorId())
			.append("AppointTime",getAppointTime())
			.append("SecondTime",getSecondTime())
			.append("AppointName",getAppointName())
			.append("LinkName",getLinkName())
			.append("LinkWay",getLinkWay())
			.append("AppointMessage",getAppointMessage())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("CouponId",getCouponId())
			.append("Pics",getPics())
			.append("AppointStatus",getAppointStatus())
			.append("Adjustment",getAdjustment())
			.append("RefuseReason",getRefuseReason())
			.append("ConfirmTime",getConfirmTime())
			.append("FirstConfirm",getFirstConfirm())
			.append("File",getFile())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberAppointment == false) return false;
		if(this == obj) return true;
		FdMemberAppointment other = (FdMemberAppointment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

