package com.ontheroad.core.annotation;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {

    /**
     * 参数中是否需要携带token信息
     *
     * @return
     */
    boolean needToken() default true;

}
