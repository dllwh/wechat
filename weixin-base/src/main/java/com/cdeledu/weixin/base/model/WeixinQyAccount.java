package com.cdeledu.weixin.base.model;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: WeixinQyAccount
 * @Description: 微信企业号信息
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午2:59:23
 * @version: V1.0
 * @history:
 */
public class WeixinQyAccount extends WeixinAccount {
	private static final long serialVersionUID = 1L;

	@JSONCreator
	public WeixinQyAccount(@JSONField(name = "id") String id,
			@JSONField(name = "secret") String secret) {
		super(id, secret);
	}
}
