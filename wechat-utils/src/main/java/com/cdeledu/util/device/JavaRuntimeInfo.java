package com.cdeledu.util.device;

import org.apache.commons.lang3.StringUtils;

/**
 * @类描述: 代表当前运行的JRE的信息
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月24日 上午11:42:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JavaRuntimeInfo {
	/**
	 * @方法:取得当前JRE的名称
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getName() {
		return SystemUtil.get(SystemUtil.JAVA_RUNTIME_NAME, false);
	}

	/**
	 * @方法:取得当前JRE的版本
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getVersion() {
		return SystemUtil.get(SystemUtil.JAVA_RUNTIME_VERSION, false);
	}

	/**
	 * @方法:取得当前JRE的安装目录
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getHomeDir() {
		return SystemUtil.get(SystemUtil.JAVA_HOME, false);
	}

	/**
	 * @方法:取得当前JRE的系统classpath
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getClassPath() {
		return SystemUtil.get(SystemUtil.JAVA_CLASS_PATH, false);
	}
	/** 获取Jar lib或classes目录(前提是类被编译在其下)的父路径 */
	public static String getClassParentPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}
	/**
	 * @方法:取得当前JRE的系统classpath
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String[] getClassPathArray() {
		return StringUtils.split(getClassPath(), SystemUtil.get(SystemUtil.PATH_SEPRATOR, false));
	}

	/**
	 * @方法:取得当前JRE的class文件格式的版本
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getClassVersion() {
		return SystemUtil.get(SystemUtil.JAVA_CLASS_VERSION, false);
	}

	/**
	 * @方法:取得当前JRE的library搜索路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final String getLibraryPath() {
		return SystemUtil.get(SystemUtil.JAVA_LIBRARY_PATH, false);
	}

	/**
	 * @方法:取得当前JRE的library搜索路径
	 * @创建人:独泪了无痕
	 * @return
	 */
	public final String[] getLibraryPathArray() {
		return StringUtils.split(getLibraryPath(), SystemUtil.get("path.separator", false));
	}

	/**
	 * 将当前运行的JRE信息转换成字符串。
	 * 
	 * @return JRE信息的字符串表示
	 */
	public final String toString() {
		StringBuilder builder = new StringBuilder();

		SystemUtil.append(builder, "Java Runtime Name:      ", getName());
		SystemUtil.append(builder, "Java Runtime Version:   ", getVersion());
		SystemUtil.append(builder, "Java Home Dir:          ", getHomeDir());
		SystemUtil.append(builder, "Java Class Path:        ", getClassPath());
		SystemUtil.append(builder, "Java Class Version:     ", getClassVersion());
		SystemUtil.append(builder, "Java Library Path:      ", getLibraryPath());

		return builder.toString();
	}
}
