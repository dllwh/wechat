package com.cdeledu.util.database.redis.command;

import java.util.List;
import java.util.Map;

import com.cdeledu.util.database.redis.entity.ClientInfo;
import com.cdeledu.util.database.redis.entity.RedisServerInfo;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 服务器命令
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年5月16日 下午7:27:46
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="redisdoc.com/server/index.html">Server（服务器）</a>
 */
public interface RedisServerCommand {

	/** 返回关于 当前Redis 服务器的各种信息和统计数值 */
	List<RedisServerInfo> getRedisServerInfo() throws Exception;

	/** 根据分段获取redis info信息 */
	String getRedisInfoBySection(String section) throws Exception;

	/** 用于返回所有连接到服务器的客户端信息和统计数据 */
	List<ClientInfo> getClientList() throws Exception;

	/** 关闭客户端连接 */
	boolean kill(String addr) throws Exception;

	/** 获取占用内存大小:返回当前数据库的 key 的数量 */
	Long dbSize() throws Exception;

	/** 获取当前redis使用内存大小情况 */
	Map<String, Object> getMemeryInfo() throws Exception;

	/** 用于测试与服务器的连接是否仍然生效，或者用于测量延迟值 */
	boolean isPing() throws Exception;

	/** 如果ping通，返回true，如果ping不通，则每隔1秒尝试一次重连，如果3次重连失败，则认为连接失败 */
	boolean isConnRedisRetry() throws Exception;

}
