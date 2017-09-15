package com.cdeledu.template.codeMaker.config;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 配置类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年9月14日 下午11:16:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Configuration {
	/** ----------------------------------------------------- Fields start */
	private static final String BEAN_TEMPLATE = "%sbean%s_template.xml";

	private static final String SERVICE_TEMPLATE = "%sservice%s_template.xml";

	private static final String SERVICEIMPL_TEMPLATE = "%sserviceimpl%s_template.xml";

	private static final String DAO_TEMPLATE = "%sdao%s_template.xml";

	private static final String MYBATIS_TEMPLATE = "%smybatis%s_template.xml";

	/** ----------------------------------------------------- Fields end */

	public static String getBeanTemplateLocation() {
		return "";
	}

	public static String getServiceTemplateLocation() {
		return "";
	}

	public static String getServiceImplTemplateLocation() {
		return "";
	}

	public static String getDaoTemplateLocation() {
		return "";
	}

	public static String getMybatisTemplateLocation() {
		return "";
	}

}
