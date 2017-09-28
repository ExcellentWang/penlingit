package com.ontheroad.core.exception;

import java.io.Serializable;

public abstract class BaseException extends RuntimeException
  implements Serializable
{
  private static final long serialVersionUID = 1311416870635146320L;
  protected Exception exception;
  protected String message;
  protected String code;
  protected String title = "系统出错了！";

  protected abstract String getTitle();

  public BaseException(String message)
  {
    super(message);
  }

  public Exception getException()
  {
    return this.exception;
  }

  public void setException(Exception exception)
  {
    this.exception = exception;
  }

  public String getMessage()
  {
    return this.message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }
}