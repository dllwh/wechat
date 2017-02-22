package com.cdeledu.util.openplatform.baidu.model;

/**
 * 
 * @title : LatitudeInfo
 * 
 * @author : 独泪了无痕
 * 
 * @方法描述 : 地理编码返回结果字段
 * 
 */
public class LatitudeInfo {
	// 返回值 状态,成功返回0
	private int status;
	// 经度值
	private float lng;
	// 纬度值
	private float lat;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

}
