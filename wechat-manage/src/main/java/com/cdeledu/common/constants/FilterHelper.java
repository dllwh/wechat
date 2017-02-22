package com.cdeledu.common.constants;

import javax.servlet.http.HttpServletRequest;

public class FilterHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * 当前URI资源是否需要登录才能访问
	 * 
	 * @param requestURI
	 * @param request
	 * @return
	 */
	public static boolean isURILogin(String requestURI, HttpServletRequest request) {
		for (String uri : GlobalConstants.INHERENT_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}
}
