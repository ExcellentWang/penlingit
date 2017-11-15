package com.ontheroad.pojo.Information;

import java.io.Serializable;
import java.util.Date;

public class Deviceinformation implements Serializable{
	
	
	/**
	 * 系统消息
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;   //主键
	private Date time;  //时间
	private String content;  //内容
	private int equipment_id;  //设备id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
}
