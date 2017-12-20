package com.cdeledu.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis配置
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 上午10:29:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisConfig {
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private RedisConnectionFactory factory;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}

	public HashOperations<String, String, Object> hashOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	public ValueOperations<String, String> valueOperations(
			RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	public ListOperations<String, Object> listOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	public SetOperations<String, Object> setOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	public ZSetOperations<String, Object> zSetOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
