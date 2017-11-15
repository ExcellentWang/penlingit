package com.ontheroad.api.response;

import com.ontheroad.user.model.UserModel;

import java.io.Serializable;

/**
 * Created by kedong on 2017/6/21 0021.
 */
public class UserResponse implements Serializable {
    private static final long serialVersionUID = -8147922247114618822L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 用户头像
     */
    private String pic;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
