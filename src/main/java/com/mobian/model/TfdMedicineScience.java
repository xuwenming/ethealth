/*
 * @author John
 * @date - 2017-11-06
 */

package com.mobian.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "fd_medicine_science")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdMedicineScience implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdMedicineScience";
	public static final String ALIAS_ID = "文档ID";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_VIEW = "浏览量";
	public static final String ALIAS_COMMENT = "点赞数";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_IS_UP = "上下架 0下架，1上架";
	public static final String ALIAS_STATUS = "数据状态0禁用，1启用，-1删除";
	public static final String ALIAS_PIC = "封面图";
	public static final String ALIAS_CONTENT = "正文";
	public static final String ALIAS_DESC = "简介";
	public static final String ALIAS_FILE = "附件";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@Length(max=80)
	private java.lang.String title;
	//
	private java.lang.Integer view;
	//
	private java.lang.Integer comment;
	//
	private java.lang.Long createTime;
	//
	private java.lang.Long updateTime;
	//@Max(127)
	private Integer isUp;
	//@Max(127)
	private Integer status;
	//@Length(max=256)
	private java.lang.String pic;
	//@Length(max=65535)
	private java.lang.String content;
	//@Length(max=500)
	private java.lang.String desc;
	//@Length(max=256)
	private java.lang.String file;
	//columns END


		public TfdMedicineScience(){
		}
		public TfdMedicineScience(Integer id) {
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
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 80)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "view", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getView() {
		return this.view;
	}
	
	public void setView(java.lang.Integer view) {
		this.view = view;
	}
	
	@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getComment() {
		return this.comment;
	}
	
	public void setComment(java.lang.Integer comment) {
		this.comment = comment;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "is_up", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
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
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "desc", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getDesc() {
		return this.desc;
	}
	
	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}
	
	@Column(name = "file", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getFile() {
		return this.file;
	}
	
	public void setFile(java.lang.String file) {
		this.file = file;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("View",getView())
			.append("Comment",getComment())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("IsUp",getIsUp())
			.append("Status",getStatus())
			.append("Pic",getPic())
			.append("Content",getContent())
			.append("Desc",getDesc())
			.append("File",getFile())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdMedicineScience == false) return false;
		if(this == obj) return true;
		FdMedicineScience other = (FdMedicineScience)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

