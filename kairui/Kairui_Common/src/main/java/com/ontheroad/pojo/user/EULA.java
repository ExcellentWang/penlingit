package com.ontheroad.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class EULA implements Serializable {
    private static final long serialVersionUID = 1L;

     private int id;
     private String title;      // 标题
     private String content;    // 内容
     private String createTime; // 创建时间
     private String address;    // html地址
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
     
}
