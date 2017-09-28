package com.ontheroad.system.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(pkId = "roleId",  tabName = "t_s_role")
public class SysRole implements Serializable{
	private static final long serialVersionUID = 7153762069085534888L;

	//角色ID
	private Long roleId; 

	//角色名称
	private String roleName; 

	//描述
	private String roleDesc; 

	//
	private Integer roleOrder; 

	//角色类型
	private Integer roleType; 

	//角色状态
	private Integer roleStatus; 

	//
	private Date createTime; 

	//
	private Date updateTime; 

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}

	public String  getRoleName(){
		return this.roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String  getRoleDesc(){
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc){
		this.roleDesc = roleDesc == null ? null : roleDesc.trim();
	}

	public Integer  getRoleOrder(){
		return this.roleOrder;
	}

	public void setRoleOrder(Integer roleOrder){
		this.roleOrder=roleOrder;
	}

	public Integer  getRoleType(){
		return this.roleType;
	}

	public void setRoleType(Integer roleType){
		this.roleType=roleType;
	}

	public Integer  getRoleStatus(){
		return this.roleStatus;
	}

	public void setRoleStatus(Integer roleStatus){
		this.roleStatus=roleStatus;
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
}
