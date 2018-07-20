package com.cdeledu.util.database.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.database.redis.entity.ClientInfo;
import com.cdeledu.util.database.redis.entity.RedisServerInfo;
import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;

public class AbstractRedisService {
	/**
	 * @方法:是否为空
	 * @创建人:独泪了无痕
	 * @param key
	 * @return
	 */
	public static boolean isEmpty(final CharSequence... key) {
		if (StringUtils.isNoneBlank(key)) {
			return false;
		} else {
			return true;
		}
	}

	public List<RedisServerInfo> getRedisServerInfo(Jedis jedisClient) {
		List<RedisServerInfo> redisList = Lists.newArrayList();
		RedisServerInfo rif;
		String[] redisServerStrs = jedisClient.info().split("\n");

		if (redisServerStrs != null && redisServerStrs.length > 0) {
			for (String redisServer : redisServerStrs) {
				rif = new RedisServerInfo();
				String[] str = redisServer.split(":");
				if (str != null && str.length > 1) {
					rif.setKey(str[0]);
					rif.setValue(str[1]);
					redisList.add(rif);
				}
			}
		}
		return redisList;
	}

	public String getRedisInfoBySection(Jedis jedisClient, String section) {
		return "";
	}

	public List<ClientInfo> getClientList(Jedis jedisClient) {
		List<ClientInfo> clientList = Lists.newArrayList();
		String[] clientStrs = jedisClient.clientList().split("\n");
		if (clientStrs != null && clientStrs.length > 0) {
			for (String client : clientStrs) {
				ClientInfo clientInfo;
				String[] infoArr = client.trim().split(" ");
				if (infoArr != null && infoArr.length > 0) {
					for (String info : infoArr) {
						clientInfo = new ClientInfo();
						String[] inFoArr2 = info.split("=");
						if (inFoArr2 != null && inFoArr2.length > 1) {
							clientInfo.setKey(inFoArr2[0]);
							clientInfo.setValue(inFoArr2[1]);
							clientList.add(clientInfo);
						}
					}
				}
			}
		}
		return clientList;
	}

	public boolean kill(Jedis jedisClient, String addr) {
		jedisClient.clientKill(addr);
		return false;
	}

	public Long dbSize(Jedis jedisClient) {
		return jedisClient.dbSize();
	}

	public Map<String, Object> getMemeryInfo(Jedis jedisClient) {
		String[] strs = jedisClient.info().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory")) {
				map = new HashMap<String, Object>();
				map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", System.currentTimeMillis());
				break;
			}
		}
		return map;
	}

	public boolean isPing(Jedis jedisClient) {
		if ("pong".equalsIgnoreCase(jedisClient.ping())) {
			return true;
		}
		return false;
	}

	public boolean isConnRedisRetry(Jedis jedisClient) {
		return false;
	}
}
