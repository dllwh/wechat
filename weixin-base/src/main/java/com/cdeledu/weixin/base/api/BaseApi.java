package com.cdeledu.weixin.base.api;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cdeledu.weixin.base.http.WeixinRequestExecutor;
import com.cdeledu.weixin.base.model.WeixinAccount;
import com.cdeledu.weixin.base.util.WeixinConfigUtil;

/**
 * @ClassName: BaseApi
 * @Description: API基础
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午1:56:23
 * @version: V1.0
 * @see <a href="http://mp.weixin.qq.com/wiki/index.php">微信公众平台API文档</a>
 * @see <a href="http://qydev.weixin.qq.com/wiki/index.php">微信企业号API文档</a>
 */
public abstract class BaseApi {
	// 负责微信请求的执行
	protected final WeixinRequestExecutor weixinExecutor;

	protected abstract ResourceBundle weixinBundle();

	/**
	 * 默认使用weixin.properties文件中的公众号信息
	 */
	public final static WeixinAccount DEFAULT_WEIXIN_ACCOUNT;
	static {
		DEFAULT_WEIXIN_ACCOUNT = WeixinConfigUtil.getWeixinAccount();
	}

	public BaseApi() {
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	/**
	 * 
	 * @Title: getRequestUri
	 * @Description: 读取.properties文件的键值对
	 * @author: 独泪了无痕
	 * @param key
	 * @return
	 */
	protected String getRequestUri(String key) {
		String url = weixinBundle().getString(key);
		Pattern p = Pattern.compile("(\\{[^\\}]*\\})");
		Matcher m = p.matcher(url);
		StringBuffer sb = new StringBuffer();
		String sub = null;
		while (m.find()) {
			sub = m.group();
			m.appendReplacement(sb,
					getRequestUri(sub.substring(1, sub.length() - 1)));
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
