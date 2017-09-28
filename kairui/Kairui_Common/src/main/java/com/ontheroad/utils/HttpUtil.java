package com.ontheroad.utils;

import com.alibaba.fastjson.JSONObject;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.client.methods.HttpPost;
import ytx.org.apache.http.entity.StringEntity;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * @ClassName: HttpUtil
 * @Description: http操作工具类
 */
public class HttpUtil {
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(),
                        "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            System.out.println(result);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /** 读取服务器返回过来的json字符串数据 **/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /** 把json字符串转换成json对象 **/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    // logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            // logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

//	public static JSONObject httpPost(String url, String param,
//			boolean noNeedResponse) {
//		// post请求返回结果
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		JSONObject jsonResult = null;
//		HttpPost method = new HttpPost(url);
//		try {
//			if (null != param) {
//				// 解决中文乱码问题
//				StringEntity entity = new StringEntity(param,
//						"utf-8");
//				entity.setContentEncoding("UTF-8");
//				entity.setContentType("application/x-www-form-urlencoded");
//				method.setEntity(entity);
//			}
//			HttpResponse result = httpClient.execute(method);
//			System.out.println(result);
//			url = URLDecoder.decode(url, "UTF-8");
//			/** 请求发送成功，并得到响应 **/
//			if (result.getStatusLine().getStatusCode() == 200) {
//				String str = "";
//				try {
//					/** 读取服务器返回过来的json字符串数据 **/
//					str = EntityUtils.toString(result.getEntity(),"UTF-8");
//					if (noNeedResponse) {
//						return null;
//					}
//					/** 把json字符串转换成json对象 **/
//					jsonResult = JSONObject.parseObject(str);
//				} catch (Exception e) {
//					// logger.error("post请求提交失败:" + url, e);
//				}
//			}
//		} catch (IOException e) {
//			// logger.error("post请求提交失败:" + url, e);
//		}
//		return jsonResult;
//	}

    public static void main(String[] args) {
//		System.out.println(httpPost("http://ip.taobao.com/service/getIpInfo.php?ip=110.212.83.51", null, false).getJSONObject("data").get("city"));
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("user", "service");
//		param.put("passwd", "szyxylmr");
//		param.put("appid", 2);
//		param.put("scene_id", 1);
//		System.out.println(httpPost("http://sc.younsoo.com/service/index.php?c=wechat&a=createTempQrCode",JSONObject.parseObject(JSONObject.toJSONString(param)), false));
//		String param = "sign=f4988939b81fb5c5bdd2043982ca6949&FromUserName=ssdsdsdsd&EventKey=qrscene_52365125&Event=event&method=jiuwu.steelyard.wxmsg.receive&ver=1.0&format=json&ToUserName=sdsdsdsdsd&MsgType=event";
//		System.out.println(httpPost("http://javatest.daboowifi.net/forward/api/rest/router", param, false).toJSONString());
    }
}
