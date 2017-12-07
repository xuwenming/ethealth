package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberAppointmentComment implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer doctorId;	
	private java.lang.Integer appointmentId;	
	private java.lang.Integer userId;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.Float effect;	
	private java.lang.Float attitude;	
	private java.lang.String disease;	
	private java.lang.String objective;	
	private java.lang.String objectiveOther;	
	private java.lang.String therapy;	
	private java.lang.String therapyOther;	
	private java.lang.String comment;	

	private String userName;

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	public void setAppointmentId(java.lang.Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public java.lang.Integer getAppointmentId() {
		return this.appointmentId;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
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
	public void setEffect(java.lang.Float effect) {
		this.effect = effect;
	}
	
	public java.lang.Float getEffect() {
		return this.effect;
	}
	public void setAttitude(java.lang.Float attitude) {
		this.attitude = attitude;
	}
	
	public java.lang.Float getAttitude() {
		return this.attitude;
	}
	public void setDisease(java.lang.String disease) {
		this.disease = disease;
	}
	
	public java.lang.String getDisease() {
		return this.disease;
	}
	public void setObjective(java.lang.String objective) {
		this.objective = objective;
	}
	
	public java.lang.String getObjective() {
		return this.objective;
	}
	public void setObjectiveOther(java.lang.String objectiveOther) {
		this.objectiveOther = objectiveOther;
	}
	
	public java.lang.String getObjectiveOther() {
		return this.objectiveOther;
	}
	public void setTherapy(java.lang.String therapy) {
		this.therapy = therapy;
	}
	
	public java.lang.String getTherapy() {
		return this.therapy;
	}
	public void setTherapyOther(java.lang.String therapyOther) {
		this.therapyOther = therapyOther;
	}
	
	public java.lang.String getTherapyOther() {
		return this.therapyOther;
	}
	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	
	public java.lang.String getComment() {
		return this.comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
