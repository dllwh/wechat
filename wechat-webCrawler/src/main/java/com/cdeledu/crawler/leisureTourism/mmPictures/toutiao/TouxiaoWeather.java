package com.cdeledu.crawler.leisureTourism.mmPictures.toutiao;

import java.io.Serializable;

/**
 * @类描述:今日头条天气信息实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月24日 下午9:40:50
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TouxiaoWeather implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** IP地址 */
	private String ip;
	/** */
	private Integer aqi;
	/** 根据IP查询到的城市 */
	private String city;
	/** 查询到的城市 */
	private String city_name;
	/** 当前的天气状况 */
	private String current_condition;
	/** 当前的温度 */
	private String current_temperature;
	/** 当前时间 */
	private Double current_time;
	/** 后天的天气状况 */
	private String dat_condition;
	/** 后天的最高温度 */
	private Integer dat_high_temperature;
	/** 后天的最低温度 */
	private Integer dat_low_temperature;
	/** 后天天气图片ID */
	private String dat_weather_icon_id;
	/** 今天的天气状况 */
	private String day_condition;
	/** 今天的最高温度 */
	private Integer high_temperature;
	/** 今天的最低温度 */
	private Integer low_temperature;
	/** */
	private Integer moji_city_id;
	/** 晚上的天气情况 */
	private String night_condition;
	/** */
	private String quality_level;
	/** */
	private Integer tomorrow_aqi;
	/** 明天天的天气情况 */
	private String tomorrow_condition;
	/** 明天的最高温度 */
	private Integer tomorrow_high_temperature;
	/** 明天的最低温度 */
	private Integer tomorrow_low_temperature;
	/** */
	private String tomorrow_quality_level;
	/** */
	private String tomorrow_weather_icon_id;
	/** 该条记录所属日期 */
	private String update_time;
	/** */
	private String weather_icon_id;
	/** 白天的风况 */
	private String wind_direction;
	/** 白天的风况 */
	private Integer wind_level;

	/** ----------------------------------------------------- Fields end */
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getAqi() {
		return aqi;
	}

	public void setAqi(Integer aqi) {
		this.aqi = aqi;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCurrent_condition() {
		return current_condition;
	}

	public void setCurrent_condition(String current_condition) {
		this.current_condition = current_condition;
	}

	public String getCurrent_temperature() {
		return current_temperature;
	}

	public void setCurrent_temperature(String current_temperature) {
		this.current_temperature = current_temperature;
	}

	public Double getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(Double current_time) {
		this.current_time = current_time;
	}

	public String getDat_condition() {
		return dat_condition;
	}

	public void setDat_condition(String dat_condition) {
		this.dat_condition = dat_condition;
	}

	public Integer getDat_high_temperature() {
		return dat_high_temperature;
	}

	public void setDat_high_temperature(Integer dat_high_temperature) {
		this.dat_high_temperature = dat_high_temperature;
	}

	public Integer getDat_low_temperature() {
		return dat_low_temperature;
	}

	public void setDat_low_temperature(Integer dat_low_temperature) {
		this.dat_low_temperature = dat_low_temperature;
	}

	public String getDat_weather_icon_id() {
		return dat_weather_icon_id;
	}

	public void setDat_weather_icon_id(String dat_weather_icon_id) {
		this.dat_weather_icon_id = dat_weather_icon_id;
	}

	public String getDay_condition() {
		return day_condition;
	}

	public void setDay_condition(String day_condition) {
		this.day_condition = day_condition;
	}

	public Integer getHigh_temperature() {
		return high_temperature;
	}

	public void setHigh_temperature(Integer high_temperature) {
		this.high_temperature = high_temperature;
	}

	public Integer getLow_temperature() {
		return low_temperature;
	}

	public void setLow_temperature(Integer low_temperature) {
		this.low_temperature = low_temperature;
	}

	public Integer getMoji_city_id() {
		return moji_city_id;
	}

	public void setMoji_city_id(Integer moji_city_id) {
		this.moji_city_id = moji_city_id;
	}

	public String getNight_condition() {
		return night_condition;
	}

	public void setNight_condition(String night_condition) {
		this.night_condition = night_condition;
	}

	public String getQuality_level() {
		return quality_level;
	}

	public void setQuality_level(String quality_level) {
		this.quality_level = quality_level;
	}

	public Integer getTomorrow_aqi() {
		return tomorrow_aqi;
	}

	public void setTomorrow_aqi(Integer tomorrow_aqi) {
		this.tomorrow_aqi = tomorrow_aqi;
	}

	public String getTomorrow_condition() {
		return tomorrow_condition;
	}

	public void setTomorrow_condition(String tomorrow_condition) {
		this.tomorrow_condition = tomorrow_condition;
	}

	public Integer getTomorrow_high_temperature() {
		return tomorrow_high_temperature;
	}

	public void setTomorrow_high_temperature(Integer tomorrow_high_temperature) {
		this.tomorrow_high_temperature = tomorrow_high_temperature;
	}

	public Integer getTomorrow_low_temperature() {
		return tomorrow_low_temperature;
	}

	public void setTomorrow_low_temperature(Integer tomorrow_low_temperature) {
		this.tomorrow_low_temperature = tomorrow_low_temperature;
	}

	public String getTomorrow_quality_level() {
		return tomorrow_quality_level;
	}

	public void setTomorrow_quality_level(String tomorrow_quality_level) {
		this.tomorrow_quality_level = tomorrow_quality_level;
	}

	public String getTomorrow_weather_icon_id() {
		return tomorrow_weather_icon_id;
	}

	public void setTomorrow_weather_icon_id(String tomorrow_weather_icon_id) {
		this.tomorrow_weather_icon_id = tomorrow_weather_icon_id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getWeather_icon_id() {
		return weather_icon_id;
	}

	public void setWeather_icon_id(String weather_icon_id) {
		this.weather_icon_id = weather_icon_id;
	}

	public String getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}

	public Integer getWind_level() {
		return wind_level;
	}

	public void setWind_level(Integer wind_level) {
		this.wind_level = wind_level;
	}

}
