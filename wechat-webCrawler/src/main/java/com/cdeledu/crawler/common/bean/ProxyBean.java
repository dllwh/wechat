package com.cdeledu.crawler.common.bean;

import java.io.Serializable;

/**
 * @类描述: 代理IP
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月29日 上午11:45:52
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ProxyBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	// 访问使用的代理服务器的地址
	private String proxyHost;
	// 访问使用的代理服务器的端口
	private int proxyPort;
	// 访问使用的代理服务器的用户名
	private String userName;
	// 访问使用的代理服务器的密码
	private String password;

	/** ----------------------------------------------------- Fields end */

	public ProxyBean() {
	}

	public ProxyBean(String proxyHost, int proxyPort) {
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "正在使用的代理ip [IP地址=" + proxyHost + ", IP端口=" + proxyPort + "]";
	}

}
