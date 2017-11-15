package com.ontheroad.pojo.Information;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class NewsInformation implements Serializable{
	
	/**资讯消息实体bean
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private int id;   //主键
	 private String title;  //标题
	 private Date createTime;  //时间
	 private String content;  //内容
	 private String summary;  //概述
	 private String picture;  //图片
	 private String address;   //地址
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
