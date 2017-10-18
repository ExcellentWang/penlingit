package com.ontheroad.pojo.TerminalDevice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
 * 设备绑定分享
 * @author Administrator
 *
 */
public class DeviceShare implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int share_id; // 主键
	private int share_user_id; // 分享用户ID
	private int user_id; // 用户ID
	private int equipment_id; // 设备id
	private String equipmentNum;// 设备编号
	private String phone;// 用户手机号O
	private int authority; // 权限 0查看 1查看并控制
	private String remark; // 备注
	private int status; // 拥有状态 0绑定 1共享;
	private String username;
	
	
	public int getShare_user_id() {
		return share_user_id;
	}
	public void setShare_user_id(int share_user_id) {
		this.share_user_id = share_user_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getShare_id() {
		return share_id;
	}
	public void setShare_id(int share_id) {
		this.share_id = share_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
}
