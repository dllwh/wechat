package com.cdeledu.util.database.redis.model;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年9月13日 上午12:20:20
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisServerResponseModel {
	/** 唯一标示 */
	private String serverId;
	/** 是否连接 */
	private boolean ifConnection;
	/** ping的时间 */
	private double responseTime;
	/** 状态 */
	private String status;

	public RedisServerResponseModel(String serverId) {
		this.serverId = serverId;
	}

	public String getServerId() {
		return serverId;
	}

	public boolean isIfConnection() {
		return ifConnection;
	}

	public void setIfConnection(boolean ifConnection) {
		this.ifConnection = ifConnection;
	}

	public double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

}
