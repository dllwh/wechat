package com.cdeledu.model.system;

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
 * @创建时间: 2017年11月9日 上午9:08:26
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TableEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 表名 */
	private String tableName;
	/** 表格备注 */
	private String tableComment;
	/** 主键 */
	private ColumnEntity pk;
	/** 表格列 */
	private List<ColumnEntity> columns;
	/** 类名，作为实例对象使用（sysUser） */
	private String objName;
	/** 类名，作为类型使用（SysUser） */
	private String className;
	/** 创建时间 */
	private Date createTime;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public ColumnEntity getPk() {
		return pk;
	}

	public void setPk(ColumnEntity pk) {
		this.pk = pk;
	}

	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnEntity> columns) {
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
}
