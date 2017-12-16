package com.cdeledu.service.pay;

public interface WeixinPayService {

	/** 微信支付下单 */
	String weixinPay();

	/** 微信支付退款 */
	String weixinRefund();

	/**
	 * 关闭订单 *
	 */
	String weixinCloseorder();

	/** 下载微信账单 */
	void saveBill();

	/** 微信公众号支付返回一个url地址 */
	String weixinPayMobile();

	/**
	 * <pre>
	 * H5支付 唤醒 微信APP
	 * 进行支付 申请入口：登录商户平台-->产品中心-->我的产品-->支付产品-->H5支付
	 * </pre>
	 */
	String weixinPayH5();
}
