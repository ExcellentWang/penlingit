package com.ontheroad.entity;

import java.util.Date;

public class Lunbo {
    /**
     * 
     */
    private Long id;

    /**
     * 轮播标题
     */
    private String title;

    /**
     * 排序
     */
    private Long lunboOrder;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 
     */
    private Date ctime;

    /**
     * 地址
     */
    private String address;

    /**
     * 缩略图地址
     */
    private String sImg;

    /**
     * 编辑器内容
     */
    private String content;

    /**
     * 获取
     *
     * @cgw 2017-11-03 14:11:40
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id the value for lunbo.id
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取轮播标题
     *
     * @cgw 2017-11-03 14:11:40
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置轮播标题
     *
     * @param title the value for lunbo.title
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取排序
     *
     * @cgw 2017-11-03 14:11:40
     */
    public Long getLunboOrder() {
        return lunboOrder;
    }

    /**
     * 设置排序
     *
     * @param lunboOrder the value for lunbo.lunbo_order
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setLunboOrder(Long lunboOrder) {
        this.lunboOrder = lunboOrder;
    }

    /**
     * 获取是否显示
     *
     * @cgw 2017-11-03 14:11:40
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示
     *
     * @param isShow the value for lunbo.is_show
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取
     *
     * @cgw 2017-11-03 14:11:40
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置
     *
     * @param ctime the value for lunbo.ctime
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取地址
     *
     * @cgw 2017-11-03 14:11:40
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address the value for lunbo.address
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取缩略图地址
     *
     * @cgw 2017-11-03 14:11:40
     */
    public String getsImg() {
        return sImg;
    }

    /**
     * 设置缩略图地址
     *
     * @param sImg the value for lunbo.s_img
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setsImg(String sImg) {
        this.sImg = sImg == null ? null : sImg.trim();
    }

    /**
     * 获取编辑器内容
     *
     * @cgw 2017-11-03 14:11:40
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置编辑器内容
     *
     * @param content the value for lunbo.content
     *
     * @cgw 2017-11-03 14:11:40
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}