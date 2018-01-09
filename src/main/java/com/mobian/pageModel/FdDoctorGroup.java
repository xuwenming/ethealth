package com.mobian.pageModel;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class FdDoctorGroup implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer hospitalId;	
	private java.lang.Integer deptId;	
	private java.lang.String groupName;	
	private java.lang.String introduce;	
	private java.lang.String pics;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.Integer leader;
	private java.lang.Integer seq;
	private Boolean isBest;

	private String picUrl;
	private List<FdMember> members;

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
	public void setDeptId(java.lang.Integer deptId) {
		this.deptId = deptId;
	}
	
	public java.lang.Integer getDeptId() {
		return this.deptId;
	}
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}
	
	public java.lang.String getGroupName() {
		return this.groupName;
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
	public void setLeader(java.lang.Integer leader) {
		this.leader = leader;
	}
	
	public java.lang.Integer getLeader() {
		return this.leader;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public List<FdMember> getMembers() {
		return members;
	}

	public void setMembers(List<FdMember> members) {
		this.members = members;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Boolean getIsBest() {
		return isBest;
	}

	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}
}
