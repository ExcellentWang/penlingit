package com.ontheroad.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class ServiceDetail implements Serializable {
    private static final long serialVersionUID = 1L;
/*
    @Getter @Setter private int id;
    @Getter @Setter private int service_id;
    @Getter @Setter private Date time;
    @Getter @Setter private String detail;*/
    private int id;
    private int service_id;
    private Date time;
    private String detail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
    
    
    
}
