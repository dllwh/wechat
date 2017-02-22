package com.cdeledu.util.apache.collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * @类名称 : ArrayUtilHelper
 * @功能说明 : 数组工具类(继承于Apache)
 * @创建人: 独泪了无痕
 *
 */
public class ArrayUtilHelper extends ArrayUtils {
	/*-------------------------- 私有属性 start -------------------------------*/
	/*-------------------------- 私有属性 end   -------------------------------*/
	/*-------------------------- 私有方法 start -------------------------------*/
	/*-------------------------- 私有方法 end   -------------------------------*/
	/*-------------------------- 公有方法 start -------------------------------*/
	/**
	 * 
	 * @方法名称: converCharToInt
	 * @方法描述: 将字符数组转换成数字数组
	 * 
	 * @param ca
	 * @return
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/** 清除字符串数组中的null */
	public static String[] clearNull(String[] array) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				list.add(array[i]);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	/*-------------------------- 公有方法 end   -------------------------------*/
}
