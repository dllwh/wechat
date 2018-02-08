package com.cdeledu.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cdeledu.common.plugs.bootstrap.ZtreeNode;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.system.SysArea;
import com.cdeledu.service.sys.SysAreaService;
import com.google.common.collect.Lists;

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
	public List<ZtreeNode> getSysAreaTree(int parentId) {
		List<ZtreeNode> resultList = Lists.newArrayList();
		List<SysArea> rootList = null;
		try {
			rootList = getArealistByParentCode(parentId);
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}
		ZtreeNode node = null;
		if (CollectionUtils.isNotEmpty(rootList)) {
			for (SysArea sysArea : rootList) {
				node = new ZtreeNode();
				node.setpId(parentId);
				node.setId(sysArea.getId());
				node.setName(sysArea.getAreaName());
				if (hasChildren(sysArea.getId())) {
					node.setParent("true");
					node.setOpen(true);
				}
				resultList.add(node);
			}
		}
		return resultList;
	}

	@Override
	public List<SysArea> getArealistByParentCode(int parentId) throws Exception {
		return (List<SysArea>) baseDao.findListForJdbcParam(prefix + "getArealistByParentCode",
				parentId);
	}

	@Override
	public boolean hasChildren(int parentId) {
		SysArea sysArea = new SysArea();
		sysArea.setParentId(parentId);
		try {
			return getCountForJdbcParam(sysArea) > 0 ? true : false;
		} catch (Exception e) {
			return true;
		}
	}
}
