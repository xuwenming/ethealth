package com.mobian.pageModel;

/**
 * Created by wenming on 2017/12/27.
 */
public class PushMessage {
    private Integer id;
    private String mtype;
    private String content;
    private Integer status;

    public PushMessage() {}

    public PushMessage(String mtype, String content) {
        this.mtype = mtype;
        this.content = content;
    }

    public PushMessage(String mtype, String content, Integer status) {
        this.mtype = mtype;
        this.content = content;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
