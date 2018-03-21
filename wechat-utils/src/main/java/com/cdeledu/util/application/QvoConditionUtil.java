/**
 * 
 */
package com.cdeledu.util.application;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 检验
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月21日 下午6:58:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class QvoConditionUtil {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:检测Integer 非空且大于0为真)
	 * @创建人:独泪了无痕
	 * @param integer
	 * @return
	 */
	public static boolean checkInteger(Integer integer) {
		if (integer != null && integer > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法:检测Float 非空且大于0为真
	 * @创建人:独泪了无痕
	 * @param f
	 * @return
	 */
	public static boolean checkFloat(Float f) {
		if (f != null && f > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法:检测Long 非空且大于0为真
	 * @创建人:独泪了无痕
	 * @param l
	 * @return
	 */
	public static boolean checkLong(Long l) {
		if (l != null && l > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法:检测Double 非空且大于0为真
	 * @创建人:独泪了无痕
	 * @param value
	 * @return
	 */
	public static boolean checkDouble(Double value) {
		if (value != null && value > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法:检测List 非空且大于0为真
	 * @创建人:独泪了无痕
	 * @param list
	 * @return
	 */
	public static boolean checkList(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
}
