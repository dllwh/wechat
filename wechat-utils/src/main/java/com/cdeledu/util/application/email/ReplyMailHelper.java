package com.cdeledu.util.application.email;

import javax.mail.Message;

/**
 * @Description: 邮件的回复
 * @author: 独泪了无痕
 * @date: 2015年9月15日 上午10:21:55
 * @version: V1.0
 * @history:
 */
final class ReplyMailHelper {
	/**
	 * @方法描述 : 邮件回复方法
	 * @思路：先获取要回复的邮件，然后获取邮件的各个详情，把详情附加给新建邮件，发送
	 */
	public static void replyEmail() {
		newReplyMessage();
	}

	/**
	 * @方法描述 :组装要回复的邮件
	 * @return
	 */
	public static Message newReplyMessage() {
		return null;
	}
}
