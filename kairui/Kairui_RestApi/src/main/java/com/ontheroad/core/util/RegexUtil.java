package com.ontheroad.core.util;

import java.util.regex.Pattern;
/**
 * 正则工具类
 * @author Administrator
 *
 */
public class RegexUtil {

	/** 检测 手机号 的正则 */
    public static final String PHONE_REGEX = "^1(3[0-9]|5[0-35-9]|7[0-9]|8[0-9]|14[57])[0-9]{8}$";
    /**
     * @Description 判断字符串是否为空
     * @param param
     * @return
     */
	public static boolean isNull(String param) {
		
	        return (param == null || "".equals(param.trim()));
	}
	 /**
     * @Description 判断正则表达式
     * @param phone
     * @return
     */
    public static boolean checkRegexWithStrict(String param, String regex) {
        return !isNull(param) && Pattern.compile(regex).matcher(param).matches();
    }
    /**
     * @Description 检测手机号
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        return checkRegexWithStrict(phone, PHONE_REGEX);
    } 
}
