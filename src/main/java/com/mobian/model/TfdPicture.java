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
@Table(name = "fd_picture")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdPicture implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdPicture";
	public static final String ALIAS_ID = "主键id自增";
	public static final String ALIAS_PATH = "路径";
	public static final String ALIAS_URL = "图片链接";
	public static final String ALIAS_MD5 = "文件md5";
	public static final String ALIAS_SHA1 = "文件 sha1编码";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_TYPE = "图片来源，或模块区分 1:微信";
	public static final String ALIAS_SOURCE_ID = "来源id，当关联其他平台时该平台生产的id";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@NotBlank @Length(max=255)
	private java.lang.String path;
	//@NotBlank @Length(max=255)
	private java.lang.String url;
	//@NotBlank @Length(max=32)
	private java.lang.String md5;
	//@NotBlank @Length(max=40)
	private java.lang.String sha1;
	//@NotNull @Max(127)
	private Integer status;
	//@NotNull 
	private java.lang.Long createTime;
	//
	private java.lang.Integer type;
	//@Length(max=255)
	private java.lang.String sourceId;
	//columns END


		public TfdPicture(){
		}
		public TfdPicture(Integer id) {
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
	
	@Column(name = "path", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getPath() {
		return this.path;
	}
	
	public void setPath(java.lang.String path) {
		this.path = path;
	}
	
	@Column(name = "url", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	@Column(name = "md5", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public java.lang.String getMd5() {
		return this.md5;
	}
	
	public void setMd5(java.lang.String md5) {
		this.md5 = md5;
	}
	
	@Column(name = "sha1", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public java.lang.String getSha1() {
		return this.sha1;
	}
	
	public void setSha1(java.lang.String sha1) {
		this.sha1 = sha1;
	}
	
	@Column(name = "status", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "create_time", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	
	@Column(name = "source_id", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSourceId() {
		return this.sourceId;
	}
	
	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Path",getPath())
			.append("Url",getUrl())
			.append("Md5",getMd5())
			.append("Sha1",getSha1())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("Type",getType())
			.append("SourceId",getSourceId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdPicture == false) return false;
		if(this == obj) return true;
		FdPicture other = (FdPicture)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

