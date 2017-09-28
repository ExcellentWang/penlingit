package com.ontheroad.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ontheroad.core.util.Globals;
import com.ontheroad.system.entity.SysUser;
 

public class SessionListener implements HttpSessionAttributeListener,
		HttpSessionListener, ServletRequestListener{
	private Logger logger = LoggerFactory.getLogger(SessionListener.class);

	public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
	}

	/**
	 * 请求初始化的监听方法
	 */
	public void requestInitialized(ServletRequestEvent servletRequestEvent) {
	}

	/**
	 * session创建时的监听方法
	 */
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		logger.info("created: valid sessionId = {} ", session.getId());
	}

	/**
	 * session销毁时的监听方法
	 */
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		if (session.getAttribute(Globals.USER_INFO) != null) {
			session.removeAttribute(Globals.USER_INFO);
		} else {
			logger.info("destroyed: expired session " + session.getId());
		}
	}

	/**
	 * session属性增加时的监听方法
	 */
	public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
		if (Globals.USER_INFO.equals(httpSessionBindingEvent.getName())) {
			logger.info("begin add "+ httpSessionBindingEvent.getSession().getId());

			SysUser userInfo = (SysUser) httpSessionBindingEvent.getValue();
			httpSessionBindingEvent.getSession().getServletContext().getAttributeNames();
			// 记录用户登录的信息，IP址址，登录时间
			/*try {
				CyBeanFactory.getBean(SysOpLogService.class).updateOnlineInfo(userInfo);
			} catch (Exception ex) {
				logger.error("error add "
						+ httpSessionBindingEvent.getSession().getId(), ex);
			}*/
			logger.info("success add "
					+ httpSessionBindingEvent.getSession().getId() + userInfo);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
		if (Globals.USER_INFO.equals(httpSessionBindingEvent.getName())) {
			System.out.println("begin remove "
					+ httpSessionBindingEvent.getSession().getId());
			SysUser userInfo = (SysUser) httpSessionBindingEvent.getValue();
			/*try {
				CyBeanFactory.getBean(SysOpLogService.class).updateOfflineInfo(userInfo.getOperatorId());
			} catch (Exception ex) {
				logger.error("error remove "
						+ httpSessionBindingEvent.getSession().getId(), ex);
			}*/
			logger.info("success remove "
					+ httpSessionBindingEvent.getSession().getId() + userInfo);
		}
	}

	public void attributeReplaced(
			HttpSessionBindingEvent httpSessionBindingEvent) {
	}


}
