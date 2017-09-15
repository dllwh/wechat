package com.cdeledu.util.database.redis.entity;

import java.io.Serializable;

public class OperateLog implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** slowlog唯一编号id */
	private long id;
	/** 查询的时间戳 */
	private String executeTime;
	/** 查询的耗时（微妙），如表示本条命令查询耗时47微秒 */
	private String usedTime;
	/** 查询命令，完整命令为 SLOWLOG GET，slowlog最多保存前面的31个key和128字符 */
	private String args;

	/** ----------------------------------------------------- Fields end */
	public OperateLog() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}
}
