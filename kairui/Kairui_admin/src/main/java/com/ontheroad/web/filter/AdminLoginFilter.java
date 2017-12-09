package com.ontheroad.web.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ontheroad.entity.TsUser;
import com.ontheroad.utils.MapUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        TsUser user = (TsUser) session.getAttribute("user");
        String url=request.getRequestURI();
        if(user!=null){
            //登陆成功的用户
            return true;
        }else {
        	if("/".equals(url)){
        		 //没有登陆，转向登陆界面
            	response.sendRedirect("/view/index.html");
                return false;
        	}
        	try {
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(MapUtil.getFailureJson("SESSION超时", 30000));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	 
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
