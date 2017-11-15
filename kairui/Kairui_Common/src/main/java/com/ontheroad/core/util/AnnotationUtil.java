package com.ontheroad.core.util;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;

public abstract class AnnotationUtil{
	
	/**
	 * 获取方法上的注解，如果没有就获取类上面的注解
	 * @param method
	 * @param annotationType
	 * @return
	 */
	public static <A extends Annotation> A findAnnotation(Method method, Class<A> annotationType) {
		A annotation = AnnotationUtils.findAnnotation(method, annotationType);
		if (annotation == null) {
			annotation = AnnotationUtils.findAnnotation(method.getDeclaringClass(), annotationType);
		}
		return annotation;
	}

}
