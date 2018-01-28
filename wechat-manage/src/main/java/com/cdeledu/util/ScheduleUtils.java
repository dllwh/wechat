package com.cdeledu.util;

import org.quartz.Scheduler;

import com.cdeledu.model.system.ScheduleJob;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 上午11:08:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ScheduleUtils {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:创建定时任务
	 * @创建人:独泪了无痕
	 * @param scheduler
	 * @param scheduleJob
	 */
	public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
	}

	/**
	 * 更新定时任务
	 */
	public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {

	}

	/**
	 * 立即执行任务
	 */
	public static void run(Scheduler scheduler, ScheduleJob scheduleJob) {

	}

	/**
	 * 暂停任务
	 */
	public static void pauseJob(Scheduler scheduler, Long jobId) {

	}

	/**
	 * 恢复任务
	 */
	public static void resumeJob(Scheduler scheduler, Long jobId) {

	}

	/**
	 * 删除定时任务
	 */
	public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {

	}
}
