package com.cdeledu.core.aspect;

import org.apache.commons.lang3.StringUtils;
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
 * @类描述: web异常切面
 * 
 *       <pre>
 *  默认spring aop不会拦截controller层，使用该类需要在spring公共配置文件中注入改bean，
 * 另外需要配置<aop:aspectj-autoproxy proxy-target-class="true"/>
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月1日 上午2:12:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Aspect
@Component
public class WebExceptionAspect {
	/** ----------------------------------------------------- Fields start */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** ----------------------------------------------------- Fields end */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void webPointcut() {

	}

	/**
	 * @方法:拦截web层异常，记录异常日志,目前只拦截Exception，是否要拦截Error需再做考虑
	 * @创建人:独泪了无痕
	 * @param e
	 */
	@AfterThrowing(pointcut = "webPointcut()", throwing = "e")
	public void handleThrowing(Exception e) {
		String errorMsg = StringUtils.isEmpty(e.getMessage()) ? "系统异常" : e.getMessage();
		if (logger.isDebugEnabled()) {
			logger.error(errorMsg);
		}
	}
}
