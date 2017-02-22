package com.cdeledu.common.httpEntity;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @类描述: 代理服务器验证
 * @创建者: 独泪了无痕
 * @创建日期: 2015年9月15日 下午4:16:21
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class BasicAuthenticator extends Authenticator {
	// http访问要使用的代理服务器的用户名
	String userName;
	// http访问要使用的代理服务器的密码
	String password;

	public BasicAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * 代理服务器验证(重写)
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password.toCharArray());
	}

}
