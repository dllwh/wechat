package com.cdeledu.weixin.base.type;

/**
 * @ClassName: IdType
 * @Description: ID类型
 * @author: 独泪了无痕
 * @date: 2015-10-13 下午10:17:23
 * @version: V1.0
 */
public enum IdType {
	/**
	 * 微信退款单号
	 */
	REFUNDID("refund_id"),
	/**
	 * 微信订单号
	 */
	TRANSACTIONID("transaction_id"),
	/**
	 * 商户订单号
	 */
	TRADENO("out_trade_no"),
	/**
	 * 商户退款号
	 */
	REFUNDNO("out_refund_no"),
	/**
	 * 成员id
	 */
	USERID("userid"),
	/**
	 * 成员id
	 */
	CHATID("chatid");
	private String name;

	IdType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
