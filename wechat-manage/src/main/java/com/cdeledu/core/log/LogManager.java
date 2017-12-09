package com.cdeledu.core.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志管理器
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:47:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LogManager {
	/** ----------------------------------------------------- Fields start */
	/** 日志记录操作延时 */
	private final int OPERATE_DELAY_TIME = 10;
	/** 异步操作记录日志的线程池 */
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

	/** ----------------------------------------------------- Fields end */
	private LogManager() {
	}

	public static LogManager getInstance() {
		return logManager;
	}

	public static LogManager logManager = new LogManager();

	public void executeLog(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}

}
