/**
 * 
 */
package com.cdeledu.util;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cdeledu.core.factory.ConstantFactory;
import com.cdeledu.model.system.ScheduleJob;
import com.cdeledu.model.system.ScheduleJobLog;
import com.cdeledu.service.sys.ScheduleJobLogService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月28日 下午2:11:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ScheduleJobHelper extends QuartzJobBean {
	/** ----------------------------------------------------- Fields start */
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService service = Executors.newSingleThreadExecutor();

	/** ----------------------------------------------------- Fields end */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
				.get(ScheduleJob.JOB_PARAM_KEY);
		// 获取spring bean
		ScheduleJobLogService scheduleJobLogService = ConstantFactory.scheduleJobLogService;
		// 数据库保存执行记录
		ScheduleJobLog log = new ScheduleJobLog();
		log.setId(scheduleJob.getId());
		log.setBeanName(scheduleJob.getBeanName());
		log.setMethodName(scheduleJob.getMethodName());
		log.setParams(scheduleJob.getParams());
		log.setCreateTime(new Date());

		// 任务开始时间
		long startTime = System.currentTimeMillis();

		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getId());

			ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
					scheduleJob.getMethodName(), scheduleJob.getParams());
			Future<?> future = service.submit(task);

			future.get();

			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;

			log.setTimes((int) times);
			// 任务状态 0：成功 1：失败
			log.setStatus(0);

			logger.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");

		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);

			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);

			// 任务状态 0：成功 1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));

		} finally {
			try {
				scheduleJobLogService.save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
