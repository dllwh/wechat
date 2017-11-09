package com.cdeledu.service.sys;

import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.model.system.SysLoginLog;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统业务处理接口
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年6月1日 下午4:58:15
 * @版本: V2.0
 * @since: JDK 1.7
 */
public interface SystemService {
	/**
	 * @方法: 日志添加
	 * @创建人:独泪了无痕
	 */
	public void addLog(SysLogEntity syslog) throws Exception;

	/**
	 * @方法: 登录\退出日志
	 * @创建人:独泪了无痕
	 * @param LogContent
	 *            内容
	 * @param loglevel
	 *            级别
	 * @param operatetype
	 *            类型
	 */
	public void addLoginLog(SysLoginLog loginLog) throws Exception;

}
