package com.cdeledu.monitor.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: 监控组件常量类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午6:43:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class MonitorConstanst {
	/** 监控的目录信息 */
	public static List<String> dirPaths = new ArrayList<String>();
	/** 默认监控类型中不需要监控的类型 */
	public static List<String> excludeTypeList = new ArrayList<String>();
	/** 默认监控类型之外需要监控的类型 */
	public static List<String> includeTypeList = new ArrayList<String>();
	/** http reqeust 不监控的url 后缀 */
	public static List<String> filterSuffixs = new ArrayList<String>();
	/** 定时器时间 */
	public static int monitorInterval = 60;
	/** 监控日志文件存储路径 */
	public static String dataPath = File.separator;
	/** 监控日志文件，默认存放路径 */
	public static String dataPathDefault = "monitorlog" + File.separator;

	public static boolean isOpenHttpSession = false;
	public static boolean isOpenHttpRequest = true;
	public static boolean isOpenSql = false;
}
