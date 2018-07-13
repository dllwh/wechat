package com.cdeledu.util.database.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.database.redis.command.RedisBasicCommand;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 单机版实现类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年6月9日 下午8:08:28
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisClientPoolHelper implements RedisBasicCommand {
	/** ----------------------------------------------------- Fields start */
	private String host;
	private int port;
	private String auth;

	/** ----------------------------------------------------- Fields end */

	public RedisClientPoolHelper(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public RedisClientPoolHelper(String host, int port, String auth) {
		this.host = host;
		this.port = port;
		this.auth = auth;
	}

	private synchronized Jedis getRedisClient() throws JedisDataException {
		Jedis jedis = new Jedis(host, port);
		if (!isEmpty(auth)) {
			jedis.auth(auth);
		}
		return jedis;
	}

	private void closeRedisClient(Jedis jedisClient) {
		if (jedisClient != null) {
			jedisClient.close();
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

	@Deprecated
	public Set<String> getAllKeys() {
		return null;
	}

	@Deprecated
	public Set<String> getkeys(String pattern) {
		return null;
	}

	public boolean exists(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.exists(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean persist(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return 1 == jedisClient.persist(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String type(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.type(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean expire(String key, int seconds) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return 1 == jedisClient.expire(key, seconds);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean pexpireat(String key, long milliseconds) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return 1 == jedisClient.expireAt(key, milliseconds);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long ttl(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
		return null;
	}

	public Long pttl(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.pttl(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean rename(String oldkey, String newkey) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.rename(oldkey, newkey));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean renamenx(String oldkey, String newkey) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return 1 == jedisClient.renamenx(oldkey, newkey);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean del(String... key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.del(key) >= 0;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean set(String key, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.set(key, value));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean setnx(String key, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return 1 == jedisClient.setnx(key, value);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean setex(String key, int seconds, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.setex(key, seconds, value));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean psetex(String key, long milliseconds, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.psetex(key, milliseconds, value));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String get(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.get(key);
			return isEmpty(result) || "nil".equalsIgnoreCase(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String getSex(String key, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.getSet(key, value);
			return isEmpty(result) || "nil".equalsIgnoreCase(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long strLength(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.strlen(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> mget(String... keys) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.mget(keys);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String getrange(String key, long startOffset, long endOffset) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.getrange(key, startOffset, endOffset);
			return isEmpty(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long incr(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.incr(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long incrBy(String key, long integer) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.incrBy(key, integer);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Double incrByFloat(String key, double value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.incrByFloat(key, value);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long decr(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.decr(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long decrBy(String key, long integer) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.decrBy(key, integer);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long append(String key, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.append(key, value);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long hdel(String key, String... field) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hdel(key, field);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long hlen(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hlen(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> hkeys(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hkeys(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> hvals(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hvals(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String hget(String key, String field) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hget(key, field);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean hmset(String key, Map<String, String> hash) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.hmset(key, hash));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> hmget(String key, String... fields) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hmget(key, fields);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Map<String, String> hgetAll(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hgetAll(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean hexists(String key, String field) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.hexists(key, field);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean hset(String key, String field, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return 1 == jedisClient.hset(key, field, value);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> blpop(int timeout, String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.blpop(timeout, key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> brpop(int timeout, String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.blpop(timeout, key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String lindex(String key, long index) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.lindex(key, index);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String lpop(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.lpop(key);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long lpush(String key, String... string) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.lpush(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long lpushx(String key, String... value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.lpushx(key, value);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long llen(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.llen(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> lrange(String key, long start, long end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.lrange(key, start, end);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long lrem(String key, long count, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.lrem(key, count, value);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean ltrim(String key, long start, long end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.ltrim(key, start, end));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean lset(String key, long index, String value) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return "ok".equalsIgnoreCase(jedisClient.lset(key, index, value));
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String rpop(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.rpop(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long rpush(String key, String... string) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.rpush(key, string);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long rpushx(String key, String... string) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.rpushx(key, string);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long sadd(String key, String... member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.sadd(key, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> sdiff(String... keys) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.sdiff(keys);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean sismember(String key, String member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.sismember(key, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> smembers(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.smembers(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> sinter(String... keys) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.sinter(keys);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String spop(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.spop(key);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> spop(String key, long count) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.spop(key, count);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public String srandmember(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			String result = jedisClient.srandmember(key);
			return "nil".equalsIgnoreCase(result) ? "" : result;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return "";
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public List<String> srandmember(String key, int count) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.srandmember(key, count);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public boolean srem(String key, String... member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.srem(key, member) >= 0;
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return false;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long scard(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.scard(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> sunion(String... keys) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.sunion(keys);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zadd(String key, double score, String member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zadd(key, score, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zcard(String key) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zcard(key);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Double zscore(String key, String member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zscore(key, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zcount(String key, double min, double max) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zcount(key, min, max);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zcount(String key, String min, String max) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zcount(key, min, max);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zlexcount(String key, String min, String max) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zlexcount(key, min, max);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrange(String key, long start, long end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrange(key, start, end);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrangeByLex(String key, String min, String max) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrangeByLex(key, min, max);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrangeByLex(key, min, max, offset, count);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrangeByScore(String key, double min, double max) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrangeByScore(key, min, max);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrangeByScore(key, min, max, offset, count);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zrank(String key, String member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrank(key, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zrevrank(String key, String member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrevrank(key, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zrem(String key, String... member) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrem(key, member);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zremrangeByRank(String key, long start, long end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zremrangeByRank(key, start, end);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zremrangeByLex(String key, String min, String max) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zremrangeByLex(key, min, max);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zremrangeByScore(String key, double start, double end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zremrangeByScore(key, start, end);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Long zremrangeByScore(String key, String start, String end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zremrangeByScore(key, start, end);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrevrange(String key, long start, long end) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrevrange(key, start, end);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrevrangeByScore(String key, double max, double min) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrevrangeByScore(key, max, min);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}

	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		Jedis jedisClient = null;
		try {
			jedisClient = getRedisClient();
			return jedisClient.zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception getExp) {
			getExp.printStackTrace();
			return null;
		} finally {
			closeRedisClient(jedisClient);
		}
	}
}
