package com.cdeledu.core.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.cdeledu.core.annotation.RequestLimit;

@Aspect
@Component
public class RequestAspect {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(RequestAspect.class);
	private static Map<String, Integer> map = new HashMap<String, Integer>();

	/** ----------------------------------------------------- Fields end */
	@Pointcut("@annotation(com.cdeledu.core.annotation.RequestLimit)")
	public void controllerAspect() {

	}

	@Around("controllerAspect()")
	public JSONObject doAround(ProceedingJoinPoint proceedingjoinPoint) throws Exception {
		// System.out.println(">>>>>SysLogAspect环绕通知开始=====");
		return requestLimit(proceedingjoinPoint);
	}

	@After("controllerAspect()")
	public void doAfter() throws Exception {
		// System.out.println("=====SysLogAspect后置通知开始<<<<<");
		// requestLimit(joinPoint);
	}

	@AfterThrowing(value = "controllerAspect()", throwing = "e")
	public void doAfter(JoinPoint joinPoint, Exception e) throws Exception {
		System.out.println("=====SysLogAspect异常通知开始=====");
		// requestLimit(joinPoint);
	}

	private static RequestLimit giveController(ProceedingJoinPoint proceedingjoinPoint)
			throws Exception {
		Signature signature = proceedingjoinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();

		if (method != null) {
			// System.out.println(">>>method不为空");
			return method.getAnnotation(RequestLimit.class);
		}
		return null;
	}

	public JSONObject requestLimit(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
		JSONObject jsonObj = new JSONObject();
		RequestLimit limit = giveController(proceedingJoinPoint);

		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			if (request == null) {
				logger.error("方法中缺失HttpServletRequest参数");
			}
			String ip = request.getLocalAddr();
			String url = request.getRequestURL().toString();

			if (Integer.valueOf(10) > limit.requestCount()) {
				if (logger.isDebugEnabled()) {
					logger.info(">>>用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数["
							+ limit.requestCount() + "]");
				}
				jsonObj.put("limit", true);
			} else {
				jsonObj = (JSONObject) proceedingJoinPoint.proceed();
			}

			if (System.currentTimeMillis() - (long) map.get("time") <= 1000 * 60) {
				logger.info(">>>一分钟之内");
				if (map.get(ip) > limit.requestCount()) {
					logger.info(">>>用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数["
							+ limit.requestCount() + "]");
					jsonObj.put("limit", true);
				}
			} else {
				// 重置访问次数
				map.put("ip", 1);
			}

			if (map.get(ip) > limit.requestCount()) {
				logger.info(">>>用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.requestCount()
						+ "]");
			} else {
				proceedingJoinPoint.proceed();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			
		}
		return jsonObj;
	}
}
