package com.cdeledu.service.sys.impl;

import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.model.system.ScheduleJob;
import com.cdeledu.service.sys.ScheduleJobService;
import com.cdeledu.util.ScheduleUtils;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	/** ----------------------------------------------------- Fields start */
	
	private Scheduler scheduler;

	/** ----------------------------------------------------- Fields end */
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ScheduleJob scheduleJob) {
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJob scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateBatch(Long[] jobIds, int status) {
		return 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void run(Long[] jobIds) {

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pause(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void resume(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}
	}
}
