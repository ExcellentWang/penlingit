package com.ontheroad.pojo.Admin;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password;
    private Date last_login_at;
    private Date created_at;
    private Date updated_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getLast_login_at() {
		return last_login_at;
	}
	public void setLast_login_at(Date last_login_at) {
		this.last_login_at = last_login_at;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
    
}
