package com.mobian.pageModel;

import com.mobian.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberDoctor implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer level;	
	private java.lang.Integer hospital;	
	private java.lang.Integer department;	
	private java.lang.String education;	
	private java.lang.String consultingHour;	
	private java.lang.String specialHour;	
	private java.lang.String speciality;	
	private java.lang.String introduce;	
	private java.lang.String pics;	
	private java.lang.Integer createBy;	
	private Date createTime;			
	private java.lang.Integer updateBy;	
	private Date updateTime;
	private java.lang.Integer sort;
	private java.lang.Integer groupId;

	private String hospitalName;
	private String departmentName;
	private String levelName;
	private String picUrl;
	private FdCustomer customer;
	private Boolean isBest; // 是否著名
	private String key; // 关键字查询

	public String getEducationName() {
		return Application.getString(this.education);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}
	
	public java.lang.Integer getLevel() {
		return this.level;
	}
	public void setHospital(java.lang.Integer hospital) {
		this.hospital = hospital;
	}
	
	public java.lang.Integer getHospital() {
		return this.hospital;
	}
	public void setDepartment(java.lang.Integer department) {
		this.department = department;
	}
	
	public java.lang.Integer getDepartment() {
		return this.department;
	}
	public void setEducation(java.lang.String education) {
		this.education = education;
	}
	
	public java.lang.String getEducation() {
		return this.education;
	}
	public void setConsultingHour(java.lang.String consultingHour) {
		this.consultingHour = consultingHour;
	}
	
	public java.lang.String getConsultingHour() {
		return this.consultingHour;
	}
	public void setSpecialHour(java.lang.String specialHour) {
		this.specialHour = specialHour;
	}
	
	public java.lang.String getSpecialHour() {
		return this.specialHour;
	}
	public void setSpeciality(java.lang.String speciality) {
		this.speciality = speciality;
	}
	
	public java.lang.String getSpeciality() {
		return this.speciality;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	public java.lang.String getIntroduce() {
		return this.introduce;
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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	public java.lang.Integer getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}


	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public FdCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(FdCustomer customer) {
		this.customer = customer;
	}

	public Boolean getIsBest() {
		return isBest;
	}

	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
