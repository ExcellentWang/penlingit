package com.ontheroad.service;

import java.util.List;

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
	
	List<TsMenu> getMenu(TsUser user);
	
}
