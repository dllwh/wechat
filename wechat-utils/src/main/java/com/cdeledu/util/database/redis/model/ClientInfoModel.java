package com.cdeledu.util.database.redis.model;

public class ClientInfoModel {
	/** 连接的总持续时间以秒为单位 */
	private Long ageSeconds;
	/** 当前的数据库ID */
	private int database;
	/** 客户机的主机 */
	private String host;
	/** 端口 */
	private int Port;
	/** 连接的空闲时间(单位秒) */
	private int idleSeconds;
	/** 最后一个命令中 */
	private String lastCommand;
	/** 订阅数量 */
	private int patternSubscriptionCount;
	/** 原始内容 */
	private String raw;
	/** 订阅频道数量 */
	private int subscriptionCount;
	/** 执行事务的命令数量 */
	private int transactionCommandLength;

	public ClientInfoModel() {
	}

	public ClientInfoModel(Long ageSeconds, int database, String host, int port, int idleSeconds,
			String lastCommand, int patternSubscriptionCount, String raw, int subscriptionCount,
			int transactionCommandLength) {
		this.ageSeconds = ageSeconds;
		this.database = database;
		this.host = host;
		Port = port;
		this.idleSeconds = idleSeconds;
		this.lastCommand = lastCommand;
		this.patternSubscriptionCount = patternSubscriptionCount;
		this.raw = raw;
		this.subscriptionCount = subscriptionCount;
		this.transactionCommandLength = transactionCommandLength;
	}

	public Long getAgeSeconds() {
		return ageSeconds;
	}

	public void setAgeSeconds(Long ageSeconds) {
		this.ageSeconds = ageSeconds;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public int getIdleSeconds() {
		return idleSeconds;
	}

	public void setIdleSeconds(int idleSeconds) {
		this.idleSeconds = idleSeconds;
	}

	public String getLastCommand() {
		return lastCommand;
	}

	public void setLastCommand(String lastCommand) {
		this.lastCommand = lastCommand;
	}

	public int getPatternSubscriptionCount() {
		return patternSubscriptionCount;
	}

	public void setPatternSubscriptionCount(int patternSubscriptionCount) {
		this.patternSubscriptionCount = patternSubscriptionCount;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public int getSubscriptionCount() {
		return subscriptionCount;
	}

	public void setSubscriptionCount(int subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}

	public int getTransactionCommandLength() {
		return transactionCommandLength;
	}

	public void setTransactionCommandLength(int transactionCommandLength) {
		this.transactionCommandLength = transactionCommandLength;
	}

}
