package com.cdeledu.service.sys.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.service.sys.CountService;

@Service("countService")
@SuppressWarnings("unchecked")
public class CountServiceImpl implements CountService {
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;
	private final static String PREFIX = "com.cdeledu.dao.impl.sys.CountDaoImpl.";

	/** ----------------------------------------------------- Fields end */
	@Override
	public Integer countUserTotal() {
		try {
			return baseDao.getCountForJdbcParam(PREFIX + "countUserTotal", null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer countLoginLogTotal() {
		try {
			return baseDao.getCountForJdbcParam(PREFIX + "countloginLogTotal", null);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer countOperateLogTotal() {
		try {
			return baseDao.getCountForJdbcParam(PREFIX + "countOperateLogTotal", null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getOperateLogCountByMonth(Integer year) {
		try {
			return (List<Map<String, Object>>) baseDao.findListForJdbcParam(PREFIX+"getOperateLogCountByMonth", year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
