package com.cdeledu.core.aspect;

import java.lang.reflect.UndeclaredThrowableException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:36:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/** ----------------------------------------------------- Fields start */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** ----------------------------------------------------- Fields end */
	/**
	 * 
	 * @方法描述 : 用户未登录
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void unAuth(AuthenticationException e) {
		if (logger.isDebugEnabled()) {
			logger.error("用户未登陆：", e);
		}
	}

	/**
	 * @方法描述 : 账号被冻结
	 * @param e
	 */
	@ExceptionHandler(DisabledAccountException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void accountLocked(AuthenticationException e) {
		if (logger.isDebugEnabled()) {
			logger.error("账号被冻结：", e);
		}
	}

	/**
	 * @方法描述 : 账号密码错误
	 * @param e
	 */
	@ExceptionHandler(CredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void credentials(AuthenticationException e) {
		if (logger.isDebugEnabled()) {
			logger.error("账号密码错误：", e);
		}
	}

	/**
	 * @方法描述 : 无权访问该资源
	 * @param e
	 */
	@ExceptionHandler(UndeclaredThrowableException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void credentials(UndeclaredThrowableException e) {
		if (logger.isDebugEnabled()) {
			logger.error("无权访问该资源：", e);
		}
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public void notFount(RuntimeException e) {
		if (logger.isDebugEnabled()) {
			logger.error("服务器未知运行时异常:", e);
		}
	}

}
