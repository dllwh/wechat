package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.model.system.ScheduleJobLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 下午2:39:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface ScheduleJobLogService {
	List<ScheduleJobLog> findForJdbcParam(ScheduleJobLog log) throws Exception;

	Integer getCountForJdbcParam(ScheduleJobLog log) throws Exception;

	void save(ScheduleJobLog log) throws Exception;
}
