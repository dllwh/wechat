package com.cdeledu.util.database.model;

import java.io.Serializable;

/**
 * @类描述:
 *       <p>
 *       数据库表的列信息
 *       </p>
 *       <p>
 *       利用ResultSet的getMetaData的方法可以获得ResultSetMeta对象，而ResultSetMetaData存储了
 *       ResultSet的MetaData,所以getMetaData就包括了数据的字段名称、类型以及数目等表格所必须具备的信息。
 *       </p>
 * @创建者: 独泪了无痕
 * @创建时间: 2016年1月20日 下午10:45:29
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class ColumnProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 列所在的表目录名称 */
	private String catalogName;
	/** 列所在的名称 */
	private String columnName;
	/** 列所在的的数据类型,返回SqlType中的编号 */
	private Integer columnType;
	/** 数据库表字段的描述 */
	private String columnComment;
	/** 列在数据库中的类型，返回类型全名 */
	private String columnTypeName;
	/** 列对应数据类型的类 */
	private String columnClassName;
	/** 键类型标识 */
	private String columnKey;
	/** 获取用于打印输出和显示的指定列的建议标题 */
	private String columnLabel;
	/** 列在数据库中类型的最大字符个数 */
	private Integer columnDisplaySize;
	/** 列类型的精确度(类型的长度) */
	private Integer precision;
	/** 列小数点后的位数 */
	private Integer scale;
	/** 列对应的模式的名称（应该用于Oracle） */
	private String schemaName;
	/** 列是否自动递增 */
	private boolean isAutoIncrement;
	/** 列在数据库中是否为货币型 */
	private boolean isCurrency;
	/** 列是否为空 */
	private boolean isNullable;
	/** 列是否为只读 */
	private boolean isReadOnly;
	/** 默认值 */
	private String defaultValue;

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getColumnType() {
		return columnType;
	}

	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public Integer getColumnDisplaySize() {
		return columnDisplaySize;
	}

	public void setColumnDisplaySize(Integer columnDisplaySize) {
		this.columnDisplaySize = columnDisplaySize;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public boolean isCurrency() {
		return isCurrency;
	}

	public void setCurrency(boolean isCurrency) {
		this.isCurrency = isCurrency;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "ColumnProperty [catalogName=" + catalogName + ", columnName=" + columnName
				+ ", columnType=" + columnType + ", columnComment=" + columnComment
				+ ", columnTypeName=" + columnTypeName + ", columnClassName=" + columnClassName
				+ ", columnKey=" + columnKey + ", columnLabel=" + columnLabel
				+ ", columnDisplaySize=" + columnDisplaySize + ", precision=" + precision
				+ ", scale=" + scale + ", schemaName=" + schemaName + ", isAutoIncrement="
				+ isAutoIncrement + ", isCurrency=" + isCurrency + ", isNullable=" + isNullable
				+ ", isReadOnly=" + isReadOnly + ", defaultValue=" + defaultValue + "]";
	}
}
