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
     * 上传的数据状态，01，预热开始洗浴 02，改变设定温度  03 改变设定 
     */
    private String uploadstatus;

    /**
     * 使用时间
     */
    private Date usetime;

    /**
     * 模式，01，成人模式 02，儿童模式
     */
    private String usetype;

    /**
     * 使用时长
     */
    private String useduration;

    /**
     * 用水量
     */
    private String useweight;

    /**
     * 节水量
     */
    private String saveweight;

    /**
     * 混水阀出水温度
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
     * 流量等级
     */
    private String flowgrade;

    /**
     * 流量等级对应的流速单位L/min
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
     * 泵启动时间（单位s）
     */
    private String starttime;

    /**
     * 
     */
    private Date createTime;

    /**
     * 获取
     *
     * @cgw 2017-11-03 10:32:49
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id the value for device_use_log.id
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取设备ID
     *
     * @cgw 2017-11-03 10:32:49
     */
    public Integer getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置设备ID
     *
     * @param equipmentId the value for device_use_log.equipment_id
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 获取上传的数据状态，01，预热开始洗浴 02，改变设定温度  03 改变设定 
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getUploadstatus() {
        return uploadstatus;
    }

    /**
     * 设置上传的数据状态，01，预热开始洗浴 02，改变设定温度  03 改变设定 
     *
     * @param uploadstatus the value for device_use_log.uploadStatus
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setUploadstatus(String uploadstatus) {
        this.uploadstatus = uploadstatus == null ? null : uploadstatus.trim();
    }

    /**
     * 获取使用时间
     *
     * @cgw 2017-11-03 10:32:49
     */
    public Date getUsetime() {
        return usetime;
    }

    /**
     * 设置使用时间
     *
     * @param usetime the value for device_use_log.useTime
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    /**
     * 获取模式，01，成人模式 02，儿童模式
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getUsetype() {
        return usetype;
    }

    /**
     * 设置模式，01，成人模式 02，儿童模式
     *
     * @param usetype the value for device_use_log.useType
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setUsetype(String usetype) {
        this.usetype = usetype == null ? null : usetype.trim();
    }

    /**
     * 获取使用时长
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getUseduration() {
        return useduration;
    }

    /**
     * 设置使用时长
     *
     * @param useduration the value for device_use_log.useDuration
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setUseduration(String useduration) {
        this.useduration = useduration == null ? null : useduration.trim();
    }

    /**
     * 获取用水量
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getUseweight() {
        return useweight;
    }

    /**
     * 设置用水量
     *
     * @param useweight the value for device_use_log.useWeight
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setUseweight(String useweight) {
        this.useweight = useweight == null ? null : useweight.trim();
    }

    /**
     * 获取节水量
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getSaveweight() {
        return saveweight;
    }

    /**
     * 设置节水量
     *
     * @param saveweight the value for device_use_log.saveWeight
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setSaveweight(String saveweight) {
        this.saveweight = saveweight == null ? null : saveweight.trim();
    }

    /**
     * 获取混水阀出水温度
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getValveouttemperature() {
        return valveouttemperature;
    }

    /**
     * 设置混水阀出水温度
     *
     * @param valveouttemperature the value for device_use_log.valveOutTemperature
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setValveouttemperature(String valveouttemperature) {
        this.valveouttemperature = valveouttemperature == null ? null : valveouttemperature.trim();
    }

    /**
     * 获取设定温度
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getSettemperature() {
        return settemperature;
    }

    /**
     * 设置设定温度
     *
     * @param settemperature the value for device_use_log.setTemperature
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setSettemperature(String settemperature) {
        this.settemperature = settemperature == null ? null : settemperature.trim();
    }

    /**
     * 获取缓冲温度
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getBuffertemperature() {
        return buffertemperature;
    }

    /**
     * 设置缓冲温度
     *
     * @param buffertemperature the value for device_use_log.bufferTemperature
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setBuffertemperature(String buffertemperature) {
        this.buffertemperature = buffertemperature == null ? null : buffertemperature.trim();
    }

    /**
     * 获取出水温度
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getOuttemperature() {
        return outtemperature;
    }

    /**
     * 设置出水温度
     *
     * @param outtemperature the value for device_use_log.outTemperature
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setOuttemperature(String outtemperature) {
        this.outtemperature = outtemperature == null ? null : outtemperature.trim();
    }

    /**
     * 获取流量等级
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getFlowgrade() {
        return flowgrade;
    }

    /**
     * 设置流量等级
     *
     * @param flowgrade the value for device_use_log.flowGrade
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setFlowgrade(String flowgrade) {
        this.flowgrade = flowgrade == null ? null : flowgrade.trim();
    }

    /**
     * 获取流量等级对应的流速单位L/min
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getFlowspeed() {
        return flowspeed;
    }

    /**
     * 设置流量等级对应的流速单位L/min
     *
     * @param flowspeed the value for device_use_log.flowSpeed
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setFlowspeed(String flowspeed) {
        this.flowspeed = flowspeed == null ? null : flowspeed.trim();
    }

    /**
     * 获取电池电压
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getBatteryvoltage() {
        return batteryvoltage;
    }

    /**
     * 设置电池电压
     *
     * @param batteryvoltage the value for device_use_log.batteryVoltage
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setBatteryvoltage(String batteryvoltage) {
        this.batteryvoltage = batteryvoltage == null ? null : batteryvoltage.trim();
    }

    /**
     * 获取电池温度
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getBatterytemperature() {
        return batterytemperature;
    }

    /**
     * 设置电池温度
     *
     * @param batterytemperature the value for device_use_log.batteryTemperature
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setBatterytemperature(String batterytemperature) {
        this.batterytemperature = batterytemperature == null ? null : batterytemperature.trim();
    }

    /**
     * 获取泵启动时间（单位s）
     *
     * @cgw 2017-11-03 10:32:49
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * 设置泵启动时间（单位s）
     *
     * @param starttime the value for device_use_log.startTime
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    /**
     * 获取
     *
     * @cgw 2017-11-03 10:32:49
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     *
     * @param createTime the value for device_use_log.create_time
     *
     * @cgw 2017-11-03 10:32:49
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}