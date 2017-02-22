package com.cdeledu.util.apache.lang;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @ClassName: RandomUtil
 * @Description: 随机码工具类(继承与Apache RandStringUtils)
 * @author: 独泪了无痕
 * @date: 2015年9月15日 上午11:57:33
 * @version: V1.0
 * @history:
 */
public class RandomUtil extends RandomStringUtils {
	/**--------------------------私有属性  -------------------------------*/
	private static Random random = new Random();
	/**--------------------------私有属性  -------------------------------*/

	/** 用于随机选的数字 */
	private static final String BASE_NUMBER = "0123456789";
	/** 用于随机选的字符 */
	private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
	/** 用于随机选的字符和数字 */
	public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;
	/** 用于随机选的字符(小写+大写) */
	public static final String LETTERCHAR = BASE_CHAR.toLowerCase() + BASE_CHAR.toUpperCase();
	/** 用于随机选的字符(小写+大写)和数字 */
	public static final String ALLCHAR = BASE_NUMBER + BASE_CHAR + BASE_CHAR.toUpperCase();

	/**
	 * java生成随机数字和字母组合
	 * 
	 * @param length
	 *            {生成随机数的长度}
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * @Title: randomString
	 * @Description: 返回一个定长的随机字符串(包含数字和大小写字母)
	 * @author: 独泪了无痕
	 * @param length
	 *            随机数的长度
	 * @return
	 */
	public static String randomString(String baseString,int length) {
		StringBuilder sb = new StringBuilder(length);
		if (length <= 1) {
			length = 1;
		}

		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseString.length());
			sb.append(baseString.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * @Title: generateStringByNumberChar
	 * @Description: 返回一个定长的随机纯数字字符串(只包含数字)
	 * @param length
	 *            随机数的长度
	 * @return
	 */
	public static String generateStringByNumberChar(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(BASE_NUMBER.charAt(random.nextInt(BASE_NUMBER.length())));
		}
		return sb.toString();
	}

	/**
	 * @Title: generateStringByLetterCharr
	 * @Description: 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * @param length
	 *            随机数的长度
	 * @return
	 */
	public static String generateStringByLetterCharr(int length) {
		StringBuffer sb = new StringBuffer();
		if (length <= 1) {
			length = 1;
		}

		for (int i = 0; i < length; i++) {
			sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: generateLowerString
	 * @Description: 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * @author: 独泪了无痕
	 * @param length
	 * @return
	 */
	public static String generateLowerString(int length) {
		return generateStringByLetterCharr(length).toLowerCase();
	}

	/**
	 * 
	 * @Title: generateUpperString
	 * @Description: 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * @author: 独泪了无痕
	 * @param length
	 *            随机数的长度
	 * @return
	 */
	public static String generateUpperString(int length) {
		return generateStringByLetterCharr(length).toUpperCase();
	}

	/** 随机获取UUID字符串(无中划线) */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String randomString(int length) {
		return randomString(BASE_CHAR_NUMBER, length);
	}
	/**
	 * 获得指定范围内的随机数
	 * @param min 最小数
	 * @param max 最大数
	 * @return 随机数
	 */
	public static int randomInt(int min, int max) {
		return random.nextInt(max - min) + min;
	}
	/**
	 * @方法:获得指定范围内的随机数 [0,limit)
	 * @创建人:独泪了无痕
	 * @param limit 限制随机数的范围，不包括这个数 
	 * @return 随机数
	 */
	public static int randomInt(int limit) {
		return random.nextInt(limit);
	}
	/**
	 * 随机获得列表中的元素
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 随机元素
	 */
	public static <T> T randomEle(List<T> list) {
		return randomEle(list, list.size());
	}
	/**
	 * 随机获得列表中的元素
	 * @param <T> 元素类型
	 * @param list 列表
	 * @param limit 限制列表的前N项
	 * @return  随机元素
	 */
	public static <T> T randomEle(List<T> list, int limit) {
		return list.get(randomInt(limit));
	}
}
