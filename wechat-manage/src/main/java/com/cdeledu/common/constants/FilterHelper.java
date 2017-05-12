package com.cdeledu.common.constants;

import javax.servlet.http.HttpServletRequest;

public class FilterHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * 当前URI是否需要登录才能访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isURILogin(HttpServletRequest request) {
		String path = request.getServletPath();
		if (path.matches(GlobalConstants.INHERENT_PATH)) {
			return true;
		}
		return false;
	}
}
