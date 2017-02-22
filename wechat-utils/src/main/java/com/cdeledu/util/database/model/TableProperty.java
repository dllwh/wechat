package com.cdeledu.util.database.model;

import java.io.Serializable;

public class TableProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	/** 列对应的表名 */
	private String tableName;
	/** 表类别(可为null) */
	private String category;
	/** 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知 */
	private String tableSchem;
	/** 表备注 */
	private String remarks;
	/** 数据库表的类型 */
	private String tableType;

	/** ----------------------------------------------------- Fields end */
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTableSchem() {
		return tableSchem;
	}

	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	@Override
	public String toString() {
		return "Table [tableName=" + tableName + ", category=" + category + ", tableSchem="
				+ tableSchem + ", remarks=" + remarks + ", tableType=" + tableType + "]";
	}

}
