//package com.cdeledu.core.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import com.cdeledu.common.base.BaseClass;
//
///**
// * 把今天最好的表现当作明天最新的起点．．～
// *
// * Today the best performance as tomorrow newest starter!
// *
// * @类描述: Shiro切面处理类
// * @创建者: 独泪了无痕--duleilewuhen@sina.com
// * @创建时间: 2018年1月31日 下午10:24:50
// * @版本: V1.0
// * @since: JDK 1.7
// */
//@Aspect
//@Component
//public class ShiroAspect extends BaseClass {
//	/** ----------------------------------------------------- Fields start */
//	private static final long serialVersionUID = 1L;
//
//	/** ----------------------------------------------------- Fields end */
//	public Object around(ProceedingJoinPoint point) throws Throwable {
//		Object result = null;
//		try {
//			result = point.proceed();
//		} catch (Exception e) {
//			logger.error("redis error", e);
//		}
//		return result;
//	}
//}
