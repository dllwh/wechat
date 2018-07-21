package com.cdeledu.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.ScheduleJob;
import com.cdeledu.service.sys.ScheduleJobService;
import com.cdeledu.util.application.QvoConditionUtil;

@Service("scheduleJobService")
@SuppressWarnings("unchecked")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;
	private final static String PREFIX = "com.cdeledu.dao.impl.scheduleJob.ScheduleJobDaoImpl.";

	/** ----------------------------------------------------- Fields end */

	public List<ScheduleJob> findForJdbcParam(ScheduleJob scheduleJob) throws Exception {
		return (List<ScheduleJob>) baseDao.findListForJdbcParam(PREFIX + "getScheduleJob",
				scheduleJob);
	}

	public Integer getCountForJdbcParam(ScheduleJob scheduleJob) throws Exception {
		return baseDao.getCountForJdbcParam(PREFIX + "countScheduleJob", scheduleJob);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean save(ScheduleJob scheduleJob) {
		Integer resultCount = baseDao.insert(PREFIX, scheduleJob);
		return QvoConditionUtil.checkInteger(resultCount);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean update(ScheduleJob scheduleJob) {
		Integer resultCount = baseDao.update(PREFIX + "update", scheduleJob);
		return QvoConditionUtil.checkInteger(resultCount);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean delete(int jobId) {
		Integer resultCount = baseDao.delete(PREFIX + "delete", jobId);
		return QvoConditionUtil.checkInteger(resultCount);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateStatus(int jobId, int status) {
		ScheduleJob scheduleJob = new ScheduleJob(jobId, status);
		Integer resultCount = baseDao.update(PREFIX, scheduleJob);
		return QvoConditionUtil.checkInteger(resultCount);
	}

}
