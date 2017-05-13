package com.cdeledu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cdeledu.common.base.BaseDao;

@Repository
public class BaseDaoSupport implements BaseDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	public Integer insert(String statement, Object parameter) throws Exception {
		return sqlSessionTemplate.insert(statement,parameter);
	}

	public Integer delete(String statement, Object parameter) throws Exception {
		return sqlSessionTemplate.delete(statement, parameter);
	}

	public Integer update(String statement, Object parameter) throws Exception {
		return sqlSessionTemplate.update(statement, parameter);
	}

	public Integer batchInsert(String statement, List<Object> parameter) throws Exception {
		return sqlSessionTemplate.insert(statement);
	}

	public Integer batchUpdate(String statement, List<Object> parameter) throws Exception {
		return null;
	}

	public Integer batchDelete(String statement, List<Object> parameter) throws Exception {
		return sqlSessionTemplate.delete(statement, parameter);
	}

	public Object findOneForJdbc(String statement, Object parameter) throws Exception {
		return sqlSessionTemplate.selectOne(statement, parameter);
	}

	public Object findOneForMap(String statement, Object obj, String key, String value)
			throws Exception {
		return sqlSessionTemplate.selectMap(statement, obj, key);
	}

	public List<Object> findForJdbcParam(String statement, Object parameter) throws Exception {
		return sqlSessionTemplate.selectList(statement, parameter);
	}

	public Object findForList(String statement, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(statement, obj);
	}

	public Integer getCountForJdbcParam(String statement, Object parameter) throws Exception {
		return (Integer)sqlSessionTemplate.selectOne(statement, parameter);
	}

}
