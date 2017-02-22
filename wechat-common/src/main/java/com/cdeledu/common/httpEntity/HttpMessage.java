package com.cdeledu.common.httpEntity;

/**
 * @描述: HTTP消息由从客户端到服务器的请求和从服务器到客户端的响应。
 * @author: 独泪了无痕
 * @date: 2015年11月1日 下午12:42:04
 * @version: V1.0
 */
public interface HttpMessage {
	/**
	 * HTTP报头
	 * 
	 * @return
	 */
	HttpHeaders getHeaders();
}
