package com.ontheroad.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ontheroad.tokenUtil.EhcacheUtil;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenFilter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//获取token  	
//    	String token = request.getParameter("token"); 
//    	
//    	String isHasToken = (String) EhcacheUtil.getInstance().get("token",token);
//  	
//    	if (StringUtils.isNotBlank(isHasToken)) {
//    		return true;
//    	}else{
//            Map<Object, Object> map = new HashMap<Object, Object>();
//      		
//    		map.put("code", "401");
//    		map.put("msg", "没有权限");
//    		map.put("extra",null);
//    		map.put("resultMap", null);  
//    		
//        	String responseJSONObject = JSONObject.toJSONString(map);  
//            response.setCharacterEncoding("UTF-8");  
//            response.setContentType("application/json; charset=utf-8");  
//            PrintWriter out = null;  
//            try {  
//                out = response.getWriter();  
//                out.append(responseJSONObject);                 
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            } finally {  
//                if (out != null) {  
//                    out.close();  
//                }  
//            }  
//            
//            return false;
//            
//    	}
          return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
