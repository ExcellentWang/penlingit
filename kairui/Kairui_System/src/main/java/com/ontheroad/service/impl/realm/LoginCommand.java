package com.ontheroad.service.impl.realm;
public class LoginCommand implements java.io.Serializable {

    private static final long serialVersionUID = -2323421779755786226L;
	private String userName;
	private String passWord;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
