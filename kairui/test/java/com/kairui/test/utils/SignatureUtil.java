package com.kairui.test.utils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名工具类
 */
public class SignatureUtil {

    public static String md5Signature(TreeMap<String, String> params, String secretKey) {
        String result = null;
        StringBuffer orgin = getBeforeSign(params, new StringBuffer(secretKey));
        if (orgin == null)
            return result;
        orgin.append(secretKey);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = bytes2Hex(md.digest(orgin.toString().getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException("sign error !");
        }
        System.out.println("sign: " + result);
        return result;
    }

    private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) {
        if (params == null)
            return null;
        Map treeMap = new TreeMap();
        treeMap.putAll(params);
        Iterator iter = treeMap.keySet().iterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            orgin.append(name).append(params.get(name));
        }
        return orgin;
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuilder strBuf = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] >= 0) && (byteArray[i] < 16)) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }
}
