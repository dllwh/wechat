package com.cdeledu.common.base;

import java.io.Serializable;

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
	protected Integer page;
	protected Integer pageNumber;
	/** 每页记录数 :pageSize */
	protected Integer rows;
	protected Integer pageSize;
	/** 起始页 */
	protected int startRow;
	/** 排序字段名 */
	protected String sort;
	protected String sortName;
	/** 按什么排序(asc,desc) */
	protected String order;
	protected String sortOrder;

	public int getPage() {
		if (page == null) {
			page = (pageNumber != null) ? pageNumber : 1;
		}

		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageNumber() {
		if (pageNumber == null) {
			pageNumber = (page != null) ? page : 1;
		}
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRows() {
		if (rows == null) {
			rows = (pageSize != null) ? pageSize : 10;
		}
		return rows;
	}

	public void setRows(Integer rows) {
		if(rows == null){
			this.rows = 10;	
		} else {
			
		}
		this.rows = rows;
	}

	public Integer getPageSize() {
		if (pageSize == null) {
			pageSize = (rows != null) ? rows : 10;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		startRow = (getPage() - 1) * getRows();
		return startRow;
	}

	public String getSort() {
		if (StringUtils.isBlank(sort)) {
			sort = StringUtils.isNotBlank(sortName) ? sortName : "";
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortName() {
		if (StringUtils.isBlank(sortName)) {
			sortName = StringUtils.isNotBlank(sort) ? sort : "";
		}
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getOrder() {
		if (StringUtils.isBlank(order)) {
			order = StringUtils.isNotBlank(sortOrder) ? sortOrder : "";
		}
		return order;
	}

	public void setOrder(String order) {
		if (StringUtils.isNotBlank(order)
				&& (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc"))) {
			this.order = order;
		} else {
			this.order = "";
		}

	}

	public String getSortOrder() {
		if (StringUtils.isBlank(sortOrder)) {
			sortOrder = StringUtils.isNotBlank(order) ? order : "";
		}
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		if (StringUtils.isNoneBlank(sortOrder)
				&& (sortOrder.equalsIgnoreCase("asc") || sortOrder.equalsIgnoreCase("desc"))) {
			this.sortOrder = sortOrder;
		} else {
			this.sortOrder = "";
		}
	}

}
