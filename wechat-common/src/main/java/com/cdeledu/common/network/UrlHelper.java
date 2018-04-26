package com.cdeledu.common.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @Description: URL 操作工具类
 * @author: 独泪了无痕
 * @date: 2015年8月27日 下午4:39:21
 * @version: V2.0
 * @history:
 */
public class UrlHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UrlHelper.class);
	/** -------------------------- 私有属性 begin ------------------------------- */
	/** 请求网址与请求参数之间的分隔符 **/
	public static final String QUESTION_MARK = "?";
	/** 请求参数之间的分隔符 **/
	public static final String AND_SIGN = "&";
	/** 路径分隔符 **/
	public static final String PATHS_SEPARATOR = "/";
	/** equal sign **/
	public static final String EQUAL_SIGN = "=";

	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */

	/**
	 * 
	 * @Title: utf8Encode
	 * @Description: 编码
	 * @param str
	 * @return
	 * @return：String 返回类型
	 */
	private static String utf8Encode(String str) {
		if (StringUtils.isNotEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	private static void formatUrlEntity(String url, StringBuilder urlWithParas) {
		if (!url.contains(QUESTION_MARK)) {
			urlWithParas.append(QUESTION_MARK);
		} else {
			urlWithParas.append(AND_SIGN);
		}
	}

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * @Title: appendParaToUrl
	 * @Description: 网址拼接
	 * @author: 独泪了无痕
	 * @param url
	 * @param paraUrl
	 * @return
	 */
	public static String appendParaToUrl(String url, String paraUrl) {
		if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(paraUrl)) {
			if (!url.contains(QUESTION_MARK))
				return url + QUESTION_MARK + paraUrl;
			else
				return url + paraUrl;
		}
		return url;
	}

	/**
	 * 
	 * @Title: formatUrl
	 * @Description: 格式化URL链接
	 * @param url
	 *            需要格式化的URL
	 * @return 格式化后的URL，如果提供了null或者空串，返回null
	 */
	public static String formatUrl(String url) {
		if (StringUtils.isBlank(url))
			return null;
		if (url.startsWith("http://") || url.startsWith("https://"))
			return url;
		return "http://" + url;
	}

	/**
	 * 
	 * @Title: formatParameters
	 * @Description: 批量格式化
	 * @author: 独泪了无痕
	 * @param parameters
	 * @return
	 */
	public static String formatParameters(List<URLParameter> parameters) {
		StringBuilder body = new StringBuilder();
		URLParameter parameter = parameters.get(0);
		body.append(parameter.encoding());
		for (int i = 1; i < parameters.size(); i++) {
			body.append(AND_SIGN);
			parameter = parameters.get(i);
			body.append(parameter.encoding());
		}
		return body.toString();
	}

	/**
	 * 
	 * @方法名称: formatParameters
	 * @方法描述: 格式化请求url(将Map类型的参数组合转换成请求参数类型)
	 * 
	 * @param parasMap
	 *            参数所在的map;key是对名称，value是对值
	 * @return 请求参数应该是 name1=value1&name2=value2 的形式
	 */
	public static String formatParameters(Map<String, Object> paramsMap) {
		if (MapUtils.isEmpty(paramsMap)) {
			return "";
		}
		Object key = null;
		Object val = null;

		StringBuilder paras = new StringBuilder();
		for (Entry<String, Object> entry : paramsMap.entrySet()) {
			key = entry.getKey();

			if (key == null) {
				continue;
			}
			val = entry.getValue();
			if ((val == null) || val.toString().length() == 0) {
				continue;
			}

			paras.append(
					String.format("%s=%s", utf8Encode(key.toString()), utf8Encode(val.toString())));
			paras.append(AND_SIGN);
		}
		// 删除最后一个'&'
		return paras.toString().substring(0, paras.lastIndexOf(AND_SIGN));
	}

	/**
	 * 
	 * @Title: formatParameters
	 * @Description: 格式化请求url
	 * @author: 独泪了无痕
	 * @param url
	 *            发送请求的URL
	 * @param paramsMap
	 *            参数所在的map;key是对名称，value是对值
	 * @return url?name1=value1&name2=value2
	 */
	public static String formatParameters(String url, Map<String, Object> paramsMap) {
		String paramsUrl = formatParameters(paramsMap);

		if (StringUtils.isEmpty(url)) {
			return paramsUrl;
		}
		StringBuilder urlWithParas = new StringBuilder(url);

		if (StringUtils.isNotEmpty(paramsUrl)) {
			formatUrlEntity(url, urlWithParas);
			urlWithParas.append(paramsUrl);
		}
		return urlWithParas.toString();
	}

	/**
	 * 
	 * @Title: getFileEncoding
	 * @Description: 获取页面编码格式
	 * @param url
	 * @return
	 */
	public static String getFileEncoding(URL url) {
		HttpURLConnection con = null;
		String regex = "charset=[\"']?([\\w-]+?)([^\\w-]|$)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
			con.setConnectTimeout(152000);
			con.setReadTimeout(288000);
			con.connect();

			// Content-Type: text/html;charset=UTF-8
			if (con != null) {
				String tmp = con.getContentType();
				if (tmp != null) {
					Matcher matcher = pattern.matcher(tmp);
					if (matcher.find())
						return matcher.group(1);
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = br.readLine();
				while (line != null && (line.indexOf("charset=") == -1))
					line = br.readLine();
				if (line != null) {
					line = line.trim();
					if (line.indexOf("<script") == -1) {

						Matcher matcher = pattern.matcher(line);
						if (matcher.find())
							return matcher.group(1);
					}
				}
			}
		} catch (Exception e) {

		} finally {
			if (con != null)
				con.disconnect();
		}
		return "";
	}

	/**
	 * 
	 * @Title: parseParameters
	 * @Description: 从URL中分析字符串参数，放到一个 map 里
	 * @author: 独泪了无痕
	 * @param url
	 * @return
	 */
	public static Map<String, String> parseParameters(String url) {

		if (StringUtils.isBlank(url)) {
			return null;
		}
		int questionMarkIndex = url.indexOf(QUESTION_MARK);
		if (questionMarkIndex == -1 || questionMarkIndex == url.length() - 1) {
			return null;
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		String queryStr = url.substring(questionMarkIndex + 1);
		String[] paramArray = queryStr.split(String.valueOf(AND_SIGN));

		for (int i = 0; i < paramArray.length; i++) {
			int equalsSignIndex = paramArray[i].indexOf(EQUAL_SIGN);
			if (equalsSignIndex == -1) {
				continue;
			}
			String paramName = paramArray[i].substring(0, equalsSignIndex);
			String paramValue = paramArray[i].substring(equalsSignIndex + 1);
			paramMap.put(paramName, paramValue);
		}

		return paramMap;
	}

	public static Map<String, String[]> parseParams(HttpServletRequest request) {
		Map<String, String[]> resultMap = new HashMap<String, String[]>();
		Enumeration<?> params = request.getParameterNames();

		StringBuffer paramSb = null;
		StringBuffer emptyParamSb = null;

		if (logger.isDebugEnabled()) {
			paramSb = new StringBuffer();
			emptyParamSb = new StringBuffer();
			paramSb.append("<<请求参数[request params]>>");
			paramSb.append(ConstantHelper.NEW_LINE);
		}
		while (params.hasMoreElements()) {
			// 得到参数名
			String name = params.nextElement().toString();
			String[] value = request.getParameterValues(name);
			resultMap.put(name, value);
			if (logger.isDebugEnabled()) {

				String values = URLParameter.join(ConstantHelper.COMMA, value);

				if (StringUtils.isEmpty(values)) {
					emptyParamSb.append(name + "=" + values);
					emptyParamSb.append(ConstantHelper.NEW_LINE);
				} else {
					paramSb.append(name + "=" + values);
					paramSb.append(ConstantHelper.NEW_LINE);
				}
			}
		}

		if (logger.isDebugEnabled()) {

			if (emptyParamSb.length() > 0) {
				paramSb.append("--------------------------------------------");
				paramSb.append(ConstantHelper.NEW_LINE);
				paramSb.append(emptyParamSb);
			}

			if (paramSb.length() > 0) {
				paramSb.delete(paramSb.length() - ConstantHelper.NEW_LINE.length(),
						paramSb.length());
			}

			logger.debug(paramSb.toString());
		}

		return resultMap;
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
