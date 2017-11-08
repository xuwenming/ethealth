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
@Table(name = "fd_customer")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdCustomer implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdCustomer";
	public static final String ALIAS_USER_ID = "userId";
	public static final String ALIAS_REAL_NAME = "realName";
	public static final String ALIAS_PHONE = "phone";
	public static final String ALIAS_PROVINCE = "province";
	public static final String ALIAS_CITY = "city";
	public static final String ALIAS_COUNTY = "county";
	public static final String ALIAS_ADDR = "addr";
	public static final String ALIAS_QQ = "qq";
	public static final String ALIAS_SEX = "1男2女 0未知";
	public static final String ALIAS_BIRTHDAY = "birthday";
	public static final String ALIAS_GROUP_ID = "groupId";
	public static final String ALIAS_POINT = "point";
	public static final String ALIAS_MESSAGE_IDS = "messageIds";
	public static final String ALIAS_PROP = "prop";
	public static final String ALIAS_BALANCE = "balance";
	public static final String ALIAS_CUSTOM = "custom";
	public static final String ALIAS_CHECKIN_TIME = "checkinTime";
	public static final String ALIAS_NICK_NAME = "用户昵称";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Long userId;
	//@Length(max=50)
	private java.lang.String realName;
	//@Length(max=50)
	private java.lang.String phone;
	//
	private java.lang.Long province;
	//
	private java.lang.Long city;
	//
	private java.lang.Long county;
	//@Length(max=250)
	private java.lang.String addr;
	//@Length(max=15)
	private java.lang.String qq;
	//
	private java.lang.Boolean sex;
	//
	private java.lang.Long birthday;
	//
	private java.lang.Integer groupId;
	//
	private java.lang.Integer point;
	//@Length(max=65535)
	private java.lang.String messageIds;
	//@Length(max=65535)
	private java.lang.String prop;
	//
	private java.lang.Float balance;
	//@Length(max=255)
	private java.lang.String custom;
	//
	private java.lang.Long checkinTime;
	//@Length(max=50)
	private java.lang.String nickName;
	//columns END


		public TfdCustomer(){
		}
		public TfdCustomer(Long userId) {
			this.userId = userId;
		}
	

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false, length = 19)
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	@Column(name = "real_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}
	
	@Column(name = "phone", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProvince() {
		return this.province;
	}
	
	public void setProvince(java.lang.Long province) {
		this.province = province;
	}
	
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.Long city) {
		this.city = city;
	}
	
	@Column(name = "county", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCounty() {
		return this.county;
	}
	
	public void setCounty(java.lang.Long county) {
		this.county = county;
	}
	
	@Column(name = "addr", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public java.lang.String getAddr() {
		return this.addr;
	}
	
	public void setAddr(java.lang.String addr) {
		this.addr = addr;
	}
	
	@Column(name = "qq", unique = false, nullable = true, insertable = true, updatable = true, length = 15)
	public java.lang.String getQq() {
		return this.qq;
	}
	
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	
	@Column(name = "sex", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Boolean sex) {
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
	
	@Column(name = "point", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPoint() {
		return this.point;
	}
	
	public void setPoint(java.lang.Integer point) {
		this.point = point;
	}
	
	@Column(name = "message_ids", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getMessageIds() {
		return this.messageIds;
	}
	
	public void setMessageIds(java.lang.String messageIds) {
		this.messageIds = messageIds;
	}
	
	@Column(name = "prop", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getProp() {
		return this.prop;
	}
	
	public void setProp(java.lang.String prop) {
		this.prop = prop;
	}
	
	@Column(name = "balance", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Float getBalance() {
		return this.balance;
	}
	
	public void setBalance(java.lang.Float balance) {
		this.balance = balance;
	}
	
	@Column(name = "custom", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCustom() {
		return this.custom;
	}
	
	public void setCustom(java.lang.String custom) {
		this.custom = custom;
	}
	
	@Column(name = "checkin_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCheckinTime() {
		return this.checkinTime;
	}
	
	public void setCheckinTime(java.lang.Long checkinTime) {
		this.checkinTime = checkinTime;
	}
	
	@Column(name = "nick_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("RealName",getRealName())
			.append("Phone",getPhone())
			.append("Province",getProvince())
			.append("City",getCity())
			.append("County",getCounty())
			.append("Addr",getAddr())
			.append("Qq",getQq())
			.append("Sex",getSex())
			.append("Birthday",getBirthday())
			.append("GroupId",getGroupId())
			.append("Point",getPoint())
			.append("MessageIds",getMessageIds())
			.append("Prop",getProp())
			.append("Balance",getBalance())
			.append("Custom",getCustom())
			.append("CheckinTime",getCheckinTime())
			.append("NickName",getNickName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdCustomer == false) return false;
		if(this == obj) return true;
		FdCustomer other = (FdCustomer)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}*/
}

