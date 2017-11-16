package com.cdeledu.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cdeledu.common.constants.SystemConstant.SysOpType;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 自定义注解：系统日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 下午3:08:11
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	/** ----------------------------------------------------- Fields start */
	/** 日志描述,要执行的具体操作 */
	String desc() default "";

	/** 要执行的操作类型 */
	SysOpType opType();

	/** 操作表类型 */
	String[] tableName() default {};
	/** ----------------------------------------------------- Fields end */
}
