package com.kairui.test.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Md5Util {

    public static String makeMd5Sum(byte[] srcContent) {
        if (srcContent == null) {
            return null;
        }

        String strDes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(srcContent);
            strDes = bytes2Hex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] >= 0) && (byteArray[i] < 16)) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }

    public static String md5Signature(TreeMap<String, String> params, String secret) {
        String result = null;
        StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
        if (orgin == null)
            return result;
        orgin.append(secret);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = bytes2Hex(md.digest(orgin.toString().getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException("sign error !");
        }
        System.out.println("timestamp: " + params.get("timestamp"));
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
            orgin.append(name).append((String) params.get(name));
        }
        return orgin;
    }

}
