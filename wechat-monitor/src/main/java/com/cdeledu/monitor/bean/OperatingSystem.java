package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取操作系统信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月31日 下午4:14:53
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class OperatingSystem implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 操作系统类型名 */
	private String name;
	/** 操作系统的版本号 */
	private String version;
	/** 操作系统 */
	private String arch;
	private String machine;
	/** 操作系统的描述 */
	private String description;
	private String patchLevel;
	/** 操作系统的卖主 */
	private String vendor;
	/** 操作系统卖主类型 */
	private String vendorVersion;
	/** 操作系统名称 */
	private String vendorName;
	/** 操作系统的卖主名 */
	private String vendorCodeName;
	/** 操作系统DataModel() */
	private String dataModel;
	/** 操作系统CpuEndian() */
	private String cpuEndian;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPatchLevel() {
		return patchLevel;
	}

	public void setPatchLevel(String patchLevel) {
		this.patchLevel = patchLevel;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendorVersion() {
		return vendorVersion;
	}

	public void setVendorVersion(String vendorVersion) {
		this.vendorVersion = vendorVersion;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorCodeName() {
		return vendorCodeName;
	}

	public void setVendorCodeName(String vendorCodeName) {
		this.vendorCodeName = vendorCodeName;
	}

	public String getDataModel() {
		return dataModel;
	}

	public void setDataModel(String dataModel) {
		this.dataModel = dataModel;
	}

	public String getCpuEndian() {
		return cpuEndian;
	}

	public void setCpuEndian(String cpuEndian) {
		this.cpuEndian = cpuEndian;
	}

}
