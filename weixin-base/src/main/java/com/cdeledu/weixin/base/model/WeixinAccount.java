package com.cdeledu.weixin.base.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: WeixinAccount
 * @Description: 微信账号信息
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午2:05:16
 * @version: V1.0
 * @history:
 */
public class WeixinAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 唯一的身份标识 */
	private String id;
	/** 调用接口的密钥 */
	private String secret;

	public String getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}

	@JSONCreator
	public WeixinAccount(@JSONField(name = "id") String id,
			@JSONField(name = "secret") String secret) {
		this.id = id;
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "id=" + id + ", secret=" + secret;
	}
}
