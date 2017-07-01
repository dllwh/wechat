package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取JVM垃圾收集信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:31:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMCompilation implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 返回即时(JIT)编辑器的名称 */
	private String name;
	/** 返回在编译上花费的累积耗费时间的近似值（以毫秒为单位） */
	private long totalCompilationTime;
	/** 测试 Java虚拟机是否支持监视编译时间 */
	private boolean isCompilationTimeMonitoringSupported;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTotalCompilationTime() {
		return totalCompilationTime;
	}

	public void setTotalCompilationTime(long totalCompilationTime) {
		this.totalCompilationTime = totalCompilationTime;
	}

	public boolean isCompilationTimeMonitoringSupported() {
		return isCompilationTimeMonitoringSupported;
	}

	public void setCompilationTimeMonitoringSupported(
			boolean isCompilationTimeMonitoringSupported) {
		this.isCompilationTimeMonitoringSupported = isCompilationTimeMonitoringSupported;
	}

	@Override
	public String toString() {
		return "JVMCompilation [name=" + name + ", totalCompilationTime=" + totalCompilationTime
				+ ", isCompilationTimeMonitoringSupported=" + isCompilationTimeMonitoringSupported
				+ "]";
	}
}
