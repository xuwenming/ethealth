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
@Table(name = "fd_banner")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdBanner implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdBanner";
	public static final String ALIAS_ID = "医院ID";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_LINK = "链接";
	public static final String ALIAS_NUM = "排序";
	public static final String ALIAS_PIC = "医院图片ids";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_BY = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_STATUS = "是否删除 1 是 0 否";
	public static final String ALIAS_SOURCE = "所属板块 1 首页 2 专家团队 3 医学新发展 4 最新案例 5 专家见解";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@Length(max=100)
	private java.lang.String title;
	//@Length(max=100)
	private java.lang.String link;
	//
	private java.lang.Integer num;
	//
	private java.lang.Integer pic;
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
	//@Length(max=2)
	private java.lang.String source;
	//columns END


		public TfdBanner(){
		}
		public TfdBanner(Integer id) {
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
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "link", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLink() {
		return this.link;
	}
	
	public void setLink(java.lang.String link) {
		this.link = link;
	}
	
	@Column(name = "num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNum() {
		return this.num;
	}
	
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}
	
	@Column(name = "pic", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPic() {
		return this.pic;
	}
	
	public void setPic(java.lang.Integer pic) {
		this.pic = pic;
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
	
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public java.lang.String getSource() {
		return this.source;
	}
	
	public void setSource(java.lang.String source) {
		this.source = source;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Link",getLink())
			.append("Num",getNum())
			.append("Pic",getPic())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("Source",getSource())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdBanner == false) return false;
		if(this == obj) return true;
		FdBanner other = (FdBanner)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

