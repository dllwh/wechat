package com.cdeledu.model.pay;

import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 支付配置
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年5月1日 下午10:38:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PaymentConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString()+ "\r\n PaymentConfig [name=" + name + "]";
	}

}
