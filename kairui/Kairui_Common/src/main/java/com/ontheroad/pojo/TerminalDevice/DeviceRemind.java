package com.ontheroad.pojo.TerminalDevice;

import java.io.Serializable;

/***
 * 
 * 
 * 设备提醒设置
 * @author Administrator
 *
 */
public class DeviceRemind implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int remind_id;  //提醒ID
	private int equipment_id; //设备ID
	private int warn_type;  // 提醒方式 0振动 1响铃 2振铃
	private int water_warn_status;  //用水节水提醒开关 0关 1开
	private int water_under;          //热水温度提醒 0关 1开
	private String under_temperature;  //最低温度
	private String under_times;  //最低温度提醒频次
	private int water_hight;   //出水温度提醒 0关 1开
	private String hight_temperature;  //最高温度
	private String hight_times;    //高于最高温度提醒频次
	
	
	public int getRemind_id() {
		return remind_id;
	}
	public void setRemind_id(int remind_id) {
		this.remind_id = remind_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public int getWarn_type() {
		return warn_type;
	}
	public void setWarn_type(int warn_type) {
		this.warn_type = warn_type;
	}
	public int getWater_warn_status() {
		return water_warn_status;
	}
	public void setWater_warn_status(int water_warn_status) {
		this.water_warn_status = water_warn_status;
	}
	public int getWater_under() {
		return water_under;
	}
	public void setWater_under(int water_under) {
		this.water_under = water_under;
	}
	public String getUnder_temperature() {
		return under_temperature;
	}
	public void setUnder_temperature(String under_temperature) {
		this.under_temperature = under_temperature;
	}
	public String getUnder_times() {
		return under_times;
	}
	public void setUnder_times(String under_times) {
		this.under_times = under_times;
	}
	public int getWater_hight() {
		return water_hight;
	}
	public void setWater_hight(int water_hight) {
		this.water_hight = water_hight;
	}
	public String getHight_temperature() {
		return hight_temperature;
	}
	public void setHight_temperature(String hight_temperature) {
		this.hight_temperature = hight_temperature;
	}
	public String getHight_times() {
		return hight_times;
	}
	public void setHight_times(String hight_times) {
		this.hight_times = hight_times;
	}
	
	
	
	
	
}
