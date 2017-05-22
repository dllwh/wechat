package com.cdeledu.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cdeledu.common.constants.GlobalConstants;

/**
 * @类描述:
 *       <ul>
 *       <li>Spring监听器</li>
 *       <li>ContextLoaderListener监听器的作用就是启动Web容器时，自动装配ApplicationContext的配置信息。
 *       </li>
 *       <li>因为它实现了ServletContextListener这个接口，在web.xml配置这个监听器，启动容器时，
 *       就会默认执行它实现的方法。</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月8日 上午8:17:39
 * @版本: V1.0
 * @since: JDK 1.7
 */
@WebListener
public class WebContextListener extends ContextLoaderListener {
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		GlobalConstants.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		if (!GlobalConstants.printKeyLoadMessage()) {
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
