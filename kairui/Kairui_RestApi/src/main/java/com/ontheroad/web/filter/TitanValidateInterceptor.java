package com.ontheroad.web.filter;

import com.ontheroad.core.util.ParmeterSolver;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class TitanValidateInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ParmeterSolver parmeterSolver;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Validate arguments");
        }
        //获取参数，并检查是否应该验证;这里拿到的是conroller方法的参数（封装的对象或者其他类型参数）
        Object[] args = invocation.getArguments();
        if (args.length > 0) {
            Method method = invocation.getMethod();

            parmeterSolver.validator(method, args);
        }

        logger.debug("...开始...");
        Object result = invocation.proceed();
        logger.debug("...结束...");

        return result;
    }


}
