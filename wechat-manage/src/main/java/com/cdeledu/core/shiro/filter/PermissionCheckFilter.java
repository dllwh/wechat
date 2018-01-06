package com.cdeledu.core.shiro.filter;

import com.cdeledu.core.factory.ConstantFactory;
import com.cdeledu.core.shiro.service.ShiroService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 权限检查工厂
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午5:34:07
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PermissionCheckFilter {
	/** ----------------------------------------------------- Fields start */
	private static ShiroService shiroService = ConstantFactory.shiroService;

	/** ----------------------------------------------------- Fields end */

	public static boolean check(Object[] permissions) {
		return shiroService.check(permissions);
	}

	public static boolean checkAll() {
		return shiroService.checkAll();
	}
}
