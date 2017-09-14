package com.cdeledu.util.database.redis.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;

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
	protected Logger logger = Logger.getLogger(getClass());
	/** 访问密码:现在没有验证密码，所以设置为null */
	private static String auth, address, masterName;
	private static int max_active, max_idle, max_wait, timeout;
	private static boolean test_on_borrow, test_on_return;
	private static Map<String, String> proMap = null;
	/** ----------------------------------------------------- Fields end */

	static {

	}

	private static JedisSentinelPool jedisPool = null;
	/** 建立连接池配置参数 */
	private JedisPoolConfig poolConfig = null;

	public RedisConfig() {
		super();
		// 完成初始化工作
		initConfig();
	}

	/**
	 * @方法描述: 池基本配置
	 * @return
	 */
	public synchronized JedisSentinelPool redisPoolFactory() {
		if (jedisPool == null) {
			// 构造连接池

			Set<String> sentinels = new HashSet<String>();
			String[] addressArray = address.split(",");
			if (addressArray != null && addressArray.length > 0) {
				for (int i = 0; i < addressArray.length; i++) {
					sentinels.add(addressArray[i]);
				}
			}
			if (StringUtils.isNoneBlank(auth)) {
				jedisPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout, auth);
			} else {
				jedisPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout);
			}
		}
		logger.info("JedisPool注入成功！！");
		return jedisPool;
	}

	/**
	 * @方法描述: 初始化连接池参数
	 * @return
	 */
	@Before
	private void initConfig() {
		proMap = PropertyHelper.getMapByProperties("datasource/redis.properties");
		address = (String) proMap.get("redis.address");
		auth = (String) proMap.get("redis.auth");
		max_active = Integer.valueOf(proMap.get("redis.max-active"));
		max_idle = Integer.valueOf(proMap.get("redis.min-idle"));
		max_wait = Integer.valueOf(proMap.get("redis.max-wait"));
		timeout = Integer.valueOf(proMap.get("redis.timeout"));
		test_on_borrow = Boolean.valueOf(proMap.get("redis.borrow"));
		test_on_return = Boolean.valueOf(proMap.get("redis.return"));
		masterName = (String) proMap.get("redis.masterName");

		poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(max_active);
		poolConfig.setMaxIdle(max_idle);
		poolConfig.setMaxWaitMillis(max_wait);
		poolConfig.setTestOnBorrow(test_on_borrow);
		poolConfig.setTestOnReturn(test_on_return);
	}
}