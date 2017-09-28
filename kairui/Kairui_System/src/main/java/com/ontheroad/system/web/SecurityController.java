package com.ontheroad.system.web;

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ontheroad.core.web.BaseController;
import com.ontheroad.service.impl.realm.LoginCommand;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.util.StringUtils;

/**
 * Web MVC controller that handles security-related web requests, such as login
 * and logout.
 */
@Controller
@Scope("session")
@RequestMapping(value = "/security")
public class SecurityController {
	
	private static Map<String, String> signedOnUserMap = new ConcurrentHashMap<String, String>();
	
	private static Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginForm(HttpServletRequest request) {
		return "login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@OpLog(logDesc="系统登陆")
	public void login(HttpServletRequest request, PrintWriter out,
			HttpSession session,LoginCommand command,BindingResult errors) {
		UsernamePasswordToken token = new UsernamePasswordToken(command.getUserName(), command.getPassWord(),request.getRemoteAddr());
		String msg = null;
		try {
			SecurityUtils.getSubject().login(token);
			System.out.println("..");
		} catch (AuthenticationException e) {
			logger.debug("Error authenticating.", e);
			//在运行时指出对象是否是特定类的一个实例
			if (e instanceof UnknownAccountException) {
				msg = "未知账户！";
			} else if (e instanceof LockedAccountException) {
				msg = "账户已锁定！";
			} else if (e instanceof IncorrectCredentialsException) {
				msg = "密码不正确！";
			} else if (e instanceof ExcessiveAttemptsException) {
				msg = "用户名或密码错误超过5次,请5分钟后重试";
			} else if (e instanceof ConcurrentAccessException) {
				msg = "用户已登录！";
			} else if (e instanceof org.apache.shiro.authc.AccountException) {
				msg = "未知帐号错误或用户状态异常！";
			} else if (e instanceof AuthenticationException) {
				msg = "认证失败！";
			}
			if (StringUtils.isEmpty(msg)) {
				msg = "用户名密码错误！";
			}
			errors.reject("error.invalidLogin","The username or password was not correct.");
		}
		boolean result = true;
		if (errors.hasErrors()) {
			result = false;
		} 
		BaseController.ajaxJsonResponse(out, result, msg);
	}

	public static void logout(String loginName) {
		signedOnUserMap.remove(loginName);
	}

	public static boolean isLogin(String loginName) {
		return signedOnUserMap.get(loginName) != null;
	}
	
	public static String getLastLoginIP(String loginName) {
		String ip = signedOnUserMap.get(loginName);
		return ip== null ? "": ip;
	}
	
	public static void login(String loginName, String ip) {
		signedOnUserMap.put(loginName, ip);
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			signedOnUserMap.remove(session.getAttribute("user"));
			SecurityUtils.getSubject().getSession().stop();
		} catch (Exception e) {
		}
		return "redirect:/";
	}
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
