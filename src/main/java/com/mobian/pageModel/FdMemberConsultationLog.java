package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberConsultationLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String mtype;	
	private java.lang.Integer fromUserId;	
	private java.lang.Integer toUserId;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.String content;	
	private java.lang.Integer senderType;

	private java.lang.Long createTimeStart;
	private java.lang.Long createTimeEnd;


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setMtype(java.lang.String mtype) {
		this.mtype = mtype;
	}
	
	public java.lang.String getMtype() {
		return this.mtype;
	}
	public void setFromUserId(java.lang.Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	public java.lang.Integer getFromUserId() {
		return this.fromUserId;
	}
	public void setToUserId(java.lang.Integer toUserId) {
		this.toUserId = toUserId;
	}
	
	public java.lang.Integer getToUserId() {
		return this.toUserId;
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
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setSenderType(java.lang.Integer senderType) {
		this.senderType = senderType;
	}
	
	public java.lang.Integer getSenderType() {
		return this.senderType;
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
}
