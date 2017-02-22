package com.cdeledu.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @类描述: 分页基本
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月28日 上午11:44:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PageModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** 主键 */
	protected Integer id;
	/** 当前页数:第几页 */
	private int page = 1;
	/** 每页记录数 */
	private int rows = 10;
	/** 排序字段名 */
	private String sort;
	/** 按什么排序(asc,desc) */
	private String order;

	/** ----------------------------------------------------- Fields end */
	@Id
	@GeneratedValue(generator = "Generator")
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
