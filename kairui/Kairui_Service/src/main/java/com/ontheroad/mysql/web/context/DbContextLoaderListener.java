package com.ontheroad.mysql.web.context;

import com.ontheroad.utils.MessageUtils;
/*import com.ontheroad.utils.SmsUtil;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 系统启动时加载
 * @version 1.0
 */
public class DbContextLoaderListener extends ContextLoaderListener implements ServletContextListener {

	protected static final Logger logger = LoggerFactory.getLogger(DbContextLoaderListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { 
		super.contextDestroyed(event);
	}
	 
	 
	public void contextInitialized(ServletContextEvent event) {
		System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
		/**
		 * 初始化容器，<mybatis:scan base-package="com.xxx.xxx.*.mapper" /> 此段会先执行
		 */
		super.contextInitialized(event);
		
		ServletContext sc = event.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		MessageSource messageSource= (MessageSource) context.getBean("messageSource");

		if (sc != null){
			ObjectFactory.getInstance(sc);
		}
		
		
		
		//SystemCache.cacheAllDict();
		
		
		
		/*SmsUtil.init(messageSource);*/
		MessageUtils.init(messageSource);
	    System.out.println("-----------启动通信线程-----------");
		
		
		
		
	}
	
}
