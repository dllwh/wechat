package com.cdeledu.core.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 处理service层的异常
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月1日 上午2:16:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Aspect
@Component
public class ServiceExceptionAspect {
	/** ----------------------------------------------------- Fields start */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** ----------------------------------------------------- Fields end */
	@Pointcut("@within(org.springframework.stereotype.Service)")
	private void servicePointcut() {
	}

	/**
	 * @方法:拦截service层异常，记录异常日志，目前只拦截Exception，是否要拦截Error需再做考虑
	 * @创建人:独泪了无痕
	 * @param point
	 * @param e
	 */
	@AfterThrowing(pointcut = "servicePointcut()", throwing = "e")
	public void handle(JoinPoint point, Exception e) {
		String errorMsg = StringUtils.isEmpty(e.getMessage()) ? "service层异常异常" : e.getMessage();
		if (logger.isDebugEnabled()) {
			logger.error(errorMsg);
		}
	}
}
