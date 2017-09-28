package com.ontheroad.api.response;

import com.ontheroad.path.model.PathUserModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kedong on 2017/6/29 0029.
 */
public class CircleResponse implements Serializable {

    private static final long serialVersionUID = -2437666097612511338L;

    /**  */
    private Integer id;

    /**
     * 文字内容
     */
    private String content;
    /**
     * 图片(数组)
     */
    private String images;

    /**
     * 地点
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 轨迹
     */
    private Integer pathid;
    /**
     * 该轨迹的长度(公里）
     */
    private String kilometer;

    /**
     * 时长
     */
    private Double time;
    /**
     * 轨迹缩略图
     */
    private String pathIconUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPathid() {
        return pathid;
    }

    public void setPathid(Integer pathid) {
        this.pathid = pathid;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getPathIconUrl() {
        return pathIconUrl;
    }

    public void setPathIconUrl(String pathIconUrl) {
        this.pathIconUrl = pathIconUrl;
    }
}
