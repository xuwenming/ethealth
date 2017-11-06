package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMedicinePractice implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String title;	
	private java.lang.Integer view;	
	private java.lang.Integer comment;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private Integer isUp;	
	private Integer status;	
	private java.lang.String pic;	
	private java.lang.String content;	
	private java.lang.String desc;	
	private java.lang.String file;

	private String picUrl;
	private String key;

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
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
	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}
	
	public java.lang.String getDesc() {
		return this.desc;
	}
	public void setFile(java.lang.String file) {
		this.file = file;
	}
	
	public java.lang.String getFile() {
		return this.file;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
