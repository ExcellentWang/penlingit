package com.ontheroad.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;

@Table(pkId = "roleMenuId",tabName = "t_s_role_menu")
public class SysRoleMenu implements Serializable{
	private static final long serialVersionUID = -7189337772149196400L;

	//自增长ID，主键
	private Long roleMenuId; 

	//角色ID
	private Long roleId; 

	//资源ID
	private Long menuId; 

	public Long  getRoleMenuId(){
		return this.roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId){
		this.roleMenuId=roleMenuId;
	}

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}

	public Long  getMenuId(){
		return this.menuId;
	}

	public void setMenuId(Long menuId){
		this.menuId=menuId;
	}
}
