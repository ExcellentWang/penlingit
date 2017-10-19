package com.ontheroad.core.util;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVPush;
import com.avos.avoscloud.SendCallback;
import com.ontheroad.dao.TbNewsinformationMapper;
import com.ontheroad.entity.TbNewsinformation;

@Component
public class PushUtil {
	 private static final Logger logger = Logger.getLogger(PushUtil.class);
	@Autowired
	private TbNewsinformationMapper tbNewsinformationMapper;
	public  void push(JSONObject json){
		AVOSCloud.initialize("EKDyDJR6JNNjJe2L9bwAbAIa-gzGzoHsz","Syi5KsGmQuLpXDxODzvQlhKk","1SOIwMs1CONX2GVybAyKoTsK");
		AVOSCloud.setDebugLogEnabled(true);
		AVPush push = new AVPush();
		push.setPushToAndroid(true);
		push.setData(json);
		push.sendInBackground(new SendCallback() {
		    @Override
		    public void done(AVException e) {
		    	TbNewsinformation info= json.getObject("pdata", TbNewsinformation.class);
		    	if (e == null) {
		        	info.setStatus(3);
		        	logger.info("------------------推送成功------------------------------");
		        } else {
		           info.setStatus(4);
		           logger.info("------------------推送失败------------------------------");
		           logger.info("------------------"+e+"------------------------------");
		        }
		        tbNewsinformationMapper.updateByPrimaryKeySelective(info);
		    }
		    });
		}
	public static void main(String[] args) {
		JSONObject js=new JSONObject();
		js.put("test", "1");
		new PushUtil().push(js);
	}
}

