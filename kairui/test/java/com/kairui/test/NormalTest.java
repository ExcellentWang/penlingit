package com.kairui.test;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class NormalTest {

    @Test
    public void test_1_() {
        String mac = "18fe34ee2812";
        String nmac = com.kairui.test.utils.DataUtils.makeMac(mac);
        System.out.println(nmac);

        String s = "1";
        System.out.println(new Gson().fromJson(s, Integer.class));
    }

    @Test
    public void test_2_() {
        String mac = "18:FE:34:EE:28:12";
        if (StringUtils.isNotBlank(mac) && mac.contains(":")) {
            mac = mac.replace(":", "");
        }
        System.out.println(mac.toLowerCase());
    }

    @Test
    public void test_3_() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", "12334234");
        map.put("verification", "1234");
        map.put("password", "12334234");
        map.put("token", "flkasgjdlfgjdsgfgjldsfgjlsdf");
        map.put("metod", "regist");
        map.put("userId", "1");

        try {
            invoke(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void invoke(Map<String, Object> map) {
        try {
            // 参数转换
            Object parameter = ParameterConversion(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String PARMETER_NAME = "com.kairui.test.UserRequest";


    /**
     * 参数转换
     *
     * @param parmeters
     * @return
     * @throws Exception
     */
    private Object ParameterConversion(Map<String, Object> parmeters) throws Exception {

        String methodName = (String) parmeters.get("method");

        Class parmeterClass = Class.forName(PARMETER_NAME);
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
        Field[] parentFields = null;
        if (parentClazz != null) {
            parentFields = parentClazz.getDeclaredFields();
            List<Field> parentFieldList = Arrays.asList(parentFields);
            for (Field field : parentFieldList) {
                allList.add(field);
            }
        }

        Gson gson = new Gson();
        // 遍历所有的字段
        for (Field field : allList) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(object);
//            System.out.println("name:" + fieldName + "\t value = " + fieldValue);
            if (parmeters.containsKey(fieldName)) {
                // 获取该字段的类型
                Class type = field.getType();
                // 对参数做类型转换（这里通过gson来实现）
                Object obj = gson.fromJson(parmeters.get(fieldName).toString(), type);
                // 通过Spring的反射工具类 ReflectionUtils 来设置字段的值
                ReflectionUtils.setField(field, object, obj);
            }
//            System.out.println(fieldName + "=" + ReflectionUtils.getField(field, object));

        }
        return object;
    }
}
