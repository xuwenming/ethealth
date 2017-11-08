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
@Table(name = "fd_doctor_opinion")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdDoctorOpinion implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdDoctorOpinion";
	public static final String ALIAS_ID = "文档ID";
	public static final String ALIAS_USER_ID = "专家ID";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_VIEW = "浏览量";
	public static final String ALIAS_COMMENT = "点赞数";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_VERIFY_STATUS = "审核状态0待审核，1通过，2拒绝";
	public static final String ALIAS_IS_UP = "上下架 0下架，1上架";
	public static final String ALIAS_STATUS = "数据状态0禁用，1启用，-1删除";
	public static final String ALIAS_PIC = "封面图";
	public static final String ALIAS_CONTENT = "正文";
	public static final String ALIAS_BRIEF = "简介";
	public static final String ALIAS_FILE = "附件";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@NotNull 
	private java.lang.Integer userId;
	//@NotBlank @Length(max=80)
	private java.lang.String title;
	//@NotNull 
	private java.lang.Integer view;
	//@NotNull 
	private java.lang.Integer comment;
	//@NotNull 
	private java.lang.Long createTime;
	//@NotNull 
	private java.lang.Long updateTime;
	//@NotNull @Max(127)
	private Integer verifyStatus;
	//@NotNull @Max(127)
	private Integer isUp;
	//@Max(127)
	private Integer status;
	//@Length(max=256)
	private java.lang.String pic;
	//@NotBlank @Length(max=65535)
	private java.lang.String content;
	//@Length(max=200)
	private java.lang.String brief;
	//
	private java.lang.Integer file;
	//columns END


		public TfdDoctorOpinion(){
		}
		public TfdDoctorOpinion(Integer id) {
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
	
	@Column(name = "user_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "title", unique = false, nullable = false, insertable = true, updatable = true, length = 80)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "view", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getView() {
		return this.view;
	}
	
	public void setView(java.lang.Integer view) {
		this.view = view;
	}
	
	@Column(name = "comment", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getComment() {
		return this.comment;
	}
	
	public void setComment(java.lang.Integer comment) {
		this.comment = comment;
	}
	
	@Column(name = "create_time", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_time", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "verify_status", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getVerifyStatus() {
		return this.verifyStatus;
	}
	
	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	
	@Column(name = "is_up", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getIsUp() {
		return this.isUp;
	}
	
	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "pic", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getPic() {
		return this.pic;
	}
	
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	
	@Column(name = "content", unique = false, nullable = false, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "brief", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getBrief() {
		return this.brief;
	}
	
	public void setBrief(java.lang.String brief) {
		this.brief = brief;
	}
	
	@Column(name = "file", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFile() {
		return this.file;
	}
	
	public void setFile(java.lang.Integer file) {
		this.file = file;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("Title",getTitle())
			.append("View",getView())
			.append("Comment",getComment())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("VerifyStatus",getVerifyStatus())
			.append("IsUp",getIsUp())
			.append("Status",getStatus())
			.append("Pic",getPic())
			.append("Content",getContent())
			.append("Brief",getBrief())
			.append("File",getFile())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdDoctorOpinion == false) return false;
		if(this == obj) return true;
		FdDoctorOpinion other = (FdDoctorOpinion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

