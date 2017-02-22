package com.cdeledu.weixin.base.http;

import org.apache.http.HttpResponse;

/**
 * 
 * @ClassName: WeixinResponse
 * @author: 独泪了无痕
 * @date: 2015年11月9日 下午7:54:37
 * @version: V1.0
 * @since: JDK 1.7
 */
public class WeixinResponse {
	private boolean isJsonResult;
	private boolean isXmlResult;
	private volatile String text;
	private final HttpResponse response;

	public WeixinResponse(HttpResponse response) {
		this.response = response;
	}

	public boolean isJsonResult() {
		return isJsonResult;
	}

	public void setJsonResult(boolean isJsonResult) {
		this.isJsonResult = isJsonResult;
	}

	public boolean isXmlResult() {
		return isXmlResult;
	}

	public void setXmlResult(boolean isXmlResult) {
		this.isXmlResult = isXmlResult;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HttpResponse getResponse() {
		return response;
	}
}
