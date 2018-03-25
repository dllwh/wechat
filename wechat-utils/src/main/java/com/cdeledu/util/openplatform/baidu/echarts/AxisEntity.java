package com.cdeledu.util.openplatform.baidu.echarts;

import java.util.List;

import com.github.abel533.echarts.code.DataFilter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 坐标轴有
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月23日 下午3:05:20
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class AxisEntity {
	/** ----------------------------------------------------- Fields start */
	/** 横轴名称 */
	private String xAxisName;
	/** 横坐标数据 */
	private List<String> xData;
	/** 纵轴名称 */
	private String yAxisName;
	private String yAxisFormatter;
	/** 数据标线 */
	private boolean lineShowState = false;

	private DataFilter dataFilter;

	/** ----------------------------------------------------- Fields end */
	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public List<String> getxData() {
		return xData;
	}

	public void setxData(List<String> xData) {
		this.xData = xData;
	}

	public String getyAxisName() {
		return yAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	public String getyAxisFormatter() {
		return yAxisFormatter;
	}

	public void setyAxisFormatter(String yAxisFormatter) {
		this.yAxisFormatter = yAxisFormatter;
	}

	public boolean isLineShowState() {
		return lineShowState;
	}

	public void setLineShowState(boolean lineShowState) {
		this.lineShowState = lineShowState;
	}

	public DataFilter getDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(DataFilter dataFilter) {
		this.dataFilter = dataFilter;
	}

}
