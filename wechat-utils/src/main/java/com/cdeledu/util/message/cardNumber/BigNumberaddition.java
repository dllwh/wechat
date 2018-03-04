package com.cdeledu.util.message.cardNumber;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: BigNumberaddition
 * @Description: 加法、乘法、除法
 * @author: 独泪了无痕
 * @date: 2015年9月14日 下午1:47:06
 * @version: V1.0
 * @history:
 */
public class BigNumberaddition {
	/** -------------------------- 私有属性 begin ------------------------------- */
	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */

	private static String[] trimlast0(String str) {
		String[] result = new String[2];
		int i = str.length() - 1;
		for (; i >= 0; i--) {
			if (str.charAt(i) != '0') {
				break;
			}
		}
		result[0] = str.substring(0, i + 1);
		result[1] = (str.length() - 1 - i) + "";
		return result;
	}

	private static String[] split3(String strnumber) {
		int arrsize = strnumber.length() / 3;
		int yushu = strnumber.length() % 3;
		if (yushu != 0) {
			arrsize++;
		}
		String[] result = new String[arrsize];
		int arrindex = 0;
		int strindex = 0;
		if (yushu != 0) {
			result[arrsize - 1 - arrindex++] = strnumber.substring(0, yushu);
			strindex = yushu;
		}
		while (strindex <= strnumber.length() - 3) {
			result[arrsize - 1 - arrindex++] = strnumber.substring(strindex, strindex + 3);
			strindex = strindex + 3;
		}
		return result;
	}

	private static int start0num(String string) {
		int result = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '0') {
				result++;
			} else {
				break;
			}
		}
		return result;
	}

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * 
	 * @Title: add
	 * @Description: 加法运算
	 * @param add1
	 *            被加数
	 * @param add2
	 *            加数
	 * @return
	 */
	public static String add(String add1, String add2) {
		int len = Math.max(add1.length(), add2.length()) + 1;
		char[] chs1 = new char[len];
		char[] chs2 = new char[len];
		char[] chsresult = new char[len];
		int len1 = add1.length();
		for (int i = 0; i < len1; i++) {
			chs1[i] = add1.charAt(len1 - i - 1);
		}
		for (int i = len1; i < len; i++) {
			chs1[i] = '0';
		}
		int len2 = add2.length();
		for (int i = 0; i < len2; i++) {
			chs2[i] = add2.charAt(len2 - i - 1);
		}
		for (int i = len2; i < len; i++) {
			chs2[i] = '0';
		}

		int carry = 0; // carry表示进位
		for (int i = 0; i < len; i++) {// chsresult[i]
			int sum = Integer.parseInt(chs1[i] + "") + Integer.parseInt(chs2[i] + "") + carry;
			carry = sum / 10;
			chsresult[len - i - 1] = String.valueOf(sum % 10).charAt(0);
		}
		int start = 0, ln = chsresult.length;
		if (chsresult[0] == '0') {
			start = 1;
			ln = chsresult.length - 1;
		}
		return new String(chsresult, start, ln);
	}

	/**
	 * 
	 * @Title: multiply
	 * @Description: 乘法运算
	 * @param multiplier1
	 *            被乘数
	 * @param multiplier2
	 *            乘数
	 * @return
	 */
	public static String multiply(String multiplier1, String multiplier2) {
		String result = "";
		String[] tmresult = trimlast0(multiplier1);
		multiplier1 = tmresult[0];
		int nm0_1 = Integer.parseInt(tmresult[1]);
		tmresult = trimlast0(multiplier2);
		multiplier2 = tmresult[0];
		int nm0_2 = Integer.parseInt(tmresult[1]);
		String[] split3arr1 = split3(multiplier1);
		String[] split3arr2 = split3(multiplier2);
		String[][] multiplyarr = new String[split3arr2.length][split3arr1.length];
		int row = split3arr2.length;
		int col = split3arr1.length;
		for (int row0 = 0; row0 < row; row0++) {
			for (int col0 = 0; col0 < col; col0++) {
				multiplyarr[row0][col0] = String.valueOf(
						Integer.parseInt(split3arr2[row0]) * Integer.parseInt(split3arr1[col0]));
				int start0num1 = start0num(split3arr2[row0]);
				int start0num2 = start0num(split3arr1[col0]);
				int start0num = start0num1 + start0num2;
				if (start0num > 0) {
					StringBuilder bu0 = new StringBuilder();
					for (int i = 0; i < start0num; i++) {
						bu0.append("0");
					}
					multiplyarr[row0][col0] = bu0.toString() + multiplyarr[row0][col0];
				}
			}
		}
		String[] tmpsum = new String[row + col - 1];
		for (int k = 0; k < tmpsum.length; k++) {
			int imin = Math.max(k - col + 1, 0);
			int imax = Math.min(k, row - 1);
			int sum = 0;
			int maxlen = 0;
			for (int i = imin; i <= imax; i++) {
				int len = multiplyarr[i][k - i].length();
				if (maxlen < len) {
					maxlen = len;
				}
				sum += Integer.parseInt(multiplyarr[i][k - i]);
			}
			tmpsum[k] = sum + "";
			int len = tmpsum[k].length();
			if (len < maxlen) {
				int chazhi = maxlen - len;
				StringBuilder bu0 = new StringBuilder();
				for (int i = 0; i < chazhi; i++) {
					bu0.append("0");
				}
				tmpsum[k] = bu0.toString() + tmpsum[k];
			}
		}
		// System.out.println();
		int carry = 0;
		for (int i = 0; i < tmpsum.length; i++) {
			int tm = Integer.parseInt(tmpsum[i]) + carry;
			int olen = tmpsum[i].length();
			carry = tm / 1000;
			/*
			 * tmpsum[i]=tm%1000+""; if(tmpsum[i].equals("0"))tmpsum[i]="000";
			 */
			tmpsum[i] = tm + "";
			if (carry > 0) {
				tmpsum[i] = tmpsum[i].substring((carry + "").length());
			}
			int len = tmpsum[i].length();
			if (len < olen && len < 3) {
				int chazhi1 = olen - len;
				int chazhi2 = 3 - len;
				int chazhi = Math.min(chazhi1, chazhi2);
				StringBuilder bu0 = new StringBuilder();
				for (int j = 0; j < chazhi; j++) {
					bu0.append("0");
				}
				tmpsum[i] = bu0.toString() + tmpsum[i];
			}
		}
		StringBuilder bu = new StringBuilder();
		for (int i = tmpsum.length - 1; i >= 0; i--) {
			bu.append(tmpsum[i]);
		}
		result = bu.toString();
		if (carry > 0) {
			result = carry + result;
		}
		int nm0 = nm0_1 + nm0_2;
		if (nm0 > 0) {
			bu = new StringBuilder();
			for (int i = 0; i < nm0; i++) {
				bu.append("0");
			}
			result = result + bu.toString();
		}
		return result;
	}

	/**
	 * 
	 * @Title: division
	 * @Description: 除法运算
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @return
	 */
	public static String division(String dividend, int divisor) {
		String str = dividend;
		StringBuilder bu = new StringBuilder();
		if (!dividend.matches("\\d+")) {
			return null;
		}
		StringBuilder tmbu = new StringBuilder();
		int tmnum = 0;
		int i = 0;
		while (i < str.length()) {
			tmbu.append(str.charAt(i));
			tmnum = Integer.parseInt(tmbu.toString());
			if (tmnum > divisor) {
				break;
			}
			i++;
		}
		bu.append(tmnum / divisor);
		int yushu = tmnum % divisor;
		if (i + 1 > str.length() - 1) {
			bu.append(" " + yushu);
			return bu.toString();
		}
		str = str.substring(i + 1);
		Queue<String> queue = new LinkedList<String>();
		for (int j = 0; j < str.length(); j++) {
			queue.add(String.valueOf(str.charAt(j)));
		}
		tmbu = new StringBuilder(yushu + "");
		while (!queue.isEmpty()) {

			tmbu.append(queue.poll());
			tmnum = Integer.parseInt(tmbu.toString());
			if (tmnum < divisor) {
				if (!queue.isEmpty())
					bu.append(0);
				else {
					bu.append(0);
					yushu = tmnum;
					break;
				}
			} else {
				bu.append(tmnum / divisor);
				yushu = tmnum % divisor;
				tmbu = new StringBuilder(yushu + "");

			}

		}
		return bu.toString() + " " + yushu;
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
