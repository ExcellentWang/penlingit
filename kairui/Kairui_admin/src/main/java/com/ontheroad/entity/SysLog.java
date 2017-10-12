package com.ontheroad.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(pkId = "logId",tabName = "t_penlin_log")
public class SysLog implements Serializable{
	private static final long serialVersionUID = 5807793379961468388L;

	//日志ID
	private Long logId; 

	//访问URL
	private String actionUrl; 

	//访问时间
	private Date logTime; 

	//用户IP
	private String userIp; 

	//用户ID
	private Long userId; 

	//日志描述
	private String logDesc; 

	//用户名称
	private String userName; 

	//操作响应时间
	private Long processTime; 

	//控制器名称
	private String controllerName; 

	//控制器方法名称
	private String controllerMethod; 

	public Long  getLogId(){
		return this.logId;
	}

	public void setLogId(Long logId){
		this.logId=logId;
	}

	public String  getActionUrl(){
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl){
		this.actionUrl = actionUrl == null ? null : actionUrl.trim();
	}

	public Date  getLogTime(){
		return this.logTime;
	}

	public void setLogTime(Date logTime){
		this.logTime=logTime;
	}

	public String  getUserIp(){
		return this.userIp;
	}

	public void setUserIp(String userIp){
		this.userIp = userIp == null ? null : userIp.trim();
	}

	public Long  getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public String  getLogDesc(){
		return this.logDesc;
	}

	public void setLogDesc(String logDesc){
		this.logDesc = logDesc == null ? null : logDesc.trim();
	}

	public String  getUserName(){
		return this.userName;
	}

	public void setUserName(String userName){
		this.userName = userName == null ? null : userName.trim();
	}

	public Long  getProcessTime(){
		return this.processTime;
	}

	public void setProcessTime(Long processTime){
		this.processTime=processTime;
	}

	public String  getControllerName(){
		return this.controllerName;
	}

	public void setControllerName(String controllerName){
		this.controllerName = controllerName == null ? null : controllerName.trim();
	}

	public String  getControllerMethod(){
		return this.controllerMethod;
	}

	public void setControllerMethod(String controllerMethod){
		this.controllerMethod = controllerMethod == null ? null : controllerMethod.trim();
	}
}
