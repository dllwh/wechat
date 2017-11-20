package com.cdeledu.core.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.common.constants.FilterHelper;

@WebFilter(filterName = "sqlFilter", urlPatterns = { "*.shtml" })
public class SQLFilter extends BaseClass implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获得所有请求参数名
		Enumeration<String> params = request.getParameterNames();

		String sql = "";
		while (params.hasMoreElements()) {
			// 得到参数名
			String name = (String) params.nextElement();
			// 得到参数对应值
			String[] value = request.getParameterValues(name);
			for (String string : value) {
				sql = sql + string;
			}
		}

		if (sqlValidate(sql)) {
			throw new IOException("您发送请求中的参数中含有非法字符");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	/**
	 * @方法描述: 校验
	 * @param sql
	 * @return
	 */
	public static boolean sqlValidate(String sql) {
		if (StringUtils.isBlank(sql)) {
			return false;
		}
		// 统一转为小写
		sql = sql.toLowerCase().trim();
		// 判断是否包含非法字符
		for (String keyword : FilterHelper.keywords) {
			if (sql.indexOf(keyword) != -1) {
				return true;
			}
		}
		return false;
	}
}
