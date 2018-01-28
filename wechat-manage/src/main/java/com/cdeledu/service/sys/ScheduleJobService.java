package com.cdeledu.service.sys;

import com.cdeledu.model.system.ScheduleJob;

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

	/**
	 * @方法描述 : 保存定时任务
	 */
	void save(ScheduleJob scheduleJob);

	/**
	 * @方法描述 : 更新定时任务
	 */
	void update(ScheduleJob scheduleJob);

	/**
	 * @方法描述 : 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);

	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] jobIds, int status);

	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);

	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);

	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
}
