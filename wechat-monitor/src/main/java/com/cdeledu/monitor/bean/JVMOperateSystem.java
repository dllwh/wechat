package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * 
 * @类描述: 获取JVM所在操作系统信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:38:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMOperateSystem implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回操作系统的架构 */
	private String arch;
	/** 返回 Java 虚拟机可以使用的处理器数目 */
	private int availableProcessors;
	/** 返回操作系统名称 */
	private String name;
	/** 返回最后一分钟内系统加载平均值 */
	private double systemLoadAverage;
	/** 返回操作系统的版本 */
	private String version;

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSystemLoadAverage() {
		return systemLoadAverage;
	}

	public void setSystemLoadAverage(double systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "IFCJVMOperatingSystem [arch=" + arch + ", availableProcessors="
				+ availableProcessors + ", name=" + name + ", systemLoadAverage="
				+ systemLoadAverage + ", version=" + version + "]";
	}
}
