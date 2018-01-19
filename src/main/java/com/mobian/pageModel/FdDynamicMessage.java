package com.mobian.pageModel;

@SuppressWarnings("serial")
public class FdDynamicMessage implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private String content;
	private Long createTime;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}
