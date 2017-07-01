package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取JVM垃圾收集信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:35:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMGarbageCollector implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	/** /返回已发生的回收的总次数 */
	private long collectionCount;
	/** 返回近似的累积回收时间（以毫秒为单位） */
	private long collectionTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(long collectionCount) {
		this.collectionCount = collectionCount;
	}

	public long getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(long collectionTime) {
		this.collectionTime = collectionTime;
	}

	@Override
	public String toString() {
		return "JVMGarbageCollector [name=" + name + ", collectionCount=" + collectionCount
				+ ", collectionTime=" + collectionTime + "]";
	}
}
