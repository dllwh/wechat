package com.cdeledu.weixin.mp.api;

import java.util.ResourceBundle;

import com.cdeledu.weixin.base.api.BaseApi;

/**
 * @ClassName: MpApi
 * @Description: 微信公众平台API
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午1:57:48
 * @version: V1.0
 * @see <a href="http://mp.weixin.qq.com/wiki/index.php">api文档</a>
 */
public class MpApi extends BaseApi {
	private final static ResourceBundle WEIXIN_BUNDLE;
	static {
		WEIXIN_BUNDLE = ResourceBundle.getBundle("properties/weixinMp");
	}
	@Override
	protected ResourceBundle weixinBundle() {
		return WEIXIN_BUNDLE;
	}
}
