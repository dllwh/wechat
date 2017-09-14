package com.cdeledu.util.database.redis.operate;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis列表是简单的字符串列表，按照插入顺序排序。
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月13日 上午9:26:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ListRedis extends RedisOperate {
	/**
	 * @方法描述: 将一个值插入到列表头部，value可以重复，返回列表的长度
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 返回List的长度
	 */
	public static int lpush(String key, final String... value) {
		try {
			jedis = acquireConnection();
			return jedis.lpush(key, value).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时)，返回0
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 返回List的长度
	 */
	public static int lpushx(String key, final String... value) {
		try {
			jedis = acquireConnection();
			return jedis.lpushx(key, value).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 在列表中的尾部添加一个个值，返回列表的长度
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return
	 */
	public static int rpush(String key, final String... value) {
		try {
			jedis = acquireConnection();
			return jedis.rpush(key, value).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 仅当列表存在时，才会向列表中的尾部添加一个值，返回列表的长度
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return
	 */
	public static int rpushx(String key, final String... value) {
		try {
			jedis = acquireConnection();
			return jedis.rpushx(key, value).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 获取列表长度，key为空时返回0
	 * @param key
	 * @return
	 */
	public static int llen(String key) {
		if (isEmpty(key)) {
			return 0;
		}
		try {
			jedis = acquireConnection();
			return jedis.llen(key).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 移除列表元素，返回移除的元素数量
	 * @param key
	 * @param count，标识，表示动作或者查找方向
	 *            <li>当count=0时，移除所有匹配的元素；</li>
	 *            <li>当count为负数时，移除方向是从尾到头；</li>
	 *            <li>当count为正数时，移除方向是从头到尾；</li>
	 * @param value
	 *            匹配的元素
	 * @return
	 */
	public static int lrem(String key, long count, String value) {
		try {
			jedis = acquireConnection();
			return jedis.lrem(key, count, value).intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 通过索引设置列表元素的值，当超出索引时会抛错。成功设置返回true
	 * @param key
	 * @param index
	 *            索引
	 * @param value
	 * @return
	 */
	public static boolean lset(String key, long index, String value) {
		try {
			jedis = acquireConnection();
			String statusCode = jedis.lset(key, index, value);
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
	 * @方法描述: 获取List列表
	 * @param key
	 * @param start
	 *            开始索引
	 * @param end
	 *            结束索引
	 * @return
	 */
	public static List<String> lrange(String key, long start, long end) {
		try {
			jedis = acquireConnection();
			return jedis.lrange(key, start, end);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 * @param key
	 * @param start
	 *            <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
	 *            <li>如果start大于end，则返回一个空的列表，即列表被清空</li>
	 * @param end
	 *            <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
	 *            <li>可以超出索引，不影响结果</li>
	 * @return
	 */
	public static boolean ltrim(String key, long start, long end) {
		try {
			jedis = acquireConnection();
			String statusCode = jedis.ltrim(key, start, end);
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
	 * @方法描述: 通过索引获取列表中的元素
	 * @param key
	 * @param index
	 *            索引，0表示最新的一个元素
	 * @return
	 */
	public static String lindex(String key, long index) {
		try {
			jedis = acquireConnection();
			return jedis.lindex(key, index);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return
	 */
	public static String lpop(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.lpop(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return
	 */
	public static String rpop(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.rpop(key);
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 * @param sourceKey
	 *            源列表的key，当源key不存在时，结果返回Null
	 * @param targetKey
	 *            目标列表的key，当目标key不存在时，会自动创建新的
	 * @return
	 */
	public static String rpoplpush(String srckey, String dstkey) {
		try {
			jedis = acquireConnection();
			return jedis.rpoplpush(srckey, dstkey);
		} finally {
			returnResource();
		}
	}
}
