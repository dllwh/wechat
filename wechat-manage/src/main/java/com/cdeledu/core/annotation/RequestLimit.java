package com.cdeledu.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/// 高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
	/*
	 * 请求次数限制
	 */
	long requestCount() default 1L;

	/*
	 * 请求限制单位时间 /s
	 */
	long requestTime() default 60L;
}
