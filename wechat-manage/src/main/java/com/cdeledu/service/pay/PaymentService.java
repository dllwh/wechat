package com.cdeledu.service.pay;

import java.util.List;

import com.cdeledu.model.pay.PayBank;
import com.cdeledu.model.pay.PaymentConfig;

public interface PaymentService {
	List<PaymentConfig> getPaymentConfigList() throws Exception;

	PaymentConfig getPaymentConfigInfo(Integer id) throws Exception;

	List<PayBank> getPaymentConfigBank(Integer id) throws Exception;

	List<PayBank> getNotExistPaymentBank(Integer id) throws Exception;
}
