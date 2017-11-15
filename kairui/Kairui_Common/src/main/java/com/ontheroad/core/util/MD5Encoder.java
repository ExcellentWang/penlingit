package com.ontheroad.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Encoder{
  static Logger log = LoggerFactory.getLogger(MD5Encoder.class);
  private static String byte2hex(byte[] b) {
    StringBuffer hs = new StringBuffer();
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = Integer.toHexString(b[n] & 0xFF);
      if (stmp.length() == 1)
        hs = hs.append("0").append(stmp);
      else {
        hs = hs.append(stmp);
      }
    }
    return hs.toString().toUpperCase();
  }

  public static String encode(String input) {
    try {
      MessageDigest alga = MessageDigest.getInstance("MD5");
      alga.update(input.getBytes());
      return byte2hex(alga.digest());
    } catch (NoSuchAlgorithmException e) {
    	//throw new SystemException("0000", e);
    }
	return input;
  }
}