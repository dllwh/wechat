package com.cdeledu.service.pay.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.pay.PayBank;
import com.cdeledu.model.pay.PaymentConfig;
import com.cdeledu.service.pay.PaymentService;
import com.google.common.collect.Lists;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 支付管理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年5月1日 下午9:22:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Service
public class PaymentServiceImpl implements PaymentService {
	private final static String PREFIX = "com.cdeledu.dao.impl.pay.paymentDaoImpl.";
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:获取支付配置
	 * @创建人:独泪了无痕
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PaymentConfig> getPaymentConfigList() throws Exception {
		return (List<PaymentConfig>) baseDao.findListForJdbcParam(PREFIX + "getPaymentConfigList");
	}

	/**
	 * @方法:获取支付配置
	 * @创建人:独泪了无痕
	 * @throws Exception
	 */
	public PaymentConfig getPaymentConfigInfo(Integer id) throws Exception {
		if(id == null){
			return null;
		}
		return (PaymentConfig) baseDao.findOneForJdbcParam(PREFIX + "getPaymentConfigInfo", id);
	}

	/**
	 * 获取配置的银行信息
	 */
	@SuppressWarnings("unchecked")
	public List<PayBank> getPaymentConfigBank(Integer id) throws Exception {
		if(id == null){
			return Lists.newArrayList();
		}
		return (List<PayBank>) baseDao.findListForJdbcParam(PREFIX + "getPaymentConfigBank",id);
	}
	
	@SuppressWarnings("unchecked")
	public List<PayBank> getNotExistPaymentBank(Integer id) throws Exception {
		if(id == null){
			return Lists.newArrayList();
		}
		return (List<PayBank>) baseDao.findListForJdbcParam(PREFIX + "getNotExistPaymentBank",id);
	}
}
