package com.cdeledu.core.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.core.factory.LogTaskFactory;
import com.cdeledu.core.log.LogManager;
import com.cdeledu.util.WebUtilHelper;
import com.cdeledu.util.network.IpUtilHelper;

import nl.bitwalker.useragentutils.UserAgent;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统日志，切面处理类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 下午3:13:07
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Aspect
@Component
public class SystemLogAspect extends BaseClass {

	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: Controoler切入点
	 */
	@Pointcut("@annotation(com.cdeledu.core.annotation.SystemLog)")
	public void controllerlogPointCut() {

	}

	/**
	 * @方法描述: 前置通知，用于拦截记录用户的操作
	 */
	@Before("controllerlogPointCut()")
	public void doBefore(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("=========执行前置通知===============");
		}
	}

	/**
	 * @方法描述: 配置环绕通知,使用在方法aspect()上注册的切入点
	 */
	@Around("controllerlogPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("==========开始执行环绕通知===============");
		}
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		try {
			HttpServletRequest request = WebUtilHelper.getHttpServletRequest();
			String ip = IpUtilHelper.getClientIP(request);
			String browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser()
					.getName();
			LogManager.getInstance()
					.executeLog(LogTaskFactory.operateLog(point, time, null, result,ip,browser));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("==========结束执行环绕通知===============" + result);
		}
		return result;
	}

	/**
	 * @方法描述: 后置通知 用于拦截层记录用户的操作
	 */
	@After("controllerlogPointCut()")
	public void after(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("=========执行后置通知===============");
		}
	}

	/**
	 * @方法描述: 标注该方法体为后置通知,当目标方法执行成功后执行该方法体,使用在方法aspect()上注册的切入点
	 */
	@AfterReturning("controllerlogPointCut()")
	public void afterReturn(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("=========执行后置返回通知===============");
		}
	}

	/**
	 * @方法描述:标注该方法体为异常通知，当目标方法出现异常时，执行该方法体 <br>
	 *                                     用于拦截记录异常日志
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerlogPointCut()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		if (logger.isDebugEnabled()) {
			logger.debug("=========执行异常通知===============");
		}
		try {
			HttpServletRequest request = WebUtilHelper.getHttpServletRequest();
			String ip = IpUtilHelper.getClientIP(request);
			String browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser()
					.getName();
			LogManager.getInstance().executeLog(LogTaskFactory.operateLog(joinPoint, 0, e, null,ip,browser));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
