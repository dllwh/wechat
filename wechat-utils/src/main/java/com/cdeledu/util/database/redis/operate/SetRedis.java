package com.cdeledu.util.database.redis.operate;

import java.util.List;
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
		if (isEmpty(key) || isEmpty(members)) {
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
	 *        如果值是set的member返回true，否则，返回false
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

	/**
	 * @方法描述: 返回set的member个数，如果set不存在，返回0
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Long scard(String key) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.scard(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 从set中返回一个随机member
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String srandmember(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.srandmember(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 移除一个或多个membe
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Long srem(String key, String... members) throws Exception {
		if (isEmpty(key) || isEmpty(members)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.srem(key, members);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回所有给定集合的并集，相同的只会返回一个.key为空，则返null
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public static Set<String> sunion(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.sunion(keys);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 多个set的并集.key为空，则返null
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public static Set<String> sinter(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.sinter(keys);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回给定所有集合的差集（获取第一个key中与其它key不相同的值，当只有一个key时，就返回这个key的所有值）
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public static Set<String> sdiff(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.sdiff(keys);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 将 member 元素从 sourceKey 集合移动到 targetKey 集合
	 *        <li>成功返回true</li>
	 *        <li>当member不存在于sourceKey时，返回false</li>
	 *        <li>当sourceKey不存在时，也返回false</li>
	 * @param srckey
	 * @param dstkey
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public static boolean smove(String srckey, String dstkey, String member) throws Exception {
		if (isEmpty(srckey)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			Long value = jedis.smove(srckey, dstkey, member);
			if (value > 0) {
				return true;
			}
		} finally {
			returnResource();
		}
		return false;
	}

	/**
	 * @方法描述: 移除并返回集合中的一个随机元素
	 *        <li>当set为空或者不存在时，返回Null</li>
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String spop(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.spop(key);

		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 返回集合中一个或多个随机数
	 *        <li>当count大于set的长度时，set所有值返回，不会抛错。</li>
	 *        <li>当count等于0时，返回[]</li>
	 *        <li>当count小于0时，也能返回。如-1返回一个，-2返回两个</li>
	 * @param key
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public static List<String> srandMember(String key, int count) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.srandmember(key, count);
		} finally {
			returnResource();
		}
	}

}
