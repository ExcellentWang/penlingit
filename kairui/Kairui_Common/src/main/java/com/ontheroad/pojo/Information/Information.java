package com.ontheroad.pojo.Information;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class Information implements Serializable {
    private static final long serialVersionUID = 1L;
     private int id;
     private String title;
     private Date createTime;
     private String creator;
     private int informationType;  //消息类型 1资讯 2系统 3设备 4节水量
     private String typeName;
     private String content;
     private boolean isNew;
     private int user_id;
     private int equipment_id;
     private Double useWeight;
     private Double saveWeight;
     private Integer isRead;
     private String address;
     private String summary;
     private String picture;
     
     
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getInformationType() {
		return informationType;
	}
	public void setInformationType(int informationType) {
		this.informationType = informationType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public Double getUseWeight() {
		return useWeight;
	}
	public void setUseWeight(Double useWeight) {
		this.useWeight = useWeight;
	}
	public Double getSaveWeight() {
		return saveWeight;
	}
	public void setSaveWeight(Double saveWeight) {
		this.saveWeight = saveWeight;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
     
}
