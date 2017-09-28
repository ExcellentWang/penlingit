package com.ontheroad.pojo.user;

import java.io.Serializable;

/**
 * 
 * 常见问题
 * 
 * @author Administrator
 *
 */
public class Commonproblem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int commonProblem_id; //主键
	private String title;  //标题
	private String content; //内容
	private String creator; //创建人
	private String createTime; //创建时间
	
	
	public int getCommonProblem_id() {
		return commonProblem_id;
	}
	public void setCommonProblem_id(int commonProblem_id) {
		this.commonProblem_id = commonProblem_id;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	

}
