package com.ontheroad.pojo.TerminalDevice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DeviceUseLog implements Serializable {
    private static final long serialVersionUID = 1L;

     private int log_id;
     private int equipment_id = 1; // 设备ID

     private String uploadStatus = "1"; // 上传的数据状态，01，预热开始洗浴 02，改变设定温度  03 改变设定
     private Date useTime = Calendar.getInstance().getTime(); // 使用时间
     private String useType = "01"; // 模式，01，成人模式 02，儿童模式
     private String useDuration = "30"; // 使用时长
     private String useWeight = "100"; // 用水量
     private String saveWeight = "20";// 节水量

     private String valveOutTemperature = "38"; //混水阀出水温度
     private String setTemperature = "38"; //设定温度
     private String bufferTemperature = "40"; //缓冲温度
     private String outTemperature = "38";// 出水温度

     private String flowGrade = "001"; //流量等级
     private String flowSpeed = "060"; //流量等级对应的流速单位L/min

     private String hotTemperature = "50"; // 热水温度
     private String coldTemperature = "10"; // 冷水温度
    
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getUseDuration() {
		return useDuration;
	}
	public void setUseDuration(String useDuration) {
		this.useDuration = useDuration;
	}
	public String getUseWeight() {
		return useWeight;
	}
	public void setUseWeight(String useWeight) {
		this.useWeight = useWeight;
	}
	public String getSaveWeight() {
		return saveWeight;
	}
	public void setSaveWeight(String saveWeight) {
		this.saveWeight = saveWeight;
	}
	public String getValveOutTemperature() {
		return valveOutTemperature;
	}
	public void setValveOutTemperature(String valveOutTemperature) {
		this.valveOutTemperature = valveOutTemperature;
	}
	public String getSetTemperature() {
		return setTemperature;
	}
	public void setSetTemperature(String setTemperature) {
		this.setTemperature = setTemperature;
	}
	public String getBufferTemperature() {
		return bufferTemperature;
	}
	public void setBufferTemperature(String bufferTemperature) {
		this.bufferTemperature = bufferTemperature;
	}
	public String getOutTemperature() {
		return outTemperature;
	}
	public void setOutTemperature(String outTemperature) {
		this.outTemperature = outTemperature;
	}
	public String getFlowGrade() {
		return flowGrade;
	}
	public void setFlowGrade(String flowGrade) {
		this.flowGrade = flowGrade;
	}
	public String getFlowSpeed() {
		return flowSpeed;
	}
	public void setFlowSpeed(String flowSpeed) {
		this.flowSpeed = flowSpeed;
	}
	public String getHotTemperature() {
		return hotTemperature;
	}
	public void setHotTemperature(String hotTemperature) {
		this.hotTemperature = hotTemperature;
	}
	public String getColdTemperature() {
		return coldTemperature;
	}
	public void setColdTemperature(String coldTemperature) {
		this.coldTemperature = coldTemperature;
	}

     

}
