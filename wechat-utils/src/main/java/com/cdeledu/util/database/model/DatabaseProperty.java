package com.cdeledu.util.database.model;

import java.io.Serializable;

/**
 * @类描述: 数据库信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年1月20日 上午9:38:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class DatabaseProperty implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 数据库已知的用户 */
	private String userName;
	/** 数据库URL */
	private String dataBaseUrl;
	/** 是否允许只读 */
	private boolean isReadOnly;
	/** 当前数据库是什么数据库,比如oracle、access等 */
	private String productName;
	/** 当前数据库的版本 */
	private String version;
	/** 驱动程序的名称 */
	private String driverName;
	/** 当前驱动程序的版本 */
	private String driverVersion;
	/** 数据库中使用的表类型 */
	private String tableTypes;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDataBaseUrl() {
		return dataBaseUrl;
	}

	public void setDataBaseUrl(String dataBaseUrl) {
		this.dataBaseUrl = dataBaseUrl;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public String getTableTypes() {
		return tableTypes;
	}

	public void setTableTypes(String tableTypes) {
		this.tableTypes = tableTypes;
	}

	@Override
	public String toString() {
		return "Database [userName=" + userName + ", dataBaseUrl=" + dataBaseUrl + ", isReadOnly="
				+ isReadOnly + ", productName=" + productName + ", version=" + version
				+ ", driverName=" + driverName + ", driverVersion=" + driverVersion
				+ ", tableTypes=" + tableTypes + "]";
	}

}
