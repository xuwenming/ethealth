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
@Table(name = "fd_member")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMember implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMember";
	public static final String ALIAS_ID = "用户ID";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_SCORE = "用户积分";
	public static final String ALIAS_EMAIL = "用户邮箱";
	public static final String ALIAS_LOGIN = "登录次数";
	public static final String ALIAS_MOBILE = "用户手机";
	public static final String ALIAS_REG_TIME = "注册时间";
	public static final String ALIAS_REG_IP = "注册IP";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_LAST_LOGIN_IP = "最后登录IP";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "数据状态0禁用，1启用，-1删除";
	public static final String ALIAS_IS_ADMIN = "0前台用户 1管理用户 2专家用户 3专家助手";
	public static final String ALIAS_GROUPID = "groupid";
	public static final String ALIAS_AMOUNT = "amount";
	public static final String ALIAS_MODELID = "modelid";
	public static final String ALIAS_MESSAGE = "message";
	public static final String ALIAS_PIC = "用户头像";
	public static final String ALIAS_DOCTOR_ID = "助手关联专家用户ID";
	public static final String ALIAS_GROUP_ID = "团队ID";
	public static final String ALIAS_VIP_END_TIME = "会员到期时间";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@NotBlank @Length(max=16)
	private java.lang.String username;
	//@NotBlank @Length(max=32)
	private java.lang.String password;
	//
	private java.lang.Integer score;
	//@Email @Length(max=32)
	private java.lang.String email;
	//
	private java.lang.Integer login;
	//@NotBlank @Length(max=15)
	private java.lang.String mobile;
	//
	private java.lang.Long regTime;
	//
	private java.lang.Long regIp;
	//
	private java.lang.Long lastLoginTime;
	//
	private java.lang.Long lastLoginIp;
	//
	private java.lang.Long updateTime;
	//@Max(127)
	private Integer status;
	//@Max(127)
	private Integer isAdmin;
	//@Max(127)
	private Integer groupid;
	//
	private Long amount;
	//@Max(32767)
	private Integer modelid;
	//
	private java.lang.Boolean message;
	//@Length(max=256)
	private java.lang.String pic;
	private java.lang.String headImage;
	//
	private java.lang.Integer doctorId;
	//
	private java.lang.Integer groupId;
	//
	private java.lang.Long vipEndTime;

	private java.lang.String hxPassword;
	private java.lang.Boolean hxStatus;
	//columns END


		public TfdMember(){
		}
		public TfdMember(Integer id) {
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
	
	@Column(name = "username", unique = true, nullable = false, insertable = true, updatable = true, length = 16)
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	@Column(name = "score", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.lang.Integer getScore() {
		return this.score;
	}
	
	public void setScore(java.lang.Integer score) {
		this.score = score;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	@Column(name = "login", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getLogin() {
		return this.login;
	}
	
	public void setLogin(java.lang.Integer login) {
		this.login = login;
	}
	
	@Column(name = "mobile", unique = false, nullable = false, insertable = true, updatable = true, length = 15)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "reg_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.Long getRegTime() {
		return this.regTime;
	}
	
	public void setRegTime(java.lang.Long regTime) {
		this.regTime = regTime;
	}
	
	@Column(name = "reg_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getRegIp() {
		return this.regIp;
	}
	
	public void setRegIp(java.lang.Long regIp) {
		this.regIp = regIp;
	}
	
	@Column(name = "last_login_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.Long getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.lang.Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Column(name = "last_login_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(java.lang.Long lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "is_admin", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Column(name = "groupid", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getGroupid() {
		return this.groupid;
	}
	
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
	@Column(name = "amount", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
	public Long getAmount() {
		return this.amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	@Column(name = "modelid", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public Integer getModelid() {
		return this.modelid;
	}
	
	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}
	
	@Column(name = "message", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getMessage() {
		return this.message;
	}
	
	public void setMessage(java.lang.Boolean message) {
		this.message = message;
	}
	
	@Column(name = "pic", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getPic() {
		return this.pic;
	}
	
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}

	@Column(name = "head_image", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@Column(name = "doctor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	@Column(name = "group_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	
	@Column(name = "vip_end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.Long getVipEndTime() {
		return this.vipEndTime;
	}
	
	public void setVipEndTime(java.lang.Long vipEndTime) {
		this.vipEndTime = vipEndTime;
	}

	@Column(name = "hx_password", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getHxPassword() {
		return hxPassword;
	}

	public void setHxPassword(String hxPassword) {
		this.hxPassword = hxPassword;
	}
	@Column(name = "hx_status", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public Boolean getHxStatus() {
		return hxStatus;
	}

	public void setHxStatus(Boolean hxStatus) {
		this.hxStatus = hxStatus;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.append("Score",getScore())
			.append("Email",getEmail())
			.append("Login",getLogin())
			.append("Mobile",getMobile())
			.append("RegTime",getRegTime())
			.append("RegIp",getRegIp())
			.append("LastLoginTime",getLastLoginTime())
			.append("LastLoginIp",getLastLoginIp())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("IsAdmin",getIsAdmin())
			.append("Groupid",getGroupid())
			.append("Amount",getAmount())
			.append("Modelid",getModelid())
			.append("Message",getMessage())
			.append("Pic",getPic())
			.append("DoctorId",getDoctorId())
			.append("GroupId",getGroupId())
			.append("VipEndTime",getVipEndTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMember == false) return false;
		if(this == obj) return true;
		FdMember other = (FdMember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

