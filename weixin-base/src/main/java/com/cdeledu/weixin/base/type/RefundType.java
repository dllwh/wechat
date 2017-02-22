package com.cdeledu.weixin.base.type;

/**
 * @ClassName: RefundType
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:49:04
 * @version: V1.0
 * @history:
 */
public enum RefundType {
	/**
	 * 1:商户号余额退款;
	 */
	BALANCE(1),
	/**
	 * 2:现金帐号 退款;
	 */
	CASH(2),
	/**
	 * 3:优先商户号退款,若商户号余额不足, 再做现金帐号退款。 使用 2 或 3 时,需联系财 付通开通此功能
	 */
	BOTH(3);

	private int val;

	RefundType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
