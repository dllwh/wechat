package com.cdeledu.weixin.base.type;

/**
 * @ClassName: TradeType
 * @Description: 微信支付类型
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:56:22
 * @version: V1.0
 * @history:
 */
public enum TradeType {
	/**
	 * H5页面上的JSAPI支付
	 */
	JSAPI,
	/**
	 * 刷卡支付
	 */
	MICROPAY,
	/**
	 * 扫描支付
	 */
	NATIVE,
	/**
	 * APP支付
	 */
	APP;
}
