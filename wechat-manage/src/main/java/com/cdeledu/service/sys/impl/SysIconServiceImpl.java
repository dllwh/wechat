package com.cdeledu.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysIcon;
import com.cdeledu.service.sys.SysIconService;

@Service
public class SysIconServiceImpl implements SysIconService {
	@Resource
	private BaseDaoSupport<?> baseDao;
	private final static String PREFIX = "manageUserDaoImpl.";

	@Override
	public Integer insert(SysIcon record) throws Exception {
		return baseDao.insert(PREFIX + "insertSelective", record);
	}

	@Override
	public Integer batchInsert(List<SysIcon> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer delete(Object record) throws Exception {
		return baseDao.delete(PREFIX + "deleteByPrimaryKey", record);
	}

	@Override
	public Integer batchUpdate(List<SysIcon> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer update(SysIcon record) throws Exception {
		return null;
	}

	@Override
	public Integer batchDelete(List<Object> parameter) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysIcon> findForJdbcParam(SysIcon record) throws Exception {
		return (List<SysIcon>) baseDao.findListForJdbcParam(PREFIX + "findForJdbc", record);
	}

	@Override
	public Integer getCountForJdbcParam(SysIcon record) throws Exception {
		return baseDao.getCountForJdbcParam(PREFIX + "getCountForJdbcParam", record);
	}

	@Override
	public SysIcon findOneForJdbc(SysIcon record) throws Exception {
		return (SysIcon) baseDao.findOneForJdbcParam(PREFIX + "findOneForJdbc", record);
	}
}
