package com.cdeledu.util.chart.echarts;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 折线图
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月23日 下午3:03:49
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ChartsData {
	/** ----------------------------------------------------- Fields start */
	/** 数据名称 */
	private String name;
	/** 数据值 */
	private List<Object> data;

	/** ----------------------------------------------------- Fields end */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
}
