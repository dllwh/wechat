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
import com.cdeledu.util.apache.collection.MapUtilHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.database.redis.config.RedisConfig;
import com.cdeledu.util.database.redis.entity.OperateLog;
import com.cdeledu.util.database.redis.entity.RedisInfoDetail;
import com.google.common.collect.Lists;

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
	private static Jedis jedis = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	/**
	 * 成功,"OK"
	 */
	private final static String SUCCESS_OK = "ok";
	/**
	 * 成功,1L
	 */
	private static final Long SUCCESS_STATUS_LONG = 1L;

	/** ----------------------------------------------------- Fields end */

	static {
		logger.info("start jredis connection pool");
		jedisPool = new RedisConfig().redisPoolFactory();
		logger.info("start jredis connection pool successfully");
	}

	private RedisClient() {
		super();
	}

	public static RedisClient getRedisClient() {
		if (redisClient == null) {
			synchronized (RedisClient.class) {
				if (redisClient == null)
					redisClient = new RedisClient();
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
		try {
			jedis = acquireConnection();
			// 获取数据并输出
			return jedis.keys("*");
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
	public Long setnx(String key, String value) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.setnx(key, value);
		} finally {
			returnConnection(jedis);
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
	public boolean setex(String key, int time, String value) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
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
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 通过Redis的key获取值，并释放连接资源
	 * @param key
	 *            键值
	 * @return 成功返回value，失败返回""
	 */
	public String get(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.get(key);
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
			return 0L;
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
	public Long del(String[] key) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
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
	public Long StrLength(String key) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.strlen(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 设置超期时间
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
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.expire(key, seconds);
			if (SUCCESS_STATUS_LONG == statusCode) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
	}

	/**
	 * @方法描述: 将key设置为永久
	 * @param key
	 * @return
	 */
	public boolean persist(String key) {
		if (isEmpty(key)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.persist(key);
			if (SUCCESS_STATUS_LONG == statusCode) {
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
		try {
			jedis = acquireConnection();
			String statusCode = jedis.rename(oldkey, newKey);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} finally {
			returnConnection(jedis);
		}
		return false;
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
		try {
			jedis = acquireConnection();
			Long statusCode = jedis.renamenx(oldkey, newKey);
			if (SUCCESS_STATUS_LONG == statusCode) {
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
		try {
			jedis = acquireConnection();
			return jedis.type(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/******************* redis Key-Value操作结束 ************************/

	/******************* redis Hash操作 ************************/
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
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.hset(key, field, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 一次存储多个Key和值
	 * @param key
	 * @param hash
	 */
	public boolean hmset(String key, Map<String, String> hash) throws Exception {
		if (MapUtils.isEmpty(hash) || MapUtilHelper.isEmpty(hash)) {
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
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取指定的hash field
	 * @param key
	 * @param field
	 */
	public String hget(String key, String field) throws Exception {
		if (isEmpty(key) || isEmpty(field)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.hget(key, field);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 所有的域和值
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
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
			return null;
		}
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
	 */
	public Long hdel(String key, String... fields) throws Exception {
		if (isEmpty(key) || isEmpty(fields)) {
			return 0L;
		}
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
			return 0L;
		}
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
	 */
	public Set<String> hkeys(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
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
	 */
	public List<String> hvals(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
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
	 * @方法描述: 将一个值插入到列表头部，value可以重复，返回列表的长度
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return 返回List的长度
	 */
	public Long lpush(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
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
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.lpushx(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 在列表中的尾部添加一个个值，返回列表的长度
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return
	 */
	public Long rpush(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.rpush(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 仅当列表存在时，才会向列表中的尾部添加一个值，返回列表的长度
	 * @param key
	 * @param value
	 *            可以是字符传，还是可以是字符数组
	 * @return
	 */
	public Long rpushx(String key, String... value) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.rpushx(key, value);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取列表长度，key为空时返回0
	 * @param key
	 * @return
	 */
	public Long llen(String key) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.llen(key);
		} finally {
			returnConnection(jedis);
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
	public Long lrem(String key, long count, String value) {
		if (isEmpty(key)) {
			return 0L;
		}
		try {
			jedis = acquireConnection();
			return jedis.lrem(key, count, value);
		} finally {
			returnConnection(jedis);
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
	public boolean lset(String key, long index, String value) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			String statusCode = jedis.lset(key, index, value);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
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
	public List<String> lrange(String key, long start, long end) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
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
	 * @return
	 */
	public boolean ltrim(String key, long start, long end) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
		try {
			jedis = acquireConnection();
			String statusCode = jedis.ltrim(key, start, end);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 通过索引获取列表中的元素
	 * @param key
	 * @param index
	 *            索引，0表示最新的一个元素
	 * @return
	 */
	public String lindex(String key, long index) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.lindex(key, index);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return
	 */
	public String lpop(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.lpop(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return
	 */
	public String rpop(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.rpop(key);
		} finally {
			returnConnection(jedis);
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
	public String rpoplpush(String srckey, String dstkey) throws Exception {
		if (isEmpty(srckey)) {
			return "";
		}
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
	 * @return
	 */
	public long sadd(String key, String... members) throws Exception {
		if (isEmpty(key) || isEmpty(members)) {
			return 0L;
		}
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
	 * @return
	 */
	public Set<String> smembers(String key) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
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
	 */
	public boolean sismember(String key, String member) throws Exception {
		if (isEmpty(key)) {
			return false;
		}
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
	 * @return
	 * @throws Exception
	 */
	public Long scard(String key) throws Exception {
		if (isEmpty(key)) {
			return 0L;
		}
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
	 * @return
	 * @throws Exception
	 */
	public String srandmember(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.srandmember(key);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 移除一个或多个membe
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long srem(String key, String... members) throws Exception {
		if (isEmpty(key) || isEmpty(members)) {
			return 0L;
		}
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
	 * @return
	 * @throws Exception
	 */
	public Set<String> sunion(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.sunion(keys);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 多个set的并集.key为空，则返null
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public Set<String> sinter(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.sinter(keys);
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 返回给定所有集合的差集（获取第一个key中与其它key不相同的值，当只有一个key时，就返回这个key的所有值）
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public Set<String> sdiff(String... keys) throws Exception {
		if (isEmpty(keys)) {
			return null;
		}
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
	 * @return
	 * @throws Exception
	 */
	public boolean smove(String srckey, String dstkey, String member) throws Exception {
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
			returnConnection(jedis);
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
	public String spop(String key) throws Exception {
		if (isEmpty(key)) {
			return "";
		}
		try {
			jedis = acquireConnection();
			return jedis.spop(key);

		} finally {
			returnConnection(jedis);
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
	public List<String> srandMember(String key, int count) throws Exception {
		if (isEmpty(key)) {
			return null;
		}
		try {
			jedis = acquireConnection();
			return jedis.srandmember(key, count);
		} finally {
			returnConnection(jedis);
		}
	}

	/********************* redis Set操作结束 **************************/

	/********************* redis Sorted操作 **************************/

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
		try {
			jedis = acquireConnection();
			List<Slowlog> logList = jedis.slowlogGet(entries);
			if (logList != null && logList.size() > 0) {
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
		try {
			jedis = acquireConnection();
			jedis.slowlogReset();
		} finally {
			returnConnection(jedis);
		}
	}

	/********************* redis Slowlog操作结束 **************************/

	/********************* redis 服务器操作结束 **************************/

	/**
	 * @方法描述: 获取redis 服务器信息
	 * @return
	 */
	public String getRedisInfo() throws Exception {
		try {
			jedis = acquireConnection();
			Client client = jedis.getClient();
			client.info();
			return client.getBulkReply();
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
		RedisInfoDetail rif = null;

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
	 * @方法描述: 获取占用内存大小
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
				map.put("create_time", new Date().getTime());
				break;
			}
		}
		return map;
	}

	/********************* redis 服务器操作结束 **************************/
	/**
	 * @方法描述: 检查是否还再链接
	 * @return
	 */
	public boolean checkConnectionState() {
		return acquireConnection().isConnected();
	}

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
			// jedis.setDataSource(jedisPool);
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
			jedis.close();
		}
	}

	/**
	 * @方法描述: 是否为空
	 * @param key
	 * @return
	 */
	private static boolean isEmpty(final CharSequence... key) {
		if (StringUtils.isNoneBlank(key))
			return false;
		return true;
	}
}
