package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbInformation implements Serializable{
    private Integer id;

    private String title;

    private Date createtime;

    private String creator;

    private Integer informationtype;

    private Boolean isnew;

    private String content;

    private Integer equipmentId;

    private Integer userId;

    private BigDecimal useweight;

    private BigDecimal saveweight;

    private Integer isDelete;

    private String picture;

    private String address;

    private String summary;

    private Long newsId;

    private Long deviceInfoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getInformationtype() {
        return informationtype;
    }

    public void setInformationtype(Integer informationtype) {
        this.informationtype = informationtype;
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public BigDecimal getUseweight() {
        return useweight;
    }

    public void setUseweight(BigDecimal useweight) {
        this.useweight = useweight;
    }

    public BigDecimal getSaveweight() {
        return saveweight;
    }

    public void setSaveweight(BigDecimal saveweight) {
        this.saveweight = saveweight;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(Long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }
}