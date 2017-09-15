package com.cdeledu.util.database.redis;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.cdeledu.util.database.redis.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

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

	/** ----------------------------------------------- [测试方法] */
	public static void main(String[] args) {
	}
	/** ----------------------------------------------- [测试方法] */
}
