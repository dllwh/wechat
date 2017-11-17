package com.cdeledu.service.monitor;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 监控服务
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月17日 上午11:21:07
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface RedisService {

	/** 获取redis服务器信息 */
	void getRedisServerInfo(Long redisServerId);

	/** 获取redis服务器列表 */
	void getRedisList();

	/** 获取redis节点信息 */
	void getRedis(Long id);

	/** 获取redis节点数量 */
	Integer countRedis();

	/** 获取Redis详细信息 */
	void getRedisDetail(Long id);

	/** 获取Redis实时监控数据 */
	void getRedisMonitoring();

	/** 获取redis历史数据 */
	void getRedisStatusHistoryChart(Long id, String timeRange);
}
