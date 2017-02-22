package com.cdeledu.common.httpEntity;

import java.net.URI;

/**
 * @描述: HTTP 请求
 * @author: 独泪了无痕
 * @date: 2015-10-15 下午12:28:32
 * @since JDK 1.7
 * @version: V1.2
 */
public class HttpRequest implements HttpMessage {
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
