package com.cdeledu.crawler.SocialNetwork.proxy.common;

import java.io.Serializable;
import java.util.List;

/**
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月9日 下午10:03:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
class ProxyPool implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** IP 地址 */
	private String ip;
	/** 端口 */
	private Integer port;
	/** 地理位置 */
	private String position;
	/** 地理位置 :国家 */
	private String country;
	/** 地理位置：省份 */
	private String province;
	/** 地理位置：城市 */
	private String city;
	/** 运营商 */
	private String isp;
	/** 代理类型 */
	private List<String> type;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** ----------------------------------------------------- Fields end */
	
}
