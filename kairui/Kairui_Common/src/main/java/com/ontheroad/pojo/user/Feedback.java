package com.ontheroad.pojo.user;

import java.io.Serializable;

/*
 * 意见反馈
 * 
 */
public class Feedback implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int feedback_id; //主键
	private int user_id;   //  用户id
	private String createTime;  // 创建时间
	private String content;   //内容
	
	public int getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
