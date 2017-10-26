package com.ontheroad.core.util;

import javax.servlet.http.HttpServletRequest;

import com.ontheroad.entity.TsUser;

public class SessionUtil {
	public static TsUser getUser(HttpServletRequest request){
		TsUser user=(TsUser)request.getSession().getAttribute("user");
		return user;
	}
}
