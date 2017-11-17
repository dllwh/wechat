package com.cdeledu.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.cdeledu.common.base.BaseClass;

/**
 * @类描述: 处理从页面传递中文到后台而出现的中文乱码问题
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月18日 下午5:21:39
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
@WebFilter(filterName = "encodingFilter", urlPatterns = { "*.shtml" })
public class EncodingFilter extends BaseClass implements Filter {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("过滤器配置信息。");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
