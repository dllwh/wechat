package com.cdeledu.core.aspect;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cdeledu.common.base.ResponseBean;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * 
 *       <pre>
	ExceptionHandler：统一处理某一类异常，从而能够减少代码重复率和复杂度
	ControllerAdvice：异常集中处理，更好的使业务逻辑与异常处理剥离开
	ResponseStatus：可以将某种异常映射为HTTP状态码
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:36:42
 * @版本: V2.0
 * @since: JDK 1.7
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/** ----------------------------------------------------- Fields start */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** ----------------------------------------------------- Fields end */

	@ExceptionHandler(DuplicateKeyException.class)
	public void handleDuplicateKeyException(DuplicateKeyException e){
		if (logger.isDebugEnabled()) {
			logger.error("数据库中已存在该记录：", e);
		}
	}
	
	/**
	 * @方法描述 : 无权访问该资源
	 * @param e
	 */
	@ExceptionHandler(value = {UndeclaredThrowableException.class,AuthorizationException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void credentials(UndeclaredThrowableException e) {
		if (logger.isDebugEnabled()) {
			logger.error("没有权限，请联系管理员授权：", e);
		}
	}

	/**
	 * @方法描述 : 服务器未知运行时异常
	 * @param e
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public void runtimeExceptionHandler(RuntimeException e) {
		if (logger.isDebugEnabled()) {
			logger.error("服务器未知运行时异常:", e);
		}
	}

	/**
	 * @方法:空指针异常
	 * @创建人:独泪了无痕
	 * @param ex
	 */
	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	public ResponseBean nullPointerExceptionHandler(NullPointerException ex) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error("空指针异常:", ex);
		}
		responseBean.setSuccess(false);
		responseBean.setResultCode(500);
		responseBean.setMsg(NullPointerException.class.getName());
		return responseBean;
	}

	/**
	 * 
	 * @方法:类型转换异常
	 * @创建人:独泪了无痕
	 * @param ex
	 */
	@ResponseBody
	@ExceptionHandler(ClassCastException.class)
	public void classCastExceptionHandler(ClassCastException ex) {
		if (logger.isDebugEnabled()) {
			logger.error("类型转换异常:", ex);
		}
	}

	/**
	 * @方法:IO异常
	 * @创建人:独泪了无痕
	 * @param ex
	 */
	@ResponseBody
	@ExceptionHandler(IOException.class)
	public void iOExceptionHandler(IOException ex) {
		if (logger.isDebugEnabled()) {
			logger.error("IO异常:", ex);
		}
	}

	/**
	 * @方法:未知方法异常
	 * @创建人:独泪了无痕
	 * @param ex
	 */
	@ResponseBody
	@ExceptionHandler(NoSuchMethodException.class)
	public void noSuchMethodExceptionHandler(NoSuchMethodException ex) {
		if (logger.isDebugEnabled()) {
			logger.error("未知方法异常:", ex);
		}
	}

	/**
	 * @方法:数组越界异常
	 * @创建人:独泪了无痕
	 * @param ex
	 */
	@ResponseBody
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public void indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
		if (logger.isDebugEnabled()) {
			logger.error("数组越界异常:", ex);
		}
	}

	/**
	 * @方法描述 : session失效的异常拦截
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidSessionException.class)
	public void sessionTimeout(InvalidSessionException e, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.error("session失效的异常拦截:", e);
		}
	}

	/**
	 * @方法描述 : session异常
	 * @param e
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnknownSessionException.class)
	public void sessionTimeout(UnknownSessionException e, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.error("session异常拦截:", e);
		}
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
		if (logger.isDebugEnabled()) {
			logger.error("HttpMessageNotReadableException", e);
		}
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public void handleValidationException(final ValidationException e) {
		if (logger.isDebugEnabled()) {
			logger.error("ValidationException", e);
		}
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ResponseBean handleBindException(final BindException e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error("BindException", e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(400);
		return responseBean;
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ExceptionHandler(TypeMismatchException.class)
	public ResponseBean requestTypeMismatch(TypeMismatchException ex) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error("TypeMismatchException", ex);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(ex.getMessage());
		responseBean.setResultCode(400);
		return responseBean;
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseBean requestMissingServletRequest(MissingServletRequestParameterException ex) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error("MissingServletRequestParameterException", ex);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(ex.getMessage());
		responseBean.setResultCode(400);
		return responseBean;
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseBean handleSQLException(final SQLException e) {

		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error("SQLException", e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(400);
		return responseBean;
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseBean handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error("MethodArgumentNotValidException", e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(400);
		return responseBean;
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseBean handleHttpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(405);
		return responseBean;
	}

	/**
	 * 406 - Not Acceptable
	 */
	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseBean request406(HttpMediaTypeNotAcceptableException e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(406);
		return responseBean;
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseBean handleHttpMediaTypeNotSupportedException(final Exception e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(415);
		return responseBean;
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseBody
	@ExceptionHandler(ConversionNotSupportedException.class)
	public ResponseBean conversionNotSupportedException(ConversionNotSupportedException e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(500);
		return responseBean;
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseBody
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseBean httpMessageNotWritableException(HttpMessageNotWritableException e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(500);
		return responseBean;
	}
	
	/**
	@ExceptionHandler(value = Exception.class)
	public void defaultExceptionHandler(HttpServletRequest request, Exception e){
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
		}
	}
	*/

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseBean defaultExceptionHandler(final Exception e) {
		ResponseBean responseBean = new ResponseBean();
		if (logger.isDebugEnabled()) {
			logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
		}
		responseBean.setSuccess(false);
		responseBean.setMsg(e.getMessage());
		responseBean.setResultCode(500);
		return responseBean;
	}
}
