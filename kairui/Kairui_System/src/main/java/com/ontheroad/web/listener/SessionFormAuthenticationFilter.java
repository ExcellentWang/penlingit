package com.ontheroad.web.listener;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class SessionFormAuthenticationFilter extends FormAuthenticationFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {  
            if (isLoginSubmission(request, response)) {  
                return executeLogin(request, response);  
            } else {  
                return true;  
            }  
        } else {  
        	 HttpServletRequest  req = WebUtils.toHttp(request);
        	 if(req.getSession().getAttribute("user")==null){
        		 if(req.getHeader("x-requested-with")!=null
     					&& req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
//             		resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);  
//                     redirectToLogin(request, response); 
             		PrintWriter printWriter = response.getWriter();
     				//必须给easyui返回rows属性，easyui框架的bug
     				response.setContentType("application/json");
     				String loginUrl=req.getContextPath()+getLoginUrl();
     				printWriter.print("{\"sessionState\":\"0\",\"loginUrl\":\""+loginUrl+"\",\"rows\":[]}");
     				printWriter.flush();
     				printWriter.close();
                 }else{
                	this.redirectToLogin(request, response);
                 }
                 return false;
        	 }
        	 return true; 
        } 
	}
	
}
