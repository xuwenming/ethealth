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
@Table(name = "fd_hospital_dept")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdHospitalDept implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdHospitalDept";
	public static final String ALIAS_ID = "医院科室表ID";
	public static final String ALIAS_HOSPITAL_ID = "所属医院ID";
	public static final String ALIAS_NAME = "科室名称";
	public static final String ALIAS_CREATE_BY = "创建人ID";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人ID";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1 是 0 否";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer hospitalId;
	//@Length(max=100)
	private java.lang.String name;
	//@Length(max=255)
	private java.lang.String icon;
	//
	private java.lang.Integer pid;
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


		public TfdHospitalDept(){
		}
		public TfdHospitalDept(Integer id) {
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}

	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getIcon() {
		return this.icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	@Column(name = "pid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPid() {
		return this.pid;
	}

	public void setPid(java.lang.Integer pid) {
		this.pid = pid;
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
			.append("HospitalId",getHospitalId())
			.append("Name",getName())
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
		if(obj instanceof FdHospitalDept == false) return false;
		if(this == obj) return true;
		FdHospitalDept other = (FdHospitalDept)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

