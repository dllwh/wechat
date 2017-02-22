package com.cdeledu.weixin.base.http;

import java.io.Serializable;

/**
 * @描述:
 * 		<ul>
 *      <li>HTTP请求和相应的核心,它承载了关于客户端浏览器,请求页面,服务器等相关的信息</li>
 *      <li>HTTP请求和响应头代表,映射柱头名称字符串值列表</li>
 *      </ul>
 * @author: 独泪了无痕
 * @date: 2015年9月24日 下午3:28:41
 * @version: V1.0
 * @history:
 */
public final class HttpHeaders implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 指定客户端能够接收的内容类型 */
	public static final String ACCEPT = "Accept";
	/** 浏览器可以接受的字符编码集 */
	public static final String ACCEPT_CHARSET = "Accept-Charset";
	/** 指定浏览器可以支持的web服务器返回内容压缩编码类型 */
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	/** 浏览器可接受的语言:如果网站有不同的语言版本,那么就可以通过这个信息来重定向用户的浏览器 */
	public static final String ACCEPT_LANGUAGE = "Accept-Language";
	/** 表明服务器是否支持指定范围请求及哪种类型的分段请求 */
	public static final String ACCEPT_RANGES = "Accept-Ranges";
	public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
	public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
	public static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
	public static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
	/** 从原始服务器到代理缓存形成的估算时间(以秒计，非负) */
	public static final String AGE = "Age";
	/** 对某网络资源的有效的请求行为，不允许则返回405 */
	public static final String ALLOW = "Allow";
	/** HTTP授权的授权证书:当一个页面需要授权,浏览器就会弹出一个登陆窗口 */
	public static final String AUTHORIZATION = "Authorization";
	/** 指定请求和响应遵循的缓存机制 */
	public static final String CACHE_CONTROL = "Cache-Control";
	/** 表示是否需要持久连接(HTTP 1.1默认进行持久连接) */
	public static final String CONNECTION = "Connection";
	/** web服务器支持的返回内容压缩编码类型 */
	public static final String CONTENT_ENCODING = "Content-Encoding";
	/** 告诉浏览器打开一个文件下载窗口，而不是试图解析该响应的内容 */
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	/** 响应体的语言 */
	public static final String CONTENT_LANGUAGE = "Content-Language";
	/** 请求或者响应的内容长度 */
	public static final String CONTENT_LENGTH = "Content-Length";
	/** 请求资源可替代的备用的另一地址 */
	public static final String CONTENT_LOCATION = "Content-Location";
	/** 在整个返回体中本部分的字节位置 */
	public static final String CONTENT_RANGE = "Content-Range";
	/** 请求的或响应的与实体对应的MIME信息 */
	public static final String CONTENT_TYPE = "Content-Type";
	/** HTTP请求发送时,会把保存在该请求域名下的所有cookie值一起发送给web服务器 */
	public static final String COOKIE = "Cookie";
	/** 请求发送的日期和时间 */
	public static final String DATE = "Date";
	/** 用来确定缓存的信息是否正确 */
	public static final String ETAG = "ETag";
	/** 请求的特定的服务器行为 */
	public static final String EXPECT = "Expect";
	/** 响应过期的日期和时间 */
	public static final String EXPIRES = "Expires";
	/** 发出请求的用户的Email。示例：From: user@email.com */
	public static final String FROM = "From";
	/** 指定请求的服务器的域名和端口号 */
	public static final String HOST = "Host";
	/** 只有请求内容与实体相匹配才有效 */
	public static final String IF_MATCH = "If-Match";
	/**
	 * 如果请求的部分在指定时间之后被修改则请求成功,未被修改则返回304代码<br>
	 * 检查缓存内容是否被修改过.若没有则自动读取缓存中的内容
	 */
	public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
	/** 如果内容未改变返回304代码,参数为服务器先前发送的Etag,与服务器回应的Etag比较判断是否改变 */
	public static final String IF_NONE_MATCH = "If-None-Match";
	/** 如果实体未改变,服务器发送客户端丢失的部分,否则发送整个实体,参数也为Etag */
	public static final String IF_RANGE = "If-Range";
	/** 只在实体在指定时间之后未被修改才请求成功 */
	public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
	/** 请求资源的最后修改时间 */
	public static final String LAST_MODIFIED = "Last-Modified";
	public static final String LINK = "Link";
	/** 用来重定向接收方到非请求URL的位置来完成请求或标识新的资源 */
	public static final String LOCATION = "Location";
	/** 限制信息通过代理和网关传送的时间 */
	public static final String MAX_FORWARDS = "Max-Forwards";
	public static final String ORIGIN = "Origin";
	/** 用来包含实现特定的指令 */
	public static final String PRAGMA = "Pragma";
	/** 指出认证方案和可应用到代理的该URL上的参数 */
	public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
	/** 连接到代理的授权证书 */
	public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
	/** 只请求实体的一部分,指定范围 */
	public static final String RANGE = "Range";
	/**
	 * 请求：先前网页的地址,当前请求网页紧随其后,即来路<br>
	 * 响应：应用于重定向或一个新的资源被创造，在5秒之后重定向
	 */
	public static final String REFERER = "Referer";
	/** 如果实体暂时不可取，通知客户端在指定时间之后再次尝试 */
	public static final String RETRY_AFTER = "Retry-After";
	/** web服务器软件名称 */
	public static final String SERVER = "Server";
	/** 设置Http Cookie */
	public static final String SET_COOKIE = "Set-Cookie";
	public static final String SET_COOKIE2 = "Set-Cookie2";
	/** 客户端愿意接受的传输编码,并通知服务器接受接受尾加头信息 */
	public static final String TE = "TE";
	/** 指出头域在分块传输编码的尾部存在 */
	public static final String TRAILER = "Trailer";
	/** 文件传输编码 */
	public static final String TRANSFER_ENCODING = "Transfer-Encoding";
	/** 向服务器指定某种传输协议以便服务器进行转换(如果支持) */
	public static final String UPGRADE = "Upgrade";
	/**
	 * 浏览器名和版本号<br>
	 * 操作系统名和版本号<br>
	 * 默认语言<br>
	 * 发出请求的用户信息
	 */
	public static final String USER_AGENT = "User-Agent";
	/** 告诉下游代理是使用缓存响应还是从原始服务器请求 */
	public static final String VARY = "Vary";
	/** 通知中间网关或代理服务器地址,通信协议 */
	public static final String VIA = "Via";
	/** 关于消息实体的警告信息 */
	public static final String WARNING = "Warning";
	/** 表明客户端请求实体应该使用的授权方案 */
	public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
}