package com.cdeledu.core.redis;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.cdeledu.common.base.BaseClass;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis Manager Utils
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月10日 下午11:25:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
@SuppressWarnings("unused")
public class JedisManager extends BaseClass {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	private JedisPool jedisPool;
	private static ReentrantLock lockJedis = new ReentrantLock();

	/** ----------------------------------------------------- Fields end */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * @方法描述: 从连接池获取redis连接
	 * @return
	 */
	public Jedis getJedis() {
		// 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
		assert !lockJedis.isHeldByCurrentThread();
		lockJedis.lock();
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
		} catch (JedisConnectionException e) {
			String message = StringUtils.trim(e.getMessage());
			if ("Could not get a resource from the pool".equalsIgnoreCase(message)) {
				if (logger.isDebugEnabled()) {
					logger.debug("++++++++++请检查你的redis服务++++++++");
					logger.debug("|①.请检查是否安装redis服务。|");
					logger.debug("|②.请检查redis 服务是否启动。|");
					logger.debug("|③.请检查redis启动是否带配置文件启动，也就是是否有密码，是否端口有变化（默认6379）。|");
					logger.debug("项目退出中....生产环境中，请删除这些东西。我来自。JedisManage.java ");
					System.exit(0);// 停止项目
				}
			}
			throw new JedisConnectionException(e);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			lockJedis.unlock();
		}
		return jedis;
	}

	/**
	 * @方法描述: 把连接返回给连接池,释放资源
	 * @param jedis
	 */
	public void recycleJedis(Jedis jedis) {
		if (jedis == null)
			return;
		jedis.quit();
		jedis.close();
	}

	/**
	 * @方法描述: 关闭连接
	 * @param client
	 */
	public void returnClient(Client client) {
		if (client != null) {
			client.disconnect();
			client.close();
		}
	}
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
