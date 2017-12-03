package com.cdeledu.core.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.cdeledu.common.base.BaseClass;

public class RedisClientServiceImpl<K, V> extends BaseClass implements RedisClientService {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	protected RedisTemplate<K, V> redisTemplate;

	/** ----------------------------------------------------- Fields end */


	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
