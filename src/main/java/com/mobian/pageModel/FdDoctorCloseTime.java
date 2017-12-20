package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdDoctorCloseTime implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer doctorId;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private Integer status;	
	private Date closeDate;			
	private java.lang.Integer time;	
	private String week;

	private Integer[] times;
	

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
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	
	public Date getCloseDate() {
		return this.closeDate;
	}
	public void setTime(java.lang.Integer time) {
		this.time = time;
	}
	
	public java.lang.Integer getTime() {
		return this.time;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Integer[] getTimes() {
		return times;
	}

	public void setTimes(Integer[] times) {
		this.times = times;
	}
}
