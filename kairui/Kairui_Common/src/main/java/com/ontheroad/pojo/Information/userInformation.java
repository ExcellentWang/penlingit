package com.ontheroad.pojo.Information;

import java.io.Serializable;

public class userInformation implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer information_id;   //主键
	private String title;    //标题
	private String createTime;  //创建时间
	private String creator;   //创建人
	private String informationType;  //消息类型
	private Integer isNew;    //是否最新 0最新 1没有
	private String typeName; //类型名称
	
	
	
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public Integer getInformation_id() {
		return information_id;
	}
	public void setInformation_id(Integer information_id) {
		this.information_id = information_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getInformationType() {
		return informationType;
	}
	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}

}
