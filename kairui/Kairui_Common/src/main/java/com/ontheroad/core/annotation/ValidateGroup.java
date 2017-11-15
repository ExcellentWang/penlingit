package com.ontheroad.core.annotation;

import com.ontheroad.api.validate.BaseGroup;

import java.lang.annotation.*;

/**
 * Created by kedong on 2016/12/13.
 * 定时器任务上使用的自定义注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateGroup {

    Class<?>[] groups() default {BaseGroup.class};

}
