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
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** 列所在的表目录名称 */
	protected String catalogName;
	/** 列所在的名称 */
	protected String columnName;
	/** 列所在的的数据类型,返回SqlType中的编号 */
	protected Integer columnType;
	/** 列在数据库中的类型，返回类型全名 */
	protected String columnTypeName;
	/** 列对应数据类型的类 */
	protected String columnClassName;
	/** 获取用于打印输出和显示的指定列的建议标题 */
	protected String columnLabel;
	/** 列在数据库中类型的最大字符个数 */
	protected Integer columnDisplaySize;
	/** 列类型的精确度(类型的长度) */
	protected Integer precision;
	/** 列小数点后的位数 */
	protected Integer scale;
	/** 列对应的模式的名称（应该用于Oracle） */
	protected String schemaName;
	/** 列是否自动递增 */
	protected boolean isAutoIncrement;
	/** 列在数据库中是否为货币型 */
	protected boolean isCurrency;
	/** 列是否为空 */
	protected boolean isNullable;
	/** 列是否为只读 */
	protected boolean isReadOnly;
	/** 数据库表字段的描述 */
	protected String remark;
	/** 默认值 */
	protected String defaultValue;
	/** ----------------------------------------------------- Fields end */
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
