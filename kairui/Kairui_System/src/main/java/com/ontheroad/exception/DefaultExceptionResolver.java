package com.ontheroad.exception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class DefaultExceptionResolver extends AbstractHandlerExceptionResolver {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("ExceptionResolver拦截到异常", ex);
		request.setAttribute("exception", ex);
		return new ModelAndView("ControllerException");
	}

	

}
