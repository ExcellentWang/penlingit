package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.TsMenu;


public interface MenuService {
	/**
	 * 
	* 
	* @Description: 获取用户菜单
	* @param userId
	* @return    
	* List<TsMenu>    
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	List<TsMenu> getMenusUser(Integer userId);
}
