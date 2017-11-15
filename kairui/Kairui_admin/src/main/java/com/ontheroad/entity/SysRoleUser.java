package com.ontheroad.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;

@Table(pkId = "roleUserId", tabName = "t_s_role_user")
public class SysRoleUser implements Serializable{

	private static final long serialVersionUID = -4959799737486238494L;

	//自增长，主键
	private Long roleUserId; 

	//用户ID
	private Long userId; 

	//角色ID
	private Long roleId; 

	public Long  getRoleUserId(){
		return this.roleUserId;
	}

	public void setRoleUserId(Long roleUserId){
		this.roleUserId=roleUserId;
	}

	public Long  getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
}
