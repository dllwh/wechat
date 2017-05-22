package com.cdeledu.common.base;

import java.io.Serializable;

/**
 * @类描述: 分页类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 上午11:16:44
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PageEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 当前页数:第几页 --pageNo */
	private int page = 1;
	/** 每页记录数 :pageSize(设置为“-1”表示不进行分页（分页无效）) */
	private int rows = 10;
	/** 起始页 */
	private int startRow;
	/** 排序字段名 */
	private String sort;
	/** 按什么排序(asc,desc) */
	private String order;

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
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
