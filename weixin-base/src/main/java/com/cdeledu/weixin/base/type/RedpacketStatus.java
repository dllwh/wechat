package com.cdeledu.weixin.base.type;

/**
 * 
 * @ClassName: RedpacketStatus
 * @Description: 红包状态
 * @author: 独泪了无痕
 * @date: 2015年10月10日 上午9:47:46
 * @version: V1.0
 * @history:
 */
public enum RedpacketStatus {
	/**
	 * 发放中
	 */
	SENDING,
	/**
	 * 已发放待领取
	 */
	SENT,
	/**
	 * 发放失败
	 */
	FAILED,
	/**
	 * 已领取
	 */
	RECEIVED,
	/**
	 * 已退款
	 */
	REFUND;
}
