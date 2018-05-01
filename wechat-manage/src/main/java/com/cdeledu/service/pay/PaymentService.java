package com.cdeledu.service.pay;

import java.util.List;

import com.cdeledu.model.pay.PaymentConfig;

public interface PaymentService {
	public List<PaymentConfig> getPaymentConfigList() throws Exception;
}
