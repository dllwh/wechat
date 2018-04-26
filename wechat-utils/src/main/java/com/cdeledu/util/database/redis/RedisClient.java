package com.cdeledu.util.database.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.cdeledu.util.apache.collection.CollectionHelper;
import com.cdeledu.util.apache.collection.MapUtilHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.database.redis.config.RedisConfig;
import com.cdeledu.util.database.redis.entity.ClientInfo;
import com.cdeledu.util.database.redis.entity.OperateLog;
import com.cdeledu.util.database.redis.entity.RedisInfoDetail;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Slowlog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: redis的简单使用
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月11日 下午4:19:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisClient {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(RedisClient.class);
	private static ReentrantLock lockJedis = new ReentrantLock();
	private static JedisSentinelPool jedisPool = null;
	private static RedisClient redisClient;

	/** ----------------------------------------------------- Fields end */

	static {
		logger.info("start jredis connection pool");
		jedisPool = new RedisConfig().redisPoolFactory();
		if (jedisPool == null) {
			logger.info("start jredis connection pool faily");
		} else {
			logger.info("start jredis connection pool successfully");
		}

	}

	private RedisClient() {
		super();
	}

	public static RedisClient getRedisClient() {
		if (redisClient == null) {
			synchronized (RedisClient.class) {
				if (redisClient == null) {
					redisClient = new RedisClient();
				}
			}
		}
		return redisClient;
	}

	/**
	 * @方法描述: 关闭连接池
	 */
	public void shutdownConnection() {
		logger.info("start to shutdown redis server");
		if (jedisPool != null) {
			jedisPool.destroy();
		}
		logger.info("shutdown redis server successfully");
	}

	/******************* redis Key-Value操作 ************************/
	/**
	 * @方法描述: 遍历所有的key
	 * @return
	 */
	public Set<String> getAllkeys() throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			// 获取数据并输出
			return jedis.keys("*");
		} finally {
			returnConnection(jedis);
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
	public Set<String> getkeys(String pattern) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			// 获取数据并输出
			return jedis.keys(pattern);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 设定该Key持有指定的字符串Value，如果该Key已经存在，则覆盖其原有值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			String result = jedis.set(key, value);
			// 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
			if ("ok".equalsIgnoreCase(result)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
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
	public boolean setnx(String key, String value) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.setnx(key, value);
			if (1 == statusCode) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
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
	public boolean setex(String key, int time, String value) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			// 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
			String result = jedis.setex(key, time, value);
			// 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
			if ("ok".equalsIgnoreCase(result)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
	 * @param key
	 * @param value
	 * @return 返回给定 key 的旧值。 当 key 没有旧值时，也即是， key 不存在时，返回 null
	 */
	public String getSex(String key, String value) throws Exception {
		String result = "";
		if (isEmpty(key) || isEmpty(value)) {
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.getSet(key, value);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
	}

	/**
	 * @方法描述: 通过Redis的key获取值，并释放连接资源
	 * @param key
	 *            键值
	 * @return 成功返回value，失败返回""
	 */
	public String get(String key) throws Exception {
		String value = "";
		if (isEmpty(key)) {
			return value;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			value = jedis.get(key);
			value = isEmpty(value) && !"nil".equalsIgnoreCase(value) ? value : "";
		} finally {
			returnConnection(jedis);
		}
		return value;
	}

	/**
	 * @方法描述: 一次获取多个 key 的值，如果对应 key 不存在，则对应返回null
	 * @param key
	 *            键值
	 * @return 成功返回value，失败返回""
	 */
	public List<String> mget(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return Lists.newArrayList();
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.mget(keys);
		} finally {
			returnConnection(jedis);
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
	public String getrange(String key, long startOffset, long endOffset) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.getrange(key, startOffset, endOffset);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 删除指定的一批keys，如果删除中的某些key不存在，则直接忽略
	 * @param key
	 * @return 返回删除个数
	 */
	public Long del(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.del(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 根据key批量删除
	 * @param key
	 * @return 返回删除个数
	 */
	public Long del(String... key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.del(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 判断是否存在该key
	 * @param key
	 * @return 存在返回true，否则返回false
	 */
	public boolean exists(String key) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.exists(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回指定Key的Value字符长度，如果该Key不存在，返回0
	 * @param key
	 * @return
	 */
	public Long strLength(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.strlen(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * @param key
	 * @param seconds
	 *            为Null时，将会马上过期。可以设置-1，0，表示马上过期
	 * @return
	 */
	public boolean expire(String key, Integer seconds) throws Exception {
		if (isEmpty(key)) {
			return false;
		}

		if (seconds == null) {
			seconds = -1;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.expire(key, seconds);
			if (1 == statusCode) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)
	 * @param key
	 * @return 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key
	 *         的剩余生存时间。
	 */
	public Long ttl(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.ttl(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 将 key 中储存的数字值减一。 <BR>
	 *        如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作
	 * @param key
	 * @return 执行 DECR 命令之后 key 的值
	 */
	public Long decr(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.decr(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 将 key 所储存的值加上增量 integer 。<BR>
	 *        如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令
	 * @param key
	 * @param integer
	 * @return 加上 integer 之后 key 的值
	 */
	public Long decrBy(String key, long integer) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.decrBy(key, integer);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 将 key 中储存的数字值增一。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作
	 * @param key
	 * @return 执行 INCR 命令之后 key 的值
	 */
	public Long incr(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.incr(key);
		} finally {
			returnConnection(jedis);
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
	public Long incr(String key, long integer) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.incr(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 将key设置为永久
	 * @param key
	 * @return 当生存时间移除成功时，返回 true. 如果 key 不存在或 key 没有设置生存时间，返回 false
	 */
	public boolean persist(String key) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.persist(key);
			if (1 == statusCode) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 修改 key的名称,成功返回true;<br/>
	 *        如果key与newkey相同，将返回一个错误。如果newkey已经存在，则值将被覆盖
	 * @param oldkey
	 * @param newKey
	 * @return
	 * @throws Exception
	 */
	public boolean rename(String oldkey, String newKey) throws Exception {
		if (isEmpty(oldkey) || isEmpty(newKey) || oldkey.equalsIgnoreCase(newKey)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			String statusCode = jedis.rename(oldkey, newKey);
			if ("ok".equalsIgnoreCase(statusCode)) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 <br>
	 *        如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
	 * @param key
	 * @param value
	 * @return 追加 value 之后 key 中字符串的长
	 */
	public Long append(String key, String value) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.append(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 仅当 newkey 不存在时,将 key 改名为 newkey,成功返回true
	 * @param oldkey
	 * @param newKey
	 * @return
	 * @throws Exception
	 */
	public boolean renamenx(String oldkey, String newKey) throws Exception {
		if (isEmpty(oldkey) || isEmpty(newKey) || !exists(oldkey) || exists(newKey)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.renamenx(oldkey, newKey);
			if (1 == statusCode) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 返回key所存储的value的数据结构类型，它可以返回string, list, set, zset 和 hash等不同的类型。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String type(String key) throws Exception {
		if (isEmpty(key) || !exists(key)) {
			return "";
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.type(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/******************* redis Key-Value操作结束 ************************/

	/******************* redis Hash操作 ************************/

	/*******************
	 * Redis hash 是一个 string 类型的 field 和 value 的映射表.它的添加、删除操作都是 O(1) （平均）。 hash
	 * 特别适合用于存储对象。相较于将对象的每个字段存成单个 string 类型。将一个对象存 储在 hash
	 * 类型中会占用更少的内存，并且可以更方便的存取整个对象。
	 ************************/
	/**
	 * @方法描述: 设置hash field为指定值<br>
	 *        如果key不存在,则先创建;<br>
	 *        如果域 field 已经存在,旧值将被覆盖
	 * @param key
	 * @param field
	 * @param value
	 */
	public Long hset(String key, String field, String value) throws Exception {
		if (isEmpty(key) || isEmpty(field)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hset(key, field, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 设置hash\field为指定值,如果 key不存在,则先创建.如果 field已经存在,返回 0,nx 是 not exist
	 *        的意思。
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public boolean hsetnx(String key, String field, String value) throws Exception {
		if (isEmpty(key) || isEmpty(field)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.hsetnx(key, field, value);
			if (1 == statusCode) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 同时将多个 field-value (域-值)对设置到哈希表 key 中。 此命令会覆盖哈希表中已存在的域。如果 key
	 *        不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * @param key
	 * @param hash
	 * @return 如果命令执行成功，返回 true。 当 key 不是哈希表(hash)类型时，返回false
	 */
	public boolean hmset(String key, Map<String, String> hash) throws Exception {
		if (MapUtils.isEmpty(hash) || MapUtilHelper.isEmpty(hash)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			String statusCode = jedis.hmset(key, hash);
			if ("ok".equalsIgnoreCase(statusCode)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取指定的hash field
	 * @param key
	 * @param field
	 * @return 给定域的值。 当给定域不存在或是给定 key 不存在时，返回 null
	 */
	public String hget(String key, String field) throws Exception {
		String result = "";
		if (isEmpty(key) || isEmpty(field)) {
			return "";
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.hget(key, field);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
	}

	/**
	 * @方法描述: 所有的域和值
	 * @param key
	 * @return 以列表形式返回哈希表的域和域的值。 若 key 不存在，返回空列表。
	 */
	public Map<String, String> hgetAll(String key) throws Exception {
		if (isEmpty(key)) {
			return Maps.newHashMap();
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hgetAll(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取全部指定的hash filed
	 * @param key
	 * @param fields
	 */
	public List<String> hmget(String key, String... fields) throws Exception {
		if (isEmpty(key) || isEmpty(fields)) {
			return Lists.newArrayList();
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hmget(key, fields);
		} finally {
			returnConnection(jedis);
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
	public boolean hexists(String key, String field) throws Exception {
		if (isEmpty(key) || isEmpty(field)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hexists(key, field);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 删除指定的hash field
	 * @param keys
	 * @return 被成功删除的数量.当 key 不存在时，返回 0
	 */
	public Long hdel(String key, String... fields) throws Exception {
		if (isEmpty(key) || isEmpty(fields)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hdel(key, fields);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回指定hash的field数量
	 * @param key
	 */
	public Long hlen(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hlen(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回 key 指定的哈希集中所有字段的名字
	 * @param key
	 * @return 一个包含哈希表中所有域的表。 当 key 不存在时，返回一个空表
	 */
	public Set<String> hkeys(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hkeys(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回hash的所有value
	 * @param key
	 * @return result 一个包含哈希表中所有值的表。 当 key 不存在时，返回一个空表。
	 */
	public List<String> hvals(String key) throws Exception {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.hvals(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/******************* redis Hash操作结束 ************************/

	/******************* redis list操作 ************************/
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
	public Long lpush(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.lpush(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时)，返回0
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 返回List的长度
	 */
	public Long lpushx(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.lpushx(key, value);
		} finally {
			returnConnection(jedis);
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
	public Long rpush(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.rpush(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: Rpushx 命令用于将一个或多个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效。
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 执行 Rpushx 操作后，列表的长度
	 */
	public Long rpushx(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.rpushx(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回列表的长度<br>
	 *        如果列表 key 不存在，则 key 被解释为一个空列表，返回 0 <br>
	 *        如果 key 不是列表类型，返回一个错误。
	 * @param key
	 * @return
	 */
	public Long llen(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.llen(key);
		} finally {
			returnConnection(jedis);
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
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.lrem(key, count, value);
		} finally {
			returnConnection(jedis);
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
	public boolean lset(String key, long index, String value) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			String statusCode = jedis.lset(key, index, value);
			if ("ok".equalsIgnoreCase(statusCode)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
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
	public List<String> lrange(String key, long start, long end) throws Exception {
		if (isEmpty(key)) {
			return Lists.newArrayList();
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.lrange(key, start, end);
		} finally {
			returnConnection(jedis);
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
	public boolean ltrim(String key, long start, long end) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			String statusCode = jedis.ltrim(key, start, end);
			if ("ok".equalsIgnoreCase(statusCode)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
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
	public String lindex(String key, long index) throws Exception {
		String result = "";
		if (isEmpty(key)) {
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.lindex(key, index);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
	}

	/**
	 * @方法描述: 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return 列表的第一个元素。
	 */
	public String lpop(String key) throws Exception {
		String result = "";
		if (isEmpty(key)) {
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.lpop(key);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
	}

	/**
	 * @方法描述: 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return 列表的最后一个元素。
	 */
	public String rpop(String key) throws Exception {
		String result = "";
		if (isEmpty(key)) {
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.rpop(key);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
	}

	/**
	 * @方法描述: 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 * @param sourceKey
	 *            源列表的key，当源key不存在时，结果返回Null
	 * @param targetKey
	 *            目标列表的key，当目标key不存在时，会自动创建新的
	 * @return 被弹出的元素
	 */
	public String rpoplpush(String srckey, String dstkey) throws Exception {
		if (isEmpty(srckey)) {
			return "";
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.rpoplpush(srckey, dstkey);
		} finally {
			returnConnection(jedis);
		}
	}

	/********************* redis list操作结束 **************************/

	/********************* redis Set操作 **************************/
	/**
	 * @方法描述: 对特定key的set增加一个或多个值，返回是增加元素的个数<br>
	 *        注意：对同一个member多次add，set中只会保留一份。
	 * @param key
	 * @param members
	 * @return 被添加到集合中的新元素的数量
	 */
	public Long sadd(String key, String... members) throws Exception {
		if (isEmpty(key) || isEmpty(members)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.sadd(key, members);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回集合 key 中的所有member<br>
	 *        不存在的 key 被视为空集合
	 * @param key
	 * @return 集合中的所有成员。
	 */
	public Set<String> smembers(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.smembers(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 判断值是否是set的member。<br>
	 *        如果值是set的member返回true，否则，返回false
	 * @param key
	 * @param member
	 * @return 如果成员元素是集合的成员，返回 true。 如果成员元素不是集合的成员，或 key 不存在，返回 false
	 */
	public boolean sismember(String key, String member) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.sismember(key, member);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回set的member个数，如果set不存在，返回0
	 * @param key
	 * @return 集合的数量。 当集合 key 不存在时，返回 0
	 */
	public Long scard(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.scard(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 从set中返回一个随机member
	 * @param key
	 * @return 只提供集合 key 参数时，返回一个元素；如果集合为空，返回 null
	 */
	public String srandmember(String key) throws Exception {
		String result = "";
		if (isEmpty(key)) {
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.srandmember(key);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
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
	public List<String> srandmember(String key, int count) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.srandmember(key, count);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 移除一个或多个membe，不存在的成员元素会被忽略。<br>
	 *        当 key 不是集合类型，返回一个错误。
	 * @param key
	 * @return 被成功移除的元素的数量，不包括被忽略的元素。
	 */
	public Long srem(String key, String... members) throws Exception {
		if (isEmpty(key) || isEmpty(members)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.srem(key, members);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回所有给定集合的并集，相同的只会返回一个.key为空，则返null
	 * @param keys
	 * @return 并集成员的列表。
	 */
	public Set<String> sunion(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.sunion(keys);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: Sinter 命令返回给定所有给定集合的交集。<br>
	 *        不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集
	 * @param keys
	 * @return 交集成员的列表。
	 */
	public Set<String> sinter(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.sinter(keys);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: Sdiff 命令返回给定集合之间的差集。不存在的集合 key 将视为空集。
	 * @param keys
	 * @return 包含差集成员的列表。
	 */
	public Set<String> sdiff(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.sdiff(keys);
		} finally {
			returnConnection(jedis);
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
	 * @return 如果成员元素被成功移除，返回 1 。 如果成员元素不是 source 集合的成员，并且没有任何操作对 destination
	 *         集合执行，那么返回 0
	 */
	public boolean smove(String srckey, String dstkey, String member) throws Exception {
		if (isEmpty(srckey)) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			Long value = jedis.smove(srckey, dstkey, member);
			if (value > 0) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: Spop 命令用于移除并返回集合中的一个随机元素。
	 *        <li>当set为空或者不存在时，返回Null</li>
	 * @param key
	 * @return 被移除的随机元素。 当集合不存在或是空集时，返回 null
	 */
	public String spop(String key) throws Exception {
		String result = "";
		if (isEmpty(key)) {
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			result = jedis.spop(key);
			result = isEmpty(result) && !"nil".equalsIgnoreCase(result) ? result : "";
		} finally {
			returnConnection(jedis);
		}
		return result;
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
	public List<String> srandMember(String key, int count) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.srandmember(key, count);
		} finally {
			returnConnection(jedis);
		}
	}

	/********************* redis Set操作结束 **************************/

	/********************* redis Sorted操作 **************************/

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
	public Long zadd(String key, double score, String member) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zadd(key, score, member);
		} finally {
			returnConnection(jedis);
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
	public Set<String> zrange(String key, int start, int end) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrange(key, start, end);
		} finally {
			returnConnection(jedis);
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
	public Set<String> zrevrange(String key, int start, int end) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrevrange(key, start, end);
		} finally {
			returnConnection(jedis);
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
	public Long zrem(String key, String... members) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrem(key, members);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 。<br>
	 * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key
	 * increment member 。<br>
	 * 当 key 不是有序集类型时，返回一个错误。
	 *
	 * @param key
	 * @param score
	 *            score 值可以是整数值或双精度浮点数。
	 * @param member
	 *            成员
	 * @return result member成员的新 score 值，以字符串形式表示。
	 */
	public Double zincrby(String key, double score, String member) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zincrby(key, score, member);
		} finally {
			returnConnection(jedis);
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
	public Long zrank(String key, String member) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrank(key, member);
		} finally {
			returnConnection(jedis);
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
	public Long zrevrank(String key, String member) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrevrank(key, member);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * 返回有序集 key 的基数。
	 *
	 * @param key
	 * @return result 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
	 */
	public Long zcard(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zcard(key);
		} finally {
			returnConnection(jedis);
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
	public Double zscore(String key, String member) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zscore(key, member);
		} finally {
			returnConnection(jedis);
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
	public Set<String> zrangeByScore(String key, double min, double max) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrangeByScore(key, min, max);
		} finally {
			returnConnection(jedis);
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
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count)
			throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrangeByScore(key, min, max, offset, count);
		} finally {
			returnConnection(jedis);
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
	public Long zcount(String key, double min, double max) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zcount(key, min, max);
		} finally {
			returnConnection(jedis);
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
	public Set<String> zrevrangeByScore(String key, double max, double min) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrevrangeByScore(key, max, min);
		} finally {
			returnConnection(jedis);
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
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count)
			throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zrevrangeByScore(key, max, min, offset, count);
		} finally {
			returnConnection(jedis);
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
	public Long zremrangeByRank(String key, int start, int end) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zremrangeByRank(key, start, end);
		} finally {
			returnConnection(jedis);
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
	public Long zremrangeByScore(String key, double start, double end) throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.zremrangeByScore(key, start, end);
		} finally {
			returnConnection(jedis);
		}
	}

	/********************* redis Sorted操作结束 **************************/

	/********************* redis Slowlog操作 **************************/
	/**
	 * @方法描述: 获取redis日志列表
	 * @param entries
	 * @return
	 */
	public List<OperateLog> getLogs(long entries) throws Exception {
		List<OperateLog> opList = null;
		OperateLog op = null;
		Jedis jedis = null;

		try {
			jedis = acquireConnection();
			List<Slowlog> logList = jedis.slowlogGet(entries);
			
			if (CollectionHelper.isNotEmpty(logList)) {
				opList = Lists.newLinkedList();
				String args = "";
				for (Slowlog sl : logList) {
					args = JSON.toJSONString(sl.getArgs());
					if (args.equals("[\"PING\"]") || args.equals("[\"SLOWLOG\",\"get\"]")
							|| args.equals("[\"DBSIZE\"]") || args.equals("[\"INFO\"]")) {
						continue;
					}
					op = new OperateLog();
					op.setId(sl.getId());
					op.setExecuteTime(
							DateUtilHelper.formatDateTime(new Date(sl.getTimeStamp() * 1000)));
					op.setUsedTime(sl.getExecutionTime() / 1000.0 + "ms");
					op.setArgs(args);
					opList.add(op);
				}
			}
		} finally {
			returnConnection(jedis);
		}
		return opList;
	}

	/**
	 * @方法描述: 获取日志条数
	 * @return
	 */
	public Long getLogsLen() throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.slowlogLen();
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 清空日志
	 */
	public void logEmpty() throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			jedis.slowlogReset();
		} finally {
			returnConnection(jedis);
		}
	}

	/********************* redis Slowlog操作结束 **************************/

	/********************* redis 服务器操作开始 **************************/
	/**
	 * @方法描述: 检查是否还再链接
	 * @return
	 */
	public boolean checkConnectionState() {
		return acquireConnection().isConnected();
	}

	/**
	 * @方法描述: 用于测试与服务器的连接是否仍然生效，或者用于测量延迟值
	 * @说明 Redis Ping 命令使用客户端向 Redis 服务器发送一个 PING ，如果服务器运作正常的话，会返回一个 PONG
	 * @return true:客户端和服务器连接正常 false: 客户端和服务器连接不正常(网络不正常或服务器未能正常运行)
	 */
	public boolean isPing() {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			String statusCode = jedis.ping();
			if ("pong".equalsIgnoreCase(statusCode)) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 返回关于 当前Redis 服务器的各种信息和统计数值
	 * @返回参数详情见：http://redisdoc.com/server/info.html
	 * @return
	 */
	public String getRedisInfo() throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.info();
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取redis 服务器信息
	 * @return
	 */
	public List<RedisInfoDetail> getRedisServerInfo() throws Exception {
		List<RedisInfoDetail> redisList = Lists.newArrayList();

		String[] strs = getRedisInfo().split("\n");
		RedisInfoDetail rif;

		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				rif = new RedisInfoDetail();
				String s = strs[i].trim();
				String[] str = s.split(":");
				if (str != null && str.length > 1) {
					rif.setKey(str[0]);
					rif.setValue(str[1]);
					redisList.add(rif);
				}
			}
		}
		return redisList;
	}

	/**
	 * @方法描述: 用于返回所有连接到服务器的客户端信息和统计数据
	 * @返回参数详情：http://redisdoc.com/server/client_list.html
	 * @return
	 */
	public List<ClientInfo> getClientList() {
		Jedis jedis = null;
		List<ClientInfo> clientList = Lists.newArrayList();
		Client client = null;
		try {
			jedis = new RedisClient().acquireConnection();
			String[] strs = jedis.clientList().split("\n");

			if (strs != null && strs.length > 0) {
				ClientInfo clientInfo = null;

				for (int i = 0; i < strs.length; i++) {
					String[] infoArr = strs[i].trim().split(" ");

					if (infoArr != null && infoArr.length > 0) {

						for (String info : infoArr) {
							clientInfo = new ClientInfo();
							String[] inFoArr2 = info.split("=");
							if (inFoArr2 != null && inFoArr2.length > 1) {
								clientInfo.setKey(inFoArr2[0]);
								clientInfo.setValue(inFoArr2[1]);
								clientList.add(clientInfo);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnClient(client);
			returnConnection(jedis);
		}
		return clientList;
	}

	/**
	 * @方法描述: Client Kill 命令用于关闭客户端连接。
	 * @param addr
	 * @return
	 */
	public boolean kill(String addr) {
		if (isEmpty(addr)) {
			return false;
		}
		Jedis jedis = null;
		Client client = null;
		try {
			jedis = acquireConnection();
			client = jedis.getClient();
			client.clientKill(addr);
			return true;
		} catch (Exception ex) {
			return false;
		} finally {
			returnClient(client);
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取占用内存大小:返回当前数据库的 key 的数量
	 * @return
	 */
	public Long dbSize() throws Exception {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.dbSize();
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取当前redis使用内存大小情况
	 * @return
	 */
	public Map<String, Object> getMemeryInfo() throws Exception {
		String[] strs = getRedisInfo().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory")) {
				map = new HashMap<String, Object>();
				map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", System.currentTimeMillis());
				break;
			}
		}
		return map;
	}

	/********************* redis 服务器操作结束 **************************/
	/***
	 * @方法描述: 从连接池中获取连接
	 * @return
	 */
	private Jedis acquireConnection() {
		// 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
		assert !lockJedis.isHeldByCurrentThread();
		lockJedis.lock();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
		} finally {
			lockJedis.unlock();
		}
		return jedis;
	}

	/**
	 * @方法描述: 把连接返回给连接池,释放资源
	 * @param jedis
	 */
	private void returnConnection(Jedis jedis) {
		if (jedis != null) {
			// jedis.resetState();
			jedis.quit();
			jedis.close();
		}
	}

	/**
	 * @方法描述: 是否为空
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

	/**
	 * @方法描述: 关闭连接
	 * @param client
	 */
	private void returnClient(Client client) {
		if (client != null) {
			client.disconnect();
			client.close();
		}
	}
}
