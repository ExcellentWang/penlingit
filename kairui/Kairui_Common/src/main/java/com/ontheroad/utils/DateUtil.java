package com.ontheroad.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：Health_Scale_Common
 * <p>
 * 类名称：DateUtil
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version V1.0
 */
public class DateUtil {

    /**
     * util.Date转sql.Date
     *
     * @param date java.util.Date
     * @return
     */
    public static java.sql.Date toSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * sql.Date转util.Date
     *
     * @param date java.sql.Date
     * @return
     */
    public static Date toUtilDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * 格式化日期
     *
     * @param calendar java.util.Calendar类型
     * @return
     */
    public static String dateFormat(Calendar calendar) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return dateFormat(calendar.getTime(), pattern);
    }

    /**
     * 格式化日期
     *
     * @param date java.sql.Date
     * @return
     */
    public static String dateFormat(java.sql.Date date) {
        String pattern = "yyyy-MM-dd";
        return dateFormat(date, pattern);
    }

    /**
     * 格式化日期
     *
     * @param date java.util.Date
     * @return
     */
    public static String dateFormat(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return dateFormat(date, pattern);
    }

    /**
     * 格式化日期
     *
     * @param calendar java.util.Calendar类型
     * @param pattern
     * @return
     */
    public static String dateFormat(Calendar calendar, String pattern) {
        return dateFormat(calendar.getTime(), pattern);
    }

    /**
     * 格式化日期
     *
     * @param date    java.sql.Date
     * @param pattern
     * @return
     */
    public static String dateFormat(java.sql.Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String demo = sdf.format(date);
        return demo;
    }

    /**
     * 格式化日期
     *
     * @param date    java.util.Date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String demo = sdf.format(date);
        return demo;
    }

    /**
     * @param currDate
     * @param oldDate
     * @return
     * @Description: 返回两个时间的时间差，按天返回
     */
    public static Integer timeDifference(Date currDate, Date oldDate) {
        Long curr = currDate.getTime();
        Long old = oldDate.getTime();
        Long tmp = (curr - old) / 86400000;
        return tmp.intValue();
    }

    public static Integer getCurrMinuteByDay() {
        Calendar calendar = Calendar.getInstance();
        Long curr = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long old = calendar.getTimeInMillis();
        Long tmp = (curr - old) / 60000;

        return tmp.intValue();
    }


    /**
     * @param date 需要增加的时间
     * @param day  增加的天数
     * @return 返回增加之后的时间
     * @Title: addDay
     * @Description:一个时间增加多少天
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);

        return calendar.getTime();
    }

    /**
     * 字符串转时间
     * 支持格式:
     * yyyyMMdd<br>
     * yyyyMMddHH<br>
     * yyyyMMddHHmm<br>
     * yyyyMMddHHmmss<br>
     * yyyy-MM-dd<br>
     * yyyy.MM.dd<br>
     * yyyy/MM/dd<br>
     * yyyy MM dd<br>
     * 年月日格式可以和下面的日以交换
     * yyyyMMdd HH<br>
     * yyyyMMdd HHmm<br>
     * yyyyMMdd HH mm<br>
     * yyyyMMdd HH:mm<br>
     * yyyyMMdd HH：mm<br>
     * yyyyMMdd HH mm ss<br>
     * yyyyMMdd HH:mm:ss<br>
     * yyyyMMdd HH：mm：ss<br>
     * MM/dd<br>
     * MM-dd<br>
     * MM.dd<br>
     * HH<br>
     * HHmm<br>
     * HH mm<br>
     * HH:mm<br>
     * HH：mm<br>
     * HH mm ss<br>
     * HH:mm:ss<br>
     * HH：mm：ss<br>
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromString(String dateStr) {
        dateStr = dateStr.trim();
        Pattern pat = Pattern.compile("^(([0-9]{4})([0-9]{2})([0-9]{2})\\s?([0-9]{2})([0-9]{2})([0-9]{2})|((([0-9]{4})([^0-9:]*)([0-9]{2})([^0-9:]*)([0-9]{2}))|((([0-9]{4})([^0-9:\\s]))?([0-9]{1,2})([^0-9:\\s])([0-9]{1,2})))?((\\s*)?([0-9]{1,2})((([:：\\s])([0-9]{1,2})(([:：\\s])?([0-9]{1,2}))?)|([0-9]{2}))?)?)$");
        Matcher mat = pat.matcher(dateStr);
        if (!mat.find()) {
            return null;
        }

        if (StringUtils.isBlank(mat.group(1))) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            int y, M, d, H, m, s;
            if (mat.group(2) != null || mat.group(10) != null || mat.group(16) != null) {
                y = Integer.parseInt(mat.group(2) != null ? mat.group(2) : mat.group(10) != null ? mat.group(10) : mat.group(16));
                calendar.set(Calendar.YEAR, y);
            }
            if (mat.group(3) != null || mat.group(12) != null || mat.group(19) != null) {
                M = Integer.parseInt(mat.group(3) != null ? mat.group(3) : mat.group(12) != null ? mat.group(12) : mat.group(19));
                M = M > 0 ? M - 1 : 0;
                calendar.set(Calendar.MONTH, M);
            }
            if (mat.group(4) != null || mat.group(14) != null || mat.group(21) != null) {
                d = Integer.parseInt(mat.group(4) != null ? mat.group(4) : mat.group(14) != null ? mat.group(14) : mat.group(21));
                calendar.set(Calendar.DAY_OF_MONTH, d);
            }
            if (mat.group(5) != null || mat.group(24) != null) {
                H = Integer.parseInt(mat.group(5) != null ? mat.group(5) : mat.group(24));
                calendar.set(Calendar.HOUR_OF_DAY, H);
            }
            if (mat.group(6) != null || mat.group(28) != null || mat.group(32) != null) {
                m = Integer.parseInt(mat.group(6) != null ? mat.group(6) : mat.group(28) != null ? mat.group(28) : mat.group(32));
                calendar.set(Calendar.MINUTE, m);
            }
            if (mat.group(7) != null || mat.group(31) != null) {
                s = Integer.parseInt(mat.group(7) != null ? mat.group(7) : mat.group(31));
                calendar.set(Calendar.SECOND, s);
            }
            return calendar.getTime();
        }
    }
}
