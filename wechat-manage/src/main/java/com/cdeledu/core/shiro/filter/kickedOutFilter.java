package com.cdeledu.core.shiro.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.core.shiro.session.SessionStatus;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.util.WebUtilHelper;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 判断是否踢出
 * @创建者: 独泪了无痕
 * @创建时间: 2017年11月7日 下午2:28:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class kickedOutFilter extends AccessControlFilter {
	/** ----------------------------------------------------- Fields start */
	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** ----------------------------------------------------- Fields end */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws Exception {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		String url = httpRequest.getRequestURI();
		if (url.startsWith("/loginController/")) {
			return Boolean.TRUE;
		}
		HttpSession session = WebUtilHelper.getSession();
		SessionStatus sessionStatus = (SessionStatus) session
				.getAttribute(GlobalConstants.SESSION_STATUS);
		if (sessionStatus != null && sessionStatus.isOnlineStatus()) {
			if (FilterHelper.isAjax(request)) {
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("user_status", "300");
				resultMap.put("message", "您已经被踢出，请重新登录！");
				FilterHelper.out(response, resultMap);
			}
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		// 先退出
		ShiroHelper.logout();
		saveRequest(request);
		// 再重定向
		WebUtils.issueRedirect(request, response, FilterHelper.KICKED_OUT);
		return false;
	}
}
