package com.ontheroad.entity;

import java.util.Date;

public class Staff {
    private Long id;

    private String name;

    private String phone;

    private String staffNum;

    private Date createTime;

    private Date motifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum == null ? null : staffNum.trim();
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