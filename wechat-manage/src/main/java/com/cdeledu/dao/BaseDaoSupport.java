package com.cdeledu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cdeledu.common.base.BaseDao;

@Repository
public class BaseDaoSupport<T> implements BaseDao<T> {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Integer insert(String statement, Object parameter) {
		return sqlSessionTemplate.insert(statement, parameter);
	}

	@Override
	public Integer delete(String statement) {
		return sqlSessionTemplate.delete(statement);
	}

	@Override
	public Integer delete(String statement, Object parameter) {
		return sqlSessionTemplate.delete(statement, parameter);
	}

	@Override
	public Integer update(String statement) {
		return sqlSessionTemplate.update(statement);
	}

	@Override
	public Integer update(String statement, Object parameter) {
		return sqlSessionTemplate.update(statement, parameter);
	}

	@Override
	public Object findOneForJdbcParam(String statement) {
		return sqlSessionTemplate.selectOne(statement);
	}

	@Override
	public Object findOneForJdbcParam(String statement, Object parameter) {
		return sqlSessionTemplate.selectOne(statement, parameter);
	}

	@Override
	public Object findListForJdbcParam(String statement) {
		return sqlSessionTemplate.selectList(statement);
	}

	@Override
	public Object findListForJdbcParam(String statement, Object parameter) {
		return sqlSessionTemplate.selectList(statement, parameter);
	}

	@Override
	public Integer getCountForJdbcParam(String statement, Object parameter) {
		return (Integer) sqlSessionTemplate.selectOne(statement, parameter);
	}

	@Override
	public Object findForMap(String statement, Object parameter, String key) {
		return sqlSessionTemplate.selectMap(statement, parameter, key);
	}

	@Override
	public void commit() {
		sqlSessionTemplate.commit();
	}

	@Override
	public void commit(boolean force) {
		sqlSessionTemplate.commit(force);
	}

	@Override
	public void rollback() {
		sqlSessionTemplate.rollback();
	}

	@Override
	public void rollback(boolean force) {
		sqlSessionTemplate.rollback(force);
	}

	@Override
	public void clearCache() {
		sqlSessionTemplate.clearCache();
	}

	@Override
	public Integer executeSql(String statement, List<Object> parameter) {
		return sqlSessionTemplate.update(statement, parameter);
	}

	@Override
	public Integer executeSql(String statement, Object... parameter) {
		return sqlSessionTemplate.update(statement, parameter);
	}
}
