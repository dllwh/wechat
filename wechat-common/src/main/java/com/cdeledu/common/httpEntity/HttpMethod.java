package com.cdeledu.common.httpEntity;

/**
 * @描述: 请求方法
 * @author: 独泪了无痕
 * @date: 2015年9月24日 下午2:40:02
 * @version: V1.0
 * @history:
 */
public enum HttpMethod {
	/**
	 * 通常用于请求服务器发送某个资源
	 */
	GET("GET"),
	/**
	 * 与GET方法的行为很类似,但服务器在响应中只返回首部,不会反回实体的主体部分<br>
	 * 这就允许客户端在未获取实际资源的情况下，对资源的首部进行检查
	 */
	HEAD("HEAD"),
	/**
	 * 提交body中的内容给服务器中指定的url中
	 */
	POST("POST"),
	/**
	 * 与GET方法从服务器读取文档相反，PUT方法会向服务器写入文档<br>
	 * 将body上传至服务器指定url处
	 */
	PUT("PUT"),
	//
	PATCH("PATCH"),
	/**
	 * 请服务器删除请求URL所指定的资源
	 */
	DELETE("DELETE"),
	/**
	 * 获取指定url中能接收的请求方法
	 */
	OPTIONS("OPTIONS"),
	/**
	 * TRACE方法主要用于诊断<br>
	 * 中间应用程序会自行决定对TRACE请求处理方式<br>
	 * TRACE请求不能带有实体的主体部分
	 */
	TRACE("TRACE"),
	// 连接指定频段。当客户端需要通过代理服务器连接HTTPS服务器是用到
	CONNECT("CONNECT");

	String value = "";

	private HttpMethod(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
