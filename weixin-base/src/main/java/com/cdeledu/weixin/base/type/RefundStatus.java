package com.cdeledu.weixin.base.type;

/**
 * @ClassName: RefundStatus
 * @Description: 退款状态
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:45:19
 * @version: V1.0
 * @history:
 */
public enum RefundStatus {
	/**
	 * 退款成功
	 */
	SUCCESS,
	/**
	 * 退款失败
	 */
	FAIL,
	/**
	 * 退款处理中
	 */
	PROCESSING,
	/**
	 * 未确定,需要商户 原退款单号重新发起
	 */
	NOTSURE,
	/**
	 * 转入代发,退款到银行发现用户的卡作废或者冻结了,导致原路退款银行卡失败,资金回流到商户的现金帐号,需要商户人工干预,通过线下或者财付通转
	 * 账的方式进行退款。
	 */
	CHANGE;
}
