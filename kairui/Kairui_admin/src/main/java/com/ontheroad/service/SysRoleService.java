package com.ontheroad.service;

import java.util.List;

import org.springframework.remoting.service.annotation.RemoteService;

import com.ontheroad.entity.SysRole;
import com.ontheroad.entity.SysRoleMenu;
import com.ontheroad.entity.SysRoleUser;

import cn.modoumama.service.base.BaseService;

public interface SysRoleService extends BaseService<SysRole,Long>{
	
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId);
	
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId);
	
	public void  bindRoleMenu(Long roleId,String[] menuIds);
	
	public void  bindRoleUser(Long roleId,String[] userIds);
	
	public  void  deleteRoleByIds(String[] roleIds);
	
	public List<SysRole> selectRolesByUserId(Long userId);
	
}
