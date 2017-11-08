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
@Table(name = "fd_file")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TfdFile implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FdFile";
	public static final String ALIAS_ID = "文件ID";
	public static final String ALIAS_NAME = "原始文件名";
	public static final String ALIAS_SAVENAME = "保存名称";
	public static final String ALIAS_SAVEPATH = "文件保存路径";
	public static final String ALIAS_EXT = "文件后缀";
	public static final String ALIAS_MIME = "文件mime类型";
	public static final String ALIAS_SIZE = "文件大小";
	public static final String ALIAS_MD5 = "文件md5";
	public static final String ALIAS_SHA1 = "文件 sha1编码";
	public static final String ALIAS_LOCATION = "文件保存位置";
	public static final String ALIAS_URL = "远程地址";
	public static final String ALIAS_CREATE_TIME = "上传时间";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//
	private java.lang.Integer id;
	//@NotBlank @Length(max=200)
	private java.lang.String name;
	//@NotBlank @Length(max=200)
	private java.lang.String savename;
	//@NotBlank @Length(max=100)
	private java.lang.String savepath;
	//@NotBlank @Length(max=5)
	private java.lang.String ext;
	//@NotBlank @Length(max=100)
	private java.lang.String mime;
	//@NotNull 
	private java.lang.Integer size;
	//@NotBlank @Length(max=32)
	private java.lang.String md5;
	//@NotBlank @Length(max=40)
	private java.lang.String sha1;
	//@NotNull @Max(127)
	private Integer location;
	//@NotBlank @Length(max=255)
	private java.lang.String url;
	//@NotNull 
	private java.lang.Long createTime;
	//columns END


		public TfdFile(){
		}
		public TfdFile(Integer id) {
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
	
	@Column(name = "name", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "savename", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	public java.lang.String getSavename() {
		return this.savename;
	}
	
	public void setSavename(java.lang.String savename) {
		this.savename = savename;
	}
	
	@Column(name = "savepath", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getSavepath() {
		return this.savepath;
	}
	
	public void setSavepath(java.lang.String savepath) {
		this.savepath = savepath;
	}
	
	@Column(name = "ext", unique = false, nullable = false, insertable = true, updatable = true, length = 5)
	public java.lang.String getExt() {
		return this.ext;
	}
	
	public void setExt(java.lang.String ext) {
		this.ext = ext;
	}
	
	@Column(name = "mime", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getMime() {
		return this.mime;
	}
	
	public void setMime(java.lang.String mime) {
		this.mime = mime;
	}
	
	@Column(name = "size", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSize() {
		return this.size;
	}
	
	public void setSize(java.lang.Integer size) {
		this.size = size;
	}
	
	@Column(name = "md5", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
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
	
	@Column(name = "location", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getLocation() {
		return this.location;
	}
	
	public void setLocation(Integer location) {
		this.location = location;
	}
	
	@Column(name = "url", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	@Column(name = "create_time", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.Long getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Savename",getSavename())
			.append("Savepath",getSavepath())
			.append("Ext",getExt())
			.append("Mime",getMime())
			.append("Size",getSize())
			.append("Md5",getMd5())
			.append("Sha1",getSha1())
			.append("Location",getLocation())
			.append("Url",getUrl())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FdFile == false) return false;
		if(this == obj) return true;
		FdFile other = (FdFile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

