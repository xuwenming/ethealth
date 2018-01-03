/*
 * @author John
 * @date - 2017-12-18
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_message")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMessage implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMessage";
	public static final String ALIAS_ID = "消息ID";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_CONTENT = "正文";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除1:是 0:否";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_MTYPE = "消息类型";
	public static final String ALIAS_IS_READ = "是否已读1:是 0:否";
	public static final String ALIAS_URL = "正文链接";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@Length(max=100)
	private java.lang.String title;
	//@Length(max=65535)
	private java.lang.String content;
	//
	private java.lang.Integer createBy;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Integer updateBy;
	//
	private java.lang.Long updateTime;
	//
	private java.lang.String status;
	private java.lang.Boolean isdeleted;
	//
	private java.lang.Integer userId;
	//@Length(max=6)
	private java.lang.String mtype;
	//
	private java.lang.Boolean isRead;
	//@Length(max=100)
	private java.lang.String url;
	//
	private java.util.Date startDate;
	//
	private java.util.Date endDate;

	private Integer consumerType;
	private Boolean isPushed;
	private String pushContent;
	//columns END


		public TfdMessage(){
		}
		public TfdMessage(Integer id) {
			this.id = id;
		}
	

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "create_by", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateBy() {
		return this.createBy;
	}
	
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_by", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateBy() {
		return this.updateBy;
	}
	
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "isdeleted", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "mtype", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getMtype() {
		return this.mtype;
	}
	
	public void setMtype(java.lang.String mtype) {
		this.mtype = mtype;
	}
	
	@Column(name = "is_read", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getIsRead() {
		return this.isRead;
	}
	
	public void setIsRead(java.lang.Boolean isRead) {
		this.isRead = isRead;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	@Column(name = "start_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}


	@Column(name = "end_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "consumer_type", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(Integer consumerType) {
		this.consumerType = consumerType;
	}

	@Column(name = "is_pushed", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public Boolean getIsPushed() {
		return isPushed;
	}

	public void setIsPushed(Boolean isPushed) {
		this.isPushed = isPushed;
	}

	@Column(name = "push_content", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("UserId",getUserId())
			.append("Mtype",getMtype())
			.append("IsRead",getIsRead())
			.append("Url",getUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMessage == false) return false;
		if(this == obj) return true;
		FdMessage other = (FdMessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

