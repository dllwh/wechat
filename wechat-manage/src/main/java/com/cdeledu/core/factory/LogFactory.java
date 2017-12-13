package com.cdeledu.core.factory;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.constants.SystemConstant.SysLogLeavel;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.util.WebUtilHelper;
import com.cdeledu.util.network.IpUtilHelper;

import nl.bitwalker.useragentutils.UserAgent;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志对象创建工厂
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:50:17
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LogFactory {
	protected static Logger logger = LoggerFactory.getLogger(LogFactory.class);

	/**
	 * @方法描述 : 创建操作日志
	 */
	public static SysLogEntity createOperateLog(JoinPoint joinPoint, long time, Throwable throwable,
			Object opResult) {
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
		// 访问目标方法的参数：
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
		return sysLog;
	}

	/**
	 * @方法描述 : 创建登录日志
	 * @param userCode
	 * @param logContent
	 *            登录验证结果
	 * @param loginStatus
	 *            登录验证结果
	 * @param logLeavel
	 *            日志级别
	 * @param request
	 */
	public static SysLoginLog createLoginLog(String userCode, String logContent, int loginStatus) {
		SysLoginLog loginLog = new SysLoginLog();
		loginLog.setUserCode(userCode);
		loginLog.setLogContent(logContent);
		loginLog.setLoginStatus(loginStatus);
		if (loginStatus == 1) {
			loginLog.setLogLeavel(SysLogLeavel.info.name());
		} else {
			loginLog.setLogLeavel(SysLogLeavel.warn.name());
		}
		loginLog.setOpType(SysOpType.LOGIN.getValue());
		HttpServletRequest request = WebUtilHelper.getHttpServletRequest();
		loginLog.setIpAddress(IpUtilHelper.getClientIP(request));
		String userAgent = request.getHeader("User-Agent");
		loginLog.setBrowser(UserAgent.parseUserAgentString(userAgent).getBrowser().getName());
		return loginLog;
	}

	/**
	 * @方法描述 :创建退出日志
	 * @param userName
	 * @param request
	 * @return
	 */
	public static SysLoginLog createExitLog() {
		SysLoginLog exitLog = new SysLoginLog();
		String userName = WebUtilHelper.getCurrentUserName();
		exitLog.setUserCode(userName);
		exitLog.setLogContent("用户[" + userName + "]已退出");
		exitLog.setLoginStatus(-1);
		exitLog.setLogLeavel(SysLogLeavel.info.name());
		exitLog.setOpType(SysOpType.EXIT.getValue());
		HttpServletRequest request = WebUtilHelper.getHttpServletRequest();
		exitLog.setIpAddress(IpUtilHelper.getClientIP(request));
		String userAgent = request.getHeader("User-Agent");
		exitLog.setBrowser(UserAgent.parseUserAgentString(userAgent).getBrowser().getName());
		return exitLog;
	}

}
