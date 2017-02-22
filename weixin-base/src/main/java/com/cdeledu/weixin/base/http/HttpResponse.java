package com.cdeledu.weixin.base.http;

import java.io.InputStream;

/**
 * 
 * @类描述: HTTP 响应
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月12日 下午11:23:28
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public interface HttpResponse extends HttpMessage {
	/**
	 * HTTP协议
	 */
	HttpVersion getProtocol();

	/**
	 * 响应状态
	 */
	HttpStatus getStatus();

	/**
	 * 响应内容
	 */
	InputStream getBody();

	/**
	 * 响应内容
	 */
	byte[] getContent();

	/**
	 * 释放资源
	 */
	void close();
}