package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdBanner implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String title;	
	private java.lang.String link;	
	private java.lang.Integer num;	
	private java.lang.Integer pic;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.String status;	
	private java.lang.String source;	

	private String picUrl;

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
	public void setLink(java.lang.String link) {
		this.link = link;
	}
	
	public java.lang.String getLink() {
		return this.link;
	}
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}
	
	public java.lang.Integer getNum() {
		return this.num;
	}
	public void setPic(java.lang.Integer pic) {
		this.pic = pic;
	}
	
	public java.lang.Integer getPic() {
		return this.pic;
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
	public void setSource(java.lang.String source) {
		this.source = source;
	}
	
	public java.lang.String getSource() {
		return this.source;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
