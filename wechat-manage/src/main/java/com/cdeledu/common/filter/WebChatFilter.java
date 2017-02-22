package com.cdeledu.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

/**
 * @类描述: 请求拦截
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月29日 下午3:02:50
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WebChatFilter implements Filter{
	 private final Logger logger = Logger.getLogger(WebChatFilter.class);
	public void destroy() {
		logger.info("WebChatFilter已经销毁");
	}

	public void doFilter(ServletRequest request , ServletResponse response , FilterChain filterChain)
			throws IOException, ServletException {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("WebChatFilter已经启动！");
	}
	
}
