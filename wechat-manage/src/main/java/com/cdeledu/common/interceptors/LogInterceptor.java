package com.cdeledu.common.interceptors;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.util.LogUtils;
import com.cdeledu.util.apache.lang.DateUtilHelper;

/**
 * @类描述: 日志拦截器
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 下午12:13:43
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LogInterceptor extends BaseClass implements HandlerInterceptor {
	private static final long serialVersionUID = 1L;
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
			"ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			long beginTime = System.currentTimeMillis();// 1、开始时间
			startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
			logger.debug("开始计时: {}  URI: {}",
					new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime),
					request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		// 保存日志
		LogUtils.saveLog(request, handler, ex, null);
		// 打印JVM信息。
		if (logger.isDebugEnabled()) {
			long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
			long endTime = System.currentTimeMillis(); // 2、结束时间
			String msg = String.format(
					"计时结束：%s  耗时：%s  URI: %s  最大内存: %sm  已分配内存: %sm  已分配内存中的剩余空间: %sm  最大可用内存: %sm",
					new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
					DateUtilHelper.formatDateTime(endTime - beginTime), request.getRequestURI(),
					Runtime.getRuntime().maxMemory() / 1024 / 1024,
					Runtime.getRuntime().freeMemory() / 1024 / 1024,
					(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
							+ Runtime.getRuntime().freeMemory()) / 1024 / 1024);
			logger.debug(msg);
			// 删除线程变量中的数据，防止内存泄漏
			startTimeThreadLocal.remove();
		}
	}

}
