package com.cdeledu.service.log;

import java.util.List;

import com.cdeledu.model.system.SysLogEntity;

/**
 * @类描述: 操作日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月12日 下午4:00:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface OperateLogService {
	/**
	 * @方法: 日志添加
	 * @创建人:独泪了无痕
	 */
	void addLog(SysLogEntity syslog);

	Integer getSysLogCount(SysLogEntity sysLogEntity);

	/** 获取操作数据 */
	List<SysLogEntity> getSysLog(SysLogEntity sysLogEntity) throws Exception;
}
