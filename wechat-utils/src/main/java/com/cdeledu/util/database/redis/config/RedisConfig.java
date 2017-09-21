package com.cdeledu.util.database.redis.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cdeledu.common.property.PropertyHelper;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: redis 集群配置参数
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月12日 下午2:17:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisConfig {
	/** ----------------------------------------------------- Fields start */
	private static ReentrantLock lockPool = new ReentrantLock();
	protected static Logger logger = Logger.getLogger(RedisConfig.class);
	private static String auth, address, masterName;
	private static int max_active, max_idle, max_wait, timeout, database = 0;
	private static boolean test_on_borrow, test_on_return, test_whileIdle;
	private static Map<String, String> proMap = null;

	private static JedisSentinelPool jedisPool = null;
	/** 建立连接池配置参数 */
	private JedisPoolConfig poolConfig = null;

	/** ----------------------------------------------------- Fields end */

	static {
		try {
			proMap = PropertyHelper.getMapByProperties("datasource/redis.properties");
			address = (String) proMap.get("redis.address");
			auth = (String) proMap.get("redis.auth");
			try {
				database = Integer.valueOf(proMap.get("redis.database"));
			} catch (Exception e) {
			}
			max_active = Integer.valueOf(proMap.get("redis.max-active"));
			max_idle = Integer.valueOf(proMap.get("redis.min-idle"));
			max_wait = Integer.valueOf(proMap.get("redis.max-wait"));
			timeout = Integer.valueOf(proMap.get("redis.timeout"));
			test_on_borrow = Boolean.valueOf(proMap.get("redis.borrow"));
			test_on_return = Boolean.valueOf(proMap.get("redis.return"));
			test_whileIdle = Boolean.valueOf(proMap.get("redis.whileIdle"));
			masterName = (String) proMap.get("redis.masterName");
			logger.info("初始化Redis配置参数成功.");
		} catch (Exception ex) {
			logger.error("初始化Redis配置参数失败.", ex);
		}

	}

	public RedisConfig() {
		super();
		// 完成初始化工作
		initConfig();
		setShutdownWork();
	}

	/**
	 * @方法描述: 池基本配置
	 * @return
	 */
	public synchronized JedisSentinelPool redisPoolFactory() {
		if (jedisPool == null) {
			initialPool();
		}
		return jedisPool;
	}

	/**
	 * @方法描述: 初始化连接池参数
	 * @return
	 */
	private void initConfig() {
		poolConfig = new JedisPoolConfig();
		// 设置最大连接数
		poolConfig.setMaxTotal(max_active);
		// 最大空闲连接数
		poolConfig.setMaxIdle(max_idle);
		// 获取Jedis连接的最大等待时间
		poolConfig.setMaxWaitMillis(max_wait);
		// 在获取Jedis连接时，自动检验连接是否可用
		poolConfig.setTestOnBorrow(test_on_borrow);
		// 在将连接放回池中前，自动检验连接是否有效
		poolConfig.setTestOnReturn(test_on_return);
		// 自动测试池中的空闲连接是否都是可用连接
		poolConfig.setTestWhileIdle(test_whileIdle);
	}

	/**
	 * @方法描述: 初始化Redis连接池
	 */
	private synchronized void initialPool() {
		lockPool.lock();
		try {
			if (jedisPool == null) {
				Set<String> sentinels = new HashSet<String>();
				String[] addressArray = address.split(",");
				if (addressArray != null && addressArray.length > 0) {
					for (int i = 0; i < addressArray.length; i++) {
						sentinels.add(addressArray[i]);
					}
				}
				if (StringUtils.isNoneBlank(auth)) {
					jedisPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout,
							auth, database);
				} else {
					jedisPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout,
							null, database);
				}
			}
			logger.info("JedisPool注入成功！！");
		} catch (Exception ex) {
			logger.error("创建Redis Pool失败.", ex);
		} finally {
			lockPool.unlock();
		}
	}

	/**
	 * @方法描述: 设置系统停止时需执行的任务
	 */
	private static void setShutdownWork() {
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						if (jedisPool != null) {
							jedisPool.destroy();
							jedisPool = null;
							logger.info("关闭Redis Pool成功.");
						}
					} catch (Exception ex) {
						logger.error("关闭Redis Pool失败.", ex);
					}
				}
			});
			logger.info("设置系统停止时关闭Redis Pool的任务成功.");
		} catch (Exception e) {
			logger.error("设置系统停止时关闭Redis Pool的任务失败.");
		}

	}
}
