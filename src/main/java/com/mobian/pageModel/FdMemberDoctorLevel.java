package com.mobian.pageModel;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class FdMemberDoctorLevel implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;	
	private java.lang.String name;	
	private java.lang.String remark;	
	private java.lang.Integer createBy;	
	private java.lang.Long createTime;	
	private java.lang.Integer updateBy;	
	private java.lang.Long updateTime;	
	private java.lang.String status;

	private BigDecimal appointmentCost;
	private BigDecimal consultationCost;
	private Integer seq;

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	
	public java.lang.Integer getCreateBy() {
		return this.createBy;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	public java.lang.Integer getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}

	public BigDecimal getAppointmentCost() {
		return appointmentCost;
	}

	public void setAppointmentCost(BigDecimal appointmentCost) {
		this.appointmentCost = appointmentCost;
	}

	public BigDecimal getConsultationCost() {
		return consultationCost;
	}

	public void setConsultationCost(BigDecimal consultationCost) {
		this.consultationCost = consultationCost;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
