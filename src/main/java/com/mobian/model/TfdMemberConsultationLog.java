/*
 * @author John
 * @date - 2018-01-08
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_member_consultation_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberConsultationLog implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberConsultationLog";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_MTYPE = "消息类型：text、image、audio";
	public static final String ALIAS_FROM_USER_ID = "发送人";
	public static final String ALIAS_TO_USER_ID = "接收人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "是否删除 0 否 1 是";
	public static final String ALIAS_CONTENT = "聊天记录";
	public static final String ALIAS_SENDER_TYPE = "发送方类型1患者 2医生";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@Length(max=18)
	private java.lang.String mtype;
	//
	private java.lang.Integer fromUserId;
	//
	private java.lang.Integer toUserId;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//@Length(max=65535)
	private java.lang.String content;
	//
	private java.lang.Integer senderType;
	//columns END


		public TfdMemberConsultationLog(){
		}
		public TfdMemberConsultationLog(Integer id) {
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
	
	@Column(name = "mtype", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getMtype() {
		return this.mtype;
	}
	
	public void setMtype(java.lang.String mtype) {
		this.mtype = mtype;
	}
	
	@Column(name = "from_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFromUserId() {
		return this.fromUserId;
	}
	
	public void setFromUserId(java.lang.Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	@Column(name = "to_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getToUserId() {
		return this.toUserId;
	}
	
	public void setToUserId(java.lang.Integer toUserId) {
		this.toUserId = toUserId;
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
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "sender_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSenderType() {
		return this.senderType;
	}
	
	public void setSenderType(java.lang.Integer senderType) {
		this.senderType = senderType;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Mtype",getMtype())
			.append("FromUserId",getFromUserId())
			.append("ToUserId",getToUserId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("Content",getContent())
			.append("SenderType",getSenderType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberConsultationLog == false) return false;
		if(this == obj) return true;
		FdMemberConsultationLog other = (FdMemberConsultationLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

