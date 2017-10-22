package com.ontheroad.entity;

import java.util.Date;

public class GuaranteeType {
    private Long id;

    private String deviceType;

    private String typeName;

    private Date createTime;

    private Date motifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMotifyTime() {
        return motifyTime;
    }

    public void setMotifyTime(Date motifyTime) {
        this.motifyTime = motifyTime;
    }
}