package com.cdeledu.util.application.log.dialect;

import java.io.InputStream;
import java.util.logging.LogManager;

import org.apache.commons.io.IOUtils;

import com.cdeledu.util.application.log.Log;
import com.cdeledu.util.application.log.LogFactory;

/**
 * @类描述:
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午8:44:27
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://java.sun.com/javase/6/docs/technotes/guides/logging/index.html">
 *      java.util.logging</a>
 */
public class JdkLogFactory extends LogFactory {

	public JdkLogFactory() {
		super("JDK Logging");
		readConfig();
	}

	@Override
	public Log getLog(String name) {
		return new JdkLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new JdkLog(clazz);
	}

	/**
	 * 
	 * 读取ClassPath下的logging.properties配置文件
	 * 
	 */
	private void readConfig() {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
		if (null == in) {
			System.err.println(
					"[WARN] Can not find [logging.properties], use [%JRE_HOME%/lib/logging.properties] as default!");
			return;
		}

		try {
			LogManager.getLogManager().readConfiguration(in);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				LogManager.getLogManager().readConfiguration();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
