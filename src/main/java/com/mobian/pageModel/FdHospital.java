package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdHospital implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String hospitalName;	
	private java.lang.String hospitalLevel;	
	private java.lang.String introduce;	
	private java.lang.Integer province;	
	private java.lang.Integer city;	
	private java.lang.Integer county;	
	private java.lang.String pics;	
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

	
	public void setHospitalName(java.lang.String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	public java.lang.String getHospitalName() {
		return this.hospitalName;
	}
	public void setHospitalLevel(java.lang.String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	
	public java.lang.String getHospitalLevel() {
		return this.hospitalLevel;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	public void setProvince(java.lang.Integer province) {
		this.province = province;
	}
	
	public java.lang.Integer getProvince() {
		return this.province;
	}
	public void setCity(java.lang.Integer city) {
		this.city = city;
	}
	
	public java.lang.Integer getCity() {
		return this.city;
	}
	public void setCounty(java.lang.Integer county) {
		this.county = county;
	}
	
	public java.lang.Integer getCounty() {
		return this.county;
	}
	public void setPics(java.lang.String pics) {
		this.pics = pics;
	}
	
	public java.lang.String getPics() {
		return this.pics;
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
