package com.cdeledu.weixin.base.http;

import java.net.URI;

/**
 * @类描述: HTTP 请求
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月11日 下午11:44:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HttpRequest implements HttpMessage {
	/** ----------------------------------------------------- Fields start */
	/** 请求方式 */
	private final HttpMethod method;
	/** 请求路径 */
	private final URI uri;
	/** 协议参数 */
	private HttpParams params;
	/** 内容参数 */
	private HttpEntity entity;
	/** 请求表头 */
	private HttpHeaders headers;

	/** ----------------------------------------------------- Fields end */

	public HttpRequest(HttpMethod method, URI uri) {
		this.method = method;
		this.uri = uri;
	}

	public HttpRequest(HttpMethod method, String url) {
		this(method, URI.create(url));
	}

	public HttpParams getParams() {
		return params;
	}

	public void setParams(HttpParams params) {
		this.params = params;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public URI getUri() {
		return uri;
	}
}
