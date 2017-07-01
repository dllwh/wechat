package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 请求地址统计详情
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:56:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HttpRequestCounter implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 请求路径名称*/
	private String name; 
	/** 访问次数 */
	private long hits;
	/**  响应时间和 */
	private long durationsSum;
	/** 响应最长时间 */
	private long maximum; 
	/** cpu执行时间和 */
	private long cpuTimeSum; 
	/** 响应数据大小和 */
	private int responseSizeMean;
	/** CPU平均执行时间 */
	private int cpuTimeMean; 
	/** 平均响应时间*/
	private int mean; 
	/** 响应时间偏差 */
	private int standardDeviation; 
	/** 错误率*/
	private float systemErrorPercentage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	public long getDurationsSum() {
		return durationsSum;
	}

	public void setDurationsSum(long durationsSum) {
		this.durationsSum = durationsSum;
	}

	public long getMaximum() {
		return maximum;
	}

	public void setMaximum(long maximum) {
		this.maximum = maximum;
	}

	public long getCpuTimeSum() {
		return cpuTimeSum;
	}

	public void setCpuTimeSum(long cpuTimeSum) {
		this.cpuTimeSum = cpuTimeSum;
	}

	public int getResponseSizeMean() {
		return responseSizeMean;
	}

	public void setResponseSizeMean(int responseSizeMean) {
		this.responseSizeMean = responseSizeMean;
	}

	public int getCpuTimeMean() {
		return cpuTimeMean;
	}

	public void setCpuTimeMean(int cpuTimeMean) {
		this.cpuTimeMean = cpuTimeMean;
	}

	public int getMean() {
		return mean;
	}

	public void setMean(int mean) {
		this.mean = mean;
	}

	public int getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(int standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public float getSystemErrorPercentage() {
		return systemErrorPercentage;
	}

	public void setSystemErrorPercentage(float systemErrorPercentage) {
		this.systemErrorPercentage = systemErrorPercentage;
	}

}
