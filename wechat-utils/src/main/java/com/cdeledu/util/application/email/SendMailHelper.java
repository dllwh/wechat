package com.cdeledu.util.application.email;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.cdeledu.util.application.QvoConditionUtil;

/**
 * @Description: JavaMail：邮件发送
 * @author: 独泪了无痕
 * @date: 2015年7月25日 上午11:55:49
 * @version: V1.0
 */
final class SendMailHelper {
	/** ----------------------------------------------------- Fields start */
	private static final String CHARSET = "utf-8";

	// 发送邮件的服务器的IP
	private String mailServerHost;
	// 发送邮件的服务器的端口号
	private Integer mailServerPort;
	/** 登陆邮件发送服务器的用户名 */
	private String mailSenderUsername;
	/** 登陆邮件发送服务器的密码 */
	private String mailSenderPassword;
	/** 发件人昵称 */
	private String mailSenderNickName;

	/** ----------------------------------------------------- Fields end */
	public SendMailHelper(String mailServerHost, Integer mailServerPort, String mailSenderUsername,
			String mailSenderPassword) {
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.mailSenderUsername = mailSenderUsername;
		this.mailSenderPassword = mailSenderPassword;
	}

	public SendMailHelper(String mailServerHost, Integer mailServerPort, String mailSenderUsername,
			String mailSenderPassword, String mailSenderNickName) {
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.mailSenderUsername = mailSenderUsername;
		this.mailSenderPassword = mailSenderPassword;
		this.mailSenderNickName = mailSenderNickName;
	}

	/**
	 * @方法描述 :发送简单文本邮件
	 * @param fromAddress
	 *            邮件相关信息 - 发件人的地址
	 * @param nickName
	 *            邮件相关信息 - 发件人昵称
	 * @param subject
	 *            邮件相关信息 - 邮件主题
	 * @param textMsg
	 *            邮件相关信息 - 邮件正文text
	 * @param receivers
	 *            邮件相关信息 - 接收人列表
	 * @param ccReceivers
	 *            邮件相关信息 - 抄送人列表
	 * @param bccReceivers
	 *            邮件相关信息 - 密送人列表
	 * @throws EmailException
	 */
	public void sendEmail(final String subject, final String textMsg, final String[] receivers,
			final String[] ccReceivers, final String[] bccReceivers) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(mailServerHost);
		if (QvoConditionUtil.checkInteger(mailServerPort)) {
			email.setSmtpPort(mailServerPort);
		}
		// 设置授权帐号和密码
		email.setAuthenticator(new DefaultAuthenticator(mailSenderUsername, mailSenderPassword));
		// 是否采用ssl方式连接服务
		email.setSSLOnConnect(true);
		// 设置邮件主体编码,必须放在前面，否则乱码
		email.setCharset(CHARSET);

		// 设置发送人邮箱和名字
		if (StringUtils.isNotEmpty(mailSenderNickName)) {
			email.setFrom(mailSenderUsername, mailSenderNickName, CHARSET);
		} else {
			email.setFrom(mailSenderUsername);
		}

		// 设置邮件标题
		email.setSubject(subject);
		// 设置邮件主体
		email.setMsg(textMsg);
		// 添加收件人地址,可以是多个
		if (ArrayUtils.isNotEmpty(receivers)) {
			email.addTo(receivers);
		}
		// 添加抄送人地址
		if (ArrayUtils.isNotEmpty(ccReceivers)) {
			email.addCc(ccReceivers);
		}
		// 添加密送人地址
		if (ArrayUtils.isNotEmpty(bccReceivers)) {
			email.addBcc(bccReceivers);
		}
		// 添加回复人地址(暂未验证)
		// email.addReplyTo("1349310440@qq.com");

		// 增加需要回执的标记(暂未验证)
		// email.addHeader("Disposition-Notification-To", "1");
		// 通常情况下，不能投递给收件者的邮件将会退回给发件人,，只需调用setBounceAddress 进行 退回邮件处理
		email.setBounceAddress(mailSenderUsername);

		// 确定发送邮件动作
		email.send();
	}

	/**
	 * @方法描述 : 发送文本格式或Html格式的Email的方式
	 * @param fromAddress
	 *            邮件相关信息 - 发件人的地址
	 * @param subject
	 *            邮件相关信息 - 邮件主题
	 * @param textMsg
	 *            邮件相关信息 - 邮件正文text
	 * @param receivers
	 *            邮件相关信息 - 接收人列表
	 * @param ccReceivers
	 *            邮件相关信息 - 抄送人列表
	 * @param bccReceivers
	 *            邮件相关信息 - 密送人列表
	 */
	public void sendTextEmail(final String subject, final String textMsg, final String[] receivers,
			final String[] ccReceivers, final String[] bccReceivers) {
	}

	/**
	 * @方法描述 : 发送HTML格式的邮件
	 * @param fromAddress
	 *            邮件相关信息 - 发件人的地址
	 * @param subject
	 *            邮件相关信息 - 邮件主题
	 * @param textMsg
	 *            邮件相关信息 - 邮件正文text
	 * @param receivers
	 *            邮件相关信息 - 接收人列表
	 * @param ccReceivers
	 *            邮件相关信息 - 抄送人列表
	 * @param bccReceivers
	 *            邮件相关信息 - 密送人列表
	 * @return
	 * @throws EmailException
	 */
	public void sendHtmlMail(final String subject, final String HtmlMsg, String textMsg,
			final String[] receivers, final String[] ccReceivers, final String[] bccReceivers)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setCharset(CHARSET);
		// 设置smtp服务器地址
		email.setHostName(mailServerHost);
		// 设置smtp服务器端
		email.setSmtpPort(mailServerPort);
		// 设置授权帐号和密码
		email.setAuthenticator(new DefaultAuthenticator(mailSenderUsername, mailSenderPassword));
		// 是否采用ssl方式连接服务
		email.setSSLOnConnect(true);

		email.setSubject(subject);
		email.setHtmlMsg(HtmlMsg);
		if (StringUtils.isEmpty(textMsg)) {
			textMsg = "Your email client does not support HTML messages";
		}
		email.setTextMsg(textMsg);

		if (StringUtils.isNotEmpty(mailSenderNickName)) {
			email.setFrom(mailSenderUsername, mailSenderNickName, CHARSET);
		} else {
			email.setFrom(mailSenderUsername);
		}
		// 添加收件人地址,可以是多个
		if (ArrayUtils.isNotEmpty(receivers)) {
			email.addTo(receivers);
		}
		// 添加抄送人地址
		if (ArrayUtils.isNotEmpty(ccReceivers)) {
			email.addCc(ccReceivers);
		}
		// 添加密送人地址
		if (ArrayUtils.isNotEmpty(bccReceivers)) {
			email.addBcc(bccReceivers);
		}
		// 发送邮件
		email.send();
	}	
}
