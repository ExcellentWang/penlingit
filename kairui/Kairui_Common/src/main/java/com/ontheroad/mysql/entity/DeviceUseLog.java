package com.ontheroad.mysql.entity;

import java.util.Date;

public class DeviceUseLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.id
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.equipment_id
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private Integer equipmentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.uploadStatus
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String uploadstatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.useTime
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private Date usetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.useType
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String usetype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.useDuration
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String useduration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.useWeight
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String useweight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.saveWeight
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String saveweight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.valveOutTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String valveouttemperature;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.setTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String settemperature;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.bufferTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String buffertemperature;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.outTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String outtemperature;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.flowGrade
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String flowgrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.flowSpeed
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String flowspeed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.batteryVoltage
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String batteryvoltage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.batteryTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String batterytemperature;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_use_log.startTime
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    private String starttime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.id
     *
     * @return the value of device_use_log.id
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.id
     *
     * @param id the value for device_use_log.id
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.equipment_id
     *
     * @return the value of device_use_log.equipment_id
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public Integer getEquipmentId() {
        return equipmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.equipment_id
     *
     * @param equipmentId the value for device_use_log.equipment_id
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.uploadStatus
     *
     * @return the value of device_use_log.uploadStatus
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getUploadstatus() {
        return uploadstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.uploadStatus
     *
     * @param uploadstatus the value for device_use_log.uploadStatus
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setUploadstatus(String uploadstatus) {
        this.uploadstatus = uploadstatus == null ? null : uploadstatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.useTime
     *
     * @return the value of device_use_log.useTime
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public Date getUsetime() {
        return usetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.useTime
     *
     * @param usetime the value for device_use_log.useTime
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.useType
     *
     * @return the value of device_use_log.useType
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getUsetype() {
        return usetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.useType
     *
     * @param usetype the value for device_use_log.useType
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setUsetype(String usetype) {
        this.usetype = usetype == null ? null : usetype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.useDuration
     *
     * @return the value of device_use_log.useDuration
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getUseduration() {
        return useduration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.useDuration
     *
     * @param useduration the value for device_use_log.useDuration
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setUseduration(String useduration) {
        this.useduration = useduration == null ? null : useduration.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.useWeight
     *
     * @return the value of device_use_log.useWeight
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getUseweight() {
        return useweight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.useWeight
     *
     * @param useweight the value for device_use_log.useWeight
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setUseweight(String useweight) {
        this.useweight = useweight == null ? null : useweight.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.saveWeight
     *
     * @return the value of device_use_log.saveWeight
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getSaveweight() {
        return saveweight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.saveWeight
     *
     * @param saveweight the value for device_use_log.saveWeight
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setSaveweight(String saveweight) {
        this.saveweight = saveweight == null ? null : saveweight.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.valveOutTemperature
     *
     * @return the value of device_use_log.valveOutTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getValveouttemperature() {
        return valveouttemperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.valveOutTemperature
     *
     * @param valveouttemperature the value for device_use_log.valveOutTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setValveouttemperature(String valveouttemperature) {
        this.valveouttemperature = valveouttemperature == null ? null : valveouttemperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.setTemperature
     *
     * @return the value of device_use_log.setTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getSettemperature() {
        return settemperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.setTemperature
     *
     * @param settemperature the value for device_use_log.setTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setSettemperature(String settemperature) {
        this.settemperature = settemperature == null ? null : settemperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.bufferTemperature
     *
     * @return the value of device_use_log.bufferTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getBuffertemperature() {
        return buffertemperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.bufferTemperature
     *
     * @param buffertemperature the value for device_use_log.bufferTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setBuffertemperature(String buffertemperature) {
        this.buffertemperature = buffertemperature == null ? null : buffertemperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.outTemperature
     *
     * @return the value of device_use_log.outTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getOuttemperature() {
        return outtemperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.outTemperature
     *
     * @param outtemperature the value for device_use_log.outTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setOuttemperature(String outtemperature) {
        this.outtemperature = outtemperature == null ? null : outtemperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.flowGrade
     *
     * @return the value of device_use_log.flowGrade
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getFlowgrade() {
        return flowgrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.flowGrade
     *
     * @param flowgrade the value for device_use_log.flowGrade
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setFlowgrade(String flowgrade) {
        this.flowgrade = flowgrade == null ? null : flowgrade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.flowSpeed
     *
     * @return the value of device_use_log.flowSpeed
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getFlowspeed() {
        return flowspeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.flowSpeed
     *
     * @param flowspeed the value for device_use_log.flowSpeed
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setFlowspeed(String flowspeed) {
        this.flowspeed = flowspeed == null ? null : flowspeed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.batteryVoltage
     *
     * @return the value of device_use_log.batteryVoltage
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getBatteryvoltage() {
        return batteryvoltage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.batteryVoltage
     *
     * @param batteryvoltage the value for device_use_log.batteryVoltage
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setBatteryvoltage(String batteryvoltage) {
        this.batteryvoltage = batteryvoltage == null ? null : batteryvoltage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.batteryTemperature
     *
     * @return the value of device_use_log.batteryTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getBatterytemperature() {
        return batterytemperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.batteryTemperature
     *
     * @param batterytemperature the value for device_use_log.batteryTemperature
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setBatterytemperature(String batterytemperature) {
        this.batterytemperature = batterytemperature == null ? null : batterytemperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_use_log.startTime
     *
     * @return the value of device_use_log.startTime
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_use_log.startTime
     *
     * @param starttime the value for device_use_log.startTime
     *
     * @mbggenerated Fri Oct 20 21:55:42 GMT+08:00 2017
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }
}