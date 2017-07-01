package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取JVM类加载信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:24:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMClassLoading implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回当前加载到Java 虚拟机中的类的数量 */
	private long loadedClassCount;
	/** 返回自Java 虚拟机开始执行到目前已经加载的类的总数 */
	private long totalLoadedClassCount;
	/** 返回自Java 虚拟机开始执行到目前已经卸载的类的总数 */
	private long unloadedClassCount;
	/** 测试是否以为类加载系统启用了verbose输出 */
	private boolean isVerbose;

	public long getLoadedClassCount() {
		return loadedClassCount;
	}

	public void setLoadedClassCount(long loadedClassCount) {
		this.loadedClassCount = loadedClassCount;
	}

	public long getTotalLoadedClassCount() {
		return totalLoadedClassCount;
	}

	public void setTotalLoadedClassCount(long totalLoadedClassCount) {
		this.totalLoadedClassCount = totalLoadedClassCount;
	}

	public long getUnloadedClassCount() {
		return unloadedClassCount;
	}

	public void setUnloadedClassCount(long unloadedClassCount) {
		this.unloadedClassCount = unloadedClassCount;
	}

	public boolean isVerbose() {
		return isVerbose;
	}

	public void setVerbose(boolean isVerbose) {
		this.isVerbose = isVerbose;
	}

	@Override
	public String toString() {
		return "JVMClassLoading [loadedClassCount=" + loadedClassCount + ", totalLoadedClassCount="
				+ totalLoadedClassCount + ", unloadedClassCount=" + unloadedClassCount
				+ ", isVerbose=" + isVerbose + "]";
	}
}
