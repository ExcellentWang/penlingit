package com.ontheroad.api.request;

import com.ontheroad.api.validate.BaseGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by kedong on 2017/6/20 0020.
 */
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -7074408113323429334L;

    public interface regist extends BaseGroup {
    }

    public interface login extends BaseGroup {
    }

    public interface getVerification extends BaseGroup {
    }

    public interface resetPassword {
    }

    public interface forgotPassword extends BaseGroup {
    }

    public interface friendIndex {
    }


    /**
     * 自己的ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    @NotNull(groups = {BaseGroup.class})
    private String mobile;

    /**
     * 密码
     */
    @NotNull(groups = {
            regist.class,
            login.class,
            forgotPassword.class,
            resetPassword.class
    })
    private String password;

    /**
     * 验证码
     */
    @NotNull(groups = {forgotPassword.class, regist.class})
    private String verification;


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
     * 手机系统类型
     * 1：Android
     * 2：iOS
     */
    private Integer systemType;

    /**
     * 推送ID
     */
    private String clientId;

    //昵称
    private String nick;
    //地址
    private String address;
    //年龄
    private Integer age;
    //性别 0：女 1：男
    private Boolean sex;
    //头像
    private String pic;

    // 用户ID
    @NotNull(groups = friendIndex.class)
    private Integer targetId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }
}
