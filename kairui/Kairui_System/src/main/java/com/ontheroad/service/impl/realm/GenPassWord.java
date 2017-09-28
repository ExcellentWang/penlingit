package com.ontheroad.service.impl.realm;

import java.io.File;
import java.lang.reflect.Field;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class GenPassWord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testGeneratePassword();
		 
		listFile();
	}
	public static void listFile(){
		File f = new File("E:\\jboss7配置\\modules\\cxf302\\main");
		File lists[] = f.listFiles();
		for(File item : lists){
			if(item.getName().endsWith(".jar"))
			System.out.println("<resource-root path=\""+item.getName()+"\"/>");
		}
	}
	public static void abc(Class<?> clazz){
	Field[] fields = clazz.getDeclaredFields();  
      
    for (Field f : fields) {  
    	 System.out.println("sb.append(\""+f.getName() + "=\"+" + f.getName()+").append(\";\");"); 
    }  
	}
	public static void testGeneratePassword() {
        String algorithmName = "md5";
        String username = "db";
        String password = "123";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 /*+ salt2*/, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);
    }
}
