package com.cdeledu.weixin.base.payment;

import com.cdeledu.weixin.base.api.CashApi;
import com.cdeledu.weixin.base.api.CouponApi;
import com.cdeledu.weixin.base.api.Pay3Api;

/**
 * @ClassName: WeixinPayProxy
 * @Description: 微信支付接口实现
 * @author: 独泪了无痕
 * @date: 2015年12月7日 下午8:03:10
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/index.html">商户平台支付API</a>
 */
public class WeixinPayProxy {
	/** -------------------------- 属性 begin ------------------------------- */
	private final Pay3Api pay3Api;
	private final CouponApi couponApi;
	private final CashApi cashApi;

	public WeixinPayProxy(Pay3Api pay3Api, CouponApi couponApi, CashApi cashApi) {
		this.pay3Api = pay3Api;
		this.couponApi = couponApi;
		this.cashApi = cashApi;
	}

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	/** -------------------------- 公有方法 end ------------------------------- */
}
