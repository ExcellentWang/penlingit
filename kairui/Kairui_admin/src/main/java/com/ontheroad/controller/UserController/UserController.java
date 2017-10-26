package com.ontheroad.controller.UserController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ontheroad.core.util.SessionUtil;
import com.ontheroad.entity.TsMenu;
import com.ontheroad.entity.TsUser;
import com.ontheroad.service.UserService;
import com.ontheroad.utils.MapUtil;
import com.ontheroad.utils.WebUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 
	* 
	* @Description: 登录
	* @param user
	* @return    
	* String    
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Map<Object, Object> login(TsUser user, HttpServletRequest request){
		TsUser user2=userService.login(user);
		if(user2==null){
			return MapUtil.getFailureJson("用户名或者密码错误");
		}
		//存session
		request.getSession().setAttribute("user", user2);
		return MapUtil.getSuccessJson();
	}
	/**
	 * 用户拥有菜单
	 * @param user
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMenuByUser")
	public Map<Object, Object> getMenuByUser(HttpServletRequest request){
		TsUser user=SessionUtil.getUser(request);
		List<TsMenu> ls=userService.getMenu(user);
		return MapUtil.getSuccessJson(ls);
	}
}
