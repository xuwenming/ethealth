package com.mobian.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class FdMember implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String username;	
	private java.lang.String password;	
	private java.lang.Integer score;	
	private java.lang.String email;	
	private java.lang.Integer login;	
	private java.lang.String mobile;	
	private java.lang.Long regTime;	
	private java.lang.Long regIp;	
	private java.lang.Long lastLoginTime;	
	private java.lang.Long lastLoginIp;	
	private java.lang.Long updateTime;	
	private Integer status;	
	private Integer isAdmin;	
	private Integer groupid;	
	private Long amount;	
	private Integer modelid;	
	private java.lang.Boolean message;	
	private java.lang.String pic;
	private java.lang.String headImage;
	private java.lang.Integer doctorId;	
	private java.lang.Integer groupId;	
	private java.lang.Long vipEndTime;
	private java.lang.String hxPassword;
	private java.lang.Boolean hxStatus;

	private FdCustomer customer;
	private FdMemberDoctor memberDoctor;
	private FdPatient patient;
	private String picUrl;
	private String groupName;
	private String tokenId;

	private String realName;
	private java.lang.Integer sex;
	private java.lang.String birthday;
	private java.lang.String speciality;
	private java.lang.String introduce;

	private String statusArr;
	private int unreadMsgCount;


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setScore(java.lang.Integer score) {
		this.score = score;
	}
	
	public java.lang.Integer getScore() {
		return this.score;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setLogin(java.lang.Integer login) {
		this.login = login;
	}
	
	public java.lang.Integer getLogin() {
		return this.login;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setRegTime(java.lang.Long regTime) {
		this.regTime = regTime;
	}
	
	public java.lang.Long getRegTime() {
		return this.regTime;
	}
	public void setRegIp(java.lang.Long regIp) {
		this.regIp = regIp;
	}
	
	public java.lang.Long getRegIp() {
		return this.regIp;
	}
	public void setLastLoginTime(java.lang.Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public java.lang.Long getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setLastLoginIp(java.lang.Long lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	public java.lang.Long getLastLoginIp() {
		return this.lastLoginIp;
	}
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Integer getIsAdmin() {
		return this.isAdmin;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
	public Integer getGroupid() {
		return this.groupid;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getAmount() {
		return this.amount;
	}
	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}
	
	public Integer getModelid() {
		return this.modelid;
	}
	public void setMessage(java.lang.Boolean message) {
		this.message = message;
	}
	
	public java.lang.Boolean getMessage() {
		return this.message;
	}
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	
	public java.lang.String getPic() {
		return this.pic;
	}
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	public void setVipEndTime(java.lang.Long vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	
	public java.lang.Long getVipEndTime() {
		return this.vipEndTime;
	}

	public String getHxPassword() {
		return hxPassword;
	}

	public void setHxPassword(String hxPassword) {
		this.hxPassword = hxPassword;
	}

	public Boolean getHxStatus() {
		return hxStatus;
	}

	public void setHxStatus(Boolean hxStatus) {
		this.hxStatus = hxStatus;
	}

	public FdCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(FdCustomer customer) {
		this.customer = customer;
	}

	public FdMemberDoctor getMemberDoctor() {
		return memberDoctor;
	}

	public void setMemberDoctor(FdMemberDoctor memberDoctor) {
		this.memberDoctor = memberDoctor;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStatusArr() {
		return statusArr;
	}

	public void setStatusArr(String statusArr) {
		this.statusArr = statusArr;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public FdPatient getPatient() {
		return patient;
	}

	public void setPatient(FdPatient patient) {
		this.patient = patient;
	}

	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}

	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}
}
