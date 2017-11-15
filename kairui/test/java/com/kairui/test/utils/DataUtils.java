package com.kairui.test.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class DataUtils {

	public static String byteToHexString(byte[] b) {
		StringBuffer buf = new StringBuffer();
		if (b.length>0) {
			for (int i = 0; i < b.length; i++) {
				int a = b[i];
	        	int c = 0xff;
				String str = Integer.toHexString(a&c);
				if (str.length()==1) {
					buf.append("0");
				}
				buf.append(str);
			}
			
		}
		return buf.toString();
	}
	
	public static String byteToString(byte[] b) {
		StringBuffer buf = new StringBuffer();
		if (b.length>0) {
			for (int i = 0; i < b.length; i++) {
				int a = b[i];
	        	int c = 0xff;
				String str = Integer.toString(a&c);
				if (str.length()==1) {
					buf.append("0");
				}
				buf.append(str);
			}
			
		}
		return buf.toString();
	}
	
	public static byte[] subBytes(byte[] src, int begin, int count) {  
	    byte[] bs = new byte[count];  
	    System.arraycopy(src, begin, bs, 0, count);  
	    return bs;  
	}  
	
	public static byte[] subBytes(byte[] src,byte[] src2, int begin, int count) { 
	    System.arraycopy(src, begin, src2, 0, count);  
	    return src2;  
	}  
	
	/** 
     * bytes字符串转换为Byte值 
     * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F) 
     * @return byte[] 
     */  
    public static byte[] hexStr2Bytes(String src){  
        /*对输入值进行规范化整理*/  
        src = src.trim().replace(" ", "").toUpperCase(Locale.US);  
        //处理值初始化  
        int m=0,n=0;  
        int iLen=src.length()/2; //计算长度  
        byte[] ret = new byte[iLen]; //分配存储空间  
          
        for (int i = 0; i < iLen; i++){  
            m=i*2+1;  
            n=m+1;  
            ret[i] = (byte)(Integer.decode("0x"+ src.substring(i*2, m) + src.substring(m,n)) & 0xFF);  
        }  
        return ret;  
    }  
	
    /**
     * 生成16进制累加和校验码
     *
     * @param data 除去校验位的数据
     * @return
     */
    public static String makeChecksum(String data) {
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        int mod = total & 0xff;
        String hex = Integer.toHexString(mod+Integer.parseInt("69", 16));
        len = hex.length();
        //如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }
    
    /**
     * 16进制累加和校验
     *
     * @param data 除去校验位的数据
     * @param sign 校验位的数据
     * @return
     */
    public static boolean checkSum(String data, String sign) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(sign)) {
            return false;
        }
        String checksum = makeChecksum(data);
//        Integer sum = Integer.parseInt(checksum)+45;
        System.out.println("=============" + checksum);
        if (checksum.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public static String getDataLength(String data) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(Integer.toString(data.length()/2, 16));
		if (buffer.length()%2!=0) {
			buffer.insert(0,"0");
		}
		if (buffer.length()==2) {
			buffer.insert(0,"00");
		}
		return buffer.toString();
	}
    
	public static void main(String[] args) {
//		StringBuffer buffer = new StringBuffer("1");
//		if (buffer.length()==1) {
//			buffer.insert(0, "0");
//		}
//		System.out.println(buffer.toString());
		System.out.println(intToHexString(25612561));
	}
	
	public static String makeMac(String mac) {
		String remac = "";
		if (StringUtils.isNotBlank(mac)&&mac.length()==12) {
			int length = 2;
			int size = 6;
			for (int i = 1; i <= size; i++) {
				remac += mac.substring((i-1)*length, i*length)+":";
			}
			remac = remac.substring(0, remac.length()-1);
		}
		return remac.toUpperCase();
	}
	
	public static byte[] hexStringToBytes(String hexString) {
	    if (hexString == null || hexString.equals("")) {
	        return null;
	    }
	    hexString = hexString.toUpperCase();
	    int length = hexString.length() / 2;
	    char[] hexChars = hexString.toCharArray();
	    byte[] d = new byte[length];
	    for (int i = 0; i < length; i++) {
	        int pos = i * 2;
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	    }
	    return d;
	}
	
	public static byte charToByte(char c) {
	    return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	public static boolean checkDataLength(byte[] length, int length2) {
		boolean flag=false;
		if (length.length==2) {
			int data_length = ((int)length[0]<<8)+(int)length[1];
			flag = data_length==length2?true:false;
		}else {
			flag = false;
		}
		return flag;
	}
	
	public static String intToHexString(int i) {
		StringBuffer str = new StringBuffer(Integer.toHexString(i));
		if (str.length()%2==1) {
			str.insert(0, "0");
		}
		return str.toString();
	}

	public static int sum(byte h,byte l) {
		return ((h&0xff)<<8)+(l&0xff);
	}
}
