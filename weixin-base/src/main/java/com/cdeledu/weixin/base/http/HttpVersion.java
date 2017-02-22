package com.cdeledu.weixin.base.http;
/**
 * @描述: HTTP协议<br>
 *      web浏览器和服务器之类的交互过程必须遵守的协议, HTTP协议是TCP/IP中的一个应用协议
 * @author: 独泪了无痕
 * @date: 2015年11月1日 下午2:08:25
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="http://blog.csdn.net/gueter/article/details/1524447">HTTP协议详解
 *      </a>
 */
public class HttpVersion {
	/** -------------------------- 属性 begin ------------------------------- */
	/** Http版本 1.0 */
	public static final String HTTP_1_0_STRING = "HTTP/1.0";
	/** Http版本 1.1 */
	public static final String HTTP_1_1_STRING = "HTTP/1.1";
	// HTTP协议
	private String protocol;
	// 是否需要持久连接
	private boolean keepAlive;
	private String text;

	/** -------------------------- 属性 end ------------------------------- */
	public HttpVersion() {
		super();
	}

}