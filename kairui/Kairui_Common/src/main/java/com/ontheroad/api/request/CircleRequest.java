package com.ontheroad.api.request;

import javax.validation.constraints.NotNull;

/**
 * Created by kedong on 2017/6/29 0029.
 */
public class CircleRequest extends Request {

    private static final long serialVersionUID = 4661674343331106395L;

    public interface getUserCircleList {
    }

    public interface getCircleDetail {
    }

    public interface like {
    }

    public interface comment {
    }

    public interface reply {
    }

    public interface getCommentList {
    }

    public interface likeList {
    }

    /**
     * 动态ID
     */
    @NotNull(groups = {getCircleDetail.class, like.class, comment.class, likeList.class, getCommentList.class})
    private Integer circleId;

    /**
     * 用户id
     */
    @NotNull(groups = getUserCircleList.class)
    private Integer targetId;

    /**
     * 评论内容
     */
    @NotNull(groups = comment.class)
    private String content;

    /**
     * 父评论的ID（回复别人的评论时该字段必填，单纯评论动态时可为空）
     */
    private Integer commentId;

    /**
     * 被回复的用户ID
     */
    private Integer replyUserId;

    /**
     * 图片(数组)
     */
    private String images;
    /**
     * 轨迹 todo
     */
    private Integer pathid;
    /**
     * 地点
     */
    private String address;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;


    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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

    public Integer getPathid() {
        return pathid;
    }

    public void setPathid(Integer pathid) {
        this.pathid = pathid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }
}
