package com.cdeledu.core.factory;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.cdeledu.common.constants.SystemConstant.SysLogLeavel;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.system.SysLogEntity;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.util.WebUtilHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志对象创建工厂
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:50:17
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class LogFactory {
	protected static Logger logger = LoggerFactory.getLogger(LogFactory.class);

	/**
	 * @方法描述 : 创建操作日志
	 */
	public static SysLogEntity createOperateLog(JoinPoint joinPoint, long time, Throwable throwable,
			Object opResult, String ip, String browser) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		SystemLog systemLog = method.getAnnotation(SystemLog.class);
		if (systemLog != null) {
			// 注解上的描述
			sysLog.setRemark(systemLog.desc());
			// 注解上的操作相关表
			sysLog.setTableName(StringUtils.join(systemLog.tableName(), ","));
			// 注解上的操作类型
			sysLog.setOpType(systemLog.opType().getValue());
		}

		// 请求方法名
		String targetName = joinPoint.getTarget().getClass().getName();// 获取目标类名
		sysLog.setMethod(targetName + "." + signature.getName() + "()");
		// 访问目标方法的参数：
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length < 0 ){
			String params = JSON.toJSONString(args[0]);
			// sysLog.setParams(JsonMapper.toJsonString(joinPoint.getArgs()));
			sysLog.setParams(params);
		}

		// 操作人的信息
		String userId = "-1";
		if (ShiroHelper.isLogin()) {
			userId = WebUtilHelper.getCurrentUserName();
		}
		sysLog.setUserCode(userId);

		if (opResult != null) {
			sysLog.setOpResult(JsonMapper.toJsonString(opResult));
		}
		if (throwable != null) {
			sysLog.setLogType(-1);
			sysLog.setExceptionCode(sysLog.getClass().getName());
			sysLog.setExceptionDetail(throwable.getMessage());
		}
		sysLog.setIpAddress(ip);
		sysLog.setBrowser(browser);
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
	public static SysLoginLog createLoginLog(String userCode, String content, int status, String ip,
			String browser) {
		SysLoginLog loginLog = new SysLoginLog();
		loginLog.setUserCode(userCode);
		loginLog.setLogContent(content);
		loginLog.setLoginStatus(status);
		if (status == 1) {
			loginLog.setLogLeavel(SysLogLeavel.info.name());
			loginLog.setOpType(SysOpType.LOGIN.getValue());
		} else if (status == 2) {
			loginLog.setLogLeavel(SysLogLeavel.warn.name());
			loginLog.setOpType(SysOpType.LOGIN_FAIL.getValue());
		} else if (status == -1) {
			loginLog.setLogLeavel(SysLogLeavel.info.name());
			loginLog.setOpType(SysOpType.EXIT.getValue());
		} else if (status == -2) {
			loginLog.setLogLeavel(SysLogLeavel.error.name());
			loginLog.setOpType(SysOpType.EXIT_FAIL.getValue());
		} else {
			loginLog.setLogLeavel(SysLogLeavel.error.name());
			loginLog.setOpType(SysOpType.UNKNOWN.getValue());
		}

		loginLog.setIpAddress(ip);
		loginLog.setBrowser(browser);
		return loginLog;
	}
}
