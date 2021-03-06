package com.cdeledu.crawler.SocialNetwork.proxy.entity;

import java.io.Serializable;

/**
 * @类描述: 代理ip
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月9日 下午10:03:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ProxyPool implements Serializable {
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
	/** ip所属运营商;默认: 全部, 1: 联通（网通）, 2: 电信, 3: 移动, 4: 铁通 */
	private String isp;
	/** 按代理协议筛选代理类型 */
	private String[] protocolType;
	/** ----------------------------------------------------- Fields end */
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
	public String[] getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(String[] protocolType) {
		this.protocolType = protocolType;
	}
}
