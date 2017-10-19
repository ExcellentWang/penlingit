package com.ontheroad.core.util;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVPush;
import com.avos.avoscloud.SendCallback;

@Component
public class PushUtil {
	public static void push(JSONObject object){
		AVOSCloud.initialize("EKDyDJR6JNNjJe2L9bwAbAIa-gzGzoHsz","Syi5KsGmQuLpXDxODzvQlhKk","1SOIwMs1CONX2GVybAyKoTsK");
		AVOSCloud.setDebugLogEnabled(true);
		AVPush push = new AVPush();
		push.setPushToAndroid(true);
		push.setData(object);
		push.sendInBackground(new SendCallback() {
		    @Override
		    public void done(AVException e) {
		        if (e == null) {
		            System.out.println("success");
		        } else {
		           System.out.println(e);
		        }
		    }
		    });
		}
	public static void main(String[] args) {
		JSONObject js=new JSONObject();
		js.put("test", "1");
		push(js);
	}
}

