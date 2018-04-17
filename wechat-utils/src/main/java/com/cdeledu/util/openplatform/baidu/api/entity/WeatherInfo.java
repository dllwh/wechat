package com.cdeledu.util.openplatform.baidu.api.entity;

import java.util.List;

/**
 * @ClassName: WeatherInfo
 * @Description: 定义的承载所有的天气信息的实体类，包含了四天的天气信息。
 * @author: 独泪了无痕
 * @date: 2015年9月1日 下午1:28:14
 * @version: V1.0
 * @history:
 */
public class WeatherInfo {
	// 当前时间
	private String date;
	// 当前城市
	private String currentCity;
	// pm25指数
	private int pm;
	// 穿衣建议
	private String dressAdvise;
	// 洗车建议
	private String washCarAdvise;
	// 感冒建议
	private String coldAdvise;
	// 运动建议
	private String sportsAdvise;
	// 紫外线建议
	private String ultravioletRaysAdvise;

	// 天气情况
	private List<OneDayWeatherInfo> weather_data;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public int getPm() {
		return pm;
	}

	public void setPm(int pm) {
		this.pm = pm;
	}

	public String getDressAdvise() {
		return dressAdvise;
	}

	public void setDressAdvise(String dressAdvise) {
		this.dressAdvise = dressAdvise;
	}

	public String getWashCarAdvise() {
		return washCarAdvise;
	}

	public void setWashCarAdvise(String washCarAdvise) {
		this.washCarAdvise = washCarAdvise;
	}

	public String getColdAdvise() {
		return coldAdvise;
	}

	public void setColdAdvise(String coldAdvise) {
		this.coldAdvise = coldAdvise;
	}

	public String getSportsAdvise() {
		return sportsAdvise;
	}

	public void setSportsAdvise(String sportsAdvise) {
		this.sportsAdvise = sportsAdvise;
	}

	public String getUltravioletRaysAdvise() {
		return ultravioletRaysAdvise;
	}

	public void setUltravioletRaysAdvise(String ultravioletRaysAdvise) {
		this.ultravioletRaysAdvise = ultravioletRaysAdvise;
	}

	public List<OneDayWeatherInfo> getWeather_data() {
		return weather_data;
	}

	public void setWeather_data(List<OneDayWeatherInfo> weather_data) {
		this.weather_data = weather_data;
	}
}
