package com.mobian.pageModel;

import com.mobian.listener.Application;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMessage implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String title;	
	private java.lang.String content;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.Boolean isdeleted;
	private java.lang.String status;
	private java.lang.Integer userId;
	private java.lang.String mtype;	
	private java.lang.Boolean isRead;	
	private java.lang.String url;
	private Date startDate;
	private Date endDate;
	private Integer consumerType;

	private String startDateStr;
	private String endDateStr;
	private String alias;

	private Boolean isCustom; // 推送消息是否为自定义消息，true是
	private String customType; // 自定义消息类型

	public String getStatusZh() {
		return Application.getString(status);
	}
	public String getMtypeZh() {
		return Application.getString(mtype);
	}

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
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
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

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setMtype(java.lang.String mtype) {
		this.mtype = mtype;
	}
	
	public java.lang.String getMtype() {
		return this.mtype;
	}
	public void setIsRead(java.lang.Boolean isRead) {
		this.isRead = isRead;
	}
	
	public java.lang.Boolean getIsRead() {
		return this.isRead;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(Integer consumerType) {
		this.consumerType = consumerType;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}
}
