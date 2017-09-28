package com.ontheroad.exception;


import com.ontheroad.api.ErrDetailInfo;
import com.ontheroad.core.exception.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultExceptionResolver extends AbstractHandlerExceptionResolver {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		if(ex.getClass().toString().equals("class org.apache.catalina.connector.ClientAbortException")) {
			logger.info("断开的管道--用户{}在访问{}时主动关闭浏览器",request.getSession().getAttribute("openId"),request.getRequestURL());
		}else{
			logger.error("ExceptionResolver拦截到异常", ex);
			request.setAttribute("exception", ex);
		}

		/**
		 * 参数校验异常
		 */
		if (ex instanceof ValidateException) {
			List<ErrDetailInfo> infos = ((ValidateException) ex).getInfos();
			Map attributes = new HashMap();
			attributes.put("code", "02");
			attributes.put("msg", "参数错误");
			attributes.put("extra", infos);

			MappingJacksonJsonView view = new MappingJacksonJsonView();
			view.setAttributesMap(attributes);
			modelAndView.setView(view);
			return modelAndView;
		}

		modelAndView.setViewName("ControllerException");
		return modelAndView;
	}
}
