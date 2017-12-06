package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberConsultationFriend implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer userId;	
	private java.lang.Integer doctorId;	
	private java.lang.Long createTime;
	private java.lang.Long updateTime;
	private java.lang.String status;	
	private java.lang.String lastContent;	
	private Date lastTime;			
	private Integer senderType;

	private Integer receiverId;

	private FdMember member;
	private Integer isAdmin;
	private String userName;

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
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setLastContent(java.lang.String lastContent) {
		this.lastContent = lastContent;
	}
	
	public java.lang.String getLastContent() {
		return this.lastContent;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	public Date getLastTime() {
		return this.lastTime;
	}

	public int getSenderType() {
		return senderType;
	}

	public void setSenderType(int senderType) {
		this.senderType = senderType;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public FdMember getMember() {
		return member;
	}

	public void setMember(FdMember member) {
		this.member = member;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}