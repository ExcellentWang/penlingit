package com.ontheroad.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ontheroad.core.util.SpringUtils;
import com.ontheroad.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 项目名称：Health_Scale_Common
 * <p/>
 * 类名称：com.daboo.utils.ObjeactUtil
 * 类描述：
 * 创建人：邓强
 * 创建时间：2016年11月25日 下午1:38:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version V1.0
 */

public class ObjectUtil {
    static Logger logger = LoggerFactory.getLogger(ObjectUtil.class);


    public static boolean moveObject(Object oldObject, Object newObject) {
        boolean flag = false;
        if (oldObject.getClass().equals(newObject.getClass())) {
            //判断数据是否变化
            Field[] fields = oldObject.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
            for (Field field : fields) {
                String fieldName = field.getName();
                if (fieldName.equals("serialVersionUID") || fieldName.equals("id")) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    //无变化则设置为null,不更新
                    if (null != field.get(oldObject) && field.get(oldObject).equals(field.get(newObject))) {
                        field.set(newObject, null);
                    } else if (null == field.get(oldObject) && null == field.get(newObject)) {

                    } else {
                        flag = true;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    logger.error("实体类'" + oldObject.getClass() + "'的成员变量'" + fieldName + "':'" + field.getType() + "'类型不匹配", e);
                }
            }
        } else {
            logger.error("实体类'" + oldObject.getClass() + "不能直接赋值给" + newObject);
        }
        return flag;
    }


    /**
     * 利用java反射执行指定的方法
     * <p/>
     * 这里的对象是通过spring来获取的,不是通过 Class.forName(s) 的方式来获取。
     * 因为容器在初始化的时候已经生成了实例，所以只要通过名称来获取就行。
     *
     * @param simpleClassName 简单类名（不带包名的）
     * @param methodName      方法名
     * @param paramTypes      方法参数类型（最好是带上包名）
     * @param ParamValues     Map<String,Object> 方法需要的参数值 key为参数的类型，value为参数具体的值
     *                        eg:{"java.lang.String":"abc","java.lang.Integer:12"}
     * @throws Exception
     */
    public static void invoke(String simpleClassName, String methodName, String[] paramTypes, Map ParamValues) throws Exception {
        /**
         * 首字母小写处理
         * 根据spring bean的命名规范 javaBean允许大写字母起头的属性变量名，不过必须满足“变量的前两个字母要么全部大写，要么全部小写”
         * 这里将类名的首字母转成小写来获取bean是可以的
         */
        Object o = SpringUtils.getBean(StringUtils.firstToLowerCase(simpleClassName));
        Class clazz = o.getClass();
        // 方法参数类型
        Class paramTypesClass[] = null;
        // 参数值
        List list = null;
        if (paramTypes != null && paramTypes.length != 0) {
            paramTypesClass = new Class[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                paramTypesClass[i] = Class.forName(paramTypes[i]);
            }

            list = new ArrayList();
            for (String param : paramTypes) {
                list.add(ParamValues.get(param));
            }
        }

        Method method = clazz.getDeclaredMethod(methodName, paramTypesClass);

        method.invoke(o, list.toArray());
    }
}
