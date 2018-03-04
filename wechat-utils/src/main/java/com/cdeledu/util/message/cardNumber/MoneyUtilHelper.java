package com.cdeledu.util.message.cardNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @描述: 与金额相关的工具类
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午12:44:10
 * @version: V1.0
 * @since: JDK 1.7
 */
public class MoneyUtilHelper {
	/** -------------------------- 属性 begin ------------------------------- */
	// 不考虑分隔符的正确性
	private static final Pattern AMOUNT_PATTERN = Pattern
			.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$");
	// 将数字转化为汉字的数组,因为各个实例都要使用所以设为静态
	private static final char[] CN_UPPER_NUMBER = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌',
			'玖' };

	// 供分级转化的数组,因为各个实例都要使用所以设为静态
	private static final char[] CN_UPPER_MONETRAY_UNIT = { '元', '拾', '百', '仟', '万', '拾', '百', '仟',
			'亿' };

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	protected static int getNumber(char c) {
		String str = String.valueOf(c);
		return Integer.parseInt(str);
	}

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * @Title: format
	 * @Description: 对金额的格式调整到分
	 * @author: 独泪了无痕
	 * @param money
	 * @return
	 */
	public static String format(String money) {
		if (StringUtils.isBlank(money)) {
			return "0.00";
		}
		StringBuffer sb = new StringBuffer();

		int index = money.indexOf(".");
		if (index == -1) {
			return money + ".00";
		} else {
			String s0 = money.substring(0, index);// 整数部分
			String s1 = money.substring(index + 1);// 小数部分
			if (s1.length() == 1) {// 小数点后一位
				s1 = s1 + "0";
			} else if (s1.length() > 2) {// 如果超过3位小数，截取2位就可以了
				s1 = s1.substring(0, 2);
			}
			sb.append(s0);
			sb.append(".");
			sb.append(s1);
		}
		return sb.toString();
	}

	/**
	 * @Title: CnUpperCaser
	 * @Description: 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
	 * @author: 独泪了无痕
	 * @param amount
	 * @return
	 */
	public static String CnUpperCaser(String amount) {
		/**
		 * 去掉分隔符
		 */
		amount = amount.replaceAll(",", "");
		/**
		 * 验证金额正确性
		 */
		if (amount.equals("0.00")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		Matcher matcher = AMOUNT_PATTERN.matcher(amount);
		if (!matcher.find()) {
			throw new IllegalArgumentException("输入金额有误.");
		}

		StringBuffer result = new StringBuffer();

		// 整数部分
		String integerPart = "";
		// 小数部分
		String floatPart = "";

		// 是否包含小数点
		if (amount.contains(".")) {
			int dotIndex = amount.indexOf(".");
			integerPart = amount.substring(0, dotIndex);
			floatPart = amount.substring(dotIndex + 1);
		} else {
			integerPart = amount;
		}
		/**
		 * 整数部分处理
		 */

		for (int i = 0; i < integerPart.length(); i++) {
			int _index = getNumber(integerPart.charAt(i));
			// 得到转化的大写
			result.append(CN_UPPER_NUMBER[_index]);
			//
			result.append(CN_UPPER_MONETRAY_UNIT[integerPart.length() - 1 - i]);
		}

		/**
		 * 小数部分处理
		 */
		// 小数部分处理
		if (floatPart.length() > 0) {
			result.append("点");
			for (int i = 0; i < floatPart.length(); i++) {
				int _index = getNumber(floatPart.charAt(i));
				result.append(CN_UPPER_NUMBER[_index]);
			}
		}
		// 返回拼接好的字符串
		return result.toString();
	}

	/**
	 * @方法描述: 将阿拉伯数字转换成中文大写
	 * @创建者: 独泪了无痕
	 * @创建时间: 2016年2月25日 上午8:19:33
	 * @param n
	 * @return
	 */
	public static String toChineseNumberCase(int n) {
		String chineseNumber = "";
		if (n >= 0 && n < 10) {
			chineseNumber = String.valueOf(CN_UPPER_NUMBER[n]);
		}

		return chineseNumber;
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
