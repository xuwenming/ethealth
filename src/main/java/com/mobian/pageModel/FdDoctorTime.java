package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdDoctorTime implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer userId;	
	private java.lang.Integer week;	
	private java.lang.Integer time;	
	private java.lang.Integer visitType;	
	private java.lang.String address;	
	private java.lang.Integer number;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	

	

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
	public void setWeek(java.lang.Integer week) {
		this.week = week;
	}
	
	public java.lang.Integer getWeek() {
		return this.week;
	}
	public void setTime(java.lang.Integer time) {
		this.time = time;
	}
	
	public java.lang.Integer getTime() {
		return this.time;
	}
	public void setVisitType(java.lang.Integer visitType) {
		this.visitType = visitType;
	}
	
	public java.lang.Integer getVisitType() {
		return this.visitType;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}
	
	public java.lang.Integer getNumber() {
		return this.number;
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

}
