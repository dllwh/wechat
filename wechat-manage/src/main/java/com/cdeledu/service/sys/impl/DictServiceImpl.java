package com.cdeledu.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysDict;
import com.cdeledu.service.sys.DictService;

/**
 * @类描述: 业务处理层-数据字典
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月12日 下午6:55:21
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Service("dictService")
@SuppressWarnings("unchecked")
public class DictServiceImpl implements DictService {
	@Resource
	private BaseDaoSupport<?> baseDao;
	private final static String PREFIX = "sysDictDaoImpl.";

	@Override
	@Transactional(readOnly = false)
	public Integer insert(SysDict record) throws Exception {
		return baseDao.insert(PREFIX + "insertSelective", record);
	}

	@Override
	public Integer batchInsert(List<SysDict> parameter) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer delete(Object record) throws Exception {
		return baseDao.delete(PREFIX + "deleteByPrimaryKey", record);
	}

	@Override
	public Integer batchUpdate(List<SysDict> parameter) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer update(SysDict record) throws Exception {
		return baseDao.update(PREFIX + "updateByPrimaryKey", record);
	}

	@Override
	public Integer batchDelete(List<Object> parameter) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SysDict> findForJdbcParam(SysDict record) throws Exception {
		return (List<SysDict>) baseDao.findListForJdbcParam(PREFIX + "findOneForJdbc", record);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCountForJdbcParam(SysDict record) throws Exception {
		return baseDao.getCountForJdbcParam(PREFIX + "getCountForJdbcParam", record);
	}

	@Override
	@Transactional(readOnly = true)
	public SysDict findOneForJdbc(SysDict record) throws Exception {
		return (SysDict) baseDao.findOneForJdbcParam(PREFIX + "findOneForJdbc", record);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SysDict> findAllList(SysDict dict) throws Exception {
		return (List<SysDict>) baseDao.findListForJdbcParam(PREFIX + "findAllList", dict);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findTypeList(SysDict dict) throws Exception {
		return null;
	}
}
