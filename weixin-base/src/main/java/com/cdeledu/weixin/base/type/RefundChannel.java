package com.cdeledu.weixin.base.type;

/**
 * @ClassName: RefundChannel
 * @Description: 退款渠道
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:43:41
 * @version: V1.0
 * @history:
 */
public enum RefundChannel {
	/**
	 * 原路退款
	 */
	ORIGINAL,
	/**
	 * 退回到余额
	 */
	BALANCE,
	/**
	 * 财付通
	 */
	TENPAY,
	/**
	 * 银行
	 */
	BANK;
}
