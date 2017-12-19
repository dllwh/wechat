package com.cdeledu.service.sys.impl;

import org.springframework.stereotype.Service;

import com.cdeledu.model.system.ScheduleJob;
import com.cdeledu.service.sys.ScheduleJobService;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	@Override
	public void save(ScheduleJob scheduleJob) {
	}

	@Override
	public void update(ScheduleJob scheduleJob) {
	}

	@Override
	public void deleteBatch(Integer[] jobIds) {
	}

	@Override
	public int updateBatch(Integer[] jobIds, int status) {
		return 0;
	}

	@Override
	public void run(Integer[] jobIds) {
	}

	@Override
	public void pause(Integer[] jobIds) {
	}

	@Override
	public void resume(Integer[] jobIds) {
	}
}
