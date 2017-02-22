package com.cdeledu.weixin.base.token;

import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.model.TokenBean;

/**
 * @类描述: 负责创建新的token
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月31日 下午12:00:07
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface TokenCreator {
	/** 返回缓存KEY的名称 */
	public String getCacheKey();

	/** 创建token */
	public TokenBean createToken() throws WeixinException;
}
