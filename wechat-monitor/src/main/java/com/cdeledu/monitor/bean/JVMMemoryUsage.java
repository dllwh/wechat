package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 内存使用
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午6:21:15
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMMemoryUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 返回 Java 虚拟机最初从操作系统请求用于内存管理的内存量（以字节为单位） */
	private long init;
	/** 返回已使用的内存量（以字节为单位） */
	private long used;
	/** 返回已提交给 Java 虚拟机使用的内存量（以字节为单位） */
	private long committed;
	/** 返回可以用于内存管理的最大内存量（以字节为单位） */
	private long max;

	public long getInit() {
		return init;
	}

	public void setInit(long init) {
		this.init = init;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getCommitted() {
		return committed;
	}

	public void setCommitted(long committed) {
		this.committed = committed;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "JVMMemoryUsage [init=" + init + ", used=" + used + ", committed=" + committed
				+ ", max=" + max + "]";
	}

}
