package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 线程信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午6:38:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMThreadInfo implements Serializable {

	private static final long serialVersionUID = 1941794912309480619L;

	/** 返回与此 ThreadInfo 关联的线程被阻塞进入或重进入监视器的总次数。 */
	private long blockedCount;
	/** 返回自从启用线程争用监视以来，与此 ThreadInfo 关联的线程被阻塞进入或重进入监视器的近似累计时间（以毫秒为单位）。 */
	private long blockedTime;
	/** 返回对象的字符串表示形式，与此 ThreadInfo 关联的线程被锁定并等待该对象。 */
	private String lockName;
	/** 返回拥有对象的线程的 ID，与此 ThreadInfo 关联的线程被阻塞并等待该对象。 */
	private long lockOwnerId;
	/** 返回拥有对象的线程的名称，与此 ThreadInfo 关联的线程被阻塞并等待该对象。 */
	private String lockOwnerName;
	/** 返回与此 ThreadInfo 关联的线程的 ID。 */
	private long threadId;
	/** 返回与此 ThreadInfo 关联的线程的名称。 */
	private String threadName;
	/** 返回与此 ThreadInfo 关联的线程的状态。 */
	private String threadState;
	/** 返回与此 ThreadInfo 关联的线程等待通知的总次数。 */
	private long waitedCount;
	/** 返回自从启用线程争用监视以来,与此 ThreadInfo 关联的线程等待通知的近似累计时间（以毫秒为单位）。 */
	private long waitedTime;
	/** 测试与此 ThreadInfo 关联的线程是否通过 Java 本机接口 (JNI) 执行本机代码。 */
	private boolean isInNative;
	/** 测试与此 ThreadInfo 关联的线程是否被挂起。 */
	private boolean isSuspended;
	/** 该线程的总 CPU 时间（以毫微秒为单位）。 */
	private long threadCpuTime;
	/** 该线程在用户模式中执行的 CPU 时间（以毫微秒为单位） */
	private long threadUserTime;

	public long getBlockedCount() {
		return blockedCount;
	}

	public void setBlockedCount(long blockedCount) {
		this.blockedCount = blockedCount;
	}

	public long getBlockedTime() {
		return blockedTime;
	}

	public void setBlockedTime(long blockedTime) {
		this.blockedTime = blockedTime;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public long getLockOwnerId() {
		return lockOwnerId;
	}

	public void setLockOwnerId(long lockOwnerId) {
		this.lockOwnerId = lockOwnerId;
	}

	public String getLockOwnerName() {
		return lockOwnerName;
	}

	public void setLockOwnerName(String lockOwnerName) {
		this.lockOwnerName = lockOwnerName;
	}

	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadState() {
		return threadState;
	}

	public void setThreadState(String threadState) {
		this.threadState = threadState;
	}

	public void setThreadState(Thread.State threadState) {
		if (threadState != null) {
			this.threadState = threadState.toString();
		} else {
			this.threadState = "";
		}
	}

	public long getWaitedCount() {
		return waitedCount;
	}

	public void setWaitedCount(long waitedCount) {
		this.waitedCount = waitedCount;
	}

	public long getWaitedTime() {
		return waitedTime;
	}

	public void setWaitedTime(long waitedTime) {
		this.waitedTime = waitedTime;
	}

	public boolean isInNative() {
		return isInNative;
	}

	public void setInNative(boolean isInNative) {
		this.isInNative = isInNative;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public long getThreadCpuTime() {
		return threadCpuTime;
	}

	public void setThreadCpuTime(long threadCpuTime) {
		this.threadCpuTime = threadCpuTime;
	}

	public long getThreadUserTime() {
		return threadUserTime;
	}

	public void setThreadUserTime(long threadUserTime) {
		this.threadUserTime = threadUserTime;
	}

	@Override
	public String toString() {
		return "IFCJVMThreadInfo [blockedCount=" + blockedCount + ", blockedTime=" + blockedTime
				+ ", lockName=" + lockName + ", lockOwnerId=" + lockOwnerId + ", lockOwnerName="
				+ lockOwnerName + ", threadId=" + threadId + ", threadName=" + threadName
				+ ", threadState=" + threadState + ", waitedCount=" + waitedCount + ", waitedTime="
				+ waitedTime + ", isInNative=" + isInNative + ", isSuspended=" + isSuspended
				+ ", threadCpuTime=" + threadCpuTime + ", threadUserTime=" + threadUserTime + "]";
	}

}
