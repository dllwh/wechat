package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.model.system.ScheduleJob;
import com.cdeledu.model.system.ScheduleJobLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 下午2:37:11
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface ScheduleJobService {
	List<ScheduleJob> findForJdbcParam(ScheduleJob scheduleJob) throws Exception;

	Integer getCountForJdbcParam(ScheduleJob scheduleJob) throws Exception;

	/**
	 * @方法描述 : 保存定时任务
	 */
	boolean save(ScheduleJob scheduleJob);

	/**
	 * @方法描述 : 更新定时任务
	 */
	boolean update(ScheduleJob scheduleJob);

	/**
	 * @方法描述 : 删除定时任务
	 */
	boolean delete(int jobId);

	/**
	 * 更新定时任务状态
	 */
	boolean updateStatus(int jobId, int status);
}
