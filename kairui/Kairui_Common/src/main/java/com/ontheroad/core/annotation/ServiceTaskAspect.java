package com.ontheroad.core.annotation;

import java.lang.annotation.*;

/**
 * Created by kedong on 2016/12/13.
 * 定时器任务上使用的自定义注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceTaskAspect {

    /**
     * 任务的名称
     *
     * @return
     */
    String name() default "";

}
