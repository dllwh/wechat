package com.cdeledu.common.reflection;

import java.util.Collection;
import java.util.Date;

/***
 * @类描述: 类处理相关工具
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月23日 上午12:35:50
 * @版本: V1.2
 * @since: JDK 1.7
 * @see <a href="http://git.oschina.net/loolly/hutool">参考文档1</a>
 * @see <a href="http://git.oschina.net/jfinal/jfinal-weixin">参考文档2</a>
 */
public final class ClassUtilHelper {

	private ClassUtilHelper() {
		// 静态类不可实例化
	}

	/** Class文件扩展名 */
	public static final String CLASS_EXT = ".class";
	/** Jar文件扩展名 */
	public static final String JAR_FILE_EXT = ".jar";
	/** 在Jar中的路径jar的扩展名形式 */
	public static final String JAR_PATH_EXT = ".jar!";
	/** 当Path为文件形式时, path会加入一个表示文件的前缀 */
	public static final String PATH_FILE_PRE = "file:";

	/** 获取调用此方法的所在的类的名称 */
	public static String getClassName() {
		String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
		return clazz.substring(clazz.lastIndexOf(".") + 1);
	}

	/** 获取调用此方法的所在的方法的名称 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	/**
	 * @方法描述:
	 *        <ul>
	 *        <li>判断类是否是基础数据类型</li>
	 *        <li>目前支持11种</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午5:52:59
	 * @param clazz
	 * @return
	 */
	public static boolean isBaseDataType(Class<?> clazz) {
		return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class)
				|| clazz.equals(Integer.class) || clazz.equals(Long.class)
				|| clazz.equals(Float.class) || clazz.equals(Double.class)
				|| clazz.equals(Byte.class) || clazz.equals(Character.class)
				|| clazz.equals(Short.class) || clazz.equals(Date.class)
				|| clazz.equals(byte[].class) || clazz.equals(Byte[].class);
	}

	/**
	 * @方法描述: 判断是否是集合
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午6:18:46
	 * @param claxx
	 * @return
	 */
	public static boolean isCollection(Class<?> claxx) {
		return Collection.class.isAssignableFrom(claxx);
	}

	/**
	 * @方法描述: 判断此Class是否是一个数组
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午6:20:50
	 * @param claxx
	 * @return
	 */
	public static boolean isArray(Class<?> claxx) {
		return claxx.isArray();
	}
}
