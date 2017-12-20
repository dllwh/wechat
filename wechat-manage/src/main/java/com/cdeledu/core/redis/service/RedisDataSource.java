package com.cdeledu.core.redis.service;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	ShardedJedis getRedisClient();

	void returnResource(ShardedJedis shardedJedis);
}
