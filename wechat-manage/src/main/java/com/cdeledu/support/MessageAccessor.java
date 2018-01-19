package com.cdeledu.support;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 国际化消息类封装
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月18日 上午9:44:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Component
public class MessageAccessor {
	/** ----------------------------------------------------- Fields start */
	private static MessageSourceAccessor messageSourceAccessor;

	/** ----------------------------------------------------- Fields end */

	public static MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}

	@Autowired
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		MessageAccessor.messageSourceAccessor = messageSourceAccessor;
	}

	public static String getText(String msgKey) {
		return messageSourceAccessor.getMessage(msgKey, Locale.getDefault());
	}

	public static String getText(String msgKey, Locale locale) {
		return messageSourceAccessor.getMessage(msgKey, locale);
	}
}
