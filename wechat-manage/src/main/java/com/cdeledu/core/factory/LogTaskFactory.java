package com.cdeledu.core.factory;

import java.util.TimerTask;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.core.log.LogManager;
import com.cdeledu.service.sys.SystemService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志操作任务创建工厂
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:53:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LogTaskFactory {
	private static Logger logger = LoggerFactory.getLogger(LogManager.class);
	private static SystemService systemService = ConstantFactory.systemService;

	/** ----------------------------------------------- [公共方法] */

	public static TimerTask operateLog(final JoinPoint point, final long time, final Throwable e,
			final Object opResult) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					systemService.addLog(LogFactory.createOperateLog(point, time, e, opResult));
				} catch (Exception e) {
					logger.error("创建退出日志异常!", e);
				}
			}
		};
	}

	public static TimerTask loginLog(final String userCode, final String content,
			final int status) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					systemService.addLoginLog(LogFactory.createLoginLog(userCode, content, status));
				} catch (Exception e) {
					logger.error("创建退出日志异常!", e);
				}
			}
		};
	}

	public static TimerTask exitLog() {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					systemService.addLoginLog(LogFactory.createExitLog());
				} catch (Exception e) {
					logger.error("创建退出日志异常!", e);
				}
			}
		};
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
