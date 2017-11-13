package com.ontheroad.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/*
 * app用户基本信息表
 * 
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;  //唯一标识
	private String username;  //用户名
	private String password;  //密码
	private String sex;       //性别 0男 1女
	private String phone;     //手机
	private String headPortrait;  //头像地址
	private Date registerTime;  //注册时间
	private Date loginTime; //上次登陆时间
	private String phoneModel; //手机型号
	private String appVersion; //app版本号
	private String token; //权限标识
	private String installationId;//推送id
	

	public String getInstallationId() {
		return installationId;
	}
	public void setInstallationId(String installationId) {
		this.installationId = installationId;
	}
	private Integer owned_devices_count;
	
	
	public Integer getOwned_devices_count() {
		return owned_devices_count;
	}
	public void setOwned_devices_count(Integer owned_devices_count) {
		this.owned_devices_count = owned_devices_count;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
