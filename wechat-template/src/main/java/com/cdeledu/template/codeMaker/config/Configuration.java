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
	private static MyBatisType codeTemplateType = MyBatisType.mysql;
	private static boolean pageFlage = false;
	private static final String BEAN_TEMPLATE = "entity/bean_template.xml";
	private static final String SERVICE_TEMPLATE = "service/service_template.xml";
	private static final String SERVICEIMPL_TEMPLATE = "service/impl/serviceimpl_template.xml";
	private static final String DAO_TEMPLATE = "dao/dao_template.xml";
	private static final String MYBATIS_TEMPLATE = "mapper/mybatis_%s_template.xml";
	private static final String SQL_TEMPLATE = "sql/%s_template.xml";

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:获取实体类的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getBeanTemplateLocation() {
		return BEAN_TEMPLATE;
	}

	/**
	 * @方法:获取服务接口的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getServiceTemplateLocation() {
		return SERVICE_TEMPLATE;
	}

	/**
	 * @方法:获取服务实现的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getServiceImplTemplateLocation() {
		return SERVICEIMPL_TEMPLATE;
	}

	/**
	 * @方法:获取Dao服务的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getDaoTemplateLocation() {
		return DAO_TEMPLATE;
	}

	/**
	 * @方法:获取数据库操作的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getMybatisTemplateLocation() {
		return String.format(MYBATIS_TEMPLATE, codeTemplateType);
	}

	/**
	 * @方法:获取sql的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getSQLTemplateLocation() {
		return String.format(SQL_TEMPLATE, codeTemplateType);
	}

	/**
	 * @方法:设置连接数据库链接类型
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static void setCodeTemplateType(MyBatisType codeTemplateType) {
		Configuration.codeTemplateType = codeTemplateType;
	}

	/**
	 * @方法:获取sql的配置文件路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static boolean getPageFlage() {
		return pageFlage;
	}

	/**
	 * @方法:设置连接数据库链接类型
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static void setPageFlage(boolean pageFlage) {
		Configuration.pageFlage = pageFlage;
	}

	public static void main(String[] args) {
		System.out.println(getBeanTemplateLocation());
		System.out.println(getServiceTemplateLocation());
		System.out.println(getServiceImplTemplateLocation());
		System.out.println(getDaoTemplateLocation());
		System.out.println(getMybatisTemplateLocation());
	}

}
