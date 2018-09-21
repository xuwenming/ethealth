/*
 * @author John
 * @date - 2017-11-08
 */

package com.mobian.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_member_doctor_level")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberDoctorLevel implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberDoctorLevel";
	public static final String ALIAS_ID = "医生职称表ID";
	public static final String ALIAS_NAME = "职称名称";
	public static final String ALIAS_REMARK = "备注，预留字段";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "更新人";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "是否删除 1是 0 否";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@Length(max=100)
	private java.lang.String name;
	//@Length(max=500)
	private java.lang.String remark;
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
	private BigDecimal appointmentCost;
	private BigDecimal consultationCost;
	private Integer seq;
	//columns END


		public TfdMemberDoctorLevel(){
		}
		public TfdMemberDoctorLevel(Integer id) {
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
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

	@Column(name = "appointment_cost", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public BigDecimal getAppointmentCost() {
		return appointmentCost;
	}

	public void setAppointmentCost(BigDecimal appointmentCost) {
		this.appointmentCost = appointmentCost;
	}

	@Column(name = "consultation_cost", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public BigDecimal getConsultationCost() {
		return consultationCost;
	}

	public void setConsultationCost(BigDecimal consultationCost) {
		this.consultationCost = consultationCost;
	}

	@Column(name = "seq", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Remark",getRemark())
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
		if(obj instanceof FdMemberDoctorLevel == false) return false;
		if(this == obj) return true;
		FdMemberDoctorLevel other = (FdMemberDoctorLevel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

