package com.ontheroad.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(pkId = "menuId",  tabName = "t_s_menu")
public class SysMenu implements Serializable{
	private static final long serialVersionUID = 7356082631048900404L;

	//资源ID
	private Long menuId; 

	//资源名称
	private String menuName; 

	//资源描述
	private String menuDesc; 

	//资源URL
	private String menuUrl; 

	//资源PID
	private Integer menuPid; 

	//资源类型，1：文件夹菜单，2：功能菜单，3：功能按钮
	private Integer menuType; 

	//资源状态， 1：启用， 0：禁
	private Integer menuStatus; 

	//资源级别
	private Integer menuLevel; 

	//资源图标
	private String menuIcon; 

	//创建时间
	private Date createTime; 

	//更新时间
	private Date updateTime; 

	//菜单排序
	private Integer menuOrder; 

	public Long  getMenuId(){
		return this.menuId;
	}

	public void setMenuId(Long menuId){
		this.menuId=menuId;
	}

	public String  getMenuName(){
		return this.menuName;
	}

	public void setMenuName(String menuName){
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String  getMenuDesc(){
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc){
		this.menuDesc = menuDesc == null ? null : menuDesc.trim();
	}

	public String  getMenuUrl(){
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Integer  getMenuPid(){
		return this.menuPid;
	}

	public void setMenuPid(Integer menuPid){
		this.menuPid=menuPid;
	}

	public Integer  getMenuType(){
		return this.menuType;
	}

	public void setMenuType(Integer menuType){
		this.menuType=menuType;
	}

	public Integer  getMenuStatus(){
		return this.menuStatus;
	}

	public void setMenuStatus(Integer menuStatus){
		this.menuStatus=menuStatus;
	}

	public Integer  getMenuLevel(){
		return this.menuLevel;
	}

	public void setMenuLevel(Integer menuLevel){
		this.menuLevel=menuLevel;
	}

	public String  getMenuIcon(){
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon){
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
	}

	public Date  getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date  getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Integer  getMenuOrder(){
		return this.menuOrder;
	}

	public void setMenuOrder(Integer menuOrder){
		this.menuOrder=menuOrder;
	}
}
