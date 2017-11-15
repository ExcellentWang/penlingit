package com.ontheroad.core.util;

import java.math.BigDecimal;

/**
 * 二次封装 BigDecimal对象运用到的数学中的加、减、乘、除方法
 * 
 * @author db
 * 
 */
public class BigDecimalUtil {
	/**
	 * 加法运算(d1+d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return double
	 */
	public static double add(double d1, double d2) { // 进行加法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}
	
	 
	/**
	 * 减法运算(d1-d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return double
	 */
	public static double sub(double d1, double d2) { // 进行减法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 乘法运算(d1*d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return double
	 */
	public static BigDecimal mul(double d1, double d2) { // 进行乘法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2);
	}

	/**
	 * 除法运算(d1/d2)
	 * 
	 * @param d1
	 * @param d2
	 * @param len
	 *            指定结果的精度，默认小数点后2位
	 * @return double
	 */
	public static double div(double d1, double d2, int len) {// 进行除法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double div(double d1, double d2) {// 进行除法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入操作
	 * 
	 * @param d
	 * @param len
	 * @return BigDecimal
	 */
	public static double round(double d, int len) { // 进行四舍五入 操作
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 加法运算 （d1+d2）
	 * 
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 */
	public static BigDecimal add(BigDecimal d1, BigDecimal d2) { // 进行加法运算
		return d1.add(d2);
	}

	/**
	 * 减法运算(d1-d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 */
	public static BigDecimal sub(BigDecimal d1, BigDecimal d2) { // 进行减法运算
		return d1.subtract(d2);
	}

	public static BigDecimal sub(BigDecimal d1, double d2) { // 进行减法运算
		BigDecimal b2 = new BigDecimal(d2);
		return d1.subtract(b2);
	}

	public static BigDecimal sub(BigDecimal d1, int d2) { // 进行减法运算
		BigDecimal b2 = new BigDecimal(d2);
		return d1.subtract(b2);
	}

	/**
	 * 乘法运算(d1*d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 */
	public static BigDecimal mul(BigDecimal d1, BigDecimal d2) { // 进行乘法运算
		return d1.multiply(d2);
	}
	/**
	 * 乘法运算(d1*d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 */
	public static BigDecimal mul(BigDecimal d1, int d2) { // 进行乘法运算
		BigDecimal b2 = new BigDecimal(d2);
		return d1.multiply(b2);
	}
	/**
	 * 乘法运算(d1*d2)
	 * 
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 */
	public static BigDecimal mul(BigDecimal d1, double d2) { // 进行乘法运算
		BigDecimal b2 = new BigDecimal(d2);
		return d1.multiply(b2);
	}
	/**
	 * 除法运算(d1/d2)
	 * 
	 * @param d1
	 * @param d2
	 * @param len
	 *            指定结果的精度，默认小说点后2位
	 * @return
	 */
	public static BigDecimal div(BigDecimal d1, BigDecimal d2, int len) {// 进行除法运算
		return d1.divide(d2, len, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal div(BigDecimal d1, BigDecimal d2) {// 进行除法运算
		return d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 四舍五入操作
	 * 
	 * @param d
	 * @param len
	 * @return BigDecimal
	 */
	public static BigDecimal round(BigDecimal d, int len) { // 进行四舍五入 操作
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
		return d.divide(b2, len, BigDecimal.ROUND_HALF_UP);
	}
}
