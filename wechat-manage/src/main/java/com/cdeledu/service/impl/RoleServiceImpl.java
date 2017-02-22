package com.cdeledu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.dao.RoleDao;
import com.cdeledu.model.rbac.Role;
import com.cdeledu.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	@Override
	public int insertSelective(Role record) throws Exception {
		return roleDao.insertSelective(record);
	}

	@Override
	public int delete(Role record) throws Exception {
		return roleDao.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) throws Exception {
		return roleDao.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Role record) throws Exception {
		return roleDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Role> findForJdbc(Role record) throws Exception {
		return roleDao.findForJdbc(record);
	}

	@Override
	public int getCountForJdbcParam(Role record) throws Exception {
		return roleDao.getCountForJdbcParam(record);
	}

	@Override
	public Role findOneForJdbc(Role record) throws Exception {
		return roleDao.findOneForJdbc(record);
	}

	@Override
	public int batchInsert(List<Role> record) throws Exception {
		return 0;
	}

}
