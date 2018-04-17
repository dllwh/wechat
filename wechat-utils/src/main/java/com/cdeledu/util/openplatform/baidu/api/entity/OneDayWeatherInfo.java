package com.cdeledu.util.openplatform.baidu.api.entity;

/**
 * @ClassName: OneDayWeatherInfo
 * @Description: 定义的承载某一天的天气信息的实体类
 * @author: 独泪了无痕
 * @date: 2015年9月1日 下午1:31:38
 * @version: V1.0
 * @history:
 */
public class OneDayWeatherInfo {
	// 天气预报时间
	private String date;
	// 星期
	private String week;
	// 白天的天气预报图片url
	private String dayPictureUrl;
	// 晚上的天气预报图片url
	private String nightPictureUrl;
	// 天气状况
	private String weather;
	// 风力
	private String wind;
	// 温度
	private String temperature;
	// 实时温度
	private String tempertureNow;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDayPictureUrl() {
		return dayPictureUrl;
	}

	public void setDayPictureUrl(String dayPictureUrl) {
		this.dayPictureUrl = dayPictureUrl;
	}

	public String getNightPictureUrl() {
		return nightPictureUrl;
	}

	public void setNightPictureUrl(String nightPictureUrl) {
		this.nightPictureUrl = nightPictureUrl;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTempertureNow() {
		return tempertureNow;
	}

	public void setTempertureNow(String tempertureNow) {
		this.tempertureNow = tempertureNow;
	}
}
