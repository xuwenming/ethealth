package com.mobian.pageModel;

import com.mobian.absx.F;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberDoctorSh implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

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
	private Date createTime;			
	private java.lang.Integer updateBy;	
	private Date updateTime;			
	private java.lang.String status;	
	private java.lang.String reason;	
	private java.lang.String realName;	
	private java.lang.Integer sex;
	private java.lang.Long birthday;	
	private java.lang.Integer groupId;
	private String hospitalName;
	private String departmentName;
	private Integer auditType;
	private String email;

	private String mobile;
	private String levelName;
	private String birthdayStr;
	private String headImage;

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}
	
	public java.lang.Integer getLevel() {
		return this.level;
	}
	public void setHospital(java.lang.Integer hospital) {
		this.hospital = hospital;
	}
	
	public java.lang.Integer getHospital() {
		return this.hospital;
	}
	public void setDepartment(java.lang.Integer department) {
		this.department = department;
	}
	
	public java.lang.Integer getDepartment() {
		return this.department;
	}
	public void setEducation(java.lang.String education) {
		this.education = education;
	}
	
	public java.lang.String getEducation() {
		return this.education;
	}
	public void setConsultingHour(java.lang.String consultingHour) {
		this.consultingHour = consultingHour;
	}
	
	public java.lang.String getConsultingHour() {
		return this.consultingHour;
	}
	public void setSpecialHour(java.lang.String specialHour) {
		this.specialHour = specialHour;
	}
	
	public java.lang.String getSpecialHour() {
		return this.specialHour;
	}
	public void setSpeciality(java.lang.String speciality) {
		this.speciality = speciality;
	}
	
	public java.lang.String getSpeciality() {
		return this.speciality;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	public void setPics(java.lang.String pics) {
		this.pics = pics;
	}
	
	public java.lang.String getPics() {
		return this.pics;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	
	public java.lang.Integer getCreateBy() {
		return this.createBy;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	public java.lang.Integer getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}
	
	public java.lang.String getReason() {
		return this.reason;
	}
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public void setBirthday(java.lang.Long birthday) {
		this.birthday = birthday;
	}
	
	public java.lang.Long getBirthday() {
		return this.birthday;
	}
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdayStr() {
		if(!F.empty(birthday)) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(birthday);
			return DateUtil.format(c.getTime(), Constants.DATE_FORMAT_YMD);
		}

		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
}
