package com.cdeledu.service.sys.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdeledu.model.system.ScheduleJobLog;
import com.cdeledu.service.sys.ScheduleJobLogService;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

	@Override
	public Integer insert(ScheduleJobLog record) throws Exception {
		return null;
	}

	@Override
	public Integer batchInsert(List<ScheduleJobLog> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer delete(Object record) throws Exception {
		return null;
	}

	@Override
	public Integer batchDelete(List<Object> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer update(ScheduleJobLog record) throws Exception {
		return null;
	}

	@Override
	public Integer batchUpdate(List<ScheduleJobLog> parameter) throws Exception {
		return null;
	}

	@Override
	public List<ScheduleJobLog> findForJdbcParam(ScheduleJobLog record) throws Exception {
		return null;
	}

	@Override
	public Integer getCountForJdbcParam(ScheduleJobLog record) throws Exception {
		return null;
	}

	@Override
	public ScheduleJobLog findOneForJdbc(ScheduleJobLog record) throws Exception {
		return null;
	}
}
