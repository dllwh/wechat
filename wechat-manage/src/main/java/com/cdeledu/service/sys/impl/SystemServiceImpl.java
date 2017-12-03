package com.cdeledu.service.sys.impl;

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
	public void addLog(SysLogEntity syslog) {
		try {
			baseDao.insert("com.cdeledu.dao.impl.sys.systemMapper.insertSelective", syslog);
		} catch (Exception e) {
			error(getClass(), "添加操作日志出现异常", e);
		}
	}

	@Override
	public void addLoginLog(SysLoginLog loginLog) {
		try {
			baseDao.insert("manageUserDaoImpl.saveLoginInfo", loginLog);
		} catch (Exception e) {
			error(getClass(), "添加登录/退出日志出现异常", e);
		}
	}
}
