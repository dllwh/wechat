package com.cdeledu.util.database.redis.operate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月13日 上午9:23:03
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HashRedis extends RedisOperate {
	/**
	 * @方法描述: 设置hash field为指定值<br>
	 *        如果key不存在,则先创建;<br>
	 *        如果域 field 已经存在,旧值将被覆盖
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void hset(String key, String field, String value) {
		try {
			jedis = acquireConnection();
			jedis.hset(key, field, value);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 一次存储多个Key和值
	 * @param key
	 * @param hash
	 */
	private static boolean hmset(String key, Map<String, String> hash) {
		if (MapUtils.isEmpty(hash)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			String statusCode = jedis.hmset(key, hash);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 获取指定的hash field
	 * @param key
	 * @param field
	 */
	private static void hget(String key, String field) {
		try {
			jedis = acquireConnection();
			jedis.hget(key, field);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 所有的域和值
	 * @param key
	 * @return
	 */
	public static Map<String, String> hgetAll(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.hgetAll(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 获取全部指定的hash filed
	 * @param key
	 * @param fields
	 */
	public static List<String> hmget(String key, String... fields) {
		if (isEmpty(key) || isEmpty(fields)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.hmget(key, fields);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 测试指定field是否存在
	 * @param key
	 *            不能为空
	 * @param field
	 *            不能为空
	 * @return
	 */
	public static boolean hexists(String key, String field) {
		if (isEmpty(key) || isEmpty(field)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			return jedis.hexists(key, field);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 删除指定的hash field
	 * @param keys
	 */
	public static int hdel(String... keys) {
		if (isEmpty(keys)) {
			return 0;
		}
		try {
			jedis = acquireConnection();
			return jedis.del(keys).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回指定hash的field数量
	 * @param key
	 */
	private static void hlen(String key) {
		try {
			jedis = acquireConnection();
			jedis.hlen(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回hash的所有field
	 * @param key
	 */
	private static void hkeys(String key) {
		try {
			jedis = acquireConnection();
			jedis.hkeys(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回hash的所有value
	 * @param key
	 */
	private static List<String> hvals(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.hvals(key);
		} finally {
			returnResource();
		}
	}

	public static void main(String[] args) {
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("name", "xinxin");
		// map.put("age", "22");
		// map.put("qq", "123456");
		// hmset("chinatetLive", map);
	}

}
