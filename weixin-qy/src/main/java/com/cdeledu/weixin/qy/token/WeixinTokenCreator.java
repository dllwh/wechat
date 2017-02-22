package com.cdeledu.weixin.qy.token;

import com.cdeledu.common.weixin.WeiXinConstants;
import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.model.TokenBean;
import com.cdeledu.weixin.base.token.TokenCreator;

/**
 * @类描述: 微信企业号TOKEN创建
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月31日 下午12:01:41
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%BB%E5%8A%A8%E8%B0%83%E7%94%A8">
 *      微信企业号获取token说明</a>
 * @see com.cdeledu.model.TokenBean
 */
public class WeixinTokenCreator implements TokenCreator {
	private final String corpid;
	private final String corpsecret;

	/**
	 * @param corpid
	 *            企业号ID
	 * @param corpsecret
	 *            企业号secret
	 */
	public WeixinTokenCreator(String corpid, String corpsecret) {
		this.corpid = corpid;
		this.corpsecret = corpsecret;
	}

	public String getCacheKey() {
		return String.format("wx_qy_token_%s", corpid);
	}

	public TokenBean createToken() throws WeixinException {
		String tokenUrl = String.format(WeiXinConstants.ASSESS_TOKEN_URL, corpid, corpsecret);
		System.out.println(tokenUrl);
		return null;
	}
}