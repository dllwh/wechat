package com.cdeledu.service.sys.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.service.sys.SystemService;
import com.cdeledu.util.database.model.TableProperty;

@Service("systemService")
@Transactional(readOnly = false)
@SuppressWarnings("unchecked")
public class SystemServiceImpl extends BaseClass implements SystemService {

	private static final long serialVersionUID = 1L;
	@Resource
	private BaseDaoSupport<?> baseDao;
	private static final String sysTable = "com.cdeledu.model.system.SysLogEntity.";

	@Override
	public List<TableProperty> getTablesList() {
		try {
			return (List<TableProperty>) baseDao.findListForJdbcParam(sysTable + "getTablesList");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getTableFrameWork(String tableName) {
		Map<String, Object> tableFrameWork = null;
		try {
			tableFrameWork = (Map<String, Object>) baseDao
					.findListForJdbcParam(sysTable + "getTableFrameWork", tableName);
			if (tableFrameWork.containsKey("Create Table")) {
				return tableFrameWork.get("Create Table").toString();
			}
		} catch (Exception e) {

		}
		return "";
	}
}
