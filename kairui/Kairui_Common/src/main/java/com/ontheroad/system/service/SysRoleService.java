package com.ontheroad.system.service;

import java.util.List;

import com.ontheroad.system.entity.SysRole;
import com.ontheroad.system.entity.SysRoleMenu;
import com.ontheroad.system.entity.SysRoleUser;
import org.springframework.remoting.service.annotation.RemoteService;

import cn.modoumama.service.base.BaseService;

@RemoteService
public interface SysRoleService extends BaseService<SysRole,Long>{
	
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId);
	
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId);
	
	public void  bindRoleMenu(Long roleId,String[] menuIds);
	
	public void  bindRoleUser(Long roleId,String[] userIds);
	
	public  void  deleteRoleByIds(String[] roleIds);
	
	public List<SysRole> selectRolesByUserId(Long userId);
	
}
