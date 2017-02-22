package com.cdeledu.util.openplatform.baidu.model;

/**
 * 
 * @title : PlaceSuggestionAPI
 * 
 * @author : 独泪了无痕
 * 
 * @方法描述 :
 *       <p>
 *       Place suggestion API
 *       <p>
 *       是一套以HTTP形式提供的匹配用户输入关键字辅助信息、提示接口
 *       <p>
 */
public class PlaceSuggestionAPI {
	/**
	 * 状态字段
	 */
	// 本次API访问状态，如果成功返回0，如果失败返回其他数字。
	// private int status;
	// 对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明。
	// private String message;
	/**
	 * 结果字段(List results)
	 */
	// 与关键词 相关的
	private String name = "";
	// 城市
	private String city = "";
	// 区域
	private String district = "";
	//
	private String business = "";
	// 所属城市/区域代号
	private String cityid = "";

	private String uid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}