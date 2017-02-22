package com.cdeledu.weixin.base.type;

/**
 * @ClassName: SuiteEventType
 * @Description: 第三方(应用套件)回调事件
 * @author: 独泪了无痕
 * @date: 2015年10月27日 下午7:47:30
 * @version: V1.0
 * @see <a
 *      href=""http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8
 *      %89%E6%96%B9%E5%9B%9E%E8%B0%83%E5%8D%8F%E8%AE%AE">第三方回调协议</a>
 */
public enum SuiteEventType {
	/**
	 * 推送ticket
	 */
	suite_ticket,
	/**
	 * 变更授权
	 */
	change_auth,
	/**
	 * 取消授权
	 */
	cancel_auth;
}
