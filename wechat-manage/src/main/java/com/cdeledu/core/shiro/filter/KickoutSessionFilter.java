package com.cdeledu.core.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.core.shiro.token.ShiroHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:相同帐号登录控制
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月10日 下午11:30:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class KickoutSessionFilter extends AccessControlFilter {
	/** ----------------------------------------------------- Fields start */
	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	// 静态注入
	static String kickoutUrl;
	// 在线用户
	final static String ONLINE_USER = KickoutSessionFilter.class.getCanonicalName()
			+ "_online_user";
	// 踢出状态，true标示踢出
	final static String KICKOUT_STATUS = KickoutSessionFilter.class.getCanonicalName()
			+ "_kickout_status";

	/** ----------------------------------------------------- Fields end */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws Exception {
		/**
		 * 1.判断当前用户是否已经踢出
		 * <ul>
		 * <li>1.1 如果是Ajax 访问,那么给予json返回值提示</li>
		 * <li>1.2 如果是普通请求，直接跳转到登录页</li>
		 * </ul>
		 */
		
		if(FilterHelper.isAjax(request)){
			
		}
		/**
		 * 2. 处理当前授权用户的session
		 * <ul>
		 * <li>2.1 	如果已经包含当前Session，并且是同一个用户，跳过。</li>
		 * <li>2.2	如果用户ID相同，Session不相同，那么就要处理了</li>
		 * <li>2.2.1	获取到原来的的session，并且标记为提出 </li>
		 * <li>2.2.2	更新session </li>
		 * </ul>
		 */
		return Boolean.TRUE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		ShiroHelper.logout();
		WebUtils.getSavedRequest(request);
		// 再重定向
		WebUtils.issueRedirect(request, response, kickoutUrl);
		return Boolean.FALSE;
	}

	public void setKickoutUrl(String kickoutUrl) {
		KickoutSessionFilter.kickoutUrl = kickoutUrl;
	}
}
