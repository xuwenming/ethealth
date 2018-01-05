/*
 * @author John
 * @date - 2017-11-28
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_member_doctor_sh")
@DynamicInsert(true)
@DynamicUpdate(true)
@IdClass(value=TfdMemberDoctorShId.class)
public class TfdMemberDoctorSh implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberDoctorSh";
	public static final String ALIAS_ID = "用户ID";
	public static final String ALIAS_LEVEL = "职称";
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
	public static final String ALIAS_STATUS = "审核状态 0审核不通过 1 待审核 2审核通过";
	public static final String ALIAS_REASON = "不通过原因";
	public static final String ALIAS_REAL_NAME = "真实姓名";
	public static final String ALIAS_SEX = "1男2女 0未知";
	public static final String ALIAS_BIRTHDAY = "birthday";
	public static final String ALIAS_GROUP_ID = "团队ID";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATE_TIME = com.mobian.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer level;
	//
	private java.lang.Integer hospital;
	//
	private java.lang.Integer department;
	//@Length(max=100)
	private java.lang.String education;
	//@Length(max=500)
	private java.lang.String consultingHour;
	//@Length(max=500)
	private java.lang.String specialHour;
	//@Length(max=500)
	private java.lang.String speciality;
	//@Length(max=500)
	private java.lang.String introduce;
	//@Length(max=500)
	private java.lang.String pics;
	//
	private java.lang.Integer createBy;
	//
	private java.util.Date createTime;
	//
	private java.lang.Integer updateBy;
	//
	private java.util.Date updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//@Length(max=500)
	private java.lang.String reason;
	//@Length(max=100)
	private java.lang.String realName;
	//
	private java.lang.Integer sex;
	//
	private java.lang.Long birthday;
	//
	private java.lang.Integer groupId;
	private String hospitalName;
	private String departmentName;
	private Integer auditType;
	private String email;
	//columns END


		public TfdMemberDoctorSh(){
		}
		public TfdMemberDoctorSh(Integer id) {
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

	@Id
	@Column(name = "audit_type", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
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
	
	@Column(name = "introduce", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
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
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "reason", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getReason() {
		return this.reason;
	}
	
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}
	
	@Column(name = "real_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	@Column(name = "sex", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
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
	
	@Column(name = "group_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "hospital_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@Column(name = "department_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Level",getLevel())
			.append("Hospital",getHospital())
			.append("Department",getDepartment())
			.append("Education",getEducation())
			.append("ConsultingHour",getConsultingHour())
			.append("SpecialHour",getSpecialHour())
			.append("Speciality",getSpeciality())
			.append("Introduce",getIntroduce())
			.append("Pics",getPics())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("Reason",getReason())
			.append("RealName",getRealName())
			.append("Sex",getSex())
			.append("Birthday",getBirthday())
			.append("GroupId",getGroupId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberDoctorSh == false) return false;
		if(this == obj) return true;
		FdMemberDoctorSh other = (FdMemberDoctorSh)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

