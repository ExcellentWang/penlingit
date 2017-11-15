package com.kairui.test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostFile {
	
	public static void main(String[] args) {
		String secretKey = "asdfghjkqwertyu";

		Map<String, String> paramMap = new HashMap<String, String>();
		Date currentTime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = format.format(currentTime);
		paramMap.put("appKey", "appKey00001");
		paramMap.put("sessionKey", "3214569874563214569874563214589");
		paramMap.put("timestamp", timestamp);
		paramMap.put("format", "json");
		paramMap.put("method", "jiuwu.user.register");
		paramMap.put("ver", "1.0");

//		paramMap.put("id", "100");
//		paramMap.put("name", "ggggg");
//		paramMap.put("num", "44");

		paramMap.put("nuwwwm", "中文测试001");

		Map<String, File> fileParamMap = new HashMap<String, File>();
		fileParamMap.put("file1", new File("d:\\upload\\美女001.jpg"));
		fileParamMap.put("file2", new File("d:\\upload\\副本API接口文档规范1.xlsx"));
		
		fileParamMap.put("file3", new File("d:\\upload\\副本API接口文档规范1.xlsx"));
		
		try {
			String data = PostClient.doPost(URLCOMMON.LOCAL.URL, paramMap, null, 3000, 15000, null, secretKey);

			System.out.println(data);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}