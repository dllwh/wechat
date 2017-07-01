package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取JVM内存信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:36:28
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMMemory implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回用于对象分配的堆的当前内存使用量 */
	private JVMMemoryUsage heapMemoryUsage;
	/** 返回 Java 虚拟机使用的非堆内存的当前内存使用量 */
	private JVMMemoryUsage nonHeapMemoryUsage;
	/** 返回其终止被挂起的对象的近似数目 */
	private Integer objectPendingFinalizationCount;
	/** 测试内存系统的 verbose 输出是否已启用 */
	private boolean isVerbose;

	public JVMMemoryUsage getHeapMemoryUsage() {
		return heapMemoryUsage;
	}

	public void setHeapMemoryUsage(JVMMemoryUsage heapMemoryUsage) {
		this.heapMemoryUsage = heapMemoryUsage;
	}

	public JVMMemoryUsage getNonHeapMemoryUsage() {
		return nonHeapMemoryUsage;
	}

	public void setNonHeapMemoryUsage(JVMMemoryUsage nonHeapMemoryUsage) {
		this.nonHeapMemoryUsage = nonHeapMemoryUsage;
	}

	public Integer getObjectPendingFinalizationCount() {
		return objectPendingFinalizationCount;
	}

	public void setObjectPendingFinalizationCount(Integer objectPendingFinalizationCount) {
		this.objectPendingFinalizationCount = objectPendingFinalizationCount;
	}

	public boolean isVerbose() {
		return isVerbose;
	}

	public void setVerbose(boolean isVerbose) {
		this.isVerbose = isVerbose;
	}

	@Override
	public String toString() {
		return "JVMMemory [heapMemoryUsage=" + heapMemoryUsage + ", nonHeapMemoryUsage="
				+ nonHeapMemoryUsage + ", objectPendingFinalizationCount="
				+ objectPendingFinalizationCount + ", isVerbose=" + isVerbose + "]";
	}
}
