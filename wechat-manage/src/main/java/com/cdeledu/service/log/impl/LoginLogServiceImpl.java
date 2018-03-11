package com.cdeledu.service.log.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.service.log.LoginLogService;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseClass implements LoginLogService {

	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "com.cdeledu.dao.impl.sysLog.SysLoginLog.";
	@Resource
	private BaseDaoSupport<?> baseDao;

	/** ----------------------------------------------------- Fields end */
	public void addLoginLog(SysLoginLog loginLog) {
		try {
			baseDao.insert(PREFIX + "saveLoginInfo", loginLog);
		} catch (Exception e) {
			error(getClass(), "添加登录/退出日志出现异常", e);
		}
	}

}
