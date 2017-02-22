package com.cdeledu.util;

import java.util.ResourceBundle;

/**
 * @类描述: 项目参数工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午6:28:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ResourceUtil {
	/** ----------------------------------------------------- Fields start */
	private static final ResourceBundle sysConfig = ResourceBundle
			.getBundle("properties/sysConfig");
	private static final ResourceBundle dbConfig = ResourceBundle.getBundle("properties/dbConfig");

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:获取项目配置文件参数
	 * @创建人:独泪了无痕
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return sysConfig.getString(name);
	}

	/**
	 * @方法:获取session定义名称
	 * @创建人:独泪了无痕
	 * @param sessionName
	 * @return
	 */
	public static final String getSessionattachmenttitle(String sessionName) {
		return sysConfig.getString(sessionName);
	}

	/**
	 * @方法:获取数据库类型
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getJdbcUrl() {
		return dbConfig.getString("database.dbUrl").toLowerCase();
	}
}
