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
@Table(name = "fd_hospital")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdHospital implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdHospital";
	public static final String ALIAS_ID = "医院ID";
	public static final String ALIAS_HOSPITAL_NAME = "医院名称";
	public static final String ALIAS_HOSPITAL_LEVEL = "医院等级配置名称";
	public static final String ALIAS_INTRODUCE = "医院简介";
	public static final String ALIAS_PROVINCE = "医院所在省ID";
	public static final String ALIAS_CITY = "医院所在市ID";
	public static final String ALIAS_COUNTY = "医院所在区ID";
	public static final String ALIAS_PICS = "医院图片ids";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1 是 0 否";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@Length(max=100)
	private java.lang.String hospitalName;
	//@Length(max=20)
	private java.lang.String hospitalLevel;
	//@Length(max=500)
	private java.lang.String introduce;
	//
	private java.lang.Integer province;
	//
	private java.lang.Integer city;
	//
	private java.lang.Integer county;
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
	//columns END


		public TfdHospital(){
		}
		public TfdHospital(Integer id) {
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
	
	@Column(name = "hospital_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getHospitalName() {
		return this.hospitalName;
	}
	
	public void setHospitalName(java.lang.String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Column(name = "hospital_level", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getHospitalLevel() {
		return this.hospitalLevel;
	}
	
	public void setHospitalLevel(java.lang.String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	
	@Column(name = "introduce", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProvince() {
		return this.province;
	}
	
	public void setProvince(java.lang.Integer province) {
		this.province = province;
	}
	
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.Integer city) {
		this.city = city;
	}
	
	@Column(name = "county", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCounty() {
		return this.county;
	}
	
	public void setCounty(java.lang.Integer county) {
		this.county = county;
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
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("HospitalName",getHospitalName())
			.append("HospitalLevel",getHospitalLevel())
			.append("Introduce",getIntroduce())
			.append("Province",getProvince())
			.append("City",getCity())
			.append("County",getCounty())
			.append("Pics",getPics())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdHospital == false) return false;
		if(this == obj) return true;
		FdHospital other = (FdHospital)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

