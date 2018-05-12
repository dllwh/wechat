package com.cdeledu.util.database.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis - 集群模式
 * 
 *       <pre>
 * 将众多的key-value集合存在多个节点上，当某一个节点出现障碍，不影响整个集群的功能
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年5月11日 上午9:34:33
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class RedisClusterFactory implements RedisBasicCommand<JedisCluster> {
	/** ----------------------------------------------------- Fields start */
	private static RedisClusterFactory redisClusterFactory;
	private Set<HostAndPort> clusterNodes;
	private JedisCluster jedisClusterClient;
	/** 建立连接池配置参数 */
	private JedisPoolConfig poolConfig;
	private RedisConfigFactory factory;

	/** ----------------------------------------------------- Fields end */
	private RedisClusterFactory(List<String> hostAndPortList) {
		factory = new RedisConfigFactory(hostAndPortList);
		poolConfig = factory.genJedisConfig();
		clusterNodes = factory.genClusterNode();
	}

	/**
	 * @方法描述 : 静态工厂方法
	 * @return
	 */
	public static RedisClusterFactory getInstance(List<String> hostAndPortList) {
		if (redisClusterFactory == null) {
			redisClusterFactory = new RedisClusterFactory(hostAndPortList);
		}
		return redisClusterFactory;
	}

	/**
	 * @方法描述 : 获取操作redis的客户端
	 * @return
	 */

	private JedisCluster getRedisClient() {
		if (jedisClusterClient == null) {
			synchronized (JedisCluster.class) {
				jedisClusterClient = new JedisCluster(clusterNodes, poolConfig);
			}
		}
		return jedisClusterClient;
	}

	@SuppressWarnings("deprecation")
	private void closeRedisClient() {
		if (jedisClusterClient != null) {
			try {
				jedisClusterClient.quit();
				jedisClusterClient.close();
			} catch (Exception quitExp) {
				quitExp.printStackTrace();
			}
		}
	}

	public Set<String> getAllKeys() {
		Set<String> keys = new TreeSet<>();
		Map<String, JedisPool> clusterNodes = getRedisClient().getClusterNodes();
		try {
			for (String k : clusterNodes.keySet()) {
				JedisPool jp = clusterNodes.get(k);
				Jedis connection = jp.getResource();
				keys.addAll(connection.keys("*"));
			}
		} catch (Exception e) {
		} finally {
			closeRedisClient();// 用完一定要close这个链接！！！
		}
		return keys;
	}

	public Set<String> getkeys(String pattern) {
		Set<String> keys = new TreeSet<>();
		Map<String, JedisPool> clusterNodes = getRedisClient().getClusterNodes();
		try {
			for (String k : clusterNodes.keySet()) {
				JedisPool jp = clusterNodes.get(k);
				Jedis connection = jp.getResource();
				keys.addAll(connection.keys(pattern));
			}
		} catch (Exception e) {
		} finally {
			closeRedisClient();// 用完一定要close这个链接！！！
		}
		return keys;
	}

	public Boolean exists(String key) {
		return null;
	}

	public Long persist(String key) {
		return null;
	}

	public String type(String key) {
		return null;
	}

	public Long expire(String key, int seconds) {
		return null;
	}

	public Long pexpire(String key, long milliseconds) {
		return null;
	}

	public Long ttl(String key) {
		return null;
	}

	public Long pttl(String key) {
		return null;
	}

	public String rename(String oldkey, String newkey) {
		return null;
	}

	public Long renamenx(String oldkey, String newkey) {
		return null;
	}

	public Long del(String... key) {
		return null;
	}

	public String set(String key, String value) {
		return null;
	}

	public Long setnx(String key, String value) {
		return null;
	}

	public String setex(String key, int seconds, String value) {
		return null;
	}

	public String psetex(String key, long milliseconds, String value) {
		return null;
	}

	public String get(String key) {
		return null;
	}

	public String getSex(String key, String value) {
		return null;
	}

	public Long strLength(String key) {
		return null;
	}

	public List<String> mget(String... keys) {
		return null;
	}

	public String getrange(String key, long startOffset, long endOffset) {
		return null;
	}

	public Long incr(String key) {
		return null;
	}

	public Long incrBy(String key, long integer) {
		return null;
	}

	public Double incrByFloat(String key, double value) {
		return null;
	}

	public Long decr(String key) {
		return null;
	}

	public Long decrBy(String key, long integer) {
		return null;
	}

	public Long append(String key, String value) {
		return null;
	}

	public Long hdel(String key, String... field) {
		return null;
	}

	public Long hlen(String key) {
		return null;
	}

	public Set<String> hkeys(String key) {
		return null;
	}

	public List<String> hvals(String key) {
		return null;
	}

	public String hget(String key, String field) {
		return null;
	}

	public String hmset(String key, Map<String, String> hash) {
		return null;
	}

	public List<String> hmget(String key, String... fields) {
		return null;
	}

	public Map<String, String> hgetAll(String key) {
		return null;
	}

	public Boolean hexists(String key, String field) {
		return null;
	}

	public Long hset(String key, String field, String value) {
		return null;
	}

	public List<String> blpop(int timeout, String key) {
		return null;
	}

	public List<String> brpop(int timeout, String key) {
		return null;
	}

	public String lindex(String key, long index) {
		return null;
	}

	public String lpop(String key) {
		return null;
	}

	public Long lpush(String key, String... string) {
		return null;
	}

	public Long lpushx(String key, String... value) {
		return null;
	}

	public Long llen(String key) {
		return null;
	}

	public List<String> lrange(String key, long start, long end) {
		return null;
	}

	public Long lrem(String key, long count, String value) {
		return null;
	}

	public String ltrim(String key, long start, long end) {
		return null;
	}

	public String lset(String key, long index, String value) {
		return null;
	}

	public String rpop(String key) {
		return null;
	}

	public Long rpush(String key, String... string) {
		return null;
	}

	public Long rpushx(String key, String... string) {
		return null;
	}

	public Long sadd(String key, String... member) {
		return null;
	}

	public Set<String> sdiff(String... keys) {
		return null;
	}

	public Boolean sismember(String key, String member) {
		return null;
	}

	public Set<String> smembers(String key) {
		return null;
	}

	public Set<String> sinter(String... keys) {
		return null;
	}

	public String spop(String key) {
		return null;
	}

	public Set<String> spop(String key, long count) {
		return null;
	}

	public String srandmember(String key) {
		return null;
	}

	public List<String> srandmember(String key, int count) {
		return null;
	}

	public Long srem(String key, String... member) {
		return null;
	}

	public Long scard(String key) {
		return null;
	}

	public Set<String> sunion(String... keys) {
		return null;
	}

	public Long zadd(String key, double score, String member) {
		return null;
	}

	public Long zcard(String key) {
		return null;
	}

	public Double zscore(String key, String member) {
		return null;
	}

	public Long zcount(String key, double min, double max) {
		return null;
	}

	public Long zcount(String key, String min, String max) {
		return null;
	}

	public Long zlexcount(String key, String min, String max) {
		return null;
	}

	public Set<String> zrange(String key, long start, long end) {
		return null;
	}

	public Set<String> zrangeByLex(String key, String min, String max) {
		return null;
	}

	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		return null;
	}

	public Set<String> zrangeByScore(String key, double min, double max) {
		return null;
	}

	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		return null;
	}

	public Long zrank(String key, String member) {
		return null;
	}

	public Long zrevrank(String key, String member) {
		return null;
	}

	public Long zrem(String key, String... member) {
		return null;
	}

	public Long zremrangeByRank(String key, long start, long end) {
		return null;
	}

	public Long zremrangeByLex(String key, String min, String max) {
		return null;
	}

	public Long zremrangeByScore(String key, double start, double end) {
		return null;
	}

	public Long zremrangeByScore(String key, String start, String end) {
		return null;
	}

	public Set<String> zrevrange(String key, long start, long end) {
		return null;
	}

	public Set<String> zrevrangeByScore(String key, double max, double min) {
		return null;
	}

	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		return null;
	}

	public static void main(String[] args) {
		/** 示例 */
		// List<String> hostAndPortList = Lists.newArrayList();
		// hostAndPortList.add("192.168.192.105:27001");
		// hostAndPortList.add("192.168.192.105:27002");
		// hostAndPortList.add("192.168.192.106:27001");
		// hostAndPortList.add("192.168.192.106:27002");
		// hostAndPortList.add("192.168.192.107:27001");
		// hostAndPortList.add("192.168.192.108:27002");
		// RedisClusterFactory.getInstance(hostAndPortList);
	}
}