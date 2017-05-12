package com.cdeledu.common.base;

import java.util.List;

/**
 * @类描述: 基础service类，所有的service业务服务类都继承于这个
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月26日 上午9:48:26
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface BaseService<T> {
	/**
	 * 插入一条数据 <br/>
	 * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)<br/>
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 */
	int insertSelective(T record) throws Exception;
	/**
	 * @方法描述: 批量插入记录
	 * @param record
	 * @return 返回插入记录数
	 * @throws Exception
	 */
	int batchInsert(List<T> record) throws Exception;

	/**
	 * 根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
	 */
	int delete(T record) throws Exception;

	/**
	 * 通过主键进行删除,这里支持批量删除多条数据 <br/>
	 * 单个字段做主键时,可以直接写主键的值 <br/>
	 * 联合主键时,key可以是实体类,也可以是Map
	 */
	int deleteByPrimaryKey(Object key) throws Exception;

	/**
	 * 根据主键进行更新,这里最多只会更新一条数据 <br/>
	 * 参数为实体类
	 */
	int updateByPrimaryKey(T record) throws Exception;

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 */
	List<T> findForJdbc(T record) throws Exception;

	/**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	 */
	int getCountForJdbcParam(T record) throws Exception;

	/**
	 * 根据主键进行查询,必须保证结果唯一<br/>
	 * 单个字段做主键时,可以直接写主键的值<br/>
	 * 联合主键时,key可以是实体类,也可以是Map
	 */
	T findOneForJdbc(T record) throws Exception;
}
