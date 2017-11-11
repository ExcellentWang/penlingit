package com.ontheroad.entity;

public class EquipmentDatatype {
    /**
     * 
     */
    private Integer equipmentType;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 保修期
     */
    private Integer period;

    /**
     * 型号说明
     */
    private String remark;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 设备类型
     */
    private String type;

    /**
     * 获取
     *
     * @cgw 2017-11-11 17:07:38
     */
    public Integer getEquipmentType() {
        return equipmentType;
    }

    /**
     * 设置
     *
     * @param equipmentType the value for equipment_datatype.equipment_type
     *
     * @cgw 2017-11-11 17:07:38
     */
    public void setEquipmentType(Integer equipmentType) {
        this.equipmentType = equipmentType;
    }

    /**
     * 获取设备名称
     *
     * @cgw 2017-11-11 17:07:38
     */
    public String getEquipmentName() {
        return equipmentName;
    }

    /**
     * 设置设备名称
     *
     * @param equipmentName the value for equipment_datatype.equipment_name
     *
     * @cgw 2017-11-11 17:07:38
     */
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    /**
     * 获取保修期
     *
     * @cgw 2017-11-11 17:07:38
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置保修期
     *
     * @param period the value for equipment_datatype.period
     *
     * @cgw 2017-11-11 17:07:38
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取型号说明
     *
     * @cgw 2017-11-11 17:07:38
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置型号说明
     *
     * @param remark the value for equipment_datatype.remark
     *
     * @cgw 2017-11-11 17:07:38
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取产品名称
     *
     * @cgw 2017-11-11 17:07:38
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName the value for equipment_datatype.product_name
     *
     * @cgw 2017-11-11 17:07:38
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取设备类型
     *
     * @cgw 2017-11-11 17:07:38
     */
    public String getType() {
        return type;
    }

    /**
     * 设置设备类型
     *
     * @param type the value for equipment_datatype.type
     *
     * @cgw 2017-11-11 17:07:38
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}