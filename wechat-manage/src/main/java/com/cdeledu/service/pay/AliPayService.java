package com.cdeledu.service.pay;

public interface AliPayService {
	/** 支付预下单 */
	String aliPay();

	/** 支付退款 */
	String aliRefund();

	/** 关闭订单 */
	String aliCloseorder();

	/** 下载对账单 */
	String downloadBillUrl();

	/** 手机H5支付 */
	String aliPayMobile();

	/** 网站支付 */
	String aliPayPc();
}
