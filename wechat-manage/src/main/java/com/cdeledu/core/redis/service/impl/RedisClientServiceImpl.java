package com.cdeledu.core.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.core.redis.service.RedisClientService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 上午8:18:36
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisClientServiceImpl<K, V> extends BaseClass implements RedisClientService {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ValueOperations<String, String> valueOperations;
	@Autowired
	private HashOperations<String, String, Object> hashOperations;
	@Autowired
	private ListOperations<String, Object> listOperations;
	@Autowired
	private SetOperations<String, Object> setOperations;
	@Autowired
	private ZSetOperations<String, Object> zSetOperations;
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
