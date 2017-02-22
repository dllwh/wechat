package com.cdeledu.weixin.base.type;

/**
 * @ClassName: BillType
 * @Description: 对账单类型
 * @author: 独泪了无痕
 * @date: 2015-10-13 下午10:36:00
 * @version: V1.0
 */
public enum BillType {
	/**
	 * 全部
	 */
	ALL(0),
	/**
	 * 成功订单
	 */
	SUCCESS(1),
	/**
	 * 退款订单
	 */
	REFUND(2);
	private int val;

	BillType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
