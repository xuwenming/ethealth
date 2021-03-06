package com.mobian.pageModel;

import com.mobian.absx.F;
import com.mobian.enums.EnumConstants;
import com.mobian.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberAppointment implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer time;	
	private java.lang.Integer id;	
	private java.lang.Integer userId;	
	private java.lang.Integer doctorId;	
	private java.lang.String appointTime;	
	private java.lang.String secondTime;	
	private java.lang.String appointName;	
	private java.lang.String linkName;	
	private java.lang.String linkWay;	
	private java.lang.String appointMessage;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.Integer couponId;	
	private java.lang.String pics;	
	private java.lang.String appointStatus;	
	private java.lang.String adjustment;	
	private java.lang.String refuseReason;	
	private java.lang.String confirmTime;	
	private java.lang.String firstConfirm;	
	private java.lang.Integer file;
	private java.lang.String sourse;
	private java.lang.String appointmentNo;
	private java.lang.Long amount;
	private String appointAddress;
	private java.lang.Boolean isRefund;


	private FdMemberDoctor doctor;
	private FdMember user;
	private Boolean isCommented;
	private FdMemberAppointmentComment comment;
	private String query;

	private String userName;
	private String userMobile;
	private String doctorName;
	private String doctorMobile;

	private Boolean isShowWx; // 是否显示来自微信公众号

	private Date startDate;
	private Date endDate;

	private java.lang.Long createTimeStart;
	private java.lang.Long createTimeEnd;

	private Date createTimeStartDate;
	private Date createTimeEndDate;

	public String getTimeZh() {
		if(F.empty(time)) return null;
		return EnumConstants.TIME.getCnName(time);
	}

	public String getSourseZh() {
		return Application.getString(sourse);
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setTime(java.lang.Integer time) {
		this.time = time;
	}
	
	public java.lang.Integer getTime() {
		return this.time;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	public void setAppointTime(java.lang.String appointTime) {
		this.appointTime = appointTime;
	}
	
	public java.lang.String getAppointTime() {
		return this.appointTime;
	}
	public void setSecondTime(java.lang.String secondTime) {
		this.secondTime = secondTime;
	}
	
	public java.lang.String getSecondTime() {
		return this.secondTime;
	}
	public void setAppointName(java.lang.String appointName) {
		this.appointName = appointName;
	}
	
	public java.lang.String getAppointName() {
		return this.appointName;
	}
	public void setLinkName(java.lang.String linkName) {
		this.linkName = linkName;
	}
	
	public java.lang.String getLinkName() {
		return this.linkName;
	}
	public void setLinkWay(java.lang.String linkWay) {
		this.linkWay = linkWay;
	}
	
	public java.lang.String getLinkWay() {
		return this.linkWay;
	}
	public void setAppointMessage(java.lang.String appointMessage) {
		this.appointMessage = appointMessage;
	}
	
	public java.lang.String getAppointMessage() {
		return this.appointMessage;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	
	public java.lang.Integer getCreateBy() {
		return this.createBy;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	public java.lang.Integer getUpdateBy() {
		return this.updateBy;
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
	public void setCouponId(java.lang.Integer couponId) {
		this.couponId = couponId;
	}
	
	public java.lang.Integer getCouponId() {
		return this.couponId;
	}
	public void setPics(java.lang.String pics) {
		this.pics = pics;
	}
	
	public java.lang.String getPics() {
		return this.pics;
	}
	public void setAppointStatus(java.lang.String appointStatus) {
		this.appointStatus = appointStatus;
	}
	
	public java.lang.String getAppointStatus() {
		return this.appointStatus;
	}
	public void setAdjustment(java.lang.String adjustment) {
		this.adjustment = adjustment;
	}
	
	public java.lang.String getAdjustment() {
		return this.adjustment;
	}
	public void setRefuseReason(java.lang.String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
	public java.lang.String getRefuseReason() {
		return this.refuseReason;
	}
	public void setConfirmTime(java.lang.String confirmTime) {
		this.confirmTime = confirmTime;
	}
	
	public java.lang.String getConfirmTime() {
		return this.confirmTime;
	}
	public void setFirstConfirm(java.lang.String firstConfirm) {
		this.firstConfirm = firstConfirm;
	}
	
	public java.lang.String getFirstConfirm() {
		return this.firstConfirm;
	}
	public void setFile(java.lang.Integer file) {
		this.file = file;
	}
	
	public java.lang.Integer getFile() {
		return this.file;
	}

	public String getSourse() {
		return sourse;
	}

	public void setSourse(String sourse) {
		this.sourse = sourse;
	}

	public FdMemberDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(FdMemberDoctor doctor) {
		this.doctor = doctor;
	}

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Boolean getIsCommented() {
		return isCommented;
	}

	public void setIsCommented(Boolean isCommented) {
		this.isCommented = isCommented;
	}

	public FdMemberAppointmentComment getComment() {
		return comment;
	}

	public void setComment(FdMemberAppointmentComment comment) {
		this.comment = comment;
	}

	public String getAppointAddress() {
		return appointAddress;
	}

	public void setAppointAddress(String appointAddress) {
		this.appointAddress = appointAddress;
	}

	public java.lang.Boolean getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(java.lang.Boolean isRefund) {
		this.isRefund = isRefund;
	}

	public FdMember getUser() {
		return user;
	}

	public void setUser(FdMember user) {
		this.user = user;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorMobile() {
		return doctorMobile;
	}

	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}

	public Boolean getIsShowWx() {
		return isShowWx;
	}

	public void setIsShowWx(Boolean isShowWx) {
		this.isShowWx = isShowWx;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
}
