package com.cdeledu.service.log;

import com.cdeledu.model.system.SysLoginLog;

/**
 * @类描述: 登录日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月12日 下午3:59:37
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface LoginLogService {

	/**
	 * @方法: 登录\退出日志
	 * @创建人:独泪了无痕
	 */
	void addLoginLog(SysLoginLog loginLog);
}
