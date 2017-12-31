package com.cdeledu.core.aspect;

import java.lang.reflect.Method;

import javax.naming.NoPermissionException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.cdeledu.core.annotation.Permission;
import com.cdeledu.core.factory.ConstantFactory;
import com.cdeledu.core.shiro.service.ShiroService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: AOP 权限自定义检查
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年12月31日 下午10:26:44
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
@Aspect
@Component
public class PermissionAspect {
	/** ----------------------------------------------------- Fields start */
	private ShiroService shiroService = ConstantFactory.shiroService;
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */

	@Around("cutPermission()")
	public Object doPermission(ProceedingJoinPoint point) throws Throwable {
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		Permission permission = method.getAnnotation(Permission.class);
		Object[] permissions = permission.value();
		boolean checkResult = Boolean.FALSE;
		if (permissions == null || permissions.length == 0) {
			// 检查全体角色 checkAll
			checkResult = shiroService.checkAll();
		} else {
			// 检查指定角色 check
			checkResult = shiroService.check(permissions);
		}
		if (checkResult) {
			return point.proceed();
		} else {
			throw new NoPermissionException();
		}
	}

	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	@Pointcut(value = "@annotation(com.cdeledu.core.annotation.Permission)")
	private void cutPermission() {

	}
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
