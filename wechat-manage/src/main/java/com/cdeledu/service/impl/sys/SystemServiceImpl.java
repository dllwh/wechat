package com.cdeledu.service.impl.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.service.sys.SystemService;

@Service("systemService")
@Transactional(readOnly = false)
public class SystemServiceImpl extends BaseClass implements SystemService {

	private static final long serialVersionUID = 1L;
	@Resource
	private BaseDaoSupport<?> baseDao;

	@Override
	public void addLog(SysLogEntity syslog) throws Exception {

	}

	@Override
	public void addLoginLog(SysLoginLog loginLog) throws Exception {
		baseDao.insert("manageUserDaoImpl.saveLoginInfo", loginLog);
	}
}
