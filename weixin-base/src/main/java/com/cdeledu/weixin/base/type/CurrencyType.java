package com.cdeledu.weixin.base.type;

/**
 * @ClassName: CurrencyType
 * @Description: 钱币种类
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:39:25
 * @version: V1.0
 * @history:
 */
public enum CurrencyType {
	CNY("人民币"), 
	HKD("港元"), 
	TWD("台币"), 
	EUR("欧元"), 
	USD("美元"), 
	GBP("英镑"), 
	JPY("日元");
	private String desc;

	CurrencyType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
