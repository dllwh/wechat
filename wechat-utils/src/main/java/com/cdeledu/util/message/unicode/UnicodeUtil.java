package com.cdeledu.util.message.unicode;

/**
 * 
 * @ClassName: UnicodeUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 独泪了无痕
 * @date: 2015年9月10日 下午4:10:01
 * @version: V1.0
 * @history:
 */
public class UnicodeUtil {
	/**
	 * 
	 * @Title: chineseToUnicode
	 * @Description: Java String字符串转换 Unicode字符
	 * @param contents
	 * @return
	 * @return:String 返回类型
	 */
	public static String chineseToUnicode(String contents) {
		String result = "";
		char[] utfBytes = contents.toCharArray();
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			result = result + "\\u" + hexB;
		}
		return result;
	}

	/**
	 * 
	 * @Title: unicodeToChinese
	 * @Description: Unicode字符转换Java String字符串
	 * @param contents
	 * @return
	 * @return:String 返回类型
	 */
	public static String unicodeToChinese(String contents) {
		int n = contents.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = contents.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}
}
