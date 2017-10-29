package com.ontheroad.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ontheroad.entity.TsMenu;
import com.ontheroad.entity.TsUser;
/**
 * 用户相关
 * @author qbm
 *
 */
public interface UserService {
	/**
	 * 
	* 
	* @Description: 登录
	* @param user
	* @return    
	* TsUser    
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	TsUser login(TsUser user);
	/**
	 * 查询所有主菜单
	 * @param user
	 * @return
	 */
	List<TsMenu> getMenu(TsUser user);
	/**
	 * 根据主菜单查询所有子菜单
	 * @param user_id
	 * @param menu_id
	 * @return
	 */
	List<TsMenu> getMenusUserByParentId(Integer user_id, Integer menu_id);
}
