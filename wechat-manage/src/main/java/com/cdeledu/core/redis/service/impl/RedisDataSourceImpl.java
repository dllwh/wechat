package com.cdeledu.core.redis.service.impl;

import com.cdeledu.core.redis.service.RedisDataSource;

import redis.clients.jedis.ShardedJedis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月12日 下午8:26:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisDataSourceImpl implements RedisDataSource {

	@Override
	public ShardedJedis getRedisClient() {
		return null;
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis) {

	}

}
