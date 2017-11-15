package com.kairui.test;

import javax.validation.constraints.NotNull;

/**
 * Created by kedong on 2017/6/20 0020.
 */
public class UserRequest extends BaseBean {


    private static final long serialVersionUID = -7938524052478718484L;

    public interface regist {

    }

    public interface login {

    }

    /**
     * 用户名
     */
    @NotNull(groups = {regist.class, login.class})
    private String mobile;

    /**
     * 密码
     */
    @NotNull(groups = {regist.class, login.class})
    private String password;

    /**
     * 验证码
     */
    @NotNull(groups = {regist.class})
    private String verification;

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

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId='" + getUserId() + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", verification='" + verification + '\'' +
                '}';
    }
}
