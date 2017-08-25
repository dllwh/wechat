package com.cdeledu.util.network.tcp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cdeledu.common.browser.UserAgentType;
import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.exception.ExceptionHelper;
import com.cdeledu.common.httpEntity.HttpHeaders;
import com.cdeledu.common.mapper.ClassUtilHelper;

/**
 * @类描述:
 *       <ul>
 *       <li>封装了一些采用HttpClient发送HTTP请求的方法</li>
 *       <li>实现与 org.apache.http</li>
 *       <li>使用HttpClient步骤如下</li>
 *       <li>1. 创建HttpClient对象</li>
 *       <li>2. 创建请求方法的实例,并指定请求URL <br/>
 *       如果需要发送GET请求,创建HttpGet对象 <br/>
 *       如果需要发送POST请求,创建HttpPost对象</li>
 *       <li>3. 如果需要发送请求参数<br/>
 *       可调用HttpGet、HttpPost共同的setParams(HetpParams params)方法来添加请求参数 <br/>
 *       对于HttpPost对象而言,也可调用setEntity(HttpEntity entity)方法来设置请求参数</li>
 *       <li>4. 调用HttpClient对象的execute(HttpUriRequest
 *       request)发送请求,该方法返回一个HttpResponse</li>
 *       <li>5. 调用HttpResponse的getAllHeaders()、getHeaders(String
 *       name)等方法可获取服务器的响应头<br/>
 *       调用HttpResponse的getEntity()方法可获取HttpEntity对象,该对象包装了服务器的响应内容</li>
 *       <li>6. 释放连接:无论执行方法是否成功,都必须释放连接</li>
 *       </ul>
 * @创建者: 独泪了无痕
 * @创建时间: 2015年7月28日 上午9:23:23
 * @版本: V2.1
 * @since: JDK 1.7
 */
@SuppressWarnings("deprecation")
public class HttpClientHelper {
	/*-------------------------- 私有属性 begin -------------------------------*/
	public static HttpClientContext context = new HttpClientContext();
	private HttpClient httpClient = null;
	private static HttpClientHelper instance;
	/** 请求编码，默认使用utf-8 */
	private static String URLCHARSET = ConstantHelper.UTF_8.name();
	private String result = null;
	private HttpEntity entity = null;
	private HttpResponse response = null;
	private int statusCode = 0;

	/*-------------------------- 私有属性 end   -------------------------------*/
	/*-------------------------- 私有方法 begin -------------------------------*/

	HttpClientHelper() {
	}

	private void setUrlCharset(String urlCharset) {
		URLCHARSET = urlCharset;
	}

	public HttpClientHelper getInstance() {
		init(URLCHARSET);
		return instance;
	}

	/** 当HttpClient实例不再需要是，确保关闭connection manager，以释放其系统资源 */
	public void destroyClient() {
		httpClient.getConnectionManager().shutdown();
	}

	public HttpClientHelper getInstance(String urlCharset) {
		init(urlCharset);
		return instance;
	}

	private static synchronized void init(String urlCharset) {
		if (instance == null) {
			instance = new HttpClientHelper();
		}
		if (StringUtils.isBlank(urlCharset)) {
			urlCharset = URLCHARSET;
		}
		// 设置默认的url编码
		instance.setUrlCharset(urlCharset);
	}

	/**
	 * @方法描述: 获取响应体内容
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private String getHttpResponseContent(HttpResponse response) throws Exception {
		// 得到相应实体、包括响应头以及相应内容
		entity = response.getEntity();
		if (entity == null) {
			return null;
		}
		// 得到response的内容
		String content = EntityUtils.toString(entity, URLCHARSET);
		// 关闭输入流
		EntityUtils.consume(entity);
		return content;
	}

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- POST begin ------------------------------- */
	/**
	 * 
	 * @Title: sendPostRequest
	 * @Description: 发送HTTP_POST请求获取url指定的资源和数据
	 * @param targetUrl
	 *            服务器地址
	 * @return
	 */
	public String sendPostRequest(String url) throws Exception {
		return sendPostRequest(url, null, null);
	}

	/**
	 * 
	 * @Title: sendPostRequest
	 * @Description: 通过post提交方式获取url指定的资源和数据
	 * @param targetUrl
	 *            服务器地址
	 * @param headerMap
	 *            请求header参数
	 * @return
	 */
	public String sendPostRequest(String url, Map<String, Object> headersMap) throws Exception {
		return sendPostRequest(url, headersMap, null);
	}

	/**
	 * @方法描述: 通过post提交方式获取url指定的资源和数据
	 * @param targetUrl
	 * @param nvps
	 * @return 请求参数
	 * @throws Exception
	 */
	public String sendPostRequest(String url, List<NameValuePair> nvps) throws Exception {
		return sendPostRequestData(url, null, nvps);
	}

	/**
	 * 
	 * @Title: sendPostRequest
	 * @Description: 通过post提交方式获取url指定的资源和数据
	 * @param targetUrl
	 *            服务器地址
	 * @param headersMap
	 *            请求header参数
	 * @param nvps
	 *            请求参数
	 * @return
	 */
	public String sendPostRequest(String url, Map<String, Object> headersMap,
			List<NameValuePair> nvps) throws Exception {
		return sendPostRequestData(url, headersMap, nvps);
	}

	/**
	 * @方法描述: 通过post提交方式获取url指定的资源和数据
	 * @param targetUrl
	 *            服务器地址
	 * @param headersMap
	 *            请求header参数
	 * @param nameValuePairs
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	public String sendPostRequestData(String targetUrl, Map<String, Object> headersMap,
			List<NameValuePair> nvps) throws Exception {
		// HttpClient中的post请求包装类
		HttpPost httpPost = new HttpPost(targetUrl);

		try {
			if (MapUtils.isNotEmpty(headersMap)) {
				for (String key : headersMap.keySet()) {
					Object hKey = null == headersMap.get(key) ? "" : headersMap.get(key);
					httpPost.setHeader(key, String.valueOf(hKey));
				}
			}
			// nvps是包装请求参数的list
			if (CollectionUtils.isNotEmpty(nvps)) {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, URLCHARSET));
			}
			// 执行请求用execute方法,content用来帮我们附带上额外信息
			response = httpClient.execute(httpPost, context);

			int statusCode = response.getStatusLine().getStatusCode();
			// 请求和响应都成功了
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (statusCode == HttpStatus.SC_OK) {
				try {
					result = getHttpResponseContent(response);
				} catch (Exception ioe) {
					ioe.printStackTrace();
				}
			} else {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: sendPostRequestByJsonData
	 * @Description: 通过ContentType 为json的格式进行http传输
	 * @param targetUrl
	 *            远程url
	 * @param content
	 *            传输内容
	 * @return
	 */

	public String sendPostRequestByJsonData(String targetUrl, String content) throws Exception {
		HttpPost httpPost = null;

		if (StringUtils.isEmpty(targetUrl)) {
			ExceptionHelper.getExceptionHint(ClassUtilHelper.getClassName(),
					ClassUtilHelper.getMethodName(), "targetUrl不能为空!");
		}
		try {
			// 发送POST请求:创建一个HttpPost对象,传入目标的网络地址
			httpPost = new HttpPost(targetUrl);
			httpPost.addHeader(HttpHeaders.CONNECTION, "keep-alive");
			httpPost.addHeader(HttpHeaders.ACCEPT, "*/*");
			httpPost.addHeader(HttpHeaders.CONTENT_TYPE,
					"application/x-www-form-urlencoded; charset=UTF-8");
			httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpPost.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
			httpPost.addHeader(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());

			if (StringUtils.isNoneBlank(content)) {
				httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
			}

			// 调用HttpClient的execute()方法,并将HttpPost对象传入即可:
			// 服务器所返回的所有信息存储在HttpResponse中
			response = httpClient.execute(httpPost);

			statusCode = response.getStatusLine().getStatusCode();
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (statusCode == HttpStatus.SC_OK) {
				try {
					result = getHttpResponseContent(response);
				} catch (Exception ioe) {
					ioe.printStackTrace();
				}
			} else {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			// 释放资源
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return result;
	}

	/** -------------------------- POST end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */

	/**
	 * 
	 * @Title: sendGetRequest
	 * @Description: 发送HTTP_GET请求获取url指定的资源和数据
	 * @param targetUrl
	 *            服务器地址
	 * @return 返回响应的文本
	 */
	public String sendGetRequest(String url) throws Exception {
		return sendGetRequestDate(url, null);
	}

	/**
	 * @方法描述: 带header的get请求
	 * @param targetUrl
	 *            请求服务器地址
	 * @param headersMap
	 *            添加的请求header信息
	 * @return
	 * @throws Exception
	 */
	public String sendGetRequest(String url, Map<String, Object> headersMap) throws Exception {
		return sendGetRequestDate(url, headersMap);
	}

	/**
	 * @方法描述: 通过GET提交方式获取url指定的资源和数据
	 * @param targetUrl
	 *            服务器地址
	 * @param headersMap
	 *            请求header参数
	 * @return
	 * @throws Exception
	 */
	public String sendGetRequestDate(String targetUrl, Map<String, Object> headersMap)
			throws Exception {
		HttpGet httpGet = new HttpGet(targetUrl);
		// 设置响应头信息
		httpGet.addHeader(HttpHeaders.ACCEPT,
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.addHeader(HttpHeaders.CONNECTION, "keep-alive");
		httpGet.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
		httpGet.addHeader(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());

		try {
			if (MapUtils.isNotEmpty(headersMap)) {
				for (String key : headersMap.keySet()) {
					Object hKey = null == headersMap.get(key) ? "" : headersMap.get(key);
					httpGet.setHeader(key, String.valueOf(hKey));
				}
			}

			response = httpClient.execute(httpGet);

			statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				try {
					result = getHttpResponseContent(response);
				} catch (Exception ioe) {
					ioe.printStackTrace();
				}
			} else {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 释放资源
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
		}

		return result;
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}