package com.cdeledu.core.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.common.constants.GlobalConstants;

/**
 * @类描述: 监听器,统计在线用户人数
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月8日 上午8:04:45
 * @版本: V1.0
 * @since: JDK 1.7
 */
@WebListener
public class MySessionListener extends BaseClass implements HttpSessionListener {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	// 全站在线人数
	public static int userNumber = 0;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/** 创建session的时候+1 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		userNumber++;
		se.getSession().getServletContext().setAttribute(GlobalConstants.ONLINEUSERNUMBER,
				userNumber);
		if (logger.isDebugEnabled()) {
			logger.info("-----------------创建session的时候----" + userNumber);
		}
	}

	/** 销毁 session的时候-1 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		userNumber--;
		se.getSession().getServletContext().setAttribute(GlobalConstants.ONLINEUSERNUMBER,
				userNumber);
		if (logger.isDebugEnabled()) {
			logger.info("-----------------销毁session的时候----" + userNumber);
		}
	}
}
