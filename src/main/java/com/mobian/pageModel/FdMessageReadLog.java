package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMessageReadLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.Boolean status;	
	private java.lang.Integer userId;	
	private java.lang.Integer messageId;	

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
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
	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setMessageId(java.lang.Integer messageId) {
		this.messageId = messageId;
	}
	
	public java.lang.Integer getMessageId() {
		return this.messageId;
	}

}
