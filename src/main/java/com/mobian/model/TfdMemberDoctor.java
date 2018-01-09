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
@Table(name = "fd_member_doctor")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberDoctor implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberDoctor";
	public static final String ALIAS_ID = "用户ID";
	public static final String ALIAS_LEVEL = "职称ID";
	public static final String ALIAS_HOSPITAL = "所在医院ID";
	public static final String ALIAS_DEPARTMENT = "所在科室ID";
	public static final String ALIAS_EDUCATION = "学历配置名称";
	public static final String ALIAS_CONSULTING_HOUR = "专家门诊时间";
	public static final String ALIAS_SPECIAL_HOUR = "特需门诊时间";
	public static final String ALIAS_SPECIALITY = "擅长";
	public static final String ALIAS_INTRODUCE = "自我介绍";
	public static final String ALIAS_PICS = "证明图片ID，多个用,分开";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_SORT = "医生排序";
	public static final String ALIAS_GROUP_ID = "团队ID";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATE_TIME = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	private java.lang.Integer id;
	private java.lang.Integer level;
	private java.lang.Integer hospital;
	private java.lang.Integer department;
	private java.lang.String education;
	private java.lang.String consultingHour;
	private java.lang.String specialHour;
	private java.lang.String speciality;
	private java.lang.String introduce;
	private java.lang.String pics;
	private java.lang.Integer createBy;
	private java.util.Date createTime;
	private java.lang.Integer updateBy;
	private java.util.Date updateTime;
	private java.lang.Integer seq;
	private Boolean isBest;
	private java.lang.Integer groupId;
	private Boolean acceptAppointment;
	private Boolean acceptConsultation;
	private String departmentName;

	public TfdMemberDoctor(){
	}
	public TfdMemberDoctor(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "level", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}
	
	@Column(name = "hospital", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getHospital() {
		return this.hospital;
	}
	
	public void setHospital(java.lang.Integer hospital) {
		this.hospital = hospital;
	}
	
	@Column(name = "department", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDepartment() {
		return this.department;
	}
	
	public void setDepartment(java.lang.Integer department) {
		this.department = department;
	}
	
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.String education) {
		this.education = education;
	}
	
	@Column(name = "consulting_hour", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getConsultingHour() {
		return this.consultingHour;
	}
	
	public void setConsultingHour(java.lang.String consultingHour) {
		this.consultingHour = consultingHour;
	}
	
	@Column(name = "special_hour", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getSpecialHour() {
		return this.specialHour;
	}
	
	public void setSpecialHour(java.lang.String specialHour) {
		this.specialHour = specialHour;
	}
	
	@Column(name = "speciality", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getSpeciality() {
		return this.speciality;
	}
	
	public void setSpeciality(java.lang.String speciality) {
		this.speciality = speciality;
	}
	
	@Column(name = "introduce", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
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
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
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
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "sort", unique = true, nullable = false, length = 10)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "group_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "accept_appointment", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Boolean getAcceptAppointment() {
		return acceptAppointment;
	}

	public void setAcceptAppointment(Boolean acceptAppointment) {
		this.acceptAppointment = acceptAppointment;
	}

	@Column(name = "accept_consultation", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Boolean getAcceptConsultation() {
		return acceptConsultation;
	}

	public void setAcceptConsultation(Boolean acceptConsultation) {
		this.acceptConsultation = acceptConsultation;
	}

	@Column(name = "department_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "is_best", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Boolean getIsBest() {
		return isBest;
	}

	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}

	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberDoctor == false) return false;
		if(this == obj) return true;
		FdMemberDoctor other = (FdMemberDoctor)obj;
		return new EqualsBuilder()
			.isEquals();
	}*/
}

