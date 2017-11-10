package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdHospitalDept implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer hospitalId;	
	private java.lang.String name;
	private java.lang.String icon;
	private java.lang.Integer pid;
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

	
	public void setHospitalId(java.lang.Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	public java.lang.Integer getHospitalId() {
		return this.hospitalId;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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
