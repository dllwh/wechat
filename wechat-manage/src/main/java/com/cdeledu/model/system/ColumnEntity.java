package com.cdeledu.model.system;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据表列属性
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 上午9:07:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ColumnEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 列名 */
	private String columnName;
	/** 数据类型 */
	private String dataType;
	/** 列注释 */
	private String columnComment;
	/** 属性名，作为类属性名（userId） */
	private String fieldName;
	/** 属性名，作为类方法名（UserId） */
	private String methodName;
	/** 列数据类型对应java数据类型 */
	private String fieldType;
	/** 键类型标识 */
	private String columnKey;
	/** 自增标识 auto_increment */
	private String extra;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
}
