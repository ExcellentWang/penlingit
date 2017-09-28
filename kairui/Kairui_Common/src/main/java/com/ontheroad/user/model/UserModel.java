package com.ontheroad.user.model;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;


/**
 * User对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "user", notColumn = {"isMutualConcern"})
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1427567219707457531L;
    /**  */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 头像
     */
    private String pic;
    /**
     * 自我介绍(个人签名)
     */
    private String intro;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 地区
     */
    private String address;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 上次登录时间
     */
    private Date loginTime;
    /**
     * 手机型号
     */
    private String phoneModel;
    /**
     * 手机系统版本号
     */
    private String systemVersion;
    /**
     * APP版本号
     */
    private String appVersion;
    /**
     * 是否可用
     * (0：禁用；1：可用)
     */
    private Boolean isEnable;
    /**
     * 粉丝数量
     */
    private Integer fansCount;
    /**
     * 关注的好友数量
     */
    private Integer friendCount;

    /**
     * 推送ID
     */
    private String clientId;

    /**
     * 手机系统类型
     * 1：Android
     * 2：iOS
     */
    private Integer systemType;

    /**
     * 账号等级
     */
    private Integer level;

    /**
     * 辅助字段
     */
    private Boolean isMutualConcern = false;

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
     * 取得用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 取得密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 取得昵称
     */
    public String getNick() {
        return nick;
    }

    /**
     * 设置昵称
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 取得头像
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置头像
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 取得自我介绍(个人签名)
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置自我介绍(个人签名)
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 取得年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 取得性别
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置性别
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 取得地区
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地区
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 取得注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得上次登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置上次登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 取得手机型号
     */
    public String getPhoneModel() {
        return phoneModel;
    }

    /**
     * 设置手机型号
     */
    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    /**
     * 取得手机系统版本号
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * 设置手机系统版本号
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    /**
     * 取得APP版本号
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * 设置APP版本号
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 取得是否可用
     * (0：禁用；1：可用)
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否可用
     * (0：禁用；1：可用)
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 取得粉丝数量
     */
    public Integer getFansCount() {
        return fansCount;
    }

    /**
     * 设置粉丝数量
     */
    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    /**
     * 取得关注的好友数量
     */
    public Integer getFriendCount() {
        return friendCount;
    }

    /**
     * 设置关注的好友数量
     */
    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Boolean getIsMutualConcern() {
        return isMutualConcern;
    }

    public void setIsMutualConcern(Boolean isMutualConcern) {
        this.isMutualConcern = isMutualConcern;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
