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
@Table(name = "fd_message_read_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMessageReadLog implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMessageReadLog";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除1:是 0:否";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_MESSAGE_ID = "消息Id";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//
	private java.lang.Boolean status;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Integer messageId;
	//columns END


		public TfdMessageReadLog(){
		}
		public TfdMessageReadLog(Integer id) {
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
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "message_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMessageId() {
		return this.messageId;
	}
	
	public void setMessageId(java.lang.Integer messageId) {
		this.messageId = messageId;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("UserId",getUserId())
			.append("MessageId",getMessageId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMessageReadLog == false) return false;
		if(this == obj) return true;
		FdMessageReadLog other = (FdMessageReadLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

