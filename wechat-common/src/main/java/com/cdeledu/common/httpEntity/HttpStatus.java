package com.cdeledu.common.httpEntity;

/**
 * @描述: 常数枚举的HTTP状态码
 *      <ul>
 *      <li>所有状态码的第一个数字代表了响应的五种状态之一<br>
 *      ①.1xx:信息响应类,表示接收到请求并且继续处理(100-199 用于指定客户端应相应的某些动作)<br>
 *      ②.2xx:处理成功响应类,表示动作被成功接收、理解和接受(200-299 用于表示请求成功)<br>
 *      ③.3xx:重定向响应类,代表需要客户端采取进一步的操作才能完成请求(300-399
 *      用于已经移动的文件并且常被包含在定位头信息中指定新的地址信息)<br>
 *      ④.4xx:客户端错误,客户请求包含语法错误或者是不能正确执行(400-499 用于指出客户端的错误)<br>
 *      ⑤.5xx:服务端错误,代表了服务器在处理请求的过程中有错误或者异常状态发生(500-599 用于支持服务器错误)</li>
 *      </ul>
 * @author: 独泪了无痕
 * @date: 2015年11月1日 下午1:45:43
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="http://tool.oschina.net/commons?type=5">HTTP状态码详解</a>
 * @see <a href="http://tool.lu/httpcode/">http状态码 </a>
 * @see <a href=
 *      "http://www.cnblogs.com/lxinxuan/archive/2009/10/22/1588053.html">
 *      HTTP状态码大全</a>
 */
public class HttpStatus {
	// --- 1xx Informational【消息】 ---

	/**
	 * <tt>用来通知客户端它的部分请求已经被服务器接收，且仍未被拒绝。客户端应当继续发送请求的剩余部分</tt>
	 */
	public static final int SC_CONTINUE = 100;
	/**
	 * <tt></tt>
	 */
	public static final int SC_SWITCHING_PROTOCOLS = 101;
	/**
	 * <tt>102 Processing(由WebDAV（RFC 2518）扩展的状态码，代表处理将被继续执行)</tt>
	 */
	public static final int SC_PROCESSING = 102;

	// --- 2xx Success 【成功】---

	/**
	 * <tt>请求已成功，请求所希望的响应头或数据体将随此响应返回</tt>
	 */
	public static final int SC_OK = 200;
	/**
	 * <tt>201 (Created/已创建)</tt>
	 */
	public static final int SC_CREATED = 201;
	/**
	 * <tt>202 (Accepted/接受)</tt>
	 */
	public static final int SC_ACCEPTED = 202;
	/**
	 * <tt>203 (Non-Authoritative Information/非官方信息)</tt>
	 */
	public static final int SC_NON_AUTHORITATIVE_INFORMATION = 203;
	/**
	 * <tt>204 (No Content/无内容)</tt>
	 */
	public static final int SC_NO_CONTENT = 204;
	/**
	 * <tt>205 (Reset Content/重置内容)</tt>
	 */
	public static final int SC_RESET_CONTENT = 205;
	/**
	 * <tt>206 (Partial Content/局部内容)</tt>
	 */
	public static final int SC_PARTIAL_CONTENT = 206;
	/**
	 * <tt>207 Multi-Status</tt>
	 */
	public static final int SC_MULTI_STATUS = 207;

	// --- 3xx Redirection 【重定向】---

	/**
	 * <tt>300 (Multiple Choices/多重选择)</tt>
	 */
	public static final int SC_MULTIPLE_CHOICES = 300;
	/**
	 * <tt>301 (Moved Permanently)状态是指所请求的文档在别的地方</tt>
	 */
	public static final int SC_MOVED_PERMANENTLY = 301;
	/**
	 * <tt>302 (Found/找到)-是临时移动(Moved Temporarily)的</tt>
	 */
	public static final int SC_MOVED_TEMPORARILY = 302;
	/**
	 * <tt>303 (See Other/参见其他信息)</tt>
	 */
	public static final int SC_SEE_OTHER = 303;
	/**
	 * <tt>304 (Not Modified/为修正)</tt>
	 */
	public static final int SC_NOT_MODIFIED = 304;
	/**
	 * <tt>305 (Use Proxy/使用代理)</tt>
	 */
	public static final int SC_USE_PROXY = 305;
	/**
	 * <tt>307 (Temporary Redirect/临时重定向)</tt>
	 */
	public static final int SC_TEMPORARY_REDIRECT = 307;

	// --- 4xx Client Error 【请求错误】---
	/**
	 * <tt>400 (Bad Request/错误请求)指出客户端请求中的语法错误。 </tt>
	 */
	public static final int SC_BAD_REQUEST = 400;
	/**
	 * <tt>400 (Bad Request/错误请求)</tt>
	 */
	public static final int SC_UNAUTHORIZED = 401;
	/**
	 * <tt>402 (Payment Required)该状态码是为了将来可能的需求而预留的。</tt>
	 */
	public static final int SC_PAYMENT_REQUIRED = 402;
	/**
	 * <tt>403 (Forbidden/禁止)</tt>
	 */
	public static final int SC_FORBIDDEN = 403;
	/**
	 * <tt>404 (Not Found/未找到)</tt>
	 */
	public static final int SC_NOT_FOUND = 404;
	/**
	 * <tt>405 (Method Not Allowed/方法未允许)</tt>
	 */
	public static final int SC_METHOD_NOT_ALLOWED = 405;
	/**
	 * <tt>406 (Not Acceptable/无法访问) </tt>
	 */
	public static final int SC_NOT_ACCEPTABLE = 406;
	/**
	 * <tt>407 (Proxy Authentication Required/代理服务器认证要求)</tt>
	 */
	public static final int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
	/**
	 * <tt>408 (Request Timeout/请求超时)</tt>
	 */
	public static final int SC_REQUEST_TIMEOUT = 408;
	/**
	 * <tt>409 (Conflict/冲突)</tt>
	 */
	public static final int SC_CONFLICT = 409;
	/**
	 * <tt>410 (Gone/已经不存在)</tt>
	 */
	public static final int SC_GONE = 410;
	/**
	 * <tt>411 (Length Required/需要数据长度)</tt>
	 */
	public static final int SC_LENGTH_REQUIRED = 411;
	/**
	 * <tt>412 (Precondition Failed/先决条件错误)</tt>
	 */
	public static final int SC_PRECONDITION_FAILED = 412;
	/**
	 * <tt>413 (Request Entity Too Large/请求实体过大)</tt>
	 */
	public static final int SC_REQUEST_TOO_LONG = 413;
	/**
	 * <tt>414 (Request URI Too Long/请求URI过长)</tt>
	 */
	public static final int SC_REQUEST_URI_TOO_LONG = 414;
	/**
	 * <tt>415 (Unsupported Media Type/不支持的媒体格式)</tt>
	 */
	public static final int SC_UNSUPPORTED_MEDIA_TYPE = 415;
	/**
	 * <tt>416 (Requested Range Not Satisfiable/请求范围无法满足)</tt>
	 */
	public static final int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
	/**
	 * <tt>417 (Expectation Failed/期望失败)</tt>
	 */
	public static final int SC_EXPECTATION_FAILED = 417;
	/**
	 * <tt>419 Proxy Reauthentication Required</tt>
	 */
	public static final int SC_INSUFFICIENT_SPACE_ON_RESOURCE = 419;
	/**
	 * <tt>420 Method Failure</tt>
	 */
	public static final int SC_METHOD_FAILURE = 420;
	/**
	 * <tt>422 Unprocessable Entity</tt>
	 */
	public static final int SC_UNPROCESSABLE_ENTITY = 422;
	/**
	 * <tt>423 (Locked)请求格式正确，但是由于含有语义错误，无法响应</tt>
	 */
	public static final int SC_LOCKED = 423;
	/**
	 * <tt>424 (Failed Dependency)由于之前的某个请求发生的错误，导致当前请求失败</tt>
	 */
	public static final int SC_FAILED_DEPENDENCY = 424;

	// --- 5xx Server Error 【服务器错误】---

	/**
	 * <tt>500 (Internal Server Error/内部服务器错误)</tt>
	 */
	public static final int SC_INTERNAL_SERVER_ERROR = 500;
	/**
	 * <tt>501 (Not Implemented/未实现)</tt>
	 */
	public static final int SC_NOT_IMPLEMENTED = 501;
	/**
	 * <tt>502 (Bad Gateway/错误的网关)</tt>
	 */
	public static final int SC_BAD_GATEWAY = 502;
	/**
	 * <tt>503 (Service Unavailable/服务无法获得)</tt>
	 */
	public static final int SC_SERVICE_UNAVAILABLE = 503;
	/**
	 * <tt>504 (Gateway Timeout/网关超时)</tt>
	 */
	public static final int SC_GATEWAY_TIMEOUT = 504;
	/**
	 * <tt>505 (HTTP Version Not Supported/不支持的 HTTP 版本)</tt>
	 */
	public static final int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
	/**
	 * <tt>507 (Insufficient Storage)服务器无法存储完成请求所必须的内容</tt>
	 */
	public static final int SC_INSUFFICIENT_STORAGE = 507;
	/** HTTP状态码编码 */
	private final int statusCode;
	/** HTTP状态码内容 */
	private final String statusText;

	public HttpStatus(int statusCode, String statusText) {
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	@Override
	public String toString() {
		return "HttpStatus [statusCode=" + statusCode + ", statusText=" + statusText + "]";
	}

}
