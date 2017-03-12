package com.cdeledu.common.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @类描述: Mybatis的mapper文件中的sql语句被修改后, 只能重启服务器才能被加载,
 *       非常耗时,所以就写了一个自动加载的类,配置后检查xml文件更改,如果发生变化,重新加载xml里面的内容.
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 下午1:47:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class MapperLoader implements DisposableBean, InitializingBean, ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void destroy() throws Exception {

	}

}
