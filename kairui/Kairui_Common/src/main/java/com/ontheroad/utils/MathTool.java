package com.ontheroad.utils;

import java.math.BigDecimal;

/**
 * 类名称：MathTool
 * 类描述：常用数学公式
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version V1.0
 */
public class MathTool {

    /**
     * 保留单精度小数位个数
     *
     * @param number 保留小数位个数
     * @param value  需要修改的值
     * @return 返回保留小数位之后的值
     */
    public static Float decimal(int number, Float value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(number, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * 保留双精度小数位个数
     *
     * @param number 保留小数位个数
     * @param value  需要修改的值
     * @return 返回保留小数位之后的值
     */
    public static Double decimal(int number, Double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(number, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * @param value
     * @return
     * @Title: toInt
     * @Description: 转整形
     */
    public static int toInt(Float value) {
        BigDecimal bd = new BigDecimal(value);
        return bd.intValue();
    }

    /**
     * @param value
     * @return
     * @Title: toInt
     * @Description: 转整形
     */
    public static int toInt(Double value) {
        BigDecimal bd = new BigDecimal(value);
        return bd.intValue();
    }
}
