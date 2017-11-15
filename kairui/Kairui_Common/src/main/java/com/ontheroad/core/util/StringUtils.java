package com.ontheroad.core.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	static Logger log = LoggerFactory.getLogger(StringUtils.class);
	static char ARR[] = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public static String toUpperCase(String str) {
		return trim(str).toUpperCase();
	}

	public static String toLowerCase(String str) {
		return trim(str).toLowerCase();
	}

	public static String trim(String s) {
		return getNotNullString(s);
	}

	public static String None2Null(String s) {
		if (s != null) {
			if ("None".equals(s)) {
				return "";
			}
			return s.trim();
		}

		return "";
	}

	public static String NullString2Null(String s) throws Exception {
		if (s != null) {
			if ("null".equalsIgnoreCase(s)) {
				return "";
			}
			return s.trim();
		}

		return "";
	}

	public static String blankToBracket(String str) {
		if ((str == null) || (str.equals("")))
			return "{}";
		return str;
	}

	public static String blankToZero(String str) {
		if ((str == null) || (str.equals("")))
			return "0";
		return str;
	}

	public static String getTrimString(String src, int n, String var) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < n; i++) {
			buf.append(var);
		}
		buf.append(src);

		return buf.toString();
	}

	public static String getTrimString(String conzt, String var) {
		String ret = conzt + var;
		return ret.substring(var.length());
	}

	public static String getTrimRight(String src, int n) {
		StringBuilder buf = new StringBuilder();

		int lngth = src != null ? src.length() : 0;
		buf.append(src);
		for (int i = 0; i < n - lngth; i++) {
			buf.append(" ");
		}

		return buf.toString();
	}

	public static String getTrimString(String conzt, int var) {
		String ret = conzt + var;
		return ret.substring(ret.length() - conzt.length());
	}

	public static String getNotNullString(String s, String sdefault) {
		return s != null ? s.trim() : sdefault;
	}

	public static String getNotNullString(String s) {
		return s != null ? s.trim() : "";
	}
	
	/**
	 * 判断字符串是否为空
	 * @param value	String
	 * @return boolean
	 */
	public static boolean isEmpty(String value) {
		return (value == null || value.equals(""));
	}
	
	public static boolean isBlankString(String in) {
		
		if (isEmpty(in))
			return true;
		//加入None的判断
		if ((in.trim().equals("")) || (in.trim().equalsIgnoreCase("null")))
			return true;
		return false;
	}
	
	public static String format(String intval, int length) {
		char[] c = new char[length];
		for (int i = 0; i < c.length; i++)
			c[i] = '0';
		StringBuffer buf = new StringBuffer(String.valueOf(c));
		buf.replace(length - intval.length(), length, intval);
		return buf.toString();
	}
	
	
	public static String replace(String strSource, String strFrom, String strTo) {
		StringBuffer strDest = new StringBuffer();
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest.append(strSource.substring(0, intPos)).append(
					strTo);
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest.append(strSource);

		return strDest.toString();
	}
	
	// / 转全角的函数(SBC case) ///
		// /任意字符串 /// 全角字符串 ///
		// /全角空格为12288,半角空格为32
		// /其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///
		public static String toSBC(String input) {
			// 半角转全角：
			char[] c = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == 32) {
					c[i] = (char) 12288;
					continue;
				}
				if (c[i] < 127)
					c[i] = (char) (c[i] + 65248);
			}
			return new String(c);
		}

		// / /// 转半角的函数(DBC case) ///
		// /任意字符串
		// / 半角字符串 ///
		// /全角空格为12288，半角空格为32
		// /其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///
		public static String toDBC(String input) {
			char[] c = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == 12288) {
					c[i] = (char) 32;
					continue;
				}
				if (c[i] > 65280 && c[i] < 65375)
					c[i] = (char) (c[i] - 65248);
			}
			return new String(c);
		}
		
		/**
		 * 将字符串转成list
		 * @param childStr
		 * @return
		 */
		public static List<Long> stringtoLong(String childStr) {
			String arr[] = childStr.split(",");
			List<Long> result = new ArrayList<Long>();
			for(String item : arr){
				result.add(Long.parseLong(item));
			}
			return result;			
		 } 
		/**
		 * 获取用户后缀
		 * @param userNo
		 * @param sxh
		 * @return
		 */
	public static String getUserNoSuffix(String userNo, Integer sxh) {
		Integer mod = 0;
		StringBuffer result = new StringBuffer();
		int size = ARR.length;
		while (sxh > (size - 1)) {
			mod = sxh % size;
			sxh = sxh / size;
			result.insert(0, ARR[mod]);
		}
		result.insert(0, ARR[sxh - 1]);
		result.insert(0,userNo);
		return result.toString();
	}

	/**
	 * 首字母小写
	 * @param ss
	 * @return
	 */
	public static String firstToLowerCase(String ss) throws Exception{
		ss = trim(ss);
		if(isEmpty(ss)){
			return "";
		}else {
			return toLowerCase(ss.substring(0, 1)) + ss.substring(1);
		}
	}
}
