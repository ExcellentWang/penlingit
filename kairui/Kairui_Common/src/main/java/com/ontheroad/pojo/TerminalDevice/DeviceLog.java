package com.ontheroad.pojo.TerminalDevice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备数据上传日志
 */
public class DeviceLog implements Serializable {
    private static final long serialVersionUID = 1L;

     private Integer id;
     private String deviceType;
     private String deviceID;
     private String command;
     private String args;
     private String raw;
     private Date createdAt;
     private String ip;
     private Integer port;
     private String direction; // RECV, SEND;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
     
}
