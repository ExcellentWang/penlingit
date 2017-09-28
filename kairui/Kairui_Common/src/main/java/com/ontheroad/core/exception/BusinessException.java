package com.ontheroad.core.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 8109303352556055331L;
	private String code;
	private String message;
	private String title;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
	public BusinessException(String code, String title, String message) {
		super(title);
		this.code = code;
		this.title = title;
		this.message = message;
	}

	public BusinessException(String message) {
		super(message);
		this.code = "0000";
		this.message = message;
		this.title = "系统出错了！";
	}

	public BusinessException(String title, String message) {
		super(message);
		this.code = "0000";
		this.message = message;
		this.title = title;
	}
}