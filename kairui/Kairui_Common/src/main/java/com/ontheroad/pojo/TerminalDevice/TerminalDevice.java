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
	 private int workStatus;     //工作状态 0待机中、2预约中、3使用中、4离线
	 private String voicebroadcast; //语音播报开关状态 0关 1开
	 private String volume;  //语音播报音量
	 private String backlight;  //背光值
	 private String ip;  //ip
	 private String port;  //端口
	 private String status; //拥有状态 0绑定 1共享

	 private String effluent_way = "0"; // 出水方式
	 private String effluent_type = "0"; // 出水模式
	 private String current_flow_grade = "001";  // 当前流速等级
	 private String current_temp ;  // 当前温度
	 private String started = "1";  // 开始/暂停: 1开始出水, 2暂停出水

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
	 
	 /**
	  * 是否有进行中的售后
	  */
	 private boolean isProcessRepair;
	 
	 /**
	  * 产品类型
	  */
	 private String product;

	
	 
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
