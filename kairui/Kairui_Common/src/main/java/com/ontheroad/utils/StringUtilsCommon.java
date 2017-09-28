package com.ontheroad.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

public class StringUtilsCommon {

    private final static Log logger = LogFactory.getLog(StringUtilsCommon.class);


    /**
     * @return strong token
     * @throws Exception
     * @FuncName getToken
     * @description 获取token
     * @author xiaoyong
     */
    public static String getToken() {
        logger.info("生成token");
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(UUID.randomUUID().toString().replaceAll("-", "").getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

        } catch (Exception e) {
            logger.error("生成Token异常" + e);
        }
        return buf.toString();
    }

    public static String changePhone(String phone) {
        if (phone.length() == 11) {
            return phone.substring(0, 3) + "****" + phone.substring(7, 11);
        } else {
            return "";
        }
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param isNumber 是否是数字
     * @param length
     * @return
     */
    public static String getRandom(boolean isNumber, int length) {
        String retStr = "";
        String seed = isNumber ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = seed.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = seed.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += seed.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (new Random().nextInt(10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }


    public static void main(String[] args) {
        System.out.println(getRandom(true, 6));
        System.out.println(getRandNum(6));
    }
}
