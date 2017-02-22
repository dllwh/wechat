package com.cdeledu.common.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cdeledu.common.constants.MongoConstants;
import com.mongodb.BasicDBObject;

/**
 * @类描述: 操作日志。存储在mongodb中
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年1月18日 上午8:00:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WebOperateFilter implements Filter {
	private static final Logger logger = Logger.getLogger(WebOperateFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("Access Auth Interceptor - 进入过滤器");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String operaterUrl = httpRequest.getRequestURI();

		StringBuffer params = new StringBuffer();
		Map<String, String[]> parameterMap = httpRequest.getParameterMap();
		for (Entry<String, String[]> parameter : parameterMap.entrySet()) {
			params.append(parameter.getKey() + "=" + parameter.getValue()[0].toString() + "&");
		}

		BasicDBObject dbb = new BasicDBObject();
		dbb.put("url", operaterUrl);
		dbb.put("param", params.toString());
		MongoConstants.mongoQueue.add(dbb);

	}

	@Override
	public void destroy() {

	}

}
