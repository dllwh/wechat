package com.cdeledu.util.device;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.application.log.level.StaticLog;
import com.google.common.collect.Lists;

/**
 * @类描述: Java的System类封装工具类
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月24日 上午11:44:45
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SystemUtil {
	/**
	 * Java类信息(Java运行时环境信息)
	 */
	// Java 运行时环境规范名称
	public final static String SPECIFICATION_NAME = "java.specification.name";
	// Java 运行时环境版本
	public final static String VERSION = "java.version";
	// Java 运行时环境规范版本
	public final static String SPECIFICATION_VERSION = "java.specification.version";
	// Java 运行时环境供应商
	public final static String VENDOR = "java.vendor";
	// Java 运行时环境规范供应商
	public final static String SPECIFICATION_VENDOR = "java.specification.vendor";
	// Java 供应商的 URL
	public final static String VENDOR_URL = "java.vendor.url";
	// Java 安装目录(当前JRE的安装目录)
	public final static String JAVA_HOME = "java.home";
	// 加载库时搜索的路径列表(当前JRE的library搜索路径)
	public final static String JAVA_LIBRARY_PATH = "java.library.path";
	// 默认的临时文件路径
	public final static String TMPDIR = "java.io.tmpdir";
	// 要使用的 JIT 编译器的名称
	public final static String COMPILER = "java.compiler";
	// 一个或多个扩展目录的路径(当前JRE的扩展目录列表)
	public final static String JAVA_EXT_DIRS = "java.ext.dirs";
	// 当前JRE的名称
	public final static String JAVA_RUNTIME_NAME = "java.runtime.name";
	// 当前JRE的版本
	public final static String JAVA_RUNTIME_VERSION = "java.runtime.version";
	// 当前JRE的endorsed目录列表
	public final static String JAVA_ENDORSED_DIRS = "java.endorsed.dirs";
	// Java 类格式版本号(当前JRE的class文件格式的版本)
	public final static String JAVA_CLASS_VERSION = "java.class.version";
	// Java 类路径
	public final static String JAVA_CLASS_PATH = "java.class.path";
	/**
	 * Java虚拟机信息
	 */
	// Java 虚拟机实现名称
	public final static String JAVA_VM_NAME = "java.vm.name";
	// Java 虚拟机规范名称
	public final static String VM_SPECIFICATION_NAME = "java.vm.specification.name";
	// Java 虚拟机实现版本
	public final static String JAVA_VM_VERSION = "java.vm.version";
	// Java 虚拟机规范版本
	public final static String VM_SPECIFICATION_VERSION = "java.vm.specification.version";
	// Java 虚拟机实现供应商
	public final static String JAVA_VM_VENDOR = "java.vm.vendor";
	// Java 虚拟机规范供应商
	public final static String VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

	/**
	 * OS信息
	 */
	// 操作系统的名称
	public final static String OS_NAME = "os.name";
	// 操作系统的架构
	public final static String OS_ARCH = "os.arch";
	// 操作系统的版本
	public final static String OS_VERSION = "os.version";
	// 文件分隔符（在 UNIX 系统中是“/”）
	public final static String FILE_SEPRATOR = "file.separator";
	// 路径分隔符（在 UNIX 系统中是“:”）
	public final static String PATH_SEPRATOR = "path.separator";
	// 行分隔符（在 UNIX 系统中是“\n”）
	public final static String LINE_SEPRATOR = "line.separator";
	/**
	 * 用户信息
	 */
	// 用户的账户名称
	public final static String USER_NAME = "user.name";
	// 用户的主目录
	public final static String USER_HOME = "user.home";
	// 用户的当前工作目录
	public final static String USER_DIR = "user.dir";
	// 当前登录用户的语言设置
	public final static String USER_LANGUAGE = "user.language";
	// 当前登录用户的国家设置
	public final static String USER_COUNTRY = "user.country";
	// 当前登录用户的区域设置
	public final static String USER_REGION = "user.region";
	// 临时目录
	public final static String JAVA_IO_TMPDIR = "java_io_tmpdir";

	/**
	 * @方法:取得系统属性，如果因为Java安全的限制而失败，则将错误打在Log中，然后返回 <code>null</code>
	 * @创建人:独泪了无痕
	 * @param name
	 *            属性名
	 * @param quiet
	 *            安静模式，不将出错信息打在<code>System.err</code>中
	 * @return
	 */
	public static String get(String name, String defaultValue) {
		return StringUtils.defaultString(get(name, false), defaultValue);
	}

	/**
	 * @方法:取得系统属性，如果因为Java安全的限制而失败，则将错误打在Log中，然后返回 <code>null</code>
	 * @创建人:独泪了无痕
	 * @param name
	 *            属性名
	 * @param quiet
	 *            安静模式，不将出错信息打在<code>System.err</code>中
	 * @return
	 */
	public static String get(String name, boolean quiet) {
		try {
			return System.getProperty(name);
		} catch (SecurityException e) {
			if (!quiet) {
				StaticLog.error("Caught a SecurityException reading the system property " + "'%s'; "
						+ "the SystemUtil property value will default to null.", name);
			}
			return null;
		}
	}

	/**
	 * 输出到<code>StringBuilder</code>。
	 * 
	 * @param builder
	 *            <code>StringBuilder</code>对象
	 * @param caption
	 *            标题
	 * @param value
	 *            值
	 */
	protected static void append(StringBuilder builder, String caption, String value) {
		builder.append(caption).append(StringUtils.defaultString(value, "[n/a]")).append("\n");
	}

	/**
	 * @方法描述: 获取当前所有的系统属性的名称
	 */
	public static List<Object> showKeys() {
		Properties props = System.getProperties();
		Enumeration<?> enu = props.propertyNames();
		List<Object> resultList = Lists.newArrayList();
		while (enu.hasMoreElements()) {
			Object key = enu.nextElement();
			resultList.add(key);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取当前所有的系统属性的值
	 */
	public static List<Object> showValues() {
		Properties props = System.getProperties();
		Enumeration<Object> enu = props.elements();
		List<Object> resultList = Lists.newArrayList();
		while (enu.hasMoreElements()) {
			Object value = enu.nextElement();
			resultList.add(value);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取当前所有的系统属性
	 */
	public static List<Map<Object, Object>> showKeysAndValues() {
		Properties props = System.getProperties();
		List<Map<Object, Object>> resulitList = Lists.newArrayList();
		Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
		Map<Object, Object> paramMap = null;
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			paramMap = new HashMap<>();
			Object key = entry.getKey();
			Object value = entry.getValue();
			paramMap.put(key, value);
			resulitList.add(paramMap);
		}
		return resulitList;
	}

}
