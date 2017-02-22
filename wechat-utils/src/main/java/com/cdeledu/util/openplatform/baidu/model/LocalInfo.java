package com.cdeledu.util.openplatform.baidu.model;

/**
 * 
 * @title : LocalInfo
 * 
 * @author : 独泪了无痕
 * 
 * @方法描述 : 根据经纬度查看周围的信息
 * 
 */
public class LocalInfo  {
	// 返回结果状态，成功返回0
	private int status;
	// 地址信息
	private String addr = "";
	// 数据来源
	private String cp = "";
	// 离坐标点距离
	private String distance = "";
	// POI 名称
	private String name = "";
	// POI 类型 : 如"办公大夏、商务大厦"
	private String poiType = "";
	// POI坐标{x,y}
	// private List<?> point;
	// 电话
	private String tel = "";
	// POI唯一标识
	private String uid = "";
	// 邮编
	private String zip = "";

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoiType() {
		return poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}