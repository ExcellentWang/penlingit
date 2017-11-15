package com.ontheroad.circle.model;

import com.ontheroad.api.response.UserResponse;
import com.ontheroad.user.model.UserModel;
import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Comment对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "comment", notColumn = {"replyList", "nick", "pic"})
public class CommentModel implements Serializable {

    /**
     * uid
     */
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /**
     * 被评论的动态ID(即该评论所依附的主体)(circle 表)
     */
    private Integer circleid;
    /**
     * 评论人(发言人)(user 表)
     */
    private Integer userid;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 该评论的回复列表
     */
    private List<ReplyModel> replyList = new ArrayList<>();

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
     * 取得被评论的动态ID(即该评论所依附的主体)(circle 表)
     */
    public Integer getCircleid() {
        return circleid;
    }

    /**
     * 设置被评论的动态ID(即该评论所依附的主体)(circle 表)
     */
    public void setCircleid(Integer circleid) {
        this.circleid = circleid;
    }

    /**
     * 取得评论人(发言人)(user 表)
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置评论人(发言人)(user 表)
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 取得评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 取得评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ReplyModel> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyModel> replyList) {
        this.replyList = replyList;
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
