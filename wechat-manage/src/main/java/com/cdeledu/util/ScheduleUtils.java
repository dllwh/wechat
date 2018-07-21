package com.cdeledu.util;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

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
public final class ScheduleUtils {
	/** ----------------------------------------------------- Fields start */
	public static String basePath = "";
	/** 任务调度参数key */
	public final static String JOB_NAME = "JOB_PARAM_KEY";

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:获取触发器key
	 * @创建人:独泪了无痕
	 * @param beanName
	 * @return
	 */
	public static TriggerKey getTriggerKey(String beanName) {
		return TriggerKey.triggerKey(JOB_NAME + beanName);
	}

	/**
	 * @方法: 获取jobKey
	 * @创建人:独泪了无痕
	 * @param beanName
	 * @return
	 */
	public static JobKey getJobKey(String beanName) {
		return JobKey.jobKey(JOB_NAME + beanName);
	}

	/**
	 * @方法:获取表达式触发器
	 * @创建人:独泪了无痕
	 * @param scheduler
	 * @param beanName
	 * @return
	 */
	public static CronTrigger getCronTrigger(Scheduler scheduler, String beanName) {
		try {
			return (CronTrigger) scheduler.getTrigger(getTriggerKey(beanName));
		} catch (SchedulerException e) {
			throw new RuntimeException("获取定时任务CronTrigger出现异常", e);
		}
	}

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
		// throw new RuntimeException("更新定时任务失败", e);
	}

	/**
	 * 立即执行任务
	 */
	public static void run(Scheduler scheduler, String beanName) {
		try {
			scheduler.triggerJob(getJobKey(beanName));
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("立即执行定时任务失败、任务未启动");
		}
	}

	/**
	 * 暂停任务
	 */
	public static void pauseJob(Scheduler scheduler, String beanName) {
		try {
			scheduler.pauseJob(getJobKey(beanName));
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("暂停定时任务失败", e);
		}
	}

	/**
	 * 恢复任务
	 */
	public static void resumeJob(Scheduler scheduler, String beanName) {
		try {
			scheduler.resumeJob(getJobKey(beanName));
		} catch (SchedulerException e) {
			throw new RuntimeException("恢复定时任务失败", e);
		}
	}

	/**
	 * @方法:删除定时任务
	 * @创建人:独泪了无痕
	 * @param scheduler
	 *            调度器
	 * @param beanName
	 */
	public static void deleteScheduleJob(Scheduler scheduler, String beanName) {
		try {
			scheduler.deleteJob(getJobKey(beanName));
		} catch (SchedulerException e) {
			throw new RuntimeException("删除定时任务失败", e);
		}
	}

	/**
	 * @方法:启动
	 * @创建人:独泪了无痕
	 * @param scheduler
	 *            调度器
	 * @param scheduleJob
	 */
	public static void startTask(Scheduler scheduler, ScheduleJob scheduleJob) {
		try {
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException("启动全部定时任务失败：" + e.getMessage());
		}
	}

	/**
	 * @方法:关闭所有定时任务
	 * @创建人:独泪了无痕
	 * @param scheduler
	 *            调度器
	 */
	public static void shutdownJobs(Scheduler scheduler) {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @方法:启动所有定时任务
	 * @创建人:独泪了无痕
	 * @param scheduler
	 *            调度器
	 */
	public static void startJobs(Scheduler scheduler) {
		try {
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException("启动全部定时任务失败：" + e.getMessage());
		}
	}
}
