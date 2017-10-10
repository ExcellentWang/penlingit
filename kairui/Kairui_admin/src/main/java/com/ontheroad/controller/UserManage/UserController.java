package com.ontheroad.controller.UserManage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ontheroad.entity.TsUser;
import com.ontheroad.service.UserService;
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
	public String login(TsUser user, HttpServletRequest request){
		TsUser user2=userService.login(user);
		if(user2==null){
			return WebUtil.getFailureJson("用户名或者密码错误", 10001);
		}
		//存session
		request.getSession().setAttribute("user", user);
		return WebUtil.getSuccessJson();
	}
}
