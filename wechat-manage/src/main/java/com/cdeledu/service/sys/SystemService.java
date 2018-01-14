package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.util.database.model.TableProperty;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统业务处理接口
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年6月1日 下午4:58:15
 * @版本: V2.2
 * @since: JDK 1.7
 */
public interface SystemService {
	/**
	 * @方法: 日志添加
	 * @创建人:独泪了无痕
	 */
	void addLog(SysLogEntity syslog);

	/**
	 * @方法: 登录\退出日志
	 * @创建人:独泪了无痕
	 */
	void addLoginLog(SysLoginLog loginLog);

	/** 获取数据库 表 */
	List<TableProperty> getTablesList();

	/** 查看表结构 */
	String getTableFrameWork(String tableName);
}
