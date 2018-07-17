package com.cdeledu.arithmetic.sensitiveword;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 敏感词工具类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年7月6日 下午8:06:59
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class SensitivewordFilterHelper {
	/** ----------------------------------------------------- Fields start */
	/** 最小匹配规则 */
	public static int minMatchTYpe = 1;
	/** 最大匹配规则 */
	public static int maxMatchType = 2;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述 : 判断文字是否包含敏感字符
	 * @param text
	 *            文字
	 * @param matchType
	 *            匹配规则 1：最小匹配规则，2：最大匹配规则
	 * @return 若包含返回true，否则返回false
	 */
	public static boolean isContaintSensitiveWord(String text, int matchType) {
		boolean result = false;
		int contLeng = text.length();
		for (int i = 0; i < contLeng; i++) {
			// 判断是否包含敏感字符
			int matchFlag = checkSensitiveWord();
			// 大于0存在，返回true
			if (matchFlag > 0) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * @方法描述 : 获取文字中的敏感词
	 * @param text
	 *            文字
	 */
	public static void getSensitiveWord(String text) {

	}

	/**
	 * @方法描述 : 替换敏感字字符
	 * @param text
	 *            文字
	 */
	public static void replaceSensitiveWord(String text) {

	}

	/**
	 * @方法描述 : 获取替换字符串
	 * @param text
	 *            文字
	 */
	public static void getReplaceChars(String text) {

	}

	/**
	 * @方法描述 : 检查文字中是否包含敏感字符
	 * @return
	 */
	public static int checkSensitiveWord() {
		// 匹配标识数默认为0
		int matchFlag = 0;
		return matchFlag;
	}
}
