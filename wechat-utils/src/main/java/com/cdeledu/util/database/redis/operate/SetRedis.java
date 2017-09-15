package com.cdeledu.util.database.redis.operate;

import java.util.Set;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
 *       Redis中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月13日 上午9:27:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SetRedis extends RedisOperate {
	/**
	 * @方法描述: 对特定key的set增加一个或多个值，返回是增加元素的个数<br>
	 *        注意：对同一个member多次add，set中只会保留一份。
	 * @param key
	 * @param members
	 * @return
	 */
	public static long sadd(String key, String... members) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.sadd(key, members);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回集合 key 中的所有member<br>
	 *        不存在的 key 被视为空集合
	 * @param key
	 * @return
	 */
	public static Set<String> smembers(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.smembers(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 判断值是否是set的member。<br>
	 *        如果值是set的member返回1，否则，返回0
	 * @param key
	 * @param member
	 */
	public static boolean sismember(String key, String member) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			return jedis.sismember(key, member);
		} finally {
			returnResource();
		}
	}

	public static void scard(String key) throws Exception {
		try {
			jedis = acquireConnection();
			jedis.scard(key);
		} finally {
			returnResource();
		}
	}
}
