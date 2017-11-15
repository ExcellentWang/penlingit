package com.ontheroad.like.model;

import java.io.Serializable;

import com.ontheroad.user.model.UserModel;
import org.mybatis.annotation.Table;


/**
 * CircleLike对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "circle_like", notColumn = {"user"})
public class CircleLikeModel implements Serializable {

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
     * 点赞的用户ID
     */
    private Integer userId;

    private UserModel user;

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
     * 取得点赞的用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置点赞的用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
