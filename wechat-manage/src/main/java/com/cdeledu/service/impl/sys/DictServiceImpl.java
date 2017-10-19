package com.cdeledu.service.impl.sys;

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
public class DictServiceImpl implements DictService {
	@Resource
	private BaseDaoSupport<?> baseDao;
	private final static String prefix = "sysDictDaoImpl.";

	@Transactional(readOnly = false)
	public Integer insert(SysDict record) throws Exception {
		return baseDao.insert(prefix + "insertSelective", record);
	}

	public Integer batchInsert(List<SysDict> parameter) throws Exception {
		return null;
	}

	@Transactional(readOnly = false)
	public Integer delete(Object record) throws Exception {
		return baseDao.delete(prefix + "deleteByPrimaryKey", record);
	}

	public Integer batchUpdate(List<SysDict> parameter) throws Exception {
		return null;
	}

	@Transactional(readOnly = false)
	public Integer update(SysDict record) throws Exception {
		return baseDao.update(prefix + "updateByPrimaryKey", record);
	}

	public Integer batchDelete(List<SysDict> parameter) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SysDict> findForJdbc(SysDict record) throws Exception {
		return (List<SysDict>) baseDao.findListForJdbcParam(prefix + "findOneForJdbc", record);
	}

	@Transactional(readOnly = true)
	public Integer getCountForJdbcParam(SysDict record) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "getCountForJdbcParam", record);
	}

	@Transactional(readOnly = true)
	public SysDict findOneForJdbc(SysDict record) throws Exception {
		return (SysDict) baseDao.findOneForJdbcParam(prefix + "findOneForJdbc", record);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SysDict> findAllList(SysDict dict) throws Exception {
		return (List<SysDict>) baseDao.findListForJdbcParam(prefix + "findAllList", dict);
	}

	@Transactional(readOnly = true)
	public List<String> findTypeList(SysDict dict) throws Exception {
		return null;
	}
}
