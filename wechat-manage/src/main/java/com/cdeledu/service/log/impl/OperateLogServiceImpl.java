package com.cdeledu.service.log.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.service.log.OperateLogService;

@Service("operateLogService")
public class OperateLogServiceImpl extends BaseClass implements OperateLogService {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Resource
	private BaseDaoSupport<?> baseDao;
	private static final String PREFIX = "com.cdeledu.dao.impl.sysLog.OperateLog.";

	/** ----------------------------------------------------- Fields end */
	public void addLog(SysLogEntity syslog) {
		try {
			baseDao.insert(PREFIX + "insertSyslog", syslog);
		} catch (Exception e) {
			error(getClass(), "添加操作日志出现异常", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SysLogEntity> getSysLog(SysLogEntity sysLogEntity) throws Exception {
		return (List<SysLogEntity>) baseDao.findListForJdbcParam(PREFIX + "getSysLog",
				sysLogEntity);
	}

	@Override
	public Integer getSysLogCount(SysLogEntity sysLogEntity) {
		try {
			return baseDao.getCountForJdbcParam(PREFIX + "getSysLogCount", sysLogEntity);
		} catch (Exception e) {
			return 0;
		}
	}

}
