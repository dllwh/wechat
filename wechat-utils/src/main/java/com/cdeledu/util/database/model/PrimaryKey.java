package com.cdeledu.util.database.model;

import java.io.Serializable;

public class PrimaryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** 列对应的表名 */
	private String tableName;
	/** 列名 */
	private String columnName;
	/** 主键名称 */
	private String pkName;
	/** 序列号 */
	private Short keySeq;

	/** ----------------------------------------------------- Fields end */
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public Short getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Short keySeq) {
		this.keySeq = keySeq;
	}

	@Override
	public String toString() {
		return "PrimaryKey [tableName=" + tableName + ", columnName=" + columnName + ", pkName="
				+ pkName + ", keySeq=" + keySeq + "]";
	}

}
