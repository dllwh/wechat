package com.cdeledu.service.sys.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdeledu.model.system.ScheduleJobLog;
import com.cdeledu.service.sys.ScheduleJobLogService;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

	@Override
	public void save(ScheduleJobLog record) throws Exception {
	}

	@Override
	public List<ScheduleJobLog> findForJdbcParam(ScheduleJobLog record) throws Exception {
		return null;
	}

	@Override
	public Integer getCountForJdbcParam(ScheduleJobLog record) throws Exception {
		return null;
	}
}
