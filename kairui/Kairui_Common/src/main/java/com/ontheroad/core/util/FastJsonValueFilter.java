package com.ontheroad.core.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.ValueFilter;

public class FastJsonValueFilter implements ValueFilter {
	
	private static Set<Class<?>> pri = new HashSet<Class<?>>();
	static{
		pri.add(Boolean.class);
		pri.add(boolean.class);
		pri.add(Character.class);
		pri.add(char.class);
		pri.add(Byte.class);
		pri.add(byte.class);
		pri.add(Short.class);
		pri.add(short.class);
		pri.add(Integer.class);
		pri.add(int.class);
		pri.add(Long.class);
		pri.add(long.class);
		pri.add(Float.class);
		pri.add(float.class);
		pri.add(Double.class);
		pri.add(double.class);
		pri.add(BigDecimal.class);
		pri.add(BigInteger.class);
		pri.add(String.class);
		pri.add(Date.class);
		pri.add(java.sql.Date.class);
	}
	
	
	@Override
	public Object process(Object object, String name, Object value) {
		if (value != null) {
			return value;
		} else {
			if (value == null && checkListField(object, name)) {
			return value;
			}
			return "";
		}
		//return value;
	}
	/**
	 * 判断是否为List类型
	 * @param object
	 * @param name
	 * @return
	 */
	public static boolean checkListField(Object object, String name) {
		if (object != null) {
			Field field = findField(object.getClass(), name);
			if (field == null) {
				return false;
			} else {
				return (!pri.contains(field.getType()));
				//return List.class.isAssignableFrom(field.getType());
			}
		}
		return false;

	}
	/**
	 * 获取属性类型
	 * @param clazz
	 * @param name
	 * @return
	 */
	
	public static Field findField(Class<?> clazz, String name) {
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Field[] fields = searchType.getDeclaredFields();
			for (Field field : fields) {
				if ((name == null || name.equals(field.getName()))) {
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	public static void main(String args[]) {
		/*ddd reque = new ddd();
		reque.setCreateTime(new Date());

		System.out.println(checkDateField(reque, "createTime"));
		System.out.println(checkDateField(reque, "test"));
		System.out.println(checkDateField(reque, "ddd"));

		FastJsonValueFilter v = new FastJsonValueFilter();
		System.out
				.println(v.process(reque, "createTime", reque.getCreateTime()));
		System.out.println(v.process(reque, "test", reque.getTest()));
		System.out.println("-----" +v.process(reque, "ddd",  reque.getDdd()
				)+ "=--------");*/
	}

};
