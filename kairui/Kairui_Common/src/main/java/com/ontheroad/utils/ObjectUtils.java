package com.ontheroad.utils;

import com.ontheroad.core.util.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class ObjectUtils {
    static Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    static {
        //注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空  
        ConvertUtils.register(new DateConvert(), Date.class);
    }


    public static void copyProperties(Object target, Object source) throws InvocationTargetException, IllegalAccessException {
        //支持对日期copy  
        BeanUtils.copyProperties(target, source);
    }

    public static <T> Object copyProperties(Class<T> type, Object source) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        //支持对日期copy
        T obj = type.newInstance();
        BeanUtils.copyProperties(obj, source);
        return obj;
    }

    /**
     * @return 0.其他类型；1.基本类型；2.包装类型
     */
    public static int isPrimitive(Object obj) {
        int flag = 0;
        if (obj.getClass().isPrimitive()) {
            flag = 1;
        } else if (obj instanceof Integer
                || obj instanceof Long
                || obj instanceof Boolean
                || obj instanceof Byte
                || obj instanceof Short
                || obj instanceof Float
                || obj instanceof Double
                || obj instanceof String
                || obj instanceof StringBuffer
                || obj instanceof StringBuilder
                ) {
            flag = 2;
        }

        return flag;
    }
}

class DateConvert implements Converter {

    @Override
    public Object convert(Class class1, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return value;
        }
        if (value instanceof Long) {
            Long longValue = (Long) value;
            return new Date(longValue.longValue());
        }
        if (value instanceof String) {
            String dateStr = (String) value;
            return DateUtils.getDateFromString(dateStr);
        }
        return null;
    }
}