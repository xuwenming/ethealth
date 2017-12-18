package com.mobian.pageModel;

import com.mobian.absx.F;
import com.mobian.listener.Application;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class FdPatient implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer userId;	
	private java.lang.Long createTime;	
	private java.lang.Long updateTime;	
	private java.lang.Boolean status;	
	private java.lang.String realName;	
	private java.lang.Integer sex;	
	private java.lang.Long birthday;	
	private java.lang.String relation;	

	public String getRelationZh() {
		return Application.getString(relation);
	}

	public String getBirthdayStr() {
		if(!F.empty(birthday)) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(birthday);
			return DateUtil.format(c.getTime(), Constants.DATE_FORMAT_YMD);
		}

		return null;
	}

	public Integer getAge() {
		if(birthday != null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(birthday);
			return DateUtil.getAgeByBirthday(c.getTime());
		}
		return null;
	}

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}

	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}
	
	public java.lang.Integer getSex() {
		return this.sex;
	}
	public void setBirthday(java.lang.Long birthday) {
		this.birthday = birthday;
	}
	
	public java.lang.Long getBirthday() {
		return this.birthday;
	}
	public void setRelation(java.lang.String relation) {
		this.relation = relation;
	}
	
	public java.lang.String getRelation() {
		return this.relation;
	}

}
