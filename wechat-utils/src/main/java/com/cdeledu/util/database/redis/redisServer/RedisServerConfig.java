package com.cdeledu.util.database.redis.redisServer;

import java.util.List;

import com.cdeledu.util.database.redis.model.RedisServerModel;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年9月13日 上午12:11:29
 * @版本: V0.0.1
 * @since: JDK 1.7
 */
public class RedisServerConfig {
	/** ----------------------------------------------------- Fields start */
	private static List<RedisServerModel> redisServers;

	/** ----------------------------------------------------- Fields end */
	static {
		LoadConfig();
	}

	public static void LoadConfig() {
		redisServers = null;
	}
}
