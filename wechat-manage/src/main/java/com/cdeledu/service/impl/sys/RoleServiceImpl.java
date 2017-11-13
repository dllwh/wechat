package com.cdeledu.service.impl.sys;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.service.sys.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	@Override
	public Integer insert(SysRole record) throws Exception {
		return null;
	}

	@Override
	public Integer batchInsert(List<SysRole> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer delete(Object record) throws Exception {
		return null;
	}

	@Override
	public Integer batchDelete(List<SysRole> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer update(SysRole record) throws Exception {
		return null;
	}

	@Override
	public Integer batchUpdate(List<SysRole> parameter) throws Exception {
		return null;
	}

	@Override
	public List<SysRole> findForJdbcParam(SysRole record) throws Exception {
		return null;
	}

	@Override
	public Integer getCountForJdbcParam(SysRole record) throws Exception {
		return null;
	}

	@Override
	public SysRole findOneForJdbc(SysRole record) throws Exception {
		return null;
	}

}
