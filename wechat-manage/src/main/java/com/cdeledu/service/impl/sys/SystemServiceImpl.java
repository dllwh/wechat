package com.cdeledu.service.impl.sys;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.model.system.LoginLog;
import com.cdeledu.service.sys.SystemService;

@Service
@Transactional(readOnly = true)
public class SystemServiceImpl extends BaseClass implements SystemService {

	private static final long serialVersionUID = 1L;

	@Override
	public void addLog(String LogContent, Integer loglevel, Integer operatetype) {

	}

	@Override
	public void addLoginLog(LoginLog loginLog) {

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
