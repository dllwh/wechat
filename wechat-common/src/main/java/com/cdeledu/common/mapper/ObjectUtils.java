package com.cdeledu.common.mapper;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @类名称：ObjectUtils @功能说明： Object工具类，可用于Object常用操作
 * 
 * @创建人：dell
 * 
 */
public class ObjectUtils {

	/**
	 * 
	 * @方法名称: isEquals
	 * @方法描述: 比较两个对象是否相等
	 *        <ul>
	 *        <li>如果两者都为空，返回true</li>
	 *        </ul>
	 * @param actual
	 * @param expected
	 * @return
	 */
	public static boolean isEquals(Object actual, Object expected) {
		return actual == expected || (actual == null ? expected == null : actual.equals(expected));
	}

	/**
	 * 
	 * @方法名称: nullStrToEmpty
	 * @方法描述: 空对象转换成空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String nullStrToEmpty(Object str) {
		return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
	}

	/**
	 * 
	 * @方法名称: transformLongArray
	 * @方法描述: Long 数组转换为long数组
	 * 
	 * @param source
	 * @return
	 */
	public static long[] transformLongArray(Long[] source) {
		long[] destin = new long[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 
	 * @方法名称: transformLongArray
	 * @方法描述: long 数组转换为Long数组
	 * 
	 * @param source
	 * @return
	 */
	public static Long[] transformLongArray(long[] source) {
		Long[] destin = new Long[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 
	 * @方法名称: transformIntArray
	 * @方法描述: int 数组转换为Integer数组
	 * 
	 * @param source
	 * @return
	 */
	public static Integer[] transformIntArray(int[] source) {
		Integer[] destin = new Integer[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 
	 * @方法名称: transformIntArray
	 * @方法描述: Integer 数组转换为int数组
	 * 
	 * @param source
	 * @return
	 */
	public static int[] transformIntArray(Integer[] source) {
		int[] destin = new int[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 
	 * @方法名称: compare
	 * @方法描述: 比较两个对象大小
	 *        <ul>
	 *        <strong>关于结果</strong>
	 *        <li>if v1 > v2, return 1</li>
	 *        <li>if v1 = v2, return 0</li>
	 *        <li>if v1 < v2, return -1</li>
	 *        </ul>
	 *        <ul>
	 *        <strong>关于规则</strong>
	 *        <li>if v1 is null, v2 is null, then return 0</li>
	 *        <li>if v1 is null, v2 is not null, then return -1</li>
	 *        <li>if v1 is not null, v2 is null, then return 1</li>
	 *        <li>return v1.{@link Comparable#compareTo(Object)}</li>
	 *        </ul>
	 * @param v1
	 * @param v2
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V> int compare(V v1, V v2) {
		return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable) v1).compareTo(v2));
	}

	/**
	 * 
	 * @Title: getProperty
	 * @Description: 反射，根据当前传入对象实例，属性名，返回执行后的值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getProperty(Object obj, String fieldName) {
		if (obj == null || fieldName == null) {
			return null;
		}
		String m = fieldName.substring(0, 1).toUpperCase();
		m = "get" + m + fieldName.substring(1, fieldName.length());
		try {
			return obj.getClass().getMethod(m, new Class[0]).invoke(obj);
		} catch (Exception e) {
			m = fieldName.substring(0, 1).toUpperCase();
			m = "is" + m + fieldName.substring(1, fieldName.length());
			try {
				return obj.getClass().getMethod(m, new Class[0]).invoke(obj);
			} catch (Exception e1) {
			}
		}
		return null;
	}

	/**
	 * @方法:对象中是否包含元素
	 * @创建人:独泪了无痕
	 * @param obj
	 *            对象
	 * @param element
	 *            元素
	 * @return 是否包含
	 */
	public static boolean contains(Object obj, Object element) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			if (element == null) {
				return false;
			}
			return ((String) obj).contains(element.toString());
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).contains(element);
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).values().contains(element);
		}

		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			while (iter.hasNext()) {
				Object o = iter.next();
				if (isEquals(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) obj;
			while (enumeration.hasMoreElements()) {
				Object o = enumeration.nextElement();
				if (isEquals(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj.getClass().isArray() == true) {
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				Object o = Array.get(obj, i);
				if (isEquals(o, element)) {
					return true;
				}
			}
		}
		return false;
	}
}
