package com.cdeledu.service.impl.sys;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.service.sys.SystemService;

@Service("systemService")
@Transactional(readOnly = false)
public class SystemServiceImpl extends BaseClass implements SystemService {

	private static final long serialVersionUID = 1L;
	@Resource
	private BaseDaoSupport<?> baseDao;
	
	@Override
	public void addLog(String LogContent, Integer loglevel, Integer operatetype) throws Exception{

	}

	@Override
	public void addLoginLog(SysLoginLog loginLog) throws Exception{
		baseDao.insert("manageUserDaoImpl.saveLoginInfo", loginLog);
	}

	@Override
	public Set<String> getOperationCodesByUserIdAndFunctionId(Integer userId, Integer functionId) {
		return null;
	}

	@Override
	public Set<String> getOperationCodesByRoleIdAndFunctionId(Integer roleId, Integer functionId) {
		return null;
	}

	@Override
	public Integer getAuthByuserIdAndAuthPath(Integer userId, String requestPath) {
		return null;
	}
}
