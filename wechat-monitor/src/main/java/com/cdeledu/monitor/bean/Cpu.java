package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取CPU信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月31日 下午2:55:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Cpu implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/**
	 * cpu信息
	 */
	/** CPU的总量MHz */
	private int mhz;
	/** 获得CPU的卖主，如：Intel */
	private String vendor;
	/** 获得CPU的类别，如：Celeron */
	private String model;
	/** 缓冲存储器数量 */
	private long cacheSize;
	/** CPU核数 */
	private int totalCores;
	/** sockets数 */
	private int totalSockets;

	/**
	 * cpu使用率
	 */
	/** CPU用户使用率 */
	private double user;
	/** CPU系统使用率 */
	private double sys;
	/** CPU当前等待率 */
	private double wait;
	/** CPU当前错误率 */
	private double nice;
	/** CPU当前空闲率 */
	private double idle;
	/** CPU总的使用率 */
	private double combined;
	/** CPU硬件中断时间 */
	private double irq;
	/** CPU软件中断时间 */
	private double softIrq;
	/** CPU使用内部虚拟机运行任务的时间 */
	private double stolen;

	/** ----------------------------------------------------- Fields end */
	public int getMhz() {
		return mhz;
	}

	public void setMhz(int mhz) {
		this.mhz = mhz;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getTotalCores() {
		return totalCores;
	}

	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public int getTotalSockets() {
		return totalSockets;
	}

	public void setTotalSockets(int totalSockets) {
		this.totalSockets = totalSockets;
	}

	public double getUser() {
		return user;
	}

	public void setUser(double user) {
		this.user = user;
	}

	public double getSys() {
		return sys;
	}

	public void setSys(double sys) {
		this.sys = sys;
	}

	public double getWait() {
		return wait;
	}

	public void setWait(double wait) {
		this.wait = wait;
	}

	public double getNice() {
		return nice;
	}

	public void setNice(double nice) {
		this.nice = nice;
	}

	public double getIdle() {
		return idle;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}

	public double getCombined() {
		return combined;
	}

	public void setCombined(double combined) {
		this.combined = combined;
	}

	public double getIrq() {
		return irq;
	}

	public void setIrq(double irq) {
		this.irq = irq;
	}

	public double getSoftIrq() {
		return softIrq;
	}

	public void setSoftIrq(double softIrq) {
		this.softIrq = softIrq;
	}

	public double getStolen() {
		return stolen;
	}

	public void setStolen(double stolen) {
		this.stolen = stolen;
	}

	@Override
	public String toString() {
		return "CPU states: "
				+ format(this.user) + " user, " 
				+ format(this.sys) + " system, "
				+ format(this.nice) + " nice, " 
				+ format(this.wait) + " wait, "
				+ format(this.idle) + " idle";
	}

	public static String format(double val) {
		String p = String.valueOf(val * 100.0);
		int ix = p.indexOf(".") + 1;
		String percent = p.substring(0, ix) + p.substring(ix, ix + 1);
		return percent + "%";
	}
}
