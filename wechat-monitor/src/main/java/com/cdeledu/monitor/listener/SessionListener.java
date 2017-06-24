package com.cdeledu.monitor.listener;

import java.io.Serializable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener, HttpSessionActivationListener,
		ServletContextListener, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ServletContextListener 创建（系统启动调用）
	 */
	public void contextInitialized(ServletContextEvent sce) {

	}

	/**
	 * ServletContextListener 销毁（系统停止调用）
	 */
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 钝化session
	 */
	public void sessionWillPassivate(HttpSessionEvent se) {

	}

	/**
	 * 活化session
	 */
	public void sessionDidActivate(HttpSessionEvent se) {

	}

	/**
	 * 创建session
	 */
	public void sessionCreated(HttpSessionEvent se) {

	}

	/**
	 * 销毁session
	 */
	public void sessionDestroyed(HttpSessionEvent se) {

	}
}
