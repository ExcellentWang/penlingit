package com.ontheroad.core.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MessageUtils {

	/**
	 * @param ms
	 *            (必传)消息存放容器
	 * @param key
	 *            (必传)错误消息key，eg:jiuwu.friend.info.get_100
	 * @param params
	 *            (可选)替换消息参数
	 * @param language
	 *            (可选)语言设置默认1 （1：中文、2：英文、3：韩文）
	 * @author Sam
	 */
	public static String getMessage(MessageSource messageSource, String key,
			String[] params, Integer language) {
		String msg = null;
		Locale locale = null;
		if (language != null) {
			if (language == 1) {
				locale = new Locale("zh", "CN");
			} else if (language == 2) {
				locale = new Locale("en", "US");
			} else if (language == 3) {
				locale = new Locale("ko", "KR");
			} else {
				locale = new Locale("zh", "CN");
			}
		} else {
			locale = new Locale("zh", "CN");
		}
		msg = messageSource.getMessage(key, params, locale);
		return msg;
	}

}
