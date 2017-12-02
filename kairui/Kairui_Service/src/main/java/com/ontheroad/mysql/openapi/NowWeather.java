package com.ontheroad.mysql.openapi;

public class NowWeather {
	private String sd;//空气湿度
	private String temperature;//气温
	private String weather_code;//天氣code
	private boolean success;//是否成功
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWeather_code() {
		return weather_code;
	}
	public void setWeather_code(String weather_code) {
		this.weather_code = weather_code;
	}
	
}
