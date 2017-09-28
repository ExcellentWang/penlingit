package com.ontheroad.pojo.TerminalDevice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/*
 * 设备预约设置
 * 
 * 
 */
public class DeviceAppointment implements Serializable{

	private static final long serialVersionUID = 1L;

	 private int appointment_id;  //预约ID
	 private int equipment_id;   //设备ID
	 private int user_id;   //用户ID
	 private String effluent_speed; //出水速度 0慢 1中等 2快
	 private Date time;   //预约时间
	 private String children_status; //儿童模式 0关 1开
	 private String appointment_temperature; //预约温度
	 private String effluent_type;   //出水模式 0定量出水 1定时出水
	 private String effluent_amount;  //出水量
	 private String effluent_time; //出水时长
	 private String effluent_way;  //出水方式 0花洒 1顶喷
	 private String instructions;
	 private boolean valid;
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEffluent_speed() {
		return effluent_speed;
	}
	public void setEffluent_speed(String effluent_speed) {
		this.effluent_speed = effluent_speed;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getChildren_status() {
		return children_status;
	}
	public void setChildren_status(String children_status) {
		this.children_status = children_status;
	}
	public String getAppointment_temperature() {
		return appointment_temperature;
	}
	public void setAppointment_temperature(String appointment_temperature) {
		this.appointment_temperature = appointment_temperature;
	}
	public String getEffluent_type() {
		return effluent_type;
	}
	public void setEffluent_type(String effluent_type) {
		this.effluent_type = effluent_type;
	}
	public String getEffluent_amount() {
		return effluent_amount;
	}
	public void setEffluent_amount(String effluent_amount) {
		this.effluent_amount = effluent_amount;
	}
	public String getEffluent_time() {
		return effluent_time;
	}
	public void setEffluent_time(String effluent_time) {
		this.effluent_time = effluent_time;
	}
	public String getEffluent_way() {
		return effluent_way;
	}
	public void setEffluent_way(String effluent_way) {
		this.effluent_way = effluent_way;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	 
	 

}
