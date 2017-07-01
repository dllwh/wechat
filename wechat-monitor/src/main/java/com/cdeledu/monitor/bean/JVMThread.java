package com.cdeledu.monitor.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @类描述: 获取JVM线程相关信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:40:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMThread implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 查找因为等待获得对象监视器或可拥有同步器而处于死锁状态的线程循环 */
	private long[] findDeadlockedThreads;
	/** 找到处于死锁状态（等待获取对象监视器）的线程的周期。 */
	private long[] findMonitorDeadlockedThreads;
	/** 返回活动线程 ID。 */
	private long[] allThreadIds;
	/** 返回当前线程的总 CPU 时间（以毫微秒为单位）。 */
	private long currentThreadCpuTime;
	/** 返回当前线程在用户模式中执行的 CPU 时间（以毫微秒为单位）。 */
	private long currentThreadUserTime;
	/** 返回活动守护线程的当前数目。 */
	private int daemonThreadCount;
	/** 返回自从 Java 虚拟机启动或峰值重置以来峰值活动线程计数。 */
	private int peakThreadCount;
	/** 返回活动线程的当前数目，包括守护线程和非守护线程。 */
	private int threadCount;
	/** 返回自从 Java 虚拟机启动以来创建和启动的线程总数目。 */
	private long totalStartedThreadCount;
	/** 测试 Java 虚拟机是否支持当前线程的 CPU时间测量。 */
	private boolean isCurrentThreadCpuTimeSupported;
	/** 测试 Java 虚拟机是否支持使用对象监视器的监视。 */
	private boolean isObjectMonitorUsageSupported;
	/** 测试 Java 虚拟机是否支持使用可拥有同步器的监视。 */
	private boolean isSynchronizerUsageSupported;
	/** 测试是否启用了线程争用监视。 */
	private boolean isThreadContentionMonitoringEnabled;
	/** 测试 Java虚拟机是否支持线程争用监视。 */
	private boolean isThreadContentionMonitoringSupported;
	/** 测试是否启用了线程 CPU 时间测量。 */
	private boolean isThreadCpuTimeEnabled;
	/** 测试 Java 虚拟机实现是否支持任何线程的 CPU时间测量。 */
	private boolean isThreadCpuTimeSupported;

	private Map<String, JVMThreadInfo> threadInfoMap;

	public long[] getFindDeadlockedThreads() {
		return findDeadlockedThreads;
	}

	public void setFindDeadlockedThreads(long[] findDeadlockedThreads) {
		this.findDeadlockedThreads = findDeadlockedThreads;
	}

	public long[] getFindMonitorDeadlockedThreads() {
		return findMonitorDeadlockedThreads;
	}

	public void setFindMonitorDeadlockedThreads(long[] findMonitorDeadlockedThreads) {
		this.findMonitorDeadlockedThreads = findMonitorDeadlockedThreads;
	}

	public long[] getAllThreadIds() {
		return allThreadIds;
	}

	public void setAllThreadIds(long[] allThreadIds) {
		this.allThreadIds = allThreadIds;
	}

	public long getCurrentThreadCpuTime() {
		return currentThreadCpuTime;
	}

	public void setCurrentThreadCpuTime(long currentThreadCpuTime) {
		this.currentThreadCpuTime = currentThreadCpuTime;
	}

	public long getCurrentThreadUserTime() {
		return currentThreadUserTime;
	}

	public void setCurrentThreadUserTime(long currentThreadUserTime) {
		this.currentThreadUserTime = currentThreadUserTime;
	}

	public int getDaemonThreadCount() {
		return daemonThreadCount;
	}

	public void setDaemonThreadCount(int daemonThreadCount) {
		this.daemonThreadCount = daemonThreadCount;
	}

	public int getPeakThreadCount() {
		return peakThreadCount;
	}

	public void setPeakThreadCount(int peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public long getTotalStartedThreadCount() {
		return totalStartedThreadCount;
	}

	public void setTotalStartedThreadCount(long totalStartedThreadCount) {
		this.totalStartedThreadCount = totalStartedThreadCount;
	}

	public boolean getIsCurrentThreadCpuTimeSupported() {
		return isCurrentThreadCpuTimeSupported;
	}

	public void setIsCurrentThreadCpuTimeSupported(boolean isCurrentThreadCpuTimeSupported) {
		this.isCurrentThreadCpuTimeSupported = isCurrentThreadCpuTimeSupported;
	}

	public boolean getIsObjectMonitorUsageSupported() {
		return isObjectMonitorUsageSupported;
	}

	public void setIsObjectMonitorUsageSupported(boolean isObjectMonitorUsageSupported) {
		this.isObjectMonitorUsageSupported = isObjectMonitorUsageSupported;
	}

	public boolean getIsSynchronizerUsageSupported() {
		return isSynchronizerUsageSupported;
	}

	public void setIsSynchronizerUsageSupported(boolean isSynchronizerUsageSupported) {
		this.isSynchronizerUsageSupported = isSynchronizerUsageSupported;
	}

	public boolean getIsThreadContentionMonitoringEnabled() {
		return isThreadContentionMonitoringEnabled;
	}

	public void setIsThreadContentionMonitoringEnabled(
			boolean isThreadContentionMonitoringEnabled) {
		this.isThreadContentionMonitoringEnabled = isThreadContentionMonitoringEnabled;
	}

	public boolean getIsThreadContentionMonitoringSupported() {
		return isThreadContentionMonitoringSupported;
	}

	public void setIsThreadContentionMonitoringSupported(
			boolean isThreadContentionMonitoringSupported) {
		this.isThreadContentionMonitoringSupported = isThreadContentionMonitoringSupported;
	}

	public boolean getIsThreadCpuTimeEnabled() {
		return isThreadCpuTimeEnabled;
	}

	public void setIsThreadCpuTimeEnabled(boolean isThreadCpuTimeEnabled) {
		this.isThreadCpuTimeEnabled = isThreadCpuTimeEnabled;
	}

	public boolean getIsThreadCpuTimeSupported() {
		return isThreadCpuTimeSupported;
	}

	public void setIsThreadCpuTimeSupported(boolean isThreadCpuTimeSupported) {
		this.isThreadCpuTimeSupported = isThreadCpuTimeSupported;
	}

	public Map<String, JVMThreadInfo> getThreadInfoMap() {
		return threadInfoMap;
	}

	public void setThreadInfoMap(Map<String, JVMThreadInfo> threadInfoMap) {
		this.threadInfoMap = threadInfoMap;
	}

	// 根据id获取ThreadInfo
	public JVMThreadInfo getThreadInfo(long id) {
		JVMThreadInfo ifcThreadInfo = threadInfoMap.get(id + "");
		return ifcThreadInfo;
	}

	// 获取所有的IFCJVMThreadInfo信息
	@JSONField(serialize = false)
	public JVMThreadInfo[] getThreadInfos() {
		Set<String> keys = threadInfoMap.keySet();
		JVMThreadInfo[] ifcjvmThreadInfos = null;
		if (keys != null) {
			ifcjvmThreadInfos = new JVMThreadInfo[keys.size()];
			int count = 0;
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				ifcjvmThreadInfos[count] = threadInfoMap.get(key);
				count++;
			}
		}
		return ifcjvmThreadInfos;
	}

	@Override
	public String toString() {
		return "IFCJVMThread [findDeadlockedThreads=" + Arrays.toString(findDeadlockedThreads)
				+ ", findMonitorDeadlockedThreads=" + Arrays.toString(findMonitorDeadlockedThreads)
				+ ", allThreadIds=" + Arrays.toString(allThreadIds) + ", currentThreadCpuTime="
				+ currentThreadCpuTime + ", currentThreadUserTime=" + currentThreadUserTime
				+ ", daemonThreadCount=" + daemonThreadCount + ", peakThreadCount="
				+ peakThreadCount + ", threadCount=" + threadCount + ", totalStartedThreadCount="
				+ totalStartedThreadCount + ", isCurrentThreadCpuTimeSupported="
				+ isCurrentThreadCpuTimeSupported + ", isObjectMonitorUsageSupported="
				+ isObjectMonitorUsageSupported + ", isSynchronizerUsageSupported="
				+ isSynchronizerUsageSupported + ", isThreadContentionMonitoringEnabled="
				+ isThreadContentionMonitoringEnabled + ", isThreadContentionMonitoringSupported="
				+ isThreadContentionMonitoringSupported + ", isThreadCpuTimeEnabled="
				+ isThreadCpuTimeEnabled + ", isThreadCpuTimeSupported=" + isThreadCpuTimeSupported
				+ ", threadInfoMap=" + threadInfoMap + "]";
	}

}
