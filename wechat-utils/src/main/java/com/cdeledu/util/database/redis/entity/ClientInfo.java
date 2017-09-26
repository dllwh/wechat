package com.cdeledu.util.database.redis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月27日 上午10:40:57
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.redis.net.cn/order/3657.html">获取连接到服务器的客户端连接列表</a>
 */
public class ClientInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	private static LinkedHashMap<String, String> map = new LinkedHashMap<>();
	/** ----------------------------------------------------- Fields end */
	static {
		map.put("addr", "客户端的地址和端口");
		map.put("fd", "套接字所使用的文件描述符");
		map.put("age", "以秒计算的已连接时长");
		map.put("idle", "以秒计算的空闲时长");
		map.put("flags", "客户端 flag");
		map.put("db", "该客户端正在使用的数据库 ID");
		map.put("sub", " 已订阅频道的数量");
		map.put("psub", "已订阅模式的数量");
		map.put("multi", "在事务中被执行的命令数量");
		map.put("qbuf", "查询缓冲区的长度（字节为单位， 0 表示没有分配查询缓冲区）");
		map.put("qbuf-free", "查询缓冲区剩余空间的长度（字节为单位， 0 表示没有剩余空间）");
		map.put("obl", "输出缓冲区的长度（字节为单位， 0 表示没有分配输出缓冲区）");
		map.put("oll", "输出列表包含的对象数量（当输出缓冲区没有剩余空间时，命令回复会以字符串对象的形式被入队到这个队列里）");
		map.put("omem", "输出缓冲区和输出列表占用的内存总量");
		map.put("events", "文件描述符事件");
		map.put("cmd", "events");
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
		String result = value;
		// 客户端 flag 可以由以下部分组成：
		if ("flags".equalsIgnoreCase(key)) {
			switch (value.toUpperCase()) {
			case "O":
				result = "客户端是 MONITOR 模式下的附属节点（slave）";
				break;
			case "S":
				result = "客户端是一般模式下（normal）的附属节点";
				break;
			case "M":
				result = "客户端是主节点（master）";
				break;
			case "X":
				result = "客户端正在执行事务";
				break;
			case "B":
				result = "客户端正在等待阻塞事件";
				break;
			case "I":
				result = "客户端正在等待 VM I/O 操作（已废弃）";
				break;
			case "D":
				result = "一个受监视（watched）的键已被修改， EXEC 命令将失败";
				break;
			case "C":
				result = "在将回复完整地写出之后，关闭链接";
				break;
			case "U":
				result = "客户端未被阻塞（unblocked）";
				break;
			case "A":
				result = "尽可能快地关闭连接";
				break;
			case "N":
				result = "未设置任何 flag";
				break;
			default:
				break;
			}
		}
		if ("events".equalsIgnoreCase(key)) {
			switch (value.toUpperCase()) {
			case "R":
				result = "客户端套接字（在事件 loop 中）是可读的（readable）";
				break;
			case "W":
				result = "客户端套接字（在事件 loop 中）是可写的（writeable）";
				break;
			default:
				break;
			}
		}
		this.value = result;
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
