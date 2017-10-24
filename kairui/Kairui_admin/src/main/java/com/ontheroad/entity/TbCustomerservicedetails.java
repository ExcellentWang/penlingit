package com.ontheroad.entity;

import java.util.Date;

public class TbCustomerservicedetails {
    private Integer servicedetailId;

    private Integer customerId;

    private Integer equipmentId;

    private Integer userId;

    private Date time;

    private String personname;

    private String personphone;

    private String content;

    public Integer getServicedetailId() {
        return servicedetailId;
    }

    public void setServicedetailId(Integer servicedetailId) {
        this.servicedetailId = servicedetailId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname == null ? null : personname.trim();
    }

    public String getPersonphone() {
        return personphone;
    }

    public void setPersonphone(String personphone) {
        this.personphone = personphone == null ? null : personphone.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}