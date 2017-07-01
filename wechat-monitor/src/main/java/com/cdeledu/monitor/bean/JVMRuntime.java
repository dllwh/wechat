package com.cdeledu.monitor.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @类描述: 获取JVM运行时参数及其它信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:39:52
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMRuntime implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回由引导类加载器用于搜索类文件的引导类路径 */
	private String bootClassPath;
	/** 返回系统类加载器用于搜索类文件的 Java 类路径 */
	private String classPath;
	/** 返回传递给 Java 虚拟机的输入变量，其中不包括传递给 main方法的变量 */
	private List<String> inputArguments;
	/** 返回 Java 库路径 */
	private String libraryPath;
	/** 返回正在运行的 Java 虚拟机实现的管理接口的规范版本 */
	private String managementSpecVersion;
	/** 返回表示正在运行的 Java 虚拟机的名称 */
	private String name;
	/** 返回 Java 虚拟机规范名称 */
	private String specName;
	/** 返回 Java 虚拟机规范供应商 */
	private String specVendor;
	/** 返回 Java 虚拟机规范版本 */
	private String specVersion;
	/** 返回 Java 虚拟机的启动时间（以毫秒为单位） */
	private long startTime;
	/** 返回所有系统属性的名称和值的映射 */
	private Map<String, String> systemProperties;
	/** 返回 Java 虚拟机的正常运行时间（以毫秒为单位） */
	private long uptime;
	/** 返回 Java 虚拟机实现名称 */
	private String vmName;
	/** 返回 Java 虚拟机实现供应商 */
	private String vmVendor;
	/** 返回 Java 虚拟机实现版本 */
	private String vmVersion;
	/** 测试Java虚拟机是否支持由引导类加载器用于搜索类文件的引导类路径机制 */
	private boolean isBootClassPathSupported;

	public String getBootClassPath() {
		return bootClassPath;
	}

	public void setBootClassPath(String bootClassPath) {
		this.bootClassPath = bootClassPath;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public List<String> getInputArguments() {
		return inputArguments;
	}

	public void setInputArguments(List<String> inputArguments) {
		this.inputArguments = inputArguments;
	}

	public String getLibraryPath() {
		return libraryPath;
	}

	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public String getManagementSpecVersion() {
		return managementSpecVersion;
	}

	public void setManagementSpecVersion(String managementSpecVersion) {
		this.managementSpecVersion = managementSpecVersion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecVendor() {
		return specVendor;
	}

	public void setSpecVendor(String specVendor) {
		this.specVendor = specVendor;
	}

	public String getSpecVersion() {
		return specVersion;
	}

	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(Map<String, String> systemProperties) {
		this.systemProperties = systemProperties;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getVmVendor() {
		return vmVendor;
	}

	public void setVmVendor(String vmVendor) {
		this.vmVendor = vmVendor;
	}

	public String getVmVersion() {
		return vmVersion;
	}

	public void setVmVersion(String vmVersion) {
		this.vmVersion = vmVersion;
	}

	public boolean getIsBootClassPathSupported() {
		return isBootClassPathSupported;
	}

	public void setIsBootClassPathSupported(boolean isBootClassPathSupported) {
		this.isBootClassPathSupported = isBootClassPathSupported;
	}

	@Override
	public String toString() {
		return "IFCJVMRuntime [bootClassPath=" + bootClassPath + ", classPath=" + classPath
				+ ", inputArguments=" + inputArguments + ", libraryPath=" + libraryPath
				+ ", managementSpecVersion=" + managementSpecVersion + ", name=" + name
				+ ", specName=" + specName + ", specVendor=" + specVendor + ", specVersion="
				+ specVersion + ", startTime=" + startTime + ", systemProperties="
				+ systemProperties + ", uptime=" + uptime + ", vmName=" + vmName + ", vmVendor="
				+ vmVendor + ", vmVersion=" + vmVersion + ", isBootClassPathSupported="
				+ isBootClassPathSupported + "]";
	}
}
