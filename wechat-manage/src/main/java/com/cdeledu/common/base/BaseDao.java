package com.cdeledu.common.base;

public interface BaseDao<T> {

	/**
	 * @方法描述:执行SQL
	 */
	public Integer insert(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:执行SQL
	 */
	public Integer delete(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:执行SQL
	 */
	public Integer update(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Object findOneForJdbcParam(String statement, Object parameters) throws Exception;

	/**
	 * @方法描述:通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	public Object findListForJdbcParam(String statement, Object parameter) throws Exception;

	/**
	 * @方法描述:使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 */
	public Integer getCountForJdbcParam(String statement, Object parameter) throws Exception;

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
	public Object findForMap(String statement, Object parameter, String key) throws Exception;

}
