package com.cdeledu.util.application.email;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮箱帮助类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月28日 上午12:38:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class EmailHelper {
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

	/** ----------------------------------------------------- Fields end */
	public EmailHelper(String mailServerHost, Integer mailServerPort, String mailSenderUsername,
			String mailSenderPassword) {
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.mailSenderUsername = mailSenderUsername;
		this.mailSenderPassword = mailSenderPassword;
	}

	/**
	 * @方法描述 :发送简单文本邮件
	 * @param fromAddress
	 *            发件人(邮件发送者的地址)
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件正文text
	 * @param receivers
	 *            接收人列表
	 * @param ccReceivers
	 *            抄送人列表
	 * @param bccReceivers
	 *            密送人列表
	 * @throws EmailException
	 */
	public void sendEmail(final String fromAddress, final String subject, final String textMsg,
			final List<String> receivers, final List<String> ccReceivers,
			final List<String> bccReceivers) throws EmailException {
		Email email = new SimpleEmail();
		// 设置smtp服务器地址
		email.setHostName(mailServerHost);
		// 设置smtp服务器端
		email.setSmtpPort(mailServerPort);
		// 设置授权帐号和密码
		email.setAuthenticator(new DefaultAuthenticator(mailSenderUsername, mailSenderPassword));
		// 是否采用ssl方式连接服务
		email.setSSLOnConnect(true);
		// 设置与服务器连接session
		// email.setMailSession();
		// 设置邮件主体编码,必须放在前面，否则乱码
		email.setCharset(CHARSET);
		// // 设置发送人邮箱和名字
		email.setFrom(fromAddress, fromAddress, CHARSET);
		// 设置邮件标题
		email.setSubject(subject);
		// 设置邮件主体
		email.setMsg(textMsg);
		// 添加收件人地址,可以是多个
		for (String receiver : receivers) {
			email.addTo(receiver);
		}
		// 添加抄送人地址
		for (String ccReceiver : ccReceivers) {
			email.addCc(ccReceiver);
		}
		// 添加密送人地址
		for (String bccReceiver : bccReceivers) {
			email.addBcc(bccReceiver);
		}
		// 添加回复人地址
		// email.addReplyTo("1349310440@qq.com");
		// 确定发送邮件动作
		email.send();
	}

	/**
	 * @方法描述 : 发送文本格式或Html格式的Email的方式
	 * @param fromAddress
	 *            发件人(邮件发送者的地址)
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件正文text
	 * @param receivers
	 *            接收人列表
	 * @param ccReceivers
	 *            抄送人列表
	 * @param bccReceivers
	 *            密送人列表
	 */
	public void sendTextEmail(final String fromAddress, final String subject, final String content,
			final List<String> receivers, final List<String> ccReceivers,
			final List<String> bccReceivers) {
	}

	/**
	 * @方法描述 : 发送HTML格式的邮件
	 * @param fromAddress
	 *            发件人(邮件发送者的地址)
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件正文text
	 * @param receivers
	 *            接收人列表
	 * @param ccReceivers
	 *            抄送人列表
	 * @param bccReceivers
	 *            密送人列表
	 * @return
	 */
	public void sendHtmlMail(final String fromAddress, final String subject, final String content,
			final List<String> receivers, final List<String> ccReceivers,
			final List<String> bccReceivers) {
	}

	/**
	 * @方法描述 : 发送带附件的电子邮件的应用
	 * @param fromAddress
	 *            发件人(邮件发送者的地址)
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件正文text
	 * @param receivers
	 *            接收人列表
	 * @param ccReceivers
	 *            抄送人列表
	 * @param bccReceivers
	 *            密送人列表
	 */
	public void sentAttacheEmail(final String fromAddress, final String subject,
			final String content, final List<String> receivers, final List<String> ccReceivers,
			final List<String> bccReceivers) {
	}
}
