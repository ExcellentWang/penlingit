package com.ontheroad.mysql.openapi;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 天氣接口
 * @author 胡俊
 *
 */
public  class WeatherUtil {
	/**
	 * 根據ip獲取天氣
	 * @param ip
	 * @return
	 */
	public  static  NowWeather send(String ip) {
		NowWeather n=new NowWeather();
	    String host = "https://ali-weather.showapi.com";
	    String path = "/ip-to-weather";
	    String method = "GET";
	    String appcode = "1657fb37be3c4947af4ea834ff3d0798";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ip", ip);
	    querys.put("need3HourForcast", "0");
	    querys.put("needAlarm", "0");
	    querys.put("needHourData", "0");
	    querys.put("needIndex", "0");
	    querys.put("needMoreDay", "0");
	    try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	String json=EntityUtils.toString(response.getEntity());
	    	JSONObject now=JSON.parseObject(json).getJSONObject("showapi_res_body").getJSONObject("now");
	    	n.setSuccess(true);
	    	n.setSd(now.getString("sd"));
	    	n.setTemperature(now.getString("temperature"));
	    	n.setWeather_code(now.getString("weather_code"));
	    	return n;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	n.setSuccess(false);
	    	return n;
	    }
	}
	/**
	 * 獲取當前季節
	 * @return
	 */
	public static String getSeason(){
		int seasonNumber = Calendar.getInstance().get(Calendar.MONTH);
		return seasonNumber>=1&&seasonNumber<=3?"01":seasonNumber>=4&&seasonNumber<=6?"02":seasonNumber>=7&&seasonNumber<=9?"03":seasonNumber>=10?"04":"04";
	}
	/**
	 * 获取api对应设备协议的code
	 * @return
	 */
	public static String getCode(Integer code){
		if(code==0||code==1){
			return "000";
		}
		if(code==2){
			return "001";
		}
		if((code>=3&&code<=8)||(code>=21&&code<=22)||code==301){
			return "002";
		}
		if((code>=9&&code<=12)||(code>=23&&code<=25)){
			return "003";
		}
		if((code>=13&&code<=15)||(code>=26&&code<=27)||code==302){
			return "004";
		}
		if((code>=16&&code<=17)||code==28){
			return "005";
		}
		if((code>=18&&code<=20)||(code>=29&&code<=53)){
			return "006";
		}
		return null;
	}
	public static void main(String[] args) {
//		NowWeather now=send("183.159.182.93");
//		System.out.println(JSON.toJSONString(now));
		System.out.println(getSeason());
	}
}
