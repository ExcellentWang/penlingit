package com.ontheroad.core.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring 对象工厂
 * @version 1.0
 */
public class ObjectFactory {
	protected static WebApplicationContext wac = null;
	private static ObjectFactory me = null;

	private ObjectFactory(WebApplicationContext wac) {
		ObjectFactory.wac = wac;
	}

	public static ObjectFactory getInstance(ServletContext servletContext) {
		if (null == me) {
			me = new ObjectFactory(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
		}
		return me;
	}

	public static ObjectFactory getInstance() {
		return me;
	}

    /**
     * 经测试该方法直接调用无法使用
     *
     * @param objname
     * @return
     * @see SpringUtils.getBean 该方法的替代（ctrl+鼠标左键 可以直接跳到类）
     */
    public static Object getObject(String objname) {
		return wac.getBean(objname);
	}

	public static <T> T getBean(Class<T> requiredType){
		return wac.getBean(requiredType);
	}
}
