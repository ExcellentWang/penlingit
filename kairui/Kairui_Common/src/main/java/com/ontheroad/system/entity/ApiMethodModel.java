package com.ontheroad.system.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;


/**
 * TSApiMethod对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "t_s_api_method")
public class ApiMethodModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /**  */
    private Integer	id;
    /** 接口名(对外） */
    private String	name;
    /** 接口bean名称 */
    private String	beanName;
    /** 接口方法名 */
    private String	methodName;
    /** 参数类型,如有多个用逗号隔开(带包名的全路径) */
    private String	parmetersName;
    /** 是否需要token（0:不需要；1:需要） */
    private Boolean	needToken;
    /** 是否启用(0:禁用；1:启用） */
    private Boolean	isEnabled;
    /** 是否为更新系接口 */
    private Boolean	isUpdate;
    /** 版本 */
    private String	ver;
    /**  */
    private Date	createTime;
	/** 是否为https */
	private Boolean	isHttps;


	/** 取得 */
	public Integer getId() {
		return id;
	}
	
	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得接口名(对外） */
	public String getName() {
		return name;
	}
	
	/** 设置接口名(对外） */
	public void setName(String name) {
		this.name = name;
	}
	/** 取得接口bean名称 */
	public String getBeanName() {
		return beanName;
	}
	
	/** 设置接口bean名称 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	/** 取得接口方法名 */
	public String getMethodName() {
		return methodName;
	}
	
	/** 设置接口方法名 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/** 取得参数类型,如有多个用逗号隔开(带包名的全路径) */
	public String getParmetersName() {
		return parmetersName;
	}
	
	/** 设置参数类型,如有多个用逗号隔开(带包名的全路径) */
	public void setParmetersName(String parmetersName) {
		this.parmetersName = parmetersName;
	}
	/** 取得是否需要token（0:不需要；1:需要） */
	public Boolean getNeedToken() {
		return needToken;
	}
	
	/** 设置是否需要token（0:不需要；1:需要） */
	public void setNeedToken(Boolean needToken) {
		this.needToken = needToken;
	}
	/** 取得是否启用(0:禁用；1:启用） */
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	
	/** 设置是否启用(0:禁用；1:启用） */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	/** 取得是否为更新系接口 */
	public Boolean getIsUpdate() {
		return isUpdate;
	}
	
	/** 设置是否为更新系接口 */
	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	/** 取得版本 */
	public String getVer() {
		return ver;
	}
	
	/** 设置版本 */
	public void setVer(String ver) {
		this.ver = ver;
	}
	/** 取得 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setIsHttps(Boolean isHttps){
		this.isHttps = isHttps;
	}

	public Boolean getIsHttps(){
		return isHttps;
	}
}
