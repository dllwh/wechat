package com.cdeledu.core.factory;

import java.util.TimerTask;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.service.log.LoginLogService;
import com.cdeledu.service.log.OperateLogService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志操作任务工厂
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:53:51
 * @版本: V1.1
 * @since: JDK 1.7
 */
public class LogTaskFactory {
	private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
	private static OperateLogService operateLogService = ConstantFactory.operateLogService;
	private static LoginLogService loginLogService = ConstantFactory.loginLogService;

	public static TimerTask operateLog(final JoinPoint point, final long time, final Throwable e,
			final Object opResult, final String ip, final String browser) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					operateLogService.addLog(
							LogFactory.createOperateLog(point, time, e, opResult, ip, browser));
				} catch (Exception e) {
					logger.error("保存日常操作日志异常!", e);
				}
			}
		};
	}

	public static TimerTask loginLog(final String userCode, final String content, final int status,
			final String ip, final String browser) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					loginLogService.insert(
							LogFactory.createLoginLog(userCode, content, status, ip, browser));
				} catch (Exception e) {
					logger.error("保存登录日志异常!", e);
				}
			}
		};
	}
}
