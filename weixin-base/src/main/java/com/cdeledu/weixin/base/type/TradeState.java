package com.cdeledu.weixin.base.type;

/**
 * @ClassName: TradeState
 * @Description: 交易状态
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:55:16
 * @version: V1.0
 * @history:
 */
public enum TradeState {
	/**
	 * 支付成功
	 */
	SUCCESS,
	/**
	 * 转入退款
	 */
	REFUND,
	/**
	 * 未支付
	 */
	NOTPAY,
	/**
	 * 已关闭
	 */
	CLOSED,
	/**
	 * 已撤销
	 */
	REVOKED,
	/**
	 * 用户支付中
	 */
	USERPAYING,
	/**
	 * 未支付(输入密码或 确认支付超时)
	 */
	NOPAY,
	/**
	 * 支付失败(其他 原因,如银行返回失败)
	 */
	PAYERROR;
}
