/*
 * @author John
 * @date - 2017-11-08
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_doctor_group")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdDoctorGroup implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdDoctorGroup";
	public static final String ALIAS_ID = "用户ID";
	public static final String ALIAS_HOSPITAL_ID = "所在医院ID";
	public static final String ALIAS_DEPT_ID = "所在科室ID";
	public static final String ALIAS_GROUP_NAME = "团队名称";
	public static final String ALIAS_INTRODUCE = "团队介绍";
	public static final String ALIAS_PICS = "证明图片ID，多个用,分开";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1是 0 否";
	public static final String ALIAS_LEADER = "领队ID";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer hospitalId;
	//
	private java.lang.Integer deptId;
	//@Length(max=100)
	private java.lang.String groupName;
	//@Length(max=65535)
	private java.lang.String introduce;
	//@Length(max=500)
	private java.lang.String pics;
	//
	private java.lang.Integer createBy;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Integer updateBy;
	//
	private java.lang.Long updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//
	private java.lang.Integer leader;
	//columns END


		public TfdDoctorGroup(){
		}
		public TfdDoctorGroup(Integer id) {
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
	
	@Column(name = "hospital_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getHospitalId() {
		return this.hospitalId;
	}
	
	public void setHospitalId(java.lang.Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Column(name = "dept_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDeptId() {
		return this.deptId;
	}
	
	public void setDeptId(java.lang.Integer deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "group_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}
	
	@Column(name = "introduce", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	@Column(name = "pics", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPics() {
		return this.pics;
	}
	
	public void setPics(java.lang.String pics) {
		this.pics = pics;
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
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "leader", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getLeader() {
		return this.leader;
	}
	
	public void setLeader(java.lang.Integer leader) {
		this.leader = leader;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("HospitalId",getHospitalId())
			.append("DeptId",getDeptId())
			.append("GroupName",getGroupName())
			.append("Introduce",getIntroduce())
			.append("Pics",getPics())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("Leader",getLeader())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdDoctorGroup == false) return false;
		if(this == obj) return true;
		FdDoctorGroup other = (FdDoctorGroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

