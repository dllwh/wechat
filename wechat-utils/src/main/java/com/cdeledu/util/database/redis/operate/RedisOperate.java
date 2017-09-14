package com.cdeledu.util.database.redis.operate;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cdeledu.util.database.redis.RedisClient;

import redis.clients.jedis.Jedis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis相关操作
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月13日 下午4:42:17
 * @版本: V1.0
 * @since: JDK 1.7
 */
class RedisOperate {
	/** ----------------------------------------------------- Fields start */
	protected Logger logger = Logger.getLogger(getClass());
	private static RedisClient redisClient = RedisClient.getRedisClient();
	protected static Jedis jedis = null;
	/**
	 * redis过期时间,以秒为单位
	 */
	protected final static int EXRP_HOUR = 60 * 60; // 一小时
	protected final static int EXRP_DAY = 60 * 60 * 24; // 一天
	protected final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	protected final static String SUCCESS_OK = "ok";

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 是否为空
	 * @param key
	 * @return
	 */
	protected static boolean isEmpty(final CharSequence... key) {
		if (StringUtils.isNoneBlank(key))
			return false;
		return true;
	}

	/**
	 * @方法描述: 获取连接
	 */
	protected static Jedis acquireConnection() {
		return redisClient.acquireConnection();
	}

	/**
	 * @方法描述: 释放jedis资源
	 */
	protected static void returnResource() {
		redisClient.returnConnection(jedis);
	}
}
