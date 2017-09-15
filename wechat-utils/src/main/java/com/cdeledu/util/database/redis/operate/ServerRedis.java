package com.cdeledu.util.database.redis.operate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdeledu.util.database.redis.entity.RedisInfoDetail;
import com.google.common.collect.Lists;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月15日 上午9:18:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ServerRedis extends RedisOperate {

	/**
	 * @方法描述: 获取redis 服务器信息
	 * @return
	 */
	public static String getRedisInfo() {
		try {
			jedis = acquireConnection();
			Client client = jedis.getClient();
			client.info();
			return client.getBulkReply();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 获取redis 服务器信息
	 * @return
	 */
	public List<RedisInfoDetail> getRedisServerInfo() {
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
	public int dbSize() {
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			return jedis.dbSize().intValue();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 获取当前redis使用内存大小情况
	 * @return
	 */
	public Map<String, Object> getMemeryInfo() {
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
}
