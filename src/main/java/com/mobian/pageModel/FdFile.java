package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdFile implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String name;	
	private java.lang.String savename;	
	private java.lang.String savepath;	
	private java.lang.String ext;	
	private java.lang.String mime;	
	private java.lang.Integer size;	
	private java.lang.String md5;	
	private java.lang.String sha1;	
	private Integer location;	
	private java.lang.String url;	
	private java.lang.Long createTime;	

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setSavename(java.lang.String savename) {
		this.savename = savename;
	}
	
	public java.lang.String getSavename() {
		return this.savename;
	}
	public void setSavepath(java.lang.String savepath) {
		this.savepath = savepath;
	}
	
	public java.lang.String getSavepath() {
		return this.savepath;
	}
	public void setExt(java.lang.String ext) {
		this.ext = ext;
	}
	
	public java.lang.String getExt() {
		return this.ext;
	}
	public void setMime(java.lang.String mime) {
		this.mime = mime;
	}
	
	public java.lang.String getMime() {
		return this.mime;
	}
	public void setSize(java.lang.Integer size) {
		this.size = size;
	}
	
	public java.lang.Integer getSize() {
		return this.size;
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
	public void setLocation(Integer location) {
		this.location = location;
	}
	
	public Integer getLocation() {
		return this.location;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}

}
