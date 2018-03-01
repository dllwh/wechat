package com.cdeledu.service.sys;

import java.util.List;
import java.util.Map;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 相关统计
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月1日 下午10:23:05
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface CountService {
	/** 统计管理员个数 */
	Integer countUserTotal();

	/** 统计管理员登录记录数 */
	Integer countLoginLogTotal() ;

	/** 统计管理员 操作日志数 */
	Integer countOperateLogTotal() ;
	
	List<Map<String, Object>> getOperateLogCountByMonth(Integer year);
}
