package com.cdeledu.util.database.redis.operate;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Lists;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.database.redis.entity.OperateLog;

import redis.clients.util.Slowlog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: slowlog是redis用于记录记录慢查询执行时间的日志系统
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月15日 上午8:55:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SlowlogRedis extends RedisOperate {
	/**
	 * @方法描述: 获取redis日志列表
	 * @param entries
	 * @return
	 */
	public static List<OperateLog> getLogs(long entries) {
		List<OperateLog> opList = null;
		OperateLog op = null;
		try {
			jedis = acquireConnection();
			List<Slowlog> logList = jedis.slowlogGet(entries);
			if (logList != null && logList.size() > 0) {
				opList = Lists.newLinkedList();
				String args = "";
				for (Slowlog sl : logList) {
					args = JSON.toJSONString(sl.getArgs());
					if (args.equals("[\"PING\"]") || args.equals("[\"SLOWLOG\",\"get\"]")
							|| args.equals("[\"DBSIZE\"]") || args.equals("[\"INFO\"]")) {
						continue;
					}
					op = new OperateLog();
					op.setId(sl.getId());
					op.setExecuteTime(
							DateUtilHelper.formatDateTime(new Date(sl.getTimeStamp() * 1000)));
					op.setUsedTime(sl.getExecutionTime() / 1000.0 + "ms");
					op.setArgs(args);
					opList.add(op);
				}
			}
		} finally {
			returnResource();
		}
		return opList;
	}

	/**
	 * @方法描述: 获取日志条数
	 * @return
	 */
	public Long getLogsLen() {
		try {
			jedis = acquireConnection();
			return jedis.slowlogLen();
		} finally {
			returnResource();
		}
	}

	/**
	 * @方法描述: 清空日志
	 */
	public static void logEmpty() {
		try {
			jedis = acquireConnection();
			jedis.slowlogReset();
		} finally {
			returnResource();
		}
	}
}
