package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberConsultation implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer userId;	
	private java.lang.Integer doctorId;	
	private java.lang.String consultationMessage;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.Integer couponId;	
	private java.lang.String pics;	
	private java.lang.Integer file;	
	private java.lang.String consultationStatus;	
	private java.lang.String doctorReply;	

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
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
	public void setConsultationMessage(java.lang.String consultationMessage) {
		this.consultationMessage = consultationMessage;
	}
	
	public java.lang.String getConsultationMessage() {
		return this.consultationMessage;
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
	public void setFile(java.lang.Integer file) {
		this.file = file;
	}
	
	public java.lang.Integer getFile() {
		return this.file;
	}
	public void setConsultationStatus(java.lang.String consultationStatus) {
		this.consultationStatus = consultationStatus;
	}
	
	public java.lang.String getConsultationStatus() {
		return this.consultationStatus;
	}
	public void setDoctorReply(java.lang.String doctorReply) {
		this.doctorReply = doctorReply;
	}
	
	public java.lang.String getDoctorReply() {
		return this.doctorReply;
	}

}
