/*
 * @author John
 * @date - 2017-11-28
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_member_appointment_comment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMemberAppointmentComment implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMemberAppointmentComment";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_DOCTOR_ID = "医生id";
	public static final String ALIAS_APPOINTMENT_ID = "预约订单id";
	public static final String ALIAS_USER_ID = "评论人";
	public static final String ALIAS_CREATE_TIME = "评论时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1 是 0 否";
	public static final String ALIAS_EFFECT = "治疗效果";
	public static final String ALIAS_ATTITUDE = "服务态度";
	public static final String ALIAS_DISEASE = "看什么病";
	public static final String ALIAS_OBJECTIVE = "看病目的";
	public static final String ALIAS_OBJECTIVE_OTHER = "看病目的_其他";
	public static final String ALIAS_THERAPY = "治疗方式";
	public static final String ALIAS_THERAPY_OTHER = "治疗方式_其他";
	public static final String ALIAS_COMMENT = "评价内容";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//
	private java.lang.Integer doctorId;
	//
	private java.lang.Integer appointmentId;
	//
	private java.lang.Integer userId;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//@Length(max=2)
	private java.lang.String status;
	//
	private java.lang.Float effect;
	//
	private java.lang.Float attitude;
	//@Length(max=100)
	private java.lang.String disease;
	//@Length(max=6)
	private java.lang.String objective;
	//@Length(max=100)
	private java.lang.String objectiveOther;
	//@Length(max=6)
	private java.lang.String therapy;
	//@Length(max=100)
	private java.lang.String therapyOther;
	//@Length(max=300)
	private java.lang.String comment;
	//columns END


		public TfdMemberAppointmentComment(){
		}
		public TfdMemberAppointmentComment(Integer id) {
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
	
	@Column(name = "doctor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDoctorId() {
		return this.doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	@Column(name = "appointment_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAppointmentId() {
		return this.appointmentId;
	}
	
	public void setAppointmentId(java.lang.Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
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
	
	@Column(name = "effect", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getEffect() {
		return this.effect;
	}
	
	public void setEffect(java.lang.Float effect) {
		this.effect = effect;
	}
	
	@Column(name = "attitude", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getAttitude() {
		return this.attitude;
	}
	
	public void setAttitude(java.lang.Float attitude) {
		this.attitude = attitude;
	}
	
	@Column(name = "disease", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDisease() {
		return this.disease;
	}
	
	public void setDisease(java.lang.String disease) {
		this.disease = disease;
	}
	
	@Column(name = "objective", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getObjective() {
		return this.objective;
	}
	
	public void setObjective(java.lang.String objective) {
		this.objective = objective;
	}
	
	@Column(name = "objective_other", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getObjectiveOther() {
		return this.objectiveOther;
	}
	
	public void setObjectiveOther(java.lang.String objectiveOther) {
		this.objectiveOther = objectiveOther;
	}
	
	@Column(name = "therapy", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getTherapy() {
		return this.therapy;
	}
	
	public void setTherapy(java.lang.String therapy) {
		this.therapy = therapy;
	}
	
	@Column(name = "therapy_other", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTherapyOther() {
		return this.therapyOther;
	}
	
	public void setTherapyOther(java.lang.String therapyOther) {
		this.therapyOther = therapyOther;
	}
	
	@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getComment() {
		return this.comment;
	}
	
	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DoctorId",getDoctorId())
			.append("AppointmentId",getAppointmentId())
			.append("UserId",getUserId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("Effect",getEffect())
			.append("Attitude",getAttitude())
			.append("Disease",getDisease())
			.append("Objective",getObjective())
			.append("ObjectiveOther",getObjectiveOther())
			.append("Therapy",getTherapy())
			.append("TherapyOther",getTherapyOther())
			.append("Comment",getComment())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMemberAppointmentComment == false) return false;
		if(this == obj) return true;
		FdMemberAppointmentComment other = (FdMemberAppointmentComment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

