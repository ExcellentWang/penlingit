package com.ontheroad.core.exception;

public class DBException extends BaseException
{
  private static final long serialVersionUID = 3787730660315875183L;

  public DBException(String code, String message, Exception e)
  {
    super(message);
    this.message = message;
    this.code = code;
    this.exception = e;
  }

  public DBException(String code, String title, String message, Exception e)
  {
    super(message);
    this.message = message;
    this.code = code;
    this.exception = e;
    this.title = title;
  }

  public DBException(String message, Exception e)
  {
    super(message);
    this.code = "0002";
    this.exception = e;
  }

  public String getTitle()
  {
    if ((this.title == null) || ("".equals(this.title))) {
      return "数据库异常！";
    }
    return this.title;
  }
}