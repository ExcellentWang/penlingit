package com.ontheroad.system.service;


import java.util.List;

import com.ontheroad.system.entity.SysMenu;
import org.springframework.remoting.service.annotation.RemoteService;

import cn.modoumama.service.base.BaseService;

@RemoteService
public interface SysMenuService extends BaseService<SysMenu,Long>{
	
	/**
	 * 加载所有的菜单
	 * @param expanded 是否展开菜单
	 * @param hideDisableMenus 是否隐藏无效的菜单（被禁用的）
	 * @return
	 */
	public String listTree(boolean expanded,boolean hideDisableMenus);	
	
	/**
	 * 加载某个用户拥有权限的菜单
	 * @param userId 用户ID
	 * @param expanded 是否展开菜单
	 * @return
	 */
	public String listUserTree(Long userId,boolean expanded);
	
	public  List<SysMenu>  selectMenusByUserId(Long userId);
	
	public void deleteMenuByIds(String[] menuIds);
}
