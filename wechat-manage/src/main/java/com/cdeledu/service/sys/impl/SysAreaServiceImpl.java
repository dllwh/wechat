package com.cdeledu.service.sys.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysArea;
import com.cdeledu.service.sys.SysAreaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("sysAreaService")
@SuppressWarnings("unchecked")
public class SysAreaServiceImpl implements SysAreaService {
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;
	private final static String prefix = "com.cdeledu.dao.impl.sys.SysDictAreaDaoImpl.";

	/** ----------------------------------------------------- Fields end */
	@Override
	public Integer insert(SysArea record) throws Exception {
		return baseDao.insert(prefix + "insertSelective", record);
	}

	@Override
	public Integer batchInsert(List<SysArea> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer delete(Object record) throws Exception {
		return baseDao.delete(prefix + "deleteByPrimaryKey", record);
	}

	@Override
	public Integer batchDelete(List<Object> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer update(SysArea record) throws Exception {
		return baseDao.update(prefix + "updateByPrimaryKeySelective", record);
	}

	@Override
	public Integer batchUpdate(List<SysArea> parameter) throws Exception {
		return null;
	}

	@Override
	public List<SysArea> findForJdbcParam(SysArea record) throws Exception {
		return (List<SysArea>) baseDao.findListForJdbcParam(prefix + "findListForJdbcParam",
				record);
	}

	@Override
	public Integer getCountForJdbcParam(SysArea record) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "getCountForJdbcParam", record);
	}

	@Override
	public SysArea findOneForJdbc(SysArea record) throws Exception {
		return (SysArea) baseDao.findOneForJdbcParam(prefix + "findOneForJdbc", record);
	}

	@Override
	public List<Map<String, Object>> getSysAreaTree() throws Exception {
		return getSysArearTreeList(100000);
	}

	private List<Map<String, Object>> getSysArearTreeList(int parentId) throws Exception{
		List<SysArea> rootList = getArealistByParentCode(parentId);
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Map<String, Object> map = null;
		int parentCode = 0;
		for (SysArea sysArea : rootList) {
			map = Maps.newConcurrentMap();
			parentCode = sysArea.getId();
			map.put("id", parentCode);
			map.put("text", sysArea.getAreaName());
			if(hasChildren(parentCode)){
				map.put("state", "closed");
				map.put("children", getSysArearTreeList(parentCode));
			} else {
				map.put("state", "open");
			}
			resultList.add(map);
		}
		return resultList;
		
	}
	@Override
	public List<SysArea> getArealistByParentCode(int parentId) throws Exception {
		return (List<SysArea>) baseDao.findListForJdbcParam(prefix+"getArealistByParentCode", parentId);
	}

	@Override
	public boolean hasChildren(int parentId) throws Exception {
		SysArea sysArea = new SysArea();
		sysArea.setParentId(parentId);
		return getCountForJdbcParam(sysArea) > 0 ? true : false;
	}
}
