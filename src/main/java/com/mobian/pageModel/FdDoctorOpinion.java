package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdDoctorOpinion implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.Integer userId;	
	private java.lang.String title;	
	private java.lang.Integer view;	
	private java.lang.Integer comment;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private Integer verifyStatus;	
	private Integer isUp;	
	private Integer status;	
	private java.lang.String pic;	
	private java.lang.String content;	
	private java.lang.String brief;	
	private java.lang.Integer file;	

	

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
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setView(java.lang.Integer view) {
		this.view = view;
	}
	
	public java.lang.Integer getView() {
		return this.view;
	}
	public void setComment(java.lang.Integer comment) {
		this.comment = comment;
	}
	
	public java.lang.Integer getComment() {
		return this.comment;
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
	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	
	public Integer getVerifyStatus() {
		return this.verifyStatus;
	}
	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}
	
	public Integer getIsUp() {
		return this.isUp;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	
	public java.lang.String getPic() {
		return this.pic;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setBrief(java.lang.String brief) {
		this.brief = brief;
	}
	
	public java.lang.String getBrief() {
		return this.brief;
	}
	public void setFile(java.lang.Integer file) {
		this.file = file;
	}
	
	public java.lang.Integer getFile() {
		return this.file;
	}

}
