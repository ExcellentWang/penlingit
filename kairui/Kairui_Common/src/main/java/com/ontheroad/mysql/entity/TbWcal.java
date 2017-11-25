package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.util.Date;

public class TbWcal implements Serializable{
    /**
     * 
     */
    private Long id;

    /**
     * 热水偏差  取值范围：-5.0---5.0
     */
    private String hotWaterDe;

    /**
     * 冷水偏差  取值范围：-5.0---5.0
     */
    private String codeWaterDe;

    /**
     * 出水偏差  取值范围：-5.0---5.0
     */
    private String cWaterDe;

    /**
     * 混水偏差    取值范围：-5.0---5.0
     */
    private String hWaterDe;

    /**
     * 混水阀
     */
    private String mixingValve;

    /**
     * 流量系数
     */
    private String dischargeCoefficient;

    /**
     * 温度B值
     */
    private String temperatureBValue;

    /**
     * 
     */
    private Date createTime;

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 获取
     *
     * @cgw 2017-11-25 14:40:46
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id the value for tb_wcal.id
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取热水偏差  取值范围：-5.0---5.0
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getHotWaterDe() {
        return hotWaterDe;
    }

    /**
     * 设置热水偏差  取值范围：-5.0---5.0
     *
     * @param hotWaterDe the value for tb_wcal.hot_water_de
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setHotWaterDe(String hotWaterDe) {
        this.hotWaterDe = hotWaterDe == null ? null : hotWaterDe.trim();
    }

    /**
     * 获取冷水偏差  取值范围：-5.0---5.0
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getCodeWaterDe() {
        return codeWaterDe;
    }

    /**
     * 设置冷水偏差  取值范围：-5.0---5.0
     *
     * @param codeWaterDe the value for tb_wcal.code_water_de
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setCodeWaterDe(String codeWaterDe) {
        this.codeWaterDe = codeWaterDe == null ? null : codeWaterDe.trim();
    }

    /**
     * 获取出水偏差  取值范围：-5.0---5.0
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getcWaterDe() {
        return cWaterDe;
    }

    /**
     * 设置出水偏差  取值范围：-5.0---5.0
     *
     * @param cWaterDe the value for tb_wcal.c_water_de
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setcWaterDe(String cWaterDe) {
        this.cWaterDe = cWaterDe == null ? null : cWaterDe.trim();
    }

    /**
     * 获取混水偏差    取值范围：-5.0---5.0
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String gethWaterDe() {
        return hWaterDe;
    }

    /**
     * 设置混水偏差    取值范围：-5.0---5.0
     *
     * @param hWaterDe the value for tb_wcal.h_water_de
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void sethWaterDe(String hWaterDe) {
        this.hWaterDe = hWaterDe == null ? null : hWaterDe.trim();
    }

    /**
     * 获取混水阀
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getMixingValve() {
        return mixingValve;
    }

    /**
     * 设置混水阀
     *
     * @param mixingValve the value for tb_wcal.mixing_valve
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setMixingValve(String mixingValve) {
        this.mixingValve = mixingValve == null ? null : mixingValve.trim();
    }

    /**
     * 获取流量系数
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getDischargeCoefficient() {
        return dischargeCoefficient;
    }

    /**
     * 设置流量系数
     *
     * @param dischargeCoefficient the value for tb_wcal.discharge_coefficient
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setDischargeCoefficient(String dischargeCoefficient) {
        this.dischargeCoefficient = dischargeCoefficient == null ? null : dischargeCoefficient.trim();
    }

    /**
     * 获取温度B值
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getTemperatureBValue() {
        return temperatureBValue;
    }

    /**
     * 设置温度B值
     *
     * @param temperatureBValue the value for tb_wcal.temperature_b_value
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setTemperatureBValue(String temperatureBValue) {
        this.temperatureBValue = temperatureBValue == null ? null : temperatureBValue.trim();
    }

    /**
     * 获取
     *
     * @cgw 2017-11-25 14:40:46
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     *
     * @param createTime the value for tb_wcal.create_time
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取设备编号
     *
     * @cgw 2017-11-25 14:40:46
     */
    public String getDeviceNo() {
        return deviceNo;
    }

    /**
     * 设置设备编号
     *
     * @param deviceNo the value for tb_wcal.device_no
     *
     * @cgw 2017-11-25 14:40:46
     */
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }
}