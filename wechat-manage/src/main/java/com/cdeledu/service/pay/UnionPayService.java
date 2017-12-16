package com.cdeledu.service.pay;

import java.util.Map;

public interface UnionPayService {
	/** 银联支付 */
	String unionPay();

	/** 前台回调验证 */
	String validate(Map<String, String> valideData, String encoding);

	/** 对账单下载 */
	void fileTransfer();
}
