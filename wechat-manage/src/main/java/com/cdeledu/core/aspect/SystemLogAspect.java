package com.cdeledu.core.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.service.sys.SystemService;
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

	@Autowired
	private SystemService sysLogManager;

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
			saveSysLog(point, time, null, result);
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
			saveSysLog(joinPoint, -1L, e, null);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void saveSysLog(JoinPoint joinPoint, long time, Throwable throwable, Object opResult)
			throws Exception  {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		SystemLog systemLog = method.getAnnotation(SystemLog.class);
		if (systemLog != null) {
			sysLog.setRemark(systemLog.desc());
			sysLog.setTableName(StringUtils.join(systemLog.tableName(), ","));
			sysLog.setOpType(systemLog.opType().getValue());
		}
		// 获取request
		HttpServletRequest request = WebUtilHelper.getHttpServletRequest();

		// 请求方法名
		String targetName = joinPoint.getTarget().getClass().getName();// 获取目标类名
		sysLog.setMethod(targetName + "." + signature.getName() + "()");
		//访问目标方法的参数：
		sysLog.setParams(JsonMapper.toJsonString(joinPoint.getArgs()));

		// 操作人的信息
		int userId = -1;
		if (ShiroHelper.isLogin()) {
			userId = WebUtilHelper.getCurrentUserId();
		}
		sysLog.setUserCode(userId);
		// 登录的IP地址
		sysLog.setIpAddress(IpUtilHelper.getClientIP(request));
		// 浏览器
		String userAgent = "";
		try {
			userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser()
					.getName();
		} catch (Exception ex) {

		}

		if (opResult != null) {
			sysLog.setOpResult(JsonMapper.toJsonString(opResult));
		}
		if (throwable != null) {
			sysLog.setLogType(-1);
			sysLog.setExceptionCode(sysLog.getClass().getName());
			sysLog.setExceptionDetail(throwable.getMessage());
		}
		sysLog.setBroswer(userAgent);
		sysLog.setTime(time);
		sysLogManager.addLog(sysLog);
	}
}
