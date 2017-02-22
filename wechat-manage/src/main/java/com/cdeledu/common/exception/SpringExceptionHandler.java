package com.cdeledu.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @类描述: spring mvc异常捕获类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午9:05:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SpringExceptionHandler implements HandlerExceptionResolver {
	/** ----------------------------------------------------- Fields start */
	private static final Logger logger = Logger.getLogger(SpringExceptionHandler.class);

	/** ----------------------------------------------------- Fields end */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception exception) {
		String exceptionMessage = ExceptionHelper.getExceptionMessage(exception);
		logger.error(exceptionMessage); //把漏网的异常信息记入日志  
		System.out.println("exception name: " + exception.getClass().toString());// 异常名
		System.out.println("exception cause: " + exception.getCause());
		System.out.println("exception msg: " + exception.getLocalizedMessage());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exceptionMessage", exceptionMessage);
		model.put("ex", exception);
		return new ModelAndView("common/error", model);
	}

}
