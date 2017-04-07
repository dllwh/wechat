package com.cdeledu.common.api.email.entity;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * @ClassName: MailAuthenticator
 * @Description: 服务器邮箱登陆验证
 * @author: 独泪了无痕
 * @date: 2015年9月10日 下午5:10:43
 * @version: V1.0
 * @history:
 */
public class MailAuthenticator extends Authenticator {
	// 发送邮件的服务器的IP
	private String mailServerHost;
	// 发送邮件的服务器的端口号
	private String mailServerPort;
	// 登陆邮件发送服务器的用户名
	private String userName;
	// 登陆邮件发送服务器的密码
	private String password;

	public String getMailServerHost() {
		return mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 初始化邮箱和密码
	 * 
	 * @param username
	 * @param password
	 */
	public MailAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public MailAuthenticator(String mailServerHost, String mailServerPort,
			String userName, String password) {
		super();
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.userName = userName;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}
