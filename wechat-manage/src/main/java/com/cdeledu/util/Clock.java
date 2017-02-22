package com.cdeledu.util;

import java.util.Date;

public class Clock {
	/**
	 * period:更新频率
	 * 
	 * @since JDK 1.6
	 */
	private long period;
	/**
	 * currentTimeMillis:当前时间毫秒数
	 * 
	 * @since JDK 1.6
	 */
	private volatile long currentTimeMillis;

	/**
	 * currentDate:当前日期
	 * 
	 * @since JDK 1.6
	 */
	private volatile Date currentDate;
	private final static Clock clock = new Clock(100);

	private Clock() {
	}

	private Clock(long period) {
		this.period = period;
		this.currentTimeMillis = System.currentTimeMillis();
		this.currentDate = new Date();
		start();
	}

	private void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(period);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
					currentTimeMillis = System.currentTimeMillis();
					currentDate = new Date();
				}
			}
		}).start();
	}

	public static long currentTimeMillis() {
		return clock.getCurrentTimeMillis();
	}

	public static Date currentDate() {
		return clock.getCurrentDate();
	}

	public long getPeriod() {
		return this.period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public long getCurrentTimeMillis() {
		return this.currentTimeMillis;
	}

	public void setCurrentTimeMillis(long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}

	public Date getCurrentDate() {
		return this.currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public static void main(String[] args) {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			System.out.println((System.currentTimeMillis() - Clock.currentTimeMillis()));
		}
	}

}
