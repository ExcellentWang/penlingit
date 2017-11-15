package com.ontheroad.circle.model;

import com.ontheroad.path.model.PathUserModel;
import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;


/**
 * Circle对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "circle", notColumn = {"path"})
public class CircleModel implements Serializable {
    private static final long serialVersionUID = 1469562719992247354L;
    /**  */
    private Integer id;
    /**
     * 分享动态的用户
     */
    private Integer userid;
    /**
     * 文字内容
     */
    private String content;
    /**
     * 图片(数组)
     */
    private String images;
    /**
     * 轨迹
     */
    private Integer pathid;
    /**
     * 地点
     */
    private String address;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 被收藏次数
     */
    private Integer collectionCount;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 轨迹信息
     */
    private PathUserModel path;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 取得
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 取得分享动态的用户
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置分享动态的用户
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 取得文字内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文字内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 取得图片(数组)
     */
    public String getImages() {
        return images;
    }

    /**
     * 设置图片(数组)
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * 取得轨迹
     */
    public Integer getPathid() {
        return pathid;
    }

    /**
     * 设置轨迹
     */
    public void setPathid(Integer pathid) {
        this.pathid = pathid;
    }

    /**
     * 取得地点
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 取得评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 取得点赞数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置点赞数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 取得被收藏次数
     */
    public Integer getCollectionCount() {
        return collectionCount;
    }

    /**
     * 设置被收藏次数
     */
    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * 取得创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PathUserModel getPath() {
        return path;
    }

    public void setPath(PathUserModel path) {
        this.path = path;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
