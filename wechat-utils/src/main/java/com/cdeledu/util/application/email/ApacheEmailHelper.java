package com.cdeledu.util.application.email;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Apache Commons Email
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年9月18日 下午4:58:11
 * @版本: V 0.0.1
 * @since: JDK 1.7
 */
public final class ApacheEmailHelper {
	/** ----------------------------------------------------- Fields start */
	private static final String CHARSET = ConstantHelper.UTF_8.name();
	/** 是否开始调试模式 */
	private boolean debug = false;
	/** ----------------------------------------------------- Fields end */

	/** 邮件服务器的IP */
	private String serverHost;
	/** 登陆邮件服务器的用户名 */
	private String serverUsername;
	/** 登陆邮件服务器的密码 */
	private String serverPassword;
	/** 发件人昵称 */
	private String serverNickName;

	public ApacheEmailHelper(String serverHost, String serverUsername, String serverPassword) {
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
	}

	public ApacheEmailHelper(String serverHost, String serverUsername, String serverPassword,
			String serverNickName) {
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
		this.serverNickName = serverNickName;
	}

	/************************************************************************/
	/******************* 邮件发送 **************************************/
	/************************************************************************/

	/**
	 * @方法描述 : 邮箱服务基本配置
	 * @param email
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
	private Email getEmail(Email email, String subject, String textMsg, String[] receivers,
			String[] ccReceivers, String[] bccReceivers) throws EmailException {
		// 设置服务器地址
		email.setHostName(serverHost);
		// 设置授权帐号和密码
		email.setAuthenticator(new DefaultAuthenticator(serverUsername, serverPassword));
		// 设置邮件主体编码,必须放在前面，否则乱码
		email.setCharset(CHARSET);
		email.setDebug(debug);
		// // 设置发送人邮箱和名字
		if (StringUtils.isNotEmpty(serverNickName)) {
			email.setFrom(serverUsername, serverNickName, CHARSET);
		} else {
			email.setFrom(serverUsername);
		}

		// 设置邮件标题
		email.setSubject(subject);
		// 设置邮件主体
		if (StringUtils.isNotBlank(textMsg)) {
			email.setMsg(textMsg);
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

		// 添加回复人地址(暂未验证)
		// email.addReplyTo("1349310440@qq.com");

		// 增加需要回执的标记(暂未验证)
		// email.addHeader("Disposition-Notification-To", "1");
		// 通常情况下，不能投递给收件者的邮件将会退回给发件人,，只需调用setBounceAddress 进行 退回邮件处理
		email.setBounceAddress(serverUsername);

		return email;
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
	public void sendEmail(String subject, String textMsg, String[] receivers, String[] ccReceivers,
			String[] bccReceivers) throws EmailException {
		Email email = new SimpleEmail();
		getEmail(email, subject, textMsg, receivers, ccReceivers, bccReceivers);
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
	public void sendTextEmail(String subject, String textMsg, String[] receivers,
			String[] ccReceivers, String[] bccReceivers) {
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
	public void sendHtmlMail(String subject, String HtmlMsg, String textMsg, String[] receivers,
			String[] ccReceivers, String[] bccReceivers) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		getEmail(email, subject, textMsg, receivers, ccReceivers, bccReceivers);

		if (StringUtils.isNotBlank(HtmlMsg)) {
			email.setHtmlMsg(HtmlMsg);
		}
		if (StringUtils.isEmpty(textMsg)) {
			textMsg = "Your email client does not support HTML messages";
		}
		email.setTextMsg(textMsg);

		// 发送邮件
		email.send();
	}

	/**
	 * @方法描述 : 发送带有附件的邮件
	 */
	public void sendMultiPartEmail(String subject, String textMsg, String[] receivers,
			String[] ccReceivers, String[] bccReceivers) throws EmailException {
		// 设置邮件信息
		MultiPartEmail email = new MultiPartEmail();
		getEmail(email, subject, textMsg, receivers, ccReceivers, bccReceivers);

		// 创建附件(本地不存在的附件,在网络上下载)
		EmailAttachment attachment = new EmailAttachment();

		// 添加附件到邮箱中
		email.attach(attachment);

		// 发送邮件
		email.send();
	}

	/************************************************************************/
	/******************* 邮件接收 **************************************/
	/************************************************************************/

	/************************************************************************/
	/******************* 邮件删除 **************************************/
	/************************************************************************/
	public void deleteMail() {

	}

	/************************************************************************/
	/******************* 移动邮件 **************************************/
	/************************************************************************/
	public void moveMail() {

	}

	public static void main(String[] args) throws Exception {
		ApacheEmailHelper apacheEmailHelper = new ApacheEmailHelper("smtp.sina.com",
				"dllwhcrawler@sina.com", "dllwhcrawler");
		// apacheEmailHelper.sendEmail("测试", "来自一封遥远星球的友好问候",
		// "1349310440@qq.com".split(","), null,null);
		apacheEmailHelper.sendHtmlMail(null, null, null, "1349310440@qq.com".split(","), null,
				null);

	}
}
