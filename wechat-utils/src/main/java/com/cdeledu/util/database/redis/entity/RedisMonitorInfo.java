package com.cdeledu.util.database.redis.entity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 监控系统
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月30日 下午2:08:15
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class RedisMonitorInfo {
	/** 版本号 */
	private String version;
	/** 进程号 */
	private String processId;
	/** 运行时间 */
	private String upTime;
	/** 当前连接的客户端数量 */
	private Integer clientCount;
	/** 允许使用的内存量，单位是B，需要转换为KB */
	private Long usedMemory;
	/** 历史上分配使用的最大的内存量，单位是B，需要转换为KB */
	private Long usedMemoryMax;
	/** 使用的CPU百分比-系统 */
	private Double usedCpuSys;
	/** 使用的CPU百分比 - 用户 */
	private Double usedCpuUser;
	/** 总使用CPU百分比 */
	private Double usedCpu;
	/** 执行命令总数量 */
	private Integer totalCommandCount;
	/** 当前时段的OPS */
	private String totalPercentSecondCurrent = "0";
	/** 命中key的总数量 */
	private Integer keyspaceHits;
	/** 未命中的key的总数量 */
	private Integer keyspaceMisses;
	/** 当前时段命中成功的百分比 */
	private String keyspaceHitRateCurrent = "0";
	/** 运行以来过期的 key 的数量 */
	private Integer expiredKeys;
	/** 运行以来删除的 key 的数量 */
	private Integer evictedKeys;
	/** 当前保存的key的数量 */
	private Integer dbKeyCount;
	/** 当前实例的角色，master或者slave */
	private String role;
	/** 本次保存的时间戳，用于计算QPS */
	private long timestamp = 0L;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public Integer getClientCount() {
		return clientCount;
	}

	public void setClientCount(Integer clientCount) {
		this.clientCount = clientCount;
	}

	public Long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(Long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public Long getUsedMemoryMax() {
		return usedMemoryMax;
	}

	public void setUsedMemoryMax(Long usedMemoryMax) {
		this.usedMemoryMax = usedMemoryMax;
	}

	public Double getUsedCpuSys() {
		return usedCpuSys;
	}

	public void setUsedCpuSys(Double usedCpuSys) {
		this.usedCpuSys = usedCpuSys;
	}

	public Double getUsedCpuUser() {
		return usedCpuUser;
	}

	public void setUsedCpuUser(Double usedCpuUser) {
		this.usedCpuUser = usedCpuUser;
	}

	public Double getUsedCpu() {
		return usedCpu;
	}

	public void setUsedCpu(Double usedCpu) {
		this.usedCpu = usedCpu;
	}

	public Integer getTotalCommandCount() {
		return totalCommandCount;
	}

	public void setTotalCommandCount(Integer totalCommandCount) {
		this.totalCommandCount = totalCommandCount;
	}

	public String getTotalPercentSecondCurrent() {
		return totalPercentSecondCurrent;
	}

	public void setTotalPercentSecondCurrent(String totalPercentSecondCurrent) {
		this.totalPercentSecondCurrent = totalPercentSecondCurrent;
	}

	public Integer getKeyspaceHits() {
		return keyspaceHits;
	}

	public void setKeyspaceHits(Integer keyspaceHits) {
		this.keyspaceHits = keyspaceHits;
	}

	public Integer getKeyspaceMisses() {
		return keyspaceMisses;
	}

	public void setKeyspaceMisses(Integer keyspaceMisses) {
		this.keyspaceMisses = keyspaceMisses;
	}

	public String getKeyspaceHitRateCurrent() {
		return keyspaceHitRateCurrent;
	}

	public void setKeyspaceHitRateCurrent(String keyspaceHitRateCurrent) {
		this.keyspaceHitRateCurrent = keyspaceHitRateCurrent;
	}

	public Integer getExpiredKeys() {
		return expiredKeys;
	}

	public void setExpiredKeys(Integer expiredKeys) {
		this.expiredKeys = expiredKeys;
	}

	public Integer getEvictedKeys() {
		return evictedKeys;
	}

	public void setEvictedKeys(Integer evictedKeys) {
		this.evictedKeys = evictedKeys;
	}

	public Integer getDbKeyCount() {
		return dbKeyCount;
	}

	public void setDbKeyCount(Integer dbKeyCount) {
		this.dbKeyCount = dbKeyCount;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "RedisMonitorInfo [version=" + version + ", processId=" + processId + ", upTime="
				+ upTime + ", clientCount=" + clientCount + ", usedMemory=" + usedMemory
				+ ", usedMemoryMax=" + usedMemoryMax + ", usedCpuSys=" + usedCpuSys
				+ ", usedCpuUser=" + usedCpuUser + ", usedCpu=" + usedCpu + ", totalCommandCount="
				+ totalCommandCount + ", totalPercentSecondCurrent=" + totalPercentSecondCurrent
				+ ", keyspaceHits=" + keyspaceHits + ", keyspaceMisses=" + keyspaceMisses
				+ ", keyspaceHitRateCurrent=" + keyspaceHitRateCurrent + ", expiredKeys="
				+ expiredKeys + ", evictedKeys=" + evictedKeys + ", dbKeyCount=" + dbKeyCount
				+ ", role=" + role + ", timestamp=" + timestamp + ", getVersion()=" + getVersion()
				+ ", getProcessId()=" + getProcessId() + ", getUpTime()=" + getUpTime()
				+ ", getClientCount()=" + getClientCount() + ", getUsedMemory()=" + getUsedMemory()
				+ ", getUsedMemoryMax()=" + getUsedMemoryMax() + ", getUsedCpuSys()="
				+ getUsedCpuSys() + ", getUsedCpuUser()=" + getUsedCpuUser() + ", getUsedCpu()="
				+ getUsedCpu() + ", getTotalCommandCount()=" + getTotalCommandCount()
				+ ", getTotalPercentSecondCurrent()=" + getTotalPercentSecondCurrent()
				+ ", getKeyspaceHits()=" + getKeyspaceHits() + ", getKeyspaceMisses()="
				+ getKeyspaceMisses() + ", getKeyspaceHitRateCurrent()="
				+ getKeyspaceHitRateCurrent() + ", getExpiredKeys()=" + getExpiredKeys()
				+ ", getEvictedKeys()=" + getEvictedKeys() + ", getDbKeyCount()=" + getDbKeyCount()
				+ ", getRole()=" + getRole() + ", getTimestamp()=" + getTimestamp()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
