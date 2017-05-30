package com.cdeledu.util.application.email;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.api.email.entity.AttachBean;
import com.cdeledu.common.api.email.entity.EmailInfo;

/**
 * @Description: JavaMail：邮件发送
 * @author: 独泪了无痕
 * @date: 2015年7月25日 上午11:55:49
 * @version: V1.0
 */
class SendMailHelper {
	/**
	 * Message对象将存储我们实际发送的电子邮件信息，
	 */
	private MimeMessage msg;
	/**
	 * Session类代表JavaMail中的一个邮件会话。
	 */
	private Session session;

	/**
	 * 
	 * @Title: createSession
	 * @Description:
	 * 				<ul>
	 *               <li>邮件服务器 认证</li>
	 *               <li>Session是JavaMail提供者配置文件以及设置属性信息的“容器”,其本身不会和邮件服务器进行任何的通信
	 *               </li>
	 *               </ul>
	 * @author: 独泪了无痕
	 * @param host
	 * @param username
	 * @param password
	 * @param debug
	 * @return
	 */
	public Session createSession(String host, final String username, final String password,
			boolean debug) {
		Properties prop = new Properties();
		// 指定主机
		if (StringUtils.isNoneEmpty(host))
			prop.setProperty("mail.host", host);
		// 指定验证为true
		prop.setProperty("mail.smtp.auth", "true");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		// 创建一个新的Session实例，它不会在JVM中被作为默认实例共享；
		session = Session.getInstance(prop, auth);
		session.setDebug(debug);// 开启后有调试信息
		return session;
	}

	/**
	 * 
	 * @Title：send
	 * @Description：发送指定的邮件
	 * @param mail
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 * @return：void 返回类型
	 */
	public boolean sendAttach(Session session, EmailInfo mail) {
		try {
			// 创建一个 Message,请将 Session 对象传递给 MimeMessage 构造器
			msg = new MimeMessage(session);
			// 设置发件人
			msg.setFrom(new InternetAddress(mail.getFromAddress()));
			// 设置收件人
			msg.addRecipients(RecipientType.TO, mail.getToAddress());
			// 设置抄送
			String cc = mail.getCcAddress();
			if (StringUtils.isNotBlank(cc)) {
				msg.addRecipients(RecipientType.CC, cc);
			}
			// 设置暗送
			String bcc = mail.getBccAddress();
			if (StringUtils.isNotBlank(bcc)) {
				msg.addRecipients(RecipientType.BCC, bcc);
			}
			// 设置主题
			msg.setSubject(mail.getSubject());
			// 添加附件
			MimeMultipart parts = new MimeMultipart();// 创建部件集对象

			MimeBodyPart part = new MimeBodyPart();// 创建一个部件
			part.setContent(mail.getContent(), "text/html;charset=utf-8");// 设置邮件文本内容
			parts.addBodyPart(part);// 把部件添加到部件集中

			// 添加附件
			List<AttachBean> attachBeanList = mail.getAttachs();// 获取所有附件
			if (attachBeanList != null) {
				for (AttachBean attach : attachBeanList) {
					MimeBodyPart attachPart = new MimeBodyPart();// 创建一个部件
					attachPart.attachFile(attach.getFile());// 设置附件文件
					// 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
					// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
					attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));// 设置附件文件名
					String cid = attach.getCid();
					if (cid != null) {
						attachPart.setContentID(cid);
					}
					parts.addBodyPart(attachPart);
				}
			}
			// 将multipart对象放到message中
			msg.setContent(parts);
			// 保存邮件
			msg.saveChanges();
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	/**
	 * 
	 * @Title：sendTextMail
	 * @Description：以文本格式发送邮件(普通邮件) @param EmailInfo
	 * @return
	 * @return：boolean 返回类型
	 */
	public boolean sendTextMail(EmailInfo emailInfo) {
		return false;
	}

	/**
	 * @Description：发送HTML格式的邮件
	 * @param EmailInfo
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 * @return：boolean 返回类型
	 */
	public boolean sendHtmlMail(EmailInfo emailInfo) {
		try {
			// 创建一个 Message,请将 Session 对象传递给 MimeMessage 构造器
			msg = new MimeMessage(session);
			// 设置发件人
			msg.setFrom(new InternetAddress(emailInfo.getFromAddress()));
			// 设置收件人
			msg.addRecipients(RecipientType.TO, emailInfo.getToAddress());
			// 设置主题
			msg.setSubject(emailInfo.getSubject());
			MimeBodyPart part = new MimeBodyPart();// 创建一个部件
			part.setContent(emailInfo.getContent(), "text/html;charset=utf-8");// 设置邮件文本内容
			// 保存邮件
			msg.saveChanges();
			Transport.send(msg);
			return true;
		} catch (Exception sExp) {
			sExp.printStackTrace();
			return false;
		}
	}
}
