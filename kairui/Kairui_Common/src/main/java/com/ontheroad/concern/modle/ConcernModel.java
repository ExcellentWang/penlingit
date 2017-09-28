package com.ontheroad.concern.modle;

import java.io.Serializable;

import org.mybatis.annotation.Table;


/**
 * Concern对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "concern")
public class ConcernModel implements Serializable {

    /**
     * uid
     */
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /**
     * 用户ID(对应用户表）
     */
    private Integer userid;
    /**
     * 被关注的用户ID(对应用户表）
     */
    private Integer friendid;

    public ConcernModel() {
    }

    public ConcernModel(Integer userid, Integer friendid) {
        this.userid = userid;
        this.friendid = friendid;
    }

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
     * 取得用户ID(对应用户表）
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户ID(对应用户表）
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 取得被关注的用户ID(对应用户表）
     */
    public Integer getFriendid() {
        return friendid;
    }

    /**
     * 设置被关注的用户ID(对应用户表）
     */
    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }


}
