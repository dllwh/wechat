package com.cdeledu.common.base;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

/**
 * @类描述: 分页类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 上午11:16:44
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class PageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 当前页数:第几页 --pageNo */
	protected int page = 1;
	/** 每页记录数 :pageSize(设置为“-1”表示不进行分页（分页无效）) */
	protected int rows = 10;
	/** 起始页 */
	protected int startRow;
	/** 排序字段名 */
	protected String sort;
	/** 按什么排序(asc,desc) */
	protected String order;

	public boolean hasField(String fieldName) {
		if (null == fieldName) {
			return false;
		}
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getName().equalsIgnoreCase(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStartRow() {
		startRow = (page - 1) * rows;
		return startRow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if (hasField(sort)) {
			this.sort = sort;
		} else {
			this.sort = "";
		}
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if(StringUtils.isNoneBlank(order) && (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc"))){
			this.order = order;
		} else {
			this.order = "";
		}
		
	}

}
