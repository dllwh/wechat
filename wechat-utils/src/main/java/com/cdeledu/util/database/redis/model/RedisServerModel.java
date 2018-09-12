package com.cdeledu.util.database.redis.model;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年9月13日 上午12:18:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisServerModel {
	/** 唯一标示 */
	private String ServerId;
	/** Redis地址 */
	private String ServerHost;
	/** Redis描述 */
	private String ServerDescribe;

	public String getServerId() {
		return ServerId;
	}

	public void setServerId(String serverId) {
		ServerId = serverId;
	}

	public String getServerHost() {
		return ServerHost;
	}

	public void setServerHost(String serverHost) {
		ServerHost = serverHost;
	}

	public String getServerDescribe() {
		return ServerDescribe;
	}

	public void setServerDescribe(String serverDescribe) {
		ServerDescribe = serverDescribe;
	}

}
