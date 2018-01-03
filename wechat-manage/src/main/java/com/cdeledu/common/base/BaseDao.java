package com.cdeledu.common.base;

import java.util.List;

public interface BaseDao<T> {

	/**
	 * @方法描述:执行SQL:插入记录,返回写入数据的主键
	 */
	Integer insert(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:执行SQL
	 */
	Integer delete(String statement) throws Exception;

	Integer delete(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:执行SQL:更新记录,返回更新记录数
	 */
	Integer update(String statement) throws Exception;

	Integer update(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	Object findOneForJdbcParam(String statement) throws Exception;

	Object findOneForJdbcParam(String statement, Object parameters) throws Exception;

	/**
	 * @方法描述:通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	Object findListForJdbcParam(String statement) throws Exception;

	Object findListForJdbcParam(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 */
	Integer getCountForJdbcParam(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述: @方法描述: 查找对象封装成Map
	 * @param statement
	 *            执行的语句
	 * @param obj
	 *            参数
	 * @param key
	 *            所返集合的key;此名字与查询语句中的列名对应,表示已那个字段作为key
	 * @return
	 * @throws Exception
	 */
	Object findForMap(String statement, Object parameter, String key) throws Exception;

	/**
	 * @方法描述 : 提交
	 */
	void commit();

	void commit(boolean force);

	/**
	 * @方法描述 : 回滚
	 */
	void rollback();

	void rollback(boolean force);

	/**
	 * @方法描述 : 清理缓存，防止溢出
	 */
	void clearCache();
	
	/** 执行SQL */
	Integer executeSql(String statement, List<Object> param);

	/** 执行SQL */
	Integer executeSql(String statement, Object... param);
}
