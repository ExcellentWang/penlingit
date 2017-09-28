package com.ontheroad.circle.model;

import java.util.Date;
import java.io.Serializable;

import org.mybatis.annotation.Table;


/**
 * Reply对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "reply", notColumn = {"nick", "pic"})
public class ReplyModel implements Serializable {

    /**
     * uid
     */
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /**
     * 动态ID
     */
    private Integer circleId;
    /**
     * 评论ID
     */
    private Integer commentId;
    /**
     * 被回复的用户ID
     */
    private Integer userId;
    /**
     * 回复用户ID(即当前发言人）
     */
    private Integer replyUserId;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private Date createTime;


    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 用户头像
     */
    private String pic;

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
     * 取得动态ID
     */
    public Integer getCircleId() {
        return circleId;
    }

    /**
     * 设置动态ID
     */
    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    /**
     * 取得评论ID
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置评论ID
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 取得被回复的用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置被回复的用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 取得回复用户ID(即当前发言人）
     */
    public Integer getReplyUserId() {
        return replyUserId;
    }

    /**
     * 设置回复用户ID(即当前发言人）
     */
    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * 取得内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 取得时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
