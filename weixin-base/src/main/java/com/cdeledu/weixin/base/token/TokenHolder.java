package com.cdeledu.weixin.base.token;

import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.model.TokenBean;

/**
 * @类描述: 对token的缓存获取
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月10日 上午10:22:44
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class TokenHolder {
	/** ----------------------------------------------------- Fields start */
	// token的创建
	private final TokenCreator tokenCreator;
	// token的存储
	private final TokenStorager tokenStorager;

	public TokenHolder(TokenCreator tokenCreator, TokenStorager tokenStorager) {
		this.tokenCreator = tokenCreator;
		this.tokenStorager = tokenStorager;
	}

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法: 获取token对象
	 * @创建人:独泪了无痕
	 * @return
	 * @throws WeixinException
	 */
	public TokenBean getToken() throws WeixinException {
		String cacheKey = tokenCreator.getCacheKey();
		TokenBean token = tokenStorager.lookup(cacheKey);
		if (null == token) {
			token = tokenCreator.createToken();
			tokenStorager.caching(cacheKey, token);
		}

		return token;
	}

	/**
	 * @方法:获取token字符串
	 * @创建人:独泪了无痕
	 * @return
	 * @throws WeixinException
	 */
	public String getAccessToken() throws WeixinException {
		return getToken().getAccessToken();
	}

	/**
	 * @方法:手动刷新token
	 * @创建人:独泪了无痕
	 * @return 刷新后的token对象
	 * @throws WeixinException
	 */
	public TokenBean refreshToken() throws WeixinException {
		String cacheKey = tokenCreator.getCacheKey();
		TokenBean token = tokenCreator.createToken();
		tokenStorager.caching(cacheKey, token);
		return token;
	}

	/**
	 * @方法:手动移除token
	 * @创建人:独泪了无痕
	 * @return 被移除的token
	 * @throws WeixinException
	 */
	public TokenBean evictToken() throws WeixinException {
		String cacheKey = tokenCreator.getCacheKey();
		return tokenStorager.evict(cacheKey);
	}
}
