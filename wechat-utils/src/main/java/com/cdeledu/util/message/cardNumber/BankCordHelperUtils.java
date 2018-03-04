package com.cdeledu.util.message.cardNumber;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;

/**
 * @类描述: 银行卡以及银行卡号码功能
 *       <ul>
 *       <li>银行卡是由"发卡行标识代码 + 自定义 + 校验码 "等部分组成的</li>
 *       <li></li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年5月5日 下午2:04:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class BankCordHelperUtils {
	// 银行卡号归属地查询
	public final static String YINHANGKADATA = "http://cha.yinhangkadata.com/";

	/**
	 * @方法描述: Luhm 校验方法
	 *        <ul>
	 *        <li>● 将未带校验位的 15(或18)位卡号从右依次编号 1 到 15(18)</li>
	 *        <li>● 位于奇数位号上的数字乘以 2</li>
	 *        <li>● 将奇位乘积的个十位全部相加,再加上所有偶数位上的数字</li>
	 *        <li>● 将加法和加上校验位能被 10 整除</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月5日 下午3:04:38
	 * @param CheckCodeCardId
	 * @return
	 */
	private final static char getBankCardCheckCode(String checkCodeCardId) {
		char[] chs = checkCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		// 前15或18位倒序存进数组
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';

			// 奇数位
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			} else { // 偶数位数组

			}
			luhmSum += k;
		}
		System.out.println("luhmSum:" + luhmSum);
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	/**
	 * @方法描述: 验证输入号码是否是正确的银行卡号
	 *        <ul>
	 *        <li>银行卡号一般是16位或者19位</li>
	 *        <li>● 前六位是：发行者标识代码</li>
	 *        <li>● 中间的位数是:个人账号标识(从卡号第七位开始) 中间位数由发卡行自定义,一般由6-12位数字组成</li>
	 *        <li>● 最后一位位数是校验位:将卡号前面的数字采用Luhn算法计算出信用卡或者借记卡的最后一位数字</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月5日 下午2:07:16
	 * @param cardId
	 *            银行卡号码
	 * @see <a href=
	 *      "http://cn.unionpay.com/cardCommend/gyylbzk/gaishu/file_6330036.html">
	 *      银联卡概述</a>
	 * @return
	 */
	public final static boolean checkBankCard(String cardNumber) {
		if (StringUtils.isEmpty(cardNumber) || !cardNumber.matches("\\d+")) {
			return false;
		}
		// 位数校验
		if (cardNumber.length() != 16 && cardNumber.length() != 19) {
			// return false;
		}
		// 判断是不是银联
		if (!StringUtils.startsWith(cardNumber, "62")) {
			// return false;
		}
		// 前15或18位
		int cardLength = cardNumber.length() - 1;
		return cardNumber
				.charAt(cardLength) == getBankCardCheckCode(cardNumber.substring(0, cardLength));
	}

	public static void main(String[] args) {
		String result = null;
		try {
			// "117.135.251.133", 83
			HttpURLConnHelper conn = HttpURLConnHelper.getInstance(ConstantHelper.GBK.name());
			result = conn.sendPostRequest(YINHANGKADATA, "card=955888020016888888");
			Document jsoup = Jsoup.parse(result);
			Element home = jsoup.select("div.home_so").select("div.home_show").first();
			Elements element = home.select("p");
			if (null != element.get(0).text() && StringUtils.isNoneBlank(element.get(1).text())) {
				System.out.println(element.get(1).text());
				System.out.println(element.get(2).text());
				System.out.println(element.get(3).text());
			}
		} catch (Exception e) {
		}

	}
}
