package com.cdeledu.util;

import java.util.List;
import java.util.Map;

import com.cdeledu.core.factory.ConstantFactory;
import com.cdeledu.service.sys.CountService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 相关统计帮助类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月1日 下午10:11:09
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CountHelper {
	/** ----------------------------------------------------- Fields start */
	private static CountService countService = ConstantFactory.countService;
	/** ----------------------------------------------------- Fields end */

	/**
	 * 统计用户
	 */
	public static Integer countUserTotal() {
		return countService.countUserTotal();
	}

	/**
	 * 统计登录记录数
	 */
	public static Integer countLoginLogTotal() {
		return countService.countLoginLogTotal();
	}

	/**
	 * 统计操作记录数
	 */
	public static Integer countOperateLogTotal() {
		return countService.countOperateLogTotal();
	}

	public static List<Map<String, Object>> getCurrentYearOperateLogCountByMonth() {
		return countService.getOperateLogCountByMonth(null);
	}

	public static List<Map<String, Object>> getOperateLogCountByMonth(Integer year) {
		return countService.getOperateLogCountByMonth(year);
	}
}
