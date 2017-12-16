package com.cdeledu.common.constants;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 支付模块系统常量
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月16日 下午3:50:12
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PayConstant {
	/** 支付类型 */
	public enum PayType {
		ALI("支付宝", (short) 1), WECHAT("微信", (short) 2), UNION("银联", (short) 3);

		private Short code;
		private String name;

		private PayType(String name, Short code) {
			this.name = name;
			this.code = code;
		}

		public static String getName(Short code, String name) {
			for (PayType c : PayType.values()) {
				if (c.getCode() == code) {
					return c.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public short getCode() {
			return code;
		}

		public void setCode(short code) {
			this.code = code;
		}
	}

	/** 支付途径 */
	public enum PayWay {
		PC("PC,平板", (short) 1), MOBILE("手机", (short) 2);

		private Short code;
		private String name;

		private PayWay(String name, Short code) {
			this.name = name;
			this.code = code;
		}

		public static String getName(Short code, String name) {
			for (PayWay c : PayWay.values()) {
				if (c.getCode() == code) {
					return c.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public short getCode() {
			return code;
		}

		public void setCode(short code) {
			this.code = code;
		}

	}
}
