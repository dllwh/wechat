package com.cdeledu.util.database.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis - 哨兵模式
 * 
 *       <pre>
 * 			若有某一台主服务器出现故障，将自动把其他正常的从服务器切换为主服务器，代替出现故障主服务器的工作
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年5月11日 上午9:33:55
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class RedisSentinelFactory implements RedisBasicCommand {
	/** ----------------------------------------------------- Fields start */
	private static RedisSentinelFactory redisSentinelFactory;
	private static ReentrantLock lockJedis = new ReentrantLock();
	private static JedisSentinelPool jedisPool;
	private Jedis jedis;

	/** ----------------------------------------------------- Fields end */
	private RedisSentinelFactory(String auth, List<String> address, String masterName, int timeout,
			int database) {
		jedisPool = new RedisConfigFactory(auth, address, masterName, timeout, database)
				.redisPoolFactory();
	}

	public static RedisSentinelFactory getInstance(String auth, List<String> address,
			String masterName, int timeout, int database) {
		if (redisSentinelFactory == null) {
			synchronized (RedisSentinelFactory.class) {
				if (redisSentinelFactory == null) {
					redisSentinelFactory = new RedisSentinelFactory(auth, address, masterName,
							timeout, database);
				}
			}
		}
		return redisSentinelFactory;
	}

	private Jedis getRedisClient() {
		// 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
		assert !lockJedis.isHeldByCurrentThread();
		lockJedis.lock();
		try {
			jedis = jedisPool.getResource();
		} catch (Exception getRedisClientExp) {
			getRedisClientExp.printStackTrace();
		} finally {
			lockJedis.unlock();
		}
		return jedis;
	}

	private void closeRedisClient() {
		if (jedis != null) {
			// jedis.resetState();
			jedis.quit();
			jedis.close();
		}
	}

	/**
	 * @方法:是否为空
	 * @创建人:独泪了无痕
	 * @param key
	 * @return
	 */
	private static boolean isEmpty(final CharSequence... key) {
		if (StringUtils.isNoneBlank(key)) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		// List<String> address = Lists.newArrayList();
		// address.add("192.168.192.105:27004");
		// address.add("192.168.192.106:27004");
		// address.add("192.168.192.107:27004");
		// RedisSentinelFactory.getInstance("", address, "mymaster", 10000, 0);
	}

	/**
	 * 遍历所有的key
	 */
	public Set<String> getAllKeys() {
		try {
			jedis = getRedisClient();
			// 获取数据并输出
			return jedis.keys("*");
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 查找当前数据库中所有符合给定模式 pattern 的 key 。 <br>
	 *        KEYS * 匹配数据库中所有 key 。 <br>
	 *        KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。 <br>
	 *        KEYS h*llo 匹配 hllo 和 heeeeello 等。 <br>
	 *        KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。 <br>
	 *        特殊符号用 \ 隔开<br>
	 *
	 *        注意：KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key，
	 *        你最好还是用 Redis 的集合结构(set)来代替。
	 *
	 * @param pattern
	 *            符合给定模式的 key 列表。
	 * @return
	 */
	public Set<String> getkeys(String pattern) {
		if (isEmpty(pattern)) {
			pattern = "*";
		}
		try {
			// 获取数据并输出
			return getRedisClient().keys(pattern);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 判断是否存在该key
	 * @param key
	 * @return 存在返回true，否则返回false
	 */
	public boolean exists(String key) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			// 获取数据并输出
			return getRedisClient().exists(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将key设置为永久
	 * @param key
	 * @return 当生存时间移除成功时，返回 true. 如果 key 不存在或 key 没有设置生存时间，返回 false
	 */
	public boolean persist(String key) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			// 获取数据并输出
			return 1 == getRedisClient().persist(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回key所存储的value的数据结构类型，它可以返回string, list, set, zset 和 hash等不同的类型。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String type(String key) {
		if (isEmpty(key) || !exists(key)) {
			return "";
		}
		try {
			return getRedisClient().type(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * @param key
	 * @param seconds
	 *            为Null时，将会马上过期。可以设置-1，0，表示马上过期
	 * @return
	 */
	public boolean expire(String key, int seconds) {
		if (isEmpty(key)) {
			return false;
		}

		if (seconds < -1) {
			seconds = -1;
		}
		try {
			return 1 == getRedisClient().expire(key, seconds);
		} finally {
			closeRedisClient();
		}
	}

	public boolean pexpireat(String key, long milliseconds) {
		if (isEmpty(key)) {
			return false;
		}

		if (milliseconds < -1) {
			milliseconds = -1;
		}
		try {
			return 1 == getRedisClient().expireAt(key, milliseconds);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)
	 * @param key
	 * @return 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key
	 *         的剩余生存时间。
	 */
	public Long ttl(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().ttl(key);
		} finally {
			closeRedisClient();
		}
	}

	public Long pttl(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().pttl(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 修改 key的名称,成功返回true;<br/>
	 *        如果key与newkey相同，将返回一个错误。如果newkey已经存在，则值将被覆盖
	 * @param oldkey
	 * @param newKey
	 * @return
	 * @throws Exception
	 */
	public boolean rename(String oldkey, String newkey) {
		if (isEmpty(oldkey) || isEmpty(newkey) || oldkey.equalsIgnoreCase(newkey)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().rename(oldkey, newkey));
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 仅当 newkey 不存在时,将 key 改名为 newkey,成功返回true
	 * @param oldkey
	 * @param newKey
	 * @return
	 * @throws Exception
	 */
	public boolean renamenx(String oldkey, String newkey) {
		if (isEmpty(oldkey) || exists(newkey)) {
			return false;
		}
		try {
			return 1 == getRedisClient().renamenx(oldkey, newkey);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 删除指定的一批keys，如果删除中的某些key不存在，则直接忽略
	 * @param key
	 * @return 返回删除个数
	 */
	public boolean del(String... key) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return getRedisClient().del(key) >= 0;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 设定该Key持有指定的字符串Value，如果该Key已经存在，则覆盖其原有值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().set(key, value));
		} finally {
			closeRedisClient();
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
	public boolean setnx(String key, String value) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return 1 == getRedisClient().setnx(key, value);
		} finally {
			closeRedisClient();
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
	public boolean setex(String key, int seconds, String value) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().setex(key, seconds, value));
		} finally {
			closeRedisClient();
		}
	}

	public boolean psetex(String key, long milliseconds, String value) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().psetex(key, milliseconds, value));
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 通过Redis的key获取值，并释放连接资源
	 * @param key
	 *            键值
	 * @return 成功返回value，失败返回""
	 */
	public String get(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			String result = getRedisClient().get(key);
			return isEmpty(result) || "nil".equalsIgnoreCase(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
	 * @param key
	 * @param value
	 * @return 返回给定 key 的旧值。 当 key 没有旧值时，也即是， key 不存在时，返回 null
	 */
	public String getSex(String key, String value) {
		if (isEmpty(key) || isEmpty(value)) {
			return "";
		}
		try {
			String result = getRedisClient().getSet(key, value);
			return isEmpty(result) || "nil".equalsIgnoreCase(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回指定Key的Value字符长度，如果该Key不存在，返回0
	 * @param key
	 * @return
	 */
	public Long strLength(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().strlen(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 一次获取多个 key 的值，如果对应 key 不存在，则对应返回null
	 * @param key
	 *            键值
	 * @return 成功返回value，失败返回""
	 */
	public List<String> mget(String... keys) {
		if (isEmpty(keys)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().mget(keys);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 截取字符串，从下标为n开始截取到n或n+1
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 * @throws Exception
	 */
	public String getrange(String key, long startOffset, long endOffset) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			String result = getRedisClient().getrange(key, startOffset, endOffset);
			return isEmpty(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将 key 中储存的数字值增一。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作
	 * @param key
	 * @return 执行 INCR 命令之后 key 的值
	 */
	public Long incr(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().incr(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将 key 所储存的值加上增量 integer 。 <br>
	 *        如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
	 * @param key
	 * @param integer
	 *            要增加的值
	 * @return 加上 integer 之后key 的值
	 */
	public Long incrBy(String key, long integer) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().incrBy(key, integer);
		} finally {
			closeRedisClient();
		}
	}

	public Double incrByFloat(String key, double value) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().incrByFloat(key, value);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将 key 中储存的数字值减一。 <BR>
	 *        如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作
	 * @param key
	 * @return 执行 DECR 命令之后 key 的值
	 */
	public Long decr(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().decr(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将 key 所储存的值加上增量 integer 。<BR>
	 *        如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令
	 * @param key
	 * @param integer
	 * @return 加上 integer 之后 key 的值
	 */
	public Long decrBy(String key, long integer) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().decrBy(key, integer);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 <br>
	 *        如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
	 * @param key
	 * @param value
	 * @return 追加 value 之后 key 中字符串的长
	 */
	public Long append(String key, String value) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().append(key, value);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 删除指定的hash field
	 * @param keys
	 * @return 被成功删除的数量.当 key 不存在时，返回 0
	 */
	public Long hdel(String key, String... field) {
		if (isEmpty(key) || !exists(key)) {
			return null;
		}
		try {
			return getRedisClient().hdel(key, field);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回指定hash的field数量
	 * @param key
	 */
	public Long hlen(String key) {
		if (isEmpty(key) || !exists(key)) {
			return null;
		}
		try {
			return getRedisClient().hlen(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回 key 指定的哈希集中所有字段的名字
	 * @param key
	 * @return 一个包含哈希表中所有域的表。 当 key 不存在时，返回一个空表
	 */
	public Set<String> hkeys(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().hkeys(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回hash的所有value
	 * @param key
	 * @return result 一个包含哈希表中所有值的表。 当 key 不存在时，返回一个空表。
	 */
	public List<String> hvals(String key) {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().hvals(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 获取指定的hash field
	 * @param key
	 * @param field
	 * @return 给定域的值。 当给定域不存在或是给定 key 不存在时，返回 null
	 */
	public String hget(String key, String field) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			return getRedisClient().hget(key, field);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 同时将多个 field-value (域-值)对设置到哈希表 key 中。 此命令会覆盖哈希表中已存在的域。如果 key
	 *        不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * @param key
	 * @param hash
	 * @return 如果命令执行成功，返回 true。 当 key 不是哈希表(hash)类型时，返回false
	 */
	public boolean hmset(String key, Map<String, String> hash) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().hmset(key, hash));
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 获取全部指定的hash filed
	 * @param key
	 * @param fields
	 */
	public List<String> hmget(String key, String... fields) {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().hmget(key, fields);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 所有的域和值
	 * @param key
	 * @return 以列表形式返回哈希表的域和域的值。 若 key 不存在，返回空列表。
	 */
	public Map<String, String> hgetAll(String key) {
		if (isEmpty(key)) {
			return Maps.newHashMap();
		}
		try {
			return getRedisClient().hgetAll(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 测试指定field是否存在
	 * @param key
	 *            不能为空
	 * @param field
	 *            不能为空
	 * @return 返回hash里面field是否存在
	 */
	public boolean hexists(String key, String field) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return getRedisClient().hexists(key, field);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 设置hash field为指定值<br>
	 *        如果key不存在,则先创建;<br>
	 *        如果域 field 已经存在,旧值将被覆盖
	 * @param key
	 * @param field
	 * @param value
	 */
	public boolean hset(String key, String field, String value) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return 1 == getRedisClient().hset(key, field, value);
		} finally {
			closeRedisClient();
		}
	}

	public List<String> blpop(int timeout, String key) {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().blpop(timeout, key);
		} finally {
			closeRedisClient();
		}
	}

	public List<String> brpop(int timeout, String key) {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().blpop(timeout, key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述:Lindex 命令用于通过索引获取列表中的元素。<br>
	 *              也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
	 * @param key
	 * @param index
	 *            索引，0表示最新的一个元素
	 * @return 列表中下标为指定索引值的元素。
	 */
	public String lindex(String key, long index) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			String result = getRedisClient().lindex(key, index);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return 列表的第一个元素。
	 */
	public String lpop(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			String result = getRedisClient().lpop(key);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Lpush 命令将一个或多个值插入到列表头部。 <br>
	 *        如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。 <br>
	 *        当 key 存在但不是列表类型时，返回一个错误 <br>
	 *        value可以重复
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 返回List的长度
	 */
	public Long lpush(String key, String... string) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().lpush(key);
		} catch (Exception lpushExp) {
			return null;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时)，返回0
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 返回List的长度
	 */
	public Long lpushx(String key, String... value) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().lpushx(key, value);
		} catch (Exception lpushxExp) {
			return null;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回列表的长度<br>
	 *        如果列表 key 不存在，则 key 被解释为一个空列表，返回 0 <br>
	 *        如果 key 不是列表类型，返回一个错误。
	 * @param key
	 * @return
	 */
	public Long llen(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().llen(key);
		} catch (Exception llenExp) {
			return null;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Lrange 返回列表中指定区间内的元素<br>
	 *        其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
	 * @param key
	 * @param start
	 *            开始索引
	 * @param end
	 *            结束索引
	 * @return 一个列表，包含指定区间内的元素。
	 */
	public List<String> lrange(String key, long start, long end) {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().lrange(key, start, end);
		} catch (Exception lrangeExp) {
			return Lists.newArrayList();
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Lrem 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
	 * @param key
	 * @param count，标识，表示动作或者查找方向
	 *            <li>当count=0时，移除所有匹配的元素；</li>
	 *            <li>当count为负数时，移除方向是从尾到头；</li>
	 *            <li>当count为正数时，移除方向是从头到尾；</li>
	 * @param value
	 *            匹配的元素
	 * @return 被移除元素的数量。 列表不存在时返回 0 。
	 */
	public Long lrem(String key, long count, String value) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().lrem(key, count, value);
		} finally {
			closeRedisClient();
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
	 * @return 命令执行成功时，返回 true。
	 */
	public boolean ltrim(String key, long start, long end) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().ltrim(key, start, end));
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 通过索引设置列表元素的值，当超出索引或对空列表时会抛错。成功设置返回true
	 * @param key
	 * @param index
	 *            索引
	 * @param value
	 * @return
	 */
	public boolean lset(String key, long index, String value) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return "ok".equalsIgnoreCase(getRedisClient().lset(key, index, value));
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return 列表的最后一个元素。
	 */
	public String rpop(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			return getRedisClient().rpop(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Rpush 命令用于将一个或多个值插入到列表的尾部(最右边)。<br>
	 *        如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。 <br>
	 *        当列表存在但不是列表类型时，返回一个错误。
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 执行 RPUSH 操作后，列表的长度。
	 */
	public Long rpush(String key, String... string) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().rpush(key, string);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Rpushx 命令用于将一个或多个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效。
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 执行 Rpushx 操作后，列表的长度
	 */
	public Long rpushx(String key, String... string) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().rpushx(key, string);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 对特定key的set增加一个或多个值，返回是增加元素的个数<br>
	 *        注意：对同一个member多次add，set中只会保留一份。
	 * @param key
	 * @param members
	 * @return 被添加到集合中的新元素的数量
	 */
	public Long sadd(String key, String... member) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().sadd(key, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Sdiff 命令返回给定集合之间的差集。不存在的集合 key 将视为空集。
	 * @param keys
	 * @return 包含差集成员的列表。
	 */
	public Set<String> sdiff(String... keys) {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			return getRedisClient().sdiff(keys);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 判断值是否是set的member。<br>
	 *        如果值是set的member返回true，否则，返回false
	 * @param key
	 * @param member
	 * @return 如果成员元素是集合的成员，返回 true。 如果成员元素不是集合的成员，或 key 不存在，返回 false
	 */
	public boolean sismember(String key, String member) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return getRedisClient().sismember(key, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回集合 key 中的所有member<br>
	 *        不存在的 key 被视为空集合
	 * @param key
	 * @return 集合中的所有成员。
	 */
	public Set<String> smembers(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().smembers(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Sinter 命令返回给定所有给定集合的交集。<br>
	 *        不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集
	 * @param keys
	 * @return 交集成员的列表。
	 */
	public Set<String> sinter(String... keys) {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			return getRedisClient().sinter(keys);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Spop 命令用于移除并返回集合中的一个随机元素。
	 *        <li>当set为空或者不存在时，返回Null</li>
	 * @param key
	 * @return 被移除的随机元素。 当集合不存在或是空集时，返回 null
	 */
	public String spop(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			String result = getRedisClient().spop(key);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	public Set<String> spop(String key, long count) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().spop(key, count);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 从set中返回一个随机member
	 * @param key
	 * @return 只提供集合 key 参数时，返回一个元素；如果集合为空，返回 null
	 */
	public String srandmember(String key) {
		if (isEmpty(key)) {
			return "";
		}
		try {
			String result = getRedisClient().srandmember(key);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: Srandmember 命令用于返回集合中的一个随机元素.<br>
	 *        如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。<br>
	 *        如果 count 大于等于集合基数，那么返回整个集合。<br>
	 *        如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。
	 * @param key
	 * @param count
	 * @return 只提供集合 key 参数时，返回一个元素；如果集合为空，返回 null。 如果提供了 count
	 *         参数，那么返回一个数组；如果集合为空，返回空数组
	 */
	public List<String> srandmember(String key, int count) {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		try {
			return getRedisClient().srandmember(key, count);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 移除一个或多个membe，不存在的成员元素会被忽略。<br>
	 *        当 key 不是集合类型，返回一个错误。
	 * @param key
	 * @return 被成功移除的元素的数量，不包括被忽略的元素。
	 */
	public boolean srem(String key, String... member) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			return getRedisClient().srem(key, member) >= 0;
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回set的member个数，如果set不存在，返回0
	 * @param key
	 * @return 集合的数量。 当集合 key 不存在时，返回 0
	 */
	public Long scard(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().scard(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * @方法描述: 返回所有给定集合的并集，相同的只会返回一个.key为空，则返null
	 * @param keys
	 * @return 并集成员的列表。
	 */
	public Set<String> sunion(String... keys) {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			return getRedisClient().sunion(keys);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br>
	 * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该
	 * member 在正确的位置上。<br>
	 * score 值可以是整数值或双精度浮点数。<br>
	 * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
	 *
	 * @param key
	 * @param score
	 * @param member
	 *            成员
	 * @return result 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 */
	public Long zadd(String key, double score, String member) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zadd(key, score, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 的基数。
	 *
	 * @param key
	 * @return result 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
	 */
	public Long zcard(String key) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zcard(key);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中，成员 member 的 score 值。<br>
	 * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
	 *
	 * @param key
	 * @param member
	 *            成员
	 * @return result member 成员的 score 值，以字符串形式表示。
	 */
	public Double zscore(String key, String member) {
		if (isEmpty(key) || !exists(key)) {
			return null;
		}
		try {
			return getRedisClient().zscore(key, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
	 *
	 * @param key
	 * @param min
	 *            最小分值
	 * @param max
	 *            最大分值
	 * @return result score 值在 min 和 max 之间的成员的数量。
	 */
	public Long zcount(String key, double min, double max) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zcount(key, min, max);
		} finally {
			closeRedisClient();
		}
	}

	public Long zcount(String key, String min, String max) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zcount(key, min, max);
		} finally {
			closeRedisClient();
		}
	}

	public Long zlexcount(String key, String min, String max) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zlexcount(key, min, max);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员。 其中成员的位置按 score 值递增(从小到大)来排序。<br>
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。<br>
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br>
	 * 超出范围的下标并不会引起错误。
	 *
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return result 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrange(String key, long start, long end) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrange(key, start, end);
		} finally {
			closeRedisClient();
		}
	}

	public Set<String> zrangeByLex(String key, String min, String max) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrangeByLex(key, min, max);
		} finally {
			closeRedisClient();
		}
	}

	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrangeByLex(key, min, max, offset, count);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score
	 * 值递增(从小到大)次序排列。
	 *
	 * @param key
	 * @param min
	 *            最小分值
	 * @param max
	 *            最大分值
	 * @return result 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrangeByScore(key, min, max);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score
	 * 值递增(从小到大)次序排列。<br>
	 * 可选的 LIMIT 参数指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset
	 * 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间。
	 *
	 * @param key
	 * @param min
	 *            最小分值
	 * @param max
	 *            最大分值
	 * @param offset
	 *            要跳过的元素数量
	 * @param count
	 *            跳过offset个指定的元素之后，要返回多少个对象
	 * @return result 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrangeByScore(key, min, max, offset, count);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。<br>
	 * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
	 *
	 * @param key
	 * @param member
	 *            成员
	 * @return result 如果 member 是有序集 key 的成员，返回 member 的排名。 如果 member 不是有序集 key
	 *         的成员，返回 nil 。
	 */
	public Long zrank(String key, String member) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrank(key, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。<br>
	 * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
	 *
	 * @param key
	 * @param member
	 *            成员
	 * @return result 如果 member 是有序集 key 的成员，返回 member 的排名。 如果 member 不是有序集 key
	 *         的成员，返回 nil 。
	 */
	public Long zrevrank(String key, String member) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrevrank(key, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。 当 key 存在但不是有序集类型时，返回一个错误。
	 *
	 * @param key
	 * @param member
	 *            成员
	 * @return result 被成功移除的成员的数量，不包括被忽略的成员。
	 */
	public Long zrem(String key, String... member) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrem(key, member);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 移除有序集 key 中，指定排名(rank)区间内的所有成员。<br>
	 * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。<br>
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。<br>
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 *
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return result 被移除成员的数量。
	 */
	public Long zremrangeByRank(String key, long start, long end) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zremrangeByRank(key, start, end);
		} finally {
			closeRedisClient();
		}
	}

	public Long zremrangeByLex(String key, String min, String max) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zremrangeByLex(key, min, max);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
	 *
	 * @param key
	 * @param start
	 *            开始分值
	 * @param end
	 *            结束分值
	 * @return result 被移除成员的数量。
	 */
	public Long zremrangeByScore(String key, double start, double end) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zremrangeByScore(key, start, end);
		} finally {
			closeRedisClient();
		}
	}

	public Long zremrangeByScore(String key, String start, String end) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zremrangeByScore(key, start, end);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员。 其中成员的位置按 score 值递减(从大到小)来排列。<br>
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。<br>
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br>
	 * 超出范围的下标并不会引起错误。
	 *
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return result 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrange(String key, long start, long end) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrevrange(key, start, end);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score
	 * 值递减(从大到小)的次序排列。
	 *
	 * @param key
	 * @param min
	 *            最小分值
	 * @param max
	 *            最大分值
	 * @return result 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrevrangeByScore(key, max, min);
		} finally {
			closeRedisClient();
		}
	}

	/**
	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score
	 * 值递减(从大到小)的次序排列。
	 *
	 * @param key
	 * @param min
	 *            最小分值
	 * @param max
	 *            最大分值
	 * @param offset
	 *            要跳过的元素数量
	 * @param count
	 *            跳过offset个指定的元素之后，要返回多少个对象
	 * @return result 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		if (isEmpty(key)) {
			return null;
		}
		try {
			return getRedisClient().zrevrangeByScore(key, max, min, offset, count);
		} finally {
			closeRedisClient();
		}
	}
}
