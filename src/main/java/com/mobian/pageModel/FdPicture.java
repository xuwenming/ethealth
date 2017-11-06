package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdPicture implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String path;	
	private java.lang.String url;	
	private java.lang.String md5;	
	private java.lang.String sha1;	
	private Integer status;	
	private java.lang.Long createTime;	
	private java.lang.Integer type;	
	private java.lang.String sourceId;	

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setPath(java.lang.String path) {
		this.path = path;
	}
	
	public java.lang.String getPath() {
		return this.path;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setMd5(java.lang.String md5) {
		this.md5 = md5;
	}
	
	public java.lang.String getMd5() {
		return this.md5;
	}
	public void setSha1(java.lang.String sha1) {
		this.sha1 = sha1;
	}
	
	public java.lang.String getSha1() {
		return this.sha1;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}
	
	public java.lang.String getSourceId() {
		return this.sourceId;
	}

}
