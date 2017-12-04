/*
 * @author John
 * @date - 2017-12-04
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_member_consultation_friend")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberConsultationFriend implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberConsultationFriend";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_USER_ID = "病人id";
	public static final String ALIAS_DOCTOR_ID = "医生id";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 0 否 1 病人删除 2医生删除 3 互删";
	public static final String ALIAS_LAST_CONTENT = "最后聊天记录";
	public static final String ALIAS_LAST_TIME = "最后消息时间";
	
	//date formats
	public static final String FORMAT_LAST_TIME = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Integer doctorId;
	//
	private java.lang.Long createTime;
	private java.lang.Long updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//@Length(max=65535)
	private java.lang.String lastContent;
	//
	private java.util.Date lastTime;
	private Integer senderType;
	//columns END


		public TfdMemberConsultationFriend(){
		}
		public TfdMemberConsultationFriend(Integer id) {
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
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "doctor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
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
	
	@Column(name = "last_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getLastContent() {
		return this.lastContent;
	}
	
	public void setLastContent(java.lang.String lastContent) {
		this.lastContent = lastContent;
	}
	

	@Column(name = "last_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getLastTime() {
		return this.lastTime;
	}
	
	public void setLastTime(java.util.Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "sender_type", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getSenderType() {
		return senderType;
	}

	public void setSenderType(Integer senderType) {
		this.senderType = senderType;
	}
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("DoctorId",getDoctorId())
			.append("CreateTime",getCreateTime())
			.append("Status",getStatus())
			.append("LastContent",getLastContent())
			.append("LastTime",getLastTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberConsultationFriend == false) return false;
		if(this == obj) return true;
		FdMemberConsultationFriend other = (FdMemberConsultationFriend)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

