package com.cdeledu.weixin.base.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: TokenBean
 * @author: 独泪了无痕
 * @Description: AccessToken是公众号、企业号的全局唯一票据，调用接口时需携带AccessToken<br>
 *               公众号调用各接口时都需使用access_token,正常情况下access_token有效期为7200秒<br>
 *               【公众号】重复获取将导致上次获取的access_token失效<br>
 *               【企业号】有效期内重复获取返回相同结果
 * @date: 2015年10月9日 下午5:26:02
 * @version: V1.0
 * @see <a
 *      href="http://mp.weixin.qq.com/wiki/11/0e4b294685f817b95cbed85ba5e82b8f.html">微信公众平台获取token</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%BB%E5%8A%A8%E8%B0%83%E7%94%A8">微信企业号的主动模式</a>
 * @history:
 */
public class TokenBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 获取到的凭证
	 */
	@JSONField(name = "access_token")
	private String accessToken;
	/**
	 * 凭证有效时间，单位：秒
	 */
	@JSONField(name = "expires_in")
	private int expiresIn;
	/**
	 * token创建的时间
	 */
	private long time;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
