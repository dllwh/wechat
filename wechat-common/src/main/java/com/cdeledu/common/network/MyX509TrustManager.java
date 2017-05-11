package com.cdeledu.common.network;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * @描述: 证书信任管理器(用于处理https请求)<br>
 *      这个证书管理器的作用就是让它信任我们指定的证书<br>
 *      即:信任所有证书,不管是否权威机构颁发
 * @author: 独泪了无痕
 * @date: 2015年10月30日 上午10:42:23
 * @version: V1.0
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
