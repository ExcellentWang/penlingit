package com.ontheroad.entity;


public class Login implements java.io.Serializable {
	 
	private static final long serialVersionUID = -6747442729723757060L;
	private String username;
	private String password; 

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
}
