package com.cdeledu.util.database.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据表属性
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年1月20日 下午10:45:29
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class TableProperty implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 列对应的表名 */
	private String tableName;
	/** 表类别(可为null) */
	private String category;
	/** 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知 */
	private String tableSchem;
	/** 表备注 */
	private String tableComment;
	/** 数据库表的类型 */
	private String tableType;
	/** 表格列 */
	private List<ColumnProperty> columns;
	/** 类名，作为实例对象使用（sysUser） */
	private String objName;
	/** 类名，作为类型使用（SysUser） */
	private String className;
	/** 创建时间 */
	private Date createTime;
	/** 索引信息 */
	private String indexKeyInfo;

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

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public List<ColumnProperty> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnProperty> columns) {
		this.columns = columns;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIndexKeyInfo() {
		return indexKeyInfo;
	}

	public void setIndexKeyInfo(String indexKeyInfo) {
		this.indexKeyInfo = indexKeyInfo;
	}

	@Override
	public String toString() {
		return "TableProperty [tableName=" + tableName + ", category=" + category + ", tableSchem="
				+ tableSchem + ", tableComment=" + tableComment + ", tableType=" + tableType
				+ ", columns=" + columns + ", objName=" + objName + ", className=" + className
				+ ", createTime=" + createTime + ", indexKeyInfo=" + indexKeyInfo + "]";
	}

}
