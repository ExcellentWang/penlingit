package com.ontheroad.pojo.TerminalDevice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class DeviceError implements Serializable {

    private static final long serialVersionUID = 1L;

     private int id;
     private int equipment_id;  // 设备 id
     private int water_tank;    // 0：水箱没水     1:水箱有水   2： 水箱水满
     private int cold_water_in; // 0：进水冷水传感器正常   1：进水冷水传感器故障   2冷水温度高
     private int hot_water_in;  // 0: 热水传感器正常       1：热水传感器故障      2.热水温度高
     private int mixer_temp;    // 0：混水阀温度正常         1：混水阀温度故障   2.混水温度高
     private int mixer_comm;    // 0：混水阀通讯正常        1：混水阀通讯故障
     private int buffer_in;     // 0：缓冲进水传感器正常  1：缓冲进水传感器故障 2：缓冲水温度高
     private int water_out;     // 0：出水传感器正常       1： 出水传感器故障  2.出水温度高
     private int ac_power;      // 1：外接电源供电   0：外接电源断掉，电池供电
     private int battery_volt;  // 1： 电池电压低    0： 正常
     private int battery_temp;  // 1： 电池温度高    0： 正常
     private Date updated_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public int getWater_tank() {
		return water_tank;
	}
	public void setWater_tank(int water_tank) {
		this.water_tank = water_tank;
	}
	public int getCold_water_in() {
		return cold_water_in;
	}
	public void setCold_water_in(int cold_water_in) {
		this.cold_water_in = cold_water_in;
	}
	public int getHot_water_in() {
		return hot_water_in;
	}
	public void setHot_water_in(int hot_water_in) {
		this.hot_water_in = hot_water_in;
	}
	public int getMixer_temp() {
		return mixer_temp;
	}
	public void setMixer_temp(int mixer_temp) {
		this.mixer_temp = mixer_temp;
	}
	public int getMixer_comm() {
		return mixer_comm;
	}
	public void setMixer_comm(int mixer_comm) {
		this.mixer_comm = mixer_comm;
	}
	public int getBuffer_in() {
		return buffer_in;
	}
	public void setBuffer_in(int buffer_in) {
		this.buffer_in = buffer_in;
	}
	public int getWater_out() {
		return water_out;
	}
	public void setWater_out(int water_out) {
		this.water_out = water_out;
	}
	public int getAc_power() {
		return ac_power;
	}
	public void setAc_power(int ac_power) {
		this.ac_power = ac_power;
	}
	public int getBattery_volt() {
		return battery_volt;
	}
	public void setBattery_volt(int battery_volt) {
		this.battery_volt = battery_volt;
	}
	public int getBattery_temp() {
		return battery_temp;
	}
	public void setBattery_temp(int battery_temp) {
		this.battery_temp = battery_temp;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
     
     
}
