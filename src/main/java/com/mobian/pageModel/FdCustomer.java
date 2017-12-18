package com.mobian.pageModel;

import com.mobian.absx.F;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;

import java.util.Calendar;

@SuppressWarnings("serial")
public class FdCustomer implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Long userId;	
	private java.lang.String realName;	
	private java.lang.String phone;	
	private java.lang.Long province;	
	private java.lang.Long city;	
	private java.lang.Long county;	
	private java.lang.String addr;	
	private java.lang.String qq;	
	private java.lang.Integer sex;
	private java.lang.Long birthday;	
	private java.lang.Integer groupId;	
	private java.lang.Integer point;	
	private java.lang.String messageIds;	
	private java.lang.String prop;	
	private java.lang.Float balance;	
	private java.lang.String custom;	
	private java.lang.Long checkinTime;	
	private java.lang.String nickName;	

	public Integer getAge() {
		if(birthday != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(birthday);
			return DateUtil.getAgeByBirthday(c.getTime());
		}
		return null;
	}

	public String getBirthdayStr() {
		if(!F.empty(birthday)) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(birthday);
			return DateUtil.format(c.getTime(), Constants.DATE_FORMAT_YMD);
		}

		return null;
	}

	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}

	
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setProvince(java.lang.Long province) {
		this.province = province;
	}
	
	public java.lang.Long getProvince() {
		return this.province;
	}
	public void setCity(java.lang.Long city) {
		this.city = city;
	}
	
	public java.lang.Long getCity() {
		return this.city;
	}
	public void setCounty(java.lang.Long county) {
		this.county = county;
	}
	
	public java.lang.Long getCounty() {
		return this.county;
	}
	public void setAddr(java.lang.String addr) {
		this.addr = addr;
	}
	
	public java.lang.String getAddr() {
		return this.addr;
	}
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	
	public java.lang.String getQq() {
		return this.qq;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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
	public void setPoint(java.lang.Integer point) {
		this.point = point;
	}
	
	public java.lang.Integer getPoint() {
		return this.point;
	}
	public void setMessageIds(java.lang.String messageIds) {
		this.messageIds = messageIds;
	}
	
	public java.lang.String getMessageIds() {
		return this.messageIds;
	}
	public void setProp(java.lang.String prop) {
		this.prop = prop;
	}
	
	public java.lang.String getProp() {
		return this.prop;
	}
	public void setBalance(java.lang.Float balance) {
		this.balance = balance;
	}
	
	public java.lang.Float getBalance() {
		return this.balance;
	}
	public void setCustom(java.lang.String custom) {
		this.custom = custom;
	}
	
	public java.lang.String getCustom() {
		return this.custom;
	}
	public void setCheckinTime(java.lang.Long checkinTime) {
		this.checkinTime = checkinTime;
	}
	
	public java.lang.Long getCheckinTime() {
		return this.checkinTime;
	}
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}

}
