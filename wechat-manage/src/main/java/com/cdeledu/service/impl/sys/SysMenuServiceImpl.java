package com.cdeledu.service.impl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.service.sys.SysMenuService;

@Service
@Transactional(readOnly = false)
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private BaseDaoSupport<?> baseDao;
	private final static String prefix = "com.cdeledu.dao.SysMenuMapper.";

	@Override
	public Integer insert(SysMenu record) throws Exception {
		return baseDao.insert(prefix + "insertSelective", record);
	}

	@Override
	public Integer batchInsert(List<SysMenu> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer delete(Object record) throws Exception {
		return baseDao.delete(prefix + "deleteByPrimaryKey", record);
	}

	@Override
	public Integer batchUpdate(List<SysMenu> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer update(SysMenu record) throws Exception {
		return baseDao.update(prefix + "updateByPrimaryKeySelective", record);
	}

	@Override
	public Integer batchDelete(List<SysMenu> parameter) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> findForJdbc(SysMenu record) throws Exception {
		return (List<SysMenu>) baseDao.findListForJdbcParam(prefix + "findForJdbc", record);
	}

	@Override
	public Integer getCountForJdbcParam(SysMenu record) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "getCountForJdbcParam", record);
	}

	@Override
	public SysMenu findOneForJdbc(SysMenu record) throws Exception {
		return (SysMenu) baseDao.findOneForJdbcParam(prefix + "findOneForJdbc", record);
	}
}
