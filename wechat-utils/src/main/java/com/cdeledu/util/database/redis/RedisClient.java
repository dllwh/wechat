package com.cdeledu.util.database.redis;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.cdeledu.util.database.redis.config.RedisConfig;
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
	protected static Logger logger = Logger.getLogger(RedisClient.class);
	protected static ReentrantLock lockPool = new ReentrantLock();
	protected static ReentrantLock lockJedis = new ReentrantLock();
	// private static JedisPool jedisPool = null;
	private static JedisSentinelPool jedisPool = null;
	private static RedisClient redisClient;

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
	public Jedis acquireConnection() {
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
	public void returnConnection(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
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

	/**
	 * @方法描述: 获取redis 服务器信息
	 * @return
	 */
	public List<RedisInfoDetail> getRedisInfo() {
		Jedis jedis = null;
		String info = "";
		List<RedisInfoDetail> redisList = Lists.newArrayList();
		try {
			jedis = acquireConnection();
			Client client = jedis.getClient();
			client.info();
			info = client.getBulkReply();
		} finally {
			returnConnection(jedis);
		}

		String[] strs = info.split("\n");
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
	 * @方法描述: 获取Redis 日志列表
	 */
	public List<Slowlog> getLogList() {
		Jedis jedis = null;
		List<Slowlog> logList = null;
		try {
			jedis = acquireConnection();
			logList = jedis.slowlogGet();
		} finally {
			returnConnection(jedis);
		}
		return logList;
	}

	/**
	 * @方法描述: 获取日志条数
	 * @return
	 */
	public Long getLogsLen() {
		long logLen = 0L;
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			logLen = jedis.slowlogLen();
		} finally {
			returnConnection(jedis);
		}
		return logLen;
	}

	/**
	 * @方法描述: 清空日志
	 */
	public void logEmpty() {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
		} finally {
			returnConnection(jedis);
		}
	}

	/**
	 * @方法描述: 获取占用内存大小
	 * @return
	 */
	public int dbSize() {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.dbSize().intValue();
		} finally {
			returnConnection(jedis);
		}
	}

	/** ----------------------------------------------- [测试方法] */

	public static void main(String[] args) {
		RedisClient redisClient = RedisClient.getRedisClient();
		// System.out.println(redisClient.set("dllwh", "duleilewuhen"));
		// System.out.println(redisClient.getkeys());
		// System.out.println(redisClient.getLogList());
		// System.out.println(redisClient.checkConnectionState());
		Jedis jedis = null;
		try {
//			String key = "java framework";
			String key = "dllwh";
			jedis = redisClient.acquireConnection();
			// jedis.del(key);
			// //先向key java framework中存放三条数据
			// jedis.lpush(key,"spring");
			// jedis.lpush(key,"struts");
			// jedis.lpush(key,"hibernate");
			jedis.setex(key, 1,"duleilewuhen");
			System.out.println(jedis.strlen(key));
			try {
				Thread.sleep(2*1000);
			} catch (Exception e) {
			}
			System.out.println(jedis.strlen(key));
		} finally {
			redisClient.returnConnection(jedis);
		}
	}
	/** ----------------------------------------------- [测试方法] */
}
