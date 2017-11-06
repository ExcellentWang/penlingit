package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.util.Date;

public class DeviceUseLog implements Serializable{
    /**
     * 
     */
    private Integer id;

    /**
     * 设备ID
     */
    private Integer equipmentId;

    /**
     * 设备状态 00：待机   01：准备中（包括预约倒计时） 02：使用中  03：离线
     */
    private String uploadstatus;

    /**
     * 模式，01，成人模式 02，儿童模式
     */
    private String usetype;

    /**
     * 混水温度
     */
    private String valveouttemperature;

    /**
     * 设定温度
     */
    private String settemperature;

    /**
     * 缓冲温度
     */
    private String buffertemperature;

    /**
     * 出水温度
     */
    private String outtemperature;

    /**
     * 设定流量
     */
    private String flowgrade;

    /**
     * 实时流量
     */
    private String flowspeed;

    /**
     * 电池电压
     */
    private String batteryvoltage;

    /**
     * 电池温度
     */
    private String batterytemperature;

    /**
     * 
     */
    private Date createTime;

    /**
     * 工作状态
     */
    private String sWorkStatus;

    /**
     * 热水温度
     */
    private String hotWaterTemp;

    /**
     * 冷水温度
     */
    private String codeWaterTemp;

    /**
     * 定时定量普通模式
     */
    private String timeType;

    /**
     * 获取
     *
     * @cgw 2017-11-06 21:02:38
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id the value for device_use_log.id
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取设备ID
     *
     * @cgw 2017-11-06 21:02:38
     */
    public Integer getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置设备ID
     *
     * @param equipmentId the value for device_use_log.equipment_id
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 获取设备状态 00：待机   01：准备中（包括预约倒计时） 02：使用中  03：离线
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getUploadstatus() {
        return uploadstatus;
    }

    /**
     * 设置设备状态 00：待机   01：准备中（包括预约倒计时） 02：使用中  03：离线
     *
     * @param uploadstatus the value for device_use_log.uploadStatus
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setUploadstatus(String uploadstatus) {
        this.uploadstatus = uploadstatus == null ? null : uploadstatus.trim();
    }

    /**
     * 获取模式，01，成人模式 02，儿童模式
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getUsetype() {
        return usetype;
    }

    /**
     * 设置模式，01，成人模式 02，儿童模式
     *
     * @param usetype the value for device_use_log.useType
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setUsetype(String usetype) {
        this.usetype = usetype == null ? null : usetype.trim();
    }

    /**
     * 获取混水温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getValveouttemperature() {
        return valveouttemperature;
    }

    /**
     * 设置混水温度
     *
     * @param valveouttemperature the value for device_use_log.valveOutTemperature
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setValveouttemperature(String valveouttemperature) {
        this.valveouttemperature = valveouttemperature == null ? null : valveouttemperature.trim();
    }

    /**
     * 获取设定温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getSettemperature() {
        return settemperature;
    }

    /**
     * 设置设定温度
     *
     * @param settemperature the value for device_use_log.setTemperature
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setSettemperature(String settemperature) {
        this.settemperature = settemperature == null ? null : settemperature.trim();
    }

    /**
     * 获取缓冲温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getBuffertemperature() {
        return buffertemperature;
    }

    /**
     * 设置缓冲温度
     *
     * @param buffertemperature the value for device_use_log.bufferTemperature
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setBuffertemperature(String buffertemperature) {
        this.buffertemperature = buffertemperature == null ? null : buffertemperature.trim();
    }

    /**
     * 获取出水温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getOuttemperature() {
        return outtemperature;
    }

    /**
     * 设置出水温度
     *
     * @param outtemperature the value for device_use_log.outTemperature
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setOuttemperature(String outtemperature) {
        this.outtemperature = outtemperature == null ? null : outtemperature.trim();
    }

    /**
     * 获取设定流量
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getFlowgrade() {
        return flowgrade;
    }

    /**
     * 设置设定流量
     *
     * @param flowgrade the value for device_use_log.flowGrade
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setFlowgrade(String flowgrade) {
        this.flowgrade = flowgrade == null ? null : flowgrade.trim();
    }

    /**
     * 获取实时流量
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getFlowspeed() {
        return flowspeed;
    }

    /**
     * 设置实时流量
     *
     * @param flowspeed the value for device_use_log.flowSpeed
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setFlowspeed(String flowspeed) {
        this.flowspeed = flowspeed == null ? null : flowspeed.trim();
    }

    /**
     * 获取电池电压
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getBatteryvoltage() {
        return batteryvoltage;
    }

    /**
     * 设置电池电压
     *
     * @param batteryvoltage the value for device_use_log.batteryVoltage
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setBatteryvoltage(String batteryvoltage) {
        this.batteryvoltage = batteryvoltage == null ? null : batteryvoltage.trim();
    }

    /**
     * 获取电池温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getBatterytemperature() {
        return batterytemperature;
    }

    /**
     * 设置电池温度
     *
     * @param batterytemperature the value for device_use_log.batteryTemperature
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setBatterytemperature(String batterytemperature) {
        this.batterytemperature = batterytemperature == null ? null : batterytemperature.trim();
    }

    /**
     * 获取
     *
     * @cgw 2017-11-06 21:02:38
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     *
     * @param createTime the value for device_use_log.create_time
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取工作状态
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getsWorkStatus() {
        return sWorkStatus;
    }

    /**
     * 设置工作状态
     *
     * @param sWorkStatus the value for device_use_log.s_work_status
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setsWorkStatus(String sWorkStatus) {
        this.sWorkStatus = sWorkStatus == null ? null : sWorkStatus.trim();
    }

    /**
     * 获取热水温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getHotWaterTemp() {
        return hotWaterTemp;
    }

    /**
     * 设置热水温度
     *
     * @param hotWaterTemp the value for device_use_log.hot_water_temp
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setHotWaterTemp(String hotWaterTemp) {
        this.hotWaterTemp = hotWaterTemp == null ? null : hotWaterTemp.trim();
    }

    /**
     * 获取冷水温度
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getCodeWaterTemp() {
        return codeWaterTemp;
    }

    /**
     * 设置冷水温度
     *
     * @param codeWaterTemp the value for device_use_log.code_water_temp
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setCodeWaterTemp(String codeWaterTemp) {
        this.codeWaterTemp = codeWaterTemp == null ? null : codeWaterTemp.trim();
    }

    /**
     * 获取定时定量普通模式
     *
     * @cgw 2017-11-06 21:02:38
     */
    public String getTimeType() {
        return timeType;
    }

    /**
     * 设置定时定量普通模式
     *
     * @param timeType the value for device_use_log.time_type
     *
     * @cgw 2017-11-06 21:02:38
     */
    public void setTimeType(String timeType) {
        this.timeType = timeType == null ? null : timeType.trim();
    }
}