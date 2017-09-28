package com.ontheroad.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by kedong on 2016/12/13.
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context = null;
//    private static SpringUtils stools = null;
//
//    public synchronized static SpringUtils init() {
//        if (stools == null) {
//            stools = new SpringUtils();
//        }
//        return stools;
//    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public synchronized static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }


}
