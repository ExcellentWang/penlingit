package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.util.Date;

public class Lunbo implements Serializable{
    private Long id;

    private String title;

    private Long lunboOrder;

    private Integer isShow;

    private Date ctime;

    private String sImg;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getLunboOrder() {
        return lunboOrder;
    }

    public void setLunboOrder(Long lunboOrder) {
        this.lunboOrder = lunboOrder;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getsImg() {
        return sImg;
    }

    public void setsImg(String sImg) {
        this.sImg = sImg == null ? null : sImg.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}