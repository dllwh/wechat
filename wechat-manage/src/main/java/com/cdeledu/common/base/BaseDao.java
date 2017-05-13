package com.cdeledu.common.base;

import java.util.List;

public interface BaseDao {

	/**
	 * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 */
	public Integer insert(String statement, Object parameter) throws Exception;

	/**
	 * 根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
	 */
	public Integer delete(String statement, Object parameter) throws Exception;

	/**
	 * 根据主键进行更新,这里最多只会更新一条数据
	 */
	public Integer update(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述: 批量插入
	 */
	public Integer batchInsert(String statement, List<Object> parameter) throws Exception;

	/**
	 * @方法描述: 批量更新
	 */
	public Integer batchUpdate(String statement, List<Object> parameter) throws Exception;

	/**
	 * @方法描述: 批量删除
	 */
	public Integer batchDelete(String statement, List<Object> parameter) throws Exception;

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Object findOneForJdbc(String statement, Object parameters) throws Exception;

	/**
	 * @方法描述: @方法描述: 查找对象封装成Map
	 * @param statement
	 *            执行的语句
	 * @param obj
	 *            参数
	 * @param key
	 *            所返集合的key;此名字与查询语句中的列名对应,表示已那个字段作为key
	 * @param value
	 *            所返集合的value,此名字与查询语句中的列名对应,表示已那个字段作为value
	 * @return
	 * @throws Exception
	 */
	public Object findOneForMap(String statement, Object obj, String key, String value)
			throws Exception;

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	public List<Object> findForJdbcParam(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述: 查找对象
	 */
	public Object findForList(String statement, Object obj) throws Exception;

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 */
	public Integer getCountForJdbcParam(String statement, Object parameter) throws Exception;

}
