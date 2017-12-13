package com.cdeledu.core.factory;

import javax.servlet.http.HttpServletRequest;

import com.cdeledu.common.constants.SystemConstant.SysLogLeavel;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.model.system.SysLoginLog;
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
	/**
	 * @方法描述 : 创建操作日志
	 */
	public void createOperateLog() {

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
	public static SysLoginLog createLoginLog(String userCode, String logContent, int loginStatus,
			HttpServletRequest request) {
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
	public static SysLoginLog createExitLog(String userName, HttpServletRequest request) {
		SysLoginLog exitLog = new SysLoginLog();
		exitLog.setUserCode(userName);
		exitLog.setLogContent("用户[" + userName + "]已退出");
		exitLog.setLoginStatus(-1);
		exitLog.setLogLeavel(SysLogLeavel.info.name());
		exitLog.setOpType(SysOpType.EXIT.getValue());
		exitLog.setIpAddress(IpUtilHelper.getClientIP(request));
		String userAgent = request.getHeader("User-Agent");
		exitLog.setBrowser(UserAgent.parseUserAgentString(userAgent).getBrowser().getName());
		return exitLog;
	}

}
