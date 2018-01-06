package com.ontheroad.core.util;

import com.ontheroad.api.ErrDetailInfo;
import com.ontheroad.api.response.Response;
import com.ontheroad.core.annotation.ValidateGroup;
import com.ontheroad.core.exception.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by kedong on 2017/6/22 0022.
 */
@Component
public class ParmeterSolver {
    Logger logger = LoggerFactory.getLogger(getClass());
    private Validator validator;

    public ParmeterSolver() {
//        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 参数校验
     *
     * @param method
     * @param args
     * @throws Exception
     */
    public void validator(Method method, Object... args) throws Exception {
        if (method == null) {
            return;
        }
        // 获取方法上的ValidateGroup注解，该注解是用于参数分组校验的
        ValidateGroup validateGroup = AnnotationUtils.findAnnotation(method, ValidateGroup.class);
        Class[] groups = {};
        if (validateGroup != null) {
            groups = validateGroup.groups();
        }
        List<ErrDetailInfo> infos = new ArrayList<>();
        // 分组校验
        for (Object arg : args) {
            // 采用分组校验
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg, groups);
            if (constraintViolations.size() == 0) {
                continue;
            }
            for (ConstraintViolation<Object> violation : constraintViolations) {
                String message = violation.getMessage();
                String filed = violation.getPropertyPath().toString();
                infos.add(new ErrDetailInfo(Response.CODE_VALIDATE_ERROR, message, filed));
            }
        }

        if (infos.size() > 0) {
            throw new ValidateException(infos);
        }
    }

    public Object Conversion(Map<String, String> parmeters, Class parmeterClass) throws Exception {
        Object object = parmeterClass.newInstance();

        // 获取所有字段
        Field[] fields = parmeterClass.getDeclaredFields();

        List<Field> fieldList = Arrays.asList(fields);
        List<Field> allList = new ArrayList<Field>();
        for (Field field : fieldList) {
            allList.add(field);
        }
        // 获取父类中的字段
        Class parentClazz = parmeterClass.getSuperclass();
        if (parentClazz != null) {
            Field[] parentFields = parentClazz.getDeclaredFields();
            List<Field> parentFieldList = Arrays.asList(parentFields);
            for (Field field : parentFieldList) {
                allList.add(field);
            }
        }

        // 遍历所有的字段
        for (Field field : allList) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(object);
            if (parmeters.containsKey(fieldName)) {
                // 获取该字段的类型
                Class type = field.getType();
                // 对参数做类型转换（这里通过gson来实现）
//                String value = gson.fromJson(parmeters.get(fieldName), type);
                String value = parmeters.get(fieldName);
//                field.set(object, parmeters.get(fieldName));
                setField(field, object, value);
            }
        }
        return object;
    }

    private void setField(Field field, Object object, String value) throws Exception {
        // 获取该字段的类型
        Class type = field.getType();
        String className = type.getName();
        switch (className) {
            case "java.lang.String":
                field.set(object, value);
                break;
            case "java.lang.Integer":
                field.set(object, Integer.valueOf(value));
                break;
            case "java.lang.Long":
                field.set(object, Long.valueOf(value));
                break;
            case "java.lang.Float":
                field.set(object, Float.valueOf(value));
                break;
            case "java.lang.Double":
                field.set(object, Double.valueOf(value));
                break;
            case "java.lang.Boolean":
                field.set(object, Boolean.valueOf(value));
                break;
            case "java.lang.Byte":
                break;
            case "java.lang.Short":
                break;
            default:
                logger.error("位置类型的参数：" + value);
                break;
        }
    }
}
