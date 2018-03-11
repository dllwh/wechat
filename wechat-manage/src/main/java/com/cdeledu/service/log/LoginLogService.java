package com.cdeledu.service.log;

import java.util.List;

import com.cdeledu.model.system.SysLoginLog;

/**
 * @类描述: 登录日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月12日 下午3:59:37
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface LoginLogService{
	/** 插入一条数据, 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长 */
	Integer insert(SysLoginLog loginLog) throws Exception;
	/** 根据实体类不为null的字段进行查询,条件全部使用=号and条件 */
	List<SysLoginLog> findForJdbcParam(SysLoginLog loginLog) throws Exception;

	/** 根据实体类不为null的字段查询总数,条件全部使用=号and条件 */
	Integer getCountForJdbcParam(SysLoginLog loginLog) throws Exception;
}
