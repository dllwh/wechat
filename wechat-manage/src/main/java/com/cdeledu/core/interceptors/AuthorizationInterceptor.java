/**
 * 
 */
package com.cdeledu.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.model.api.token.TokenEntity;
import com.cdeledu.service.api.token.TokenService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 权限(Token)验证
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月28日 下午5:20:07
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Component
public class AuthorizationInterceptor extends BaseClass implements HandlerInterceptor {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TokenService tokenService;

	/** ----------------------------------------------------- Fields end */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// 从header中获取token
		String token = request.getHeader("token");
		// 如果header中不存在token，则从参数中获取token
		if (StringUtils.isBlank(token)) {
			token = request.getParameter("token");
		}
		// 查询token信息
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		if (tokenEntity == null
				|| tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
			if (logger.isDebugEnabled()) {
				logger.error("------------------------------token失效");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}
}
