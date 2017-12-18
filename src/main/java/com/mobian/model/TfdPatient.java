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
@Table(name = "fd_patient")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdPatient implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdPatient";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除1:是 0:否";
	public static final String ALIAS_REAL_NAME = "姓名";
	public static final String ALIAS_SEX = "1男2女 0未知";
	public static final String ALIAS_BIRTHDAY = "出生日期";
	public static final String ALIAS_RELATION = "与患者关系";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer userId;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//
	private java.lang.Boolean status;
	//@Length(max=50)
	private java.lang.String realName;
	//
	private java.lang.Integer sex;
	//
	private java.lang.Long birthday;
	//@Length(max=6)
	private java.lang.String relation;
	//columns END


		public TfdPatient(){
		}
		public TfdPatient(Integer userId) {
			this.userId = userId;
		}
	

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
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
	
	@Column(name = "real_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	@Column(name = "sex", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}
	
	@Column(name = "birthday", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(java.lang.Long birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "relation", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getRelation() {
		return this.relation;
	}
	
	public void setRelation(java.lang.String relation) {
		this.relation = relation;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("RealName",getRealName())
			.append("Sex",getSex())
			.append("Birthday",getBirthday())
			.append("Relation",getRelation())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdPatient == false) return false;
		if(this == obj) return true;
		FdPatient other = (FdPatient)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}*/
}

