package com.cdeledu.service.monitor.impl;

import org.springframework.stereotype.Service;

import com.cdeledu.service.monitor.RedisService;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

	@Override
	public void getRedisServerInfo(Long redisServerId) {

	}

	@Override
	public void getRedisList() {
	}

	@Override
	public void getRedis(Long id) {
	}

	@Override
	public Integer countRedis() {
		return null;
	}

	@Override
	public void getRedisDetail(Long id) {

	}

	@Override
	public void getRedisMonitoring() {
	}

	@Override
	public void getRedisStatusHistoryChart(Long id, String timeRange) {
	}

}
