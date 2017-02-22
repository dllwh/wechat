package com.cdeledu.weixin.qy.api;

import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.cdeledu.weixin.base.api.BaseApi;
import com.cdeledu.weixin.base.model.WeixinQyAccount;
import com.cdeledu.weixin.base.util.WeixinConfigUtil;

/**
 * @ClassName: QyApi
 * @Description: 微信企业号API
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午2:01:16
 * @version: V1.0
 * @see <a href="http://qydev.weixin.qq.com/wiki/index.php">api文档</a>
 */
public class QyApi extends BaseApi {
	private final static ResourceBundle WEIXIN_BUNDLE;
	/** 默认使用weixinQyAccount.properties文件中的企业号信息 */
	public final static WeixinQyAccount DEFAULT_WEIXIN_ACCOUNT;
	static {
		WEIXIN_BUNDLE = ResourceBundle
				.getBundle("properties/weixin/weixinQyAccount");
		DEFAULT_WEIXIN_ACCOUNT = JSON.parseObject(
				WeixinConfigUtil.getValue("account"), WeixinQyAccount.class);

	}

	@Override
	protected ResourceBundle weixinBundle() {
		return WEIXIN_BUNDLE;
	}
}
