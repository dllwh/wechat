package com.cdeledu.weixin.base.type;

/**
 * 
 * @ClassName: MPPaymentCheckNameType
 * @Description: 企业付款检查收款人姓名的策略
 * @author: 独泪了无痕
 * @date: 2015-10-12 下午11:59:35
 * @version: V1.0
 */
public enum MPPaymentCheckNameType {
	/**
	 * 不校验真实姓名
	 */
	NO_CHECK,
	/**
	 * 强校验真实姓名（未实名认证的用户会校验失败，无法转账）
	 */
	FORCE_CHECK,
	/**
	 * 针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
	 */
	OPTION_CHECK;
}
