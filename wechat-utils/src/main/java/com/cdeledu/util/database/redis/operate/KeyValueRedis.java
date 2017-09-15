package com.cdeledu.util.database.redis.operate;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 键命令用于管理 redis 的键-值
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月13日 上午9:24:03
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class KeyValueRedis extends RedisOperate {
	/**
	 * @方法描述: 遍历所有的key
	 * @return
	 */
	public static Set<String> getAllkeys() {
		try {
			jedis = acquireConnection();
			// 获取数据并输出
			return jedis.keys("*");
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 设定该Key持有指定的字符串Value，如果该Key已经存在，则覆盖其原有值
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, String value) {
		try {
			jedis = acquireConnection();
			String result = jedis.set(key, value);
			// 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
			if (SUCCESS_OK.equalsIgnoreCase(result)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 只insert不update</br>
	 *        如果指定的Key不存在，则设定该Key持有指定字符串Value，此时其效果等价于SET命令 </br>
	 *        相反，如果该Key已经存在，该命令将不做任何操作并返回 </br>
	 *        setnx 是set if not exists 的缩写
	 * @param key
	 * @param value
	 * @return 1表示设置成功，否则0
	 */
	public static int setnx(String key, String value) {
		try {
			jedis = acquireConnection();
			return jedis.setnx(key, value).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 一是设置该Key的值为指定字符串，同时设置该Key在Redis服务器中的存活时间(秒数)</br>
	 *        该命令主要应用于Redis被当做Cache服务器使用时</br>
	 *        如果key存在覆盖旧值成功返回OK
	 * 
	 * @param key
	 * @param time
	 *            过期时间seconds单位是秒
	 * @param value
	 * @return
	 */
	public static boolean setex(String key, int time, String value) {
		try {
			jedis = acquireConnection();
			// 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
			String result = jedis.setex(key, time, value);
			// 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
			if (SUCCESS_OK.equalsIgnoreCase(result)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 通过Redis的key获取值，并释放连接资源
	 * @param key
	 *            键值
	 * @return 成功返回value，失败返回""
	 */
	public static String get(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.get(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 根据key删除
	 * @param key
	 * @return 返回删除个数
	 */
	public static int del(String key) {
		if (isEmpty(key)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.del(key).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 根据key批量删除
	 * @param key
	 * @return 返回删除个数
	 */
	public static int del(String[] key) {
		if (isEmpty(key)) {
			return 0;
		}
		if (StringUtils.isNoneBlank(key)) {
			try {
				jedis = acquireConnection();
				return jedis.del(key).intValue();
			} finally {
				returnResource();
			}
		}
		return 0;
	}

	/**
	 * @方法描述: 判断是否存在该key
	 * @param key
	 * @return 存在返回true，否则返回false
	 */
	public static boolean exists(String key) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			return jedis.exists(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回指定Key的Value字符长度，如果该Key不存在，返回0
	 * @param key
	 * @return
	 */
	public static int StrLength(String key) {
		try {
			jedis = acquireConnection();
			return jedis.strlen(key).intValue();
		} finally {
			returnResource();
		}
	}
}
