package com.cdeledu.weixin.base.http;


/**
 * @描述: Http 参数
 * @author: 独泪了无痕
 * @date: 2015-10-15 下午12:35:52
 * @version: V1.0
 */
public interface HttpEntity {
	ContentType getContentType();

	long getContentLength();
}
