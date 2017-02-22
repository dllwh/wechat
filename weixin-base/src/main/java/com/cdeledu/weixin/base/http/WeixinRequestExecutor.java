package com.cdeledu.weixin.base.http;

import java.util.Map;

import org.apache.log4j.Logger;

import com.cdeledu.weixin.base.exception.WeixinException;

/**
 * @Description: 负责微信请求的执行
 * @author: 独泪了无痕
 * @date: 2015年10月14日 下午12:46:11
 * @version: V1.0
 * @history:
 */
public class WeixinRequestExecutor {
	/** ----------------------------------------------------- Fields start */
	protected final Logger logger = Logger.getLogger(getClass());

	/** ----------------------------------------------------- Fields end */
	/** ---------------------------------------------- PrivateMethod start */
	private WeixinResponse doRequest(HttpRequest request) throws WeixinException {
		try {
			/*// 请求日志
			logger.info("weixin request >> " + request.getMethod() + " " + request.getUri().toString());
			HttpResponse httpResponse= null;
			logger.info("weixin response << " + httpResponse.getProtocol()
			+ httpResponse.getStatus().toString() );*/
		} catch (Exception e) {
			
		}
		return null;
	}

	/**
	 * ----------------------------------------------- PrivateMethod start
	 */

	public WeixinResponse get(String url) throws WeixinException {
		HttpRequest request = new HttpRequest(HttpMethod.GET, url);
		return doRequest(request);
	}

	public WeixinResponse get(String url, Map<String, String> parameters) throws WeixinException {
		return null;
	}

	public WeixinResponse post(String url) throws WeixinException {
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		return doRequest(request);
	}

	public WeixinResponse post(String url, String body) throws WeixinException {
		return null;
	}

}
