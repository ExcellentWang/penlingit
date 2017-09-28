package com.ontheroad.core.exception;

import java.io.Serializable;

public class SystemException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 3787730660315875183L;
	private Exception exception;
	private String message;
	private String code;
	private String title = "系统出错了！";

	public Exception getException() {
		return this.exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SystemException(String code, String message, Exception e) {
		super(message);
		this.message = message;
		this.code = code;
		this.exception = e;
	}

	public SystemException(String code, String title, String message,
			Exception e) {
		super(message);
		this.message = message;
		this.code = code;
		this.exception = e;
		this.title = title;
	}

	public SystemException(String message, Exception e) {
		super(message);
		this.code = "0000";
		this.exception = e;
	}

	public String getTitle() {
		if ((this.title == null) || ("".equals(this.title))) {
			return "系统异常！";
		}
		return this.title;
	}
}