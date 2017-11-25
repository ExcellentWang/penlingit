package com.ontheroad.pojo.TerminalDevice;

import com.ontheroad.pojo.user.Customerservice;
import com.ontheroad.pojo.user.Guarantee;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 终端设备表
 * @author Administrator
 *
 */
public class TerminalDevice implements Serializable{

	private static final long serialVersionUID = 1L;

	 private int equipment_id;  //设备id
	 private String equipmentNum;   //设备编号
	 private String equipmentName; //设备名称
	 private String type;    //设备类型
	 private String equipmentPicture;  //设备图片地址
	 private int workStatus;     //工作状态 0待机中、1：准备中,2预约中、3使用中、4离线
	 private String voicebroadcast; //语音播报开关状态 0关 1开
	 private String volume;  //语音播报音量
	 private String backlight;  //背光值
	 private String ip;  //ip
	 private String port;  //端口
	 private String status; //拥有状态 0绑定 1共享

	 private String effluent_way = "0"; // 出水方式
	 private String effluent_type; // 出水模式
	 private String effluent_type2; // 出水模式定时定量普通
	 private String current_flow_grade = "001";  // 当前流速等级
	 private String current_temp ;  // 当前温度
	 private String settemperature;//设定温度
	 private String started = "1";  // 开始/暂停: 0，暂停  1，开始  

	 private List<DeviceShare> deviceShares;  // 设备分享用户


	 private DeviceAppointment appointment; // 预约
	 private com.ontheroad.mysql.entity.DeviceUseLog deviceUseLog;// 使用情况

	 private List<Customerservice> customer_services;
	 private Guarantee guarantee;
	 private String guaranteeStatus;

	 private String userTelephone;

	 private Date created_at;
	 private Date binded_at;
	 private String last_active_at;

	 private String firm_version;
	 private String m_use_water;
	 private String m_jie_water;
	 private String province;
	 private String city;
	 private String sur_water;//剩余水量
	 private String sur_time;//剩余时间
	 private String remark;//绑定备注
	 private Date m_send_time;//上次月用水量和节水量推送时间
	 private Date a_send_time;//上次预约倒计时推送时间
	 
	public Date getM_send_time() {
		return m_send_time;
	}

	public void setM_send_time(Date m_send_time) {
		this.m_send_time = m_send_time;
	}

	public Date getA_send_time() {
		return a_send_time;
	}

	public void setA_send_time(Date a_send_time) {
		this.a_send_time = a_send_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEffluent_type2() {
		return effluent_type2;
	}

	public void setEffluent_type2(String effluent_type2) {
		this.effluent_type2 = effluent_type2;
	}

	public String getSur_water() {
		return sur_water;
	}

	public void setSur_water(String sur_water) {
		this.sur_water = sur_water;
	}

	public String getSur_time() {
		return sur_time;
	}

	public void setSur_time(String sur_time) {
		this.sur_time = sur_time;
	}

	public String getSettemperature() {
		return settemperature;
	}

	public void setSettemperature(String settemperature) {
		this.settemperature = settemperature;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getM_use_water() {
		return m_use_water;
	}

	public void setM_use_water(String m_use_water) {
		this.m_use_water = m_use_water;
	}

	public String getM_jie_water() {
		return m_jie_water;
	}

	public void setM_jie_water(String m_jie_water) {
		this.m_jie_water = m_jie_water;
	}

	/**
	  * 是否有进行中的售后
	  */
	 private boolean isProcessRepair;
	 
	 /**
	  * 产品类型
	  */
	 private String product;
	 
	 /**
	  * 保修期
	  */
	 private Integer period;
	 
	 /**
	  * app禁用（1：开，0：关）
	  */
	 private String app_enabled;
	 
	 
	 
	public String getApp_enabled() {
		return app_enabled;
	}

	public void setApp_enabled(String app_enabled) {
		this.app_enabled = app_enabled;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public com.ontheroad.mysql.entity.DeviceUseLog getDeviceUseLog() {
		return deviceUseLog;
	}

	public void setDeviceUseLog(com.ontheroad.mysql.entity.DeviceUseLog deviceUseLog) {
		this.deviceUseLog = deviceUseLog;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public boolean isProcessRepair() {
		return isProcessRepair;
	}

	public void setProcessRepair(boolean isProcessRepair) {
		this.isProcessRepair = isProcessRepair;
	}

	public int getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEquipmentPicture() {
		return equipmentPicture;
	}

	public void setEquipmentPicture(String equipmentPicture) {
		this.equipmentPicture = equipmentPicture;
	}

	public int getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}

	public String getVoicebroadcast() {
		return voicebroadcast;
	}

	public void setVoicebroadcast(String voicebroadcast) {
		this.voicebroadcast = voicebroadcast;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getBacklight() {
		return backlight;
	}

	public void setBacklight(String backlight) {
		this.backlight = backlight;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEffluent_way() {
		return effluent_way;
	}

	public void setEffluent_way(String effluent_way) {
		this.effluent_way = effluent_way;
	}

	public String getEffluent_type() {
		return effluent_type;
	}

	public void setEffluent_type(String effluent_type) {
		this.effluent_type = effluent_type;
	}

	public String getCurrent_flow_grade() {
		return current_flow_grade;
	}

	public void setCurrent_flow_grade(String current_flow_grade) {
		this.current_flow_grade = current_flow_grade;
	}

	public String getCurrent_temp() {
		return current_temp;
	}

	public void setCurrent_temp(String current_temp) {
		this.current_temp = current_temp;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public List<DeviceShare> getDeviceShares() {
		return deviceShares;
	}

	public void setDeviceShares(List<DeviceShare> deviceShares) {
		this.deviceShares = deviceShares;
	}

	public DeviceAppointment getAppointment() {
		return appointment;
	}

	public void setAppointment(DeviceAppointment appointment) {
		this.appointment = appointment;
	}



	public List<Customerservice> getCustomer_services() {
		return customer_services;
	}

	public void setCustomer_services(List<Customerservice> customer_services) {
		this.customer_services = customer_services;
	}

	public Guarantee getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Guarantee guarantee) {
		this.guarantee = guarantee;
	}

	public String getGuaranteeStatus() {
		return guaranteeStatus;
	}

	public void setGuaranteeStatus(String guaranteeStatus) {
		this.guaranteeStatus = guaranteeStatus;
	}

	public String getUserTelephone() {
		return userTelephone;
	}

	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}


	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getBinded_at() {
		return binded_at;
	}

	public void setBinded_at(Date binded_at) {
		this.binded_at = binded_at;
	}

	public String getLast_active_at() {
		return last_active_at;
	}

	public void setLast_active_at(String last_active_at) {
		this.last_active_at = last_active_at;
	}

	public String getFirm_version() {
		return firm_version;
	}

	public void setFirm_version(String firm_version) {
		this.firm_version = firm_version;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	 
}
