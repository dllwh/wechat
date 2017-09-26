package com.cdeledu.util.database.redis.entity;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: readis
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月11日 下午4:44:28
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisInfoDetail {
	/** ----------------------------------------------------- Fields start */
	private static LinkedHashMap<String, String> map = new LinkedHashMap<>();
	/** ----------------------------------------------------- Fields end */

	static {
		/**
		 * Server:Redis服务器的信息
		 */
		map.put("redis_version", "Redis 服务器版本");
		map.put("redis_git_sha1", "Git SHA1");
		map.put("redis_git_dirty", "Git dirty flag");
		map.put("os", "Redis 服务器的宿主操作系统");
		map.put("arch_bits", " 架构（32 或 64 位）");
		map.put("multiplexing_api", "Redis 所使用的事件处理机制");
		map.put("gcc_version", "编译 Redis 时所使用的 GCC 版本");
		map.put("process_id", "服务器进程的 PID");
		map.put("run_id", "Redis 服务器的随机标识符（用于 Sentinel 和集群）");
		map.put("tcp_port", "TCP/IP 监听端口");
		map.put("uptime_in_seconds", "自 Redis 服务器启动以来，经过的秒数");
		map.put("uptime_in_days", "自 Redis 服务器启动以来，经过的天数");
		map.put("lru_clock", " 以分钟为单位进行自增的时钟，用于 LRU 管理");

		/**
		 * Clients:记录了已连接客户端的信息
		 */
		map.put("connected_clients", "已连接客户端的数量（不包括通过从属服务器连接的客户端）");
		map.put("client_longest_output_list", "当前连接的客户端当中，最长的输出列表");
		map.put("client_longest_input_buf", "当前连接的客户端当中，最大输入缓存");
		map.put("blocked_clients", "正在等待阻塞命令（BLPOP、BRPOP、BRPOPLPUSH）的客户端的数量");

		/**
		 * Memory:记录了服务器的内存信息
		 */
		map.put("used_memory", "由 Redis 分配器分配的内存总量，以字节（byte）为单位");
		map.put("used_memory_human", "以人类可读的格式返回 Redis 分配的内存总量");
		map.put("used_memory_rss", "从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和 top 、 ps 等命令的输出一致");
		map.put("used_memory_peak", " Redis 的内存消耗峰值(以字节为单位)");
		map.put("used_memory_peak_human", "以人类可读的格式返回 Redis 的内存消耗峰值");
		map.put("used_memory_lua", "Lua 引擎所使用的内存大小（以字节为单位）");
		map.put("mem_fragmentation_ratio", "sed_memory_rss 和 used_memory 之间的比率");
		map.put("mem_allocator", "在编译时指定的， Redis 所使用的内存分配器。可以是 libc 、 jemalloc 或者 tcmalloc");

		/**
		 * Stats
		 */
		map.put("total_connections_received", "运行以来连接过的客户端的总数量");
		map.put("total_commands_processed", "运行以来执行过的命令的总数量");
		map.put("expired_keys", "运行以来过期的 key 的数量");
		map.put("evicted_keys", "运行以来删除过的key的数量");
		map.put("instantaneous_ops_per_sec", "服务器每秒中执行的命令数量");
		map.put("rejected_connections", "因为最大客户端数量限制而被拒绝的连接请求数量");
		map.put("keyspace_hits", "查找数据库键成功的次数");
		map.put("keyspace_misses", "查找数据库键失败的次数");
		map.put("pubsub_channels", "目前被订阅的频道数量");
		map.put("pubsub_patterns", "目前被订阅的模式数量");

		/**
		 * Replication
		 */
		map.put("role", "当前实例的角色master还是slave");
		map.put("connected_slaves", "有多少个slave节点");

		/**
		 * CPU
		 */
		map.put("used_cpu_sys", "Redis服务器耗费的系统CPU");
		map.put("used_cpu_user", "Redis服务器耗费的用户CPU");
		map.put("used_cpu_sys_children", " 	Redis后台进程耗费的系统CPU");
		map.put("used_cpu_user_children", "Redis后台进程耗费的用户CPU");
	}

	private String key;
	private String value;
	private String desctiption;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
		this.desctiption = StringUtils.isNoneBlank(map.get(this.key)) ? map.get(this.key) : "";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesctiption() {
		return desctiption;
	}

	@Override
	public String toString() {
		return "RedisInfoDetail [key=" + key + ", value=" + value + ", desctiption=" + desctiption
				+ "]";
	}
}
