package com.cdeledu.util.openplatform.alibaba.aliyun;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.aliyuncs.utils.ParameterHelper;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 签名机制
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月6日 下午8:34:43
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="help.aliyun.com/document_detail/26225.html">签名机制</a>
 * @see <a href="help.aliyun.com/document_detail/29819.html">调用说明</a>
 */
final class SecurityTokenHelper {
	/** ----------------------------------------------------- Fields start */
	private static final String ENCODING = "UTF-8";
	private final static String SEPARATOR = "&";
	final static String ALGORITHM = "HmacSHA1";
	/** 用户的访问服务所用的密钥,用于标识访问者的身份 */
	private String accessKeyId;
	/** 用于加密签名、验证签名字符串的密钥 */
	private String accessKeySecret;

	/** ----------------------------------------------------- Fields end */
	public SecurityTokenHelper(String accessKeyId, String accessKeySecret) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}

	/**
	 * @方法描述 : 生成调用请求地址
	 * @param paramsMap
	 *            请求参数
	 * @param httpMethod
	 *            提交请求用的HTTP方法
	 * @param accessKeyId
	 *            用户的访问服务所用的密钥ID
	 * @param apiVersion
	 *            API版本号，为日期形式：YYYY-MM-DD，不同产品的API版本号不一样
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public String createSecurityToken(Map<String, String> paramsMap, String httpMethod,
			String apiVersion) throws InvalidKeyException, NoSuchAlgorithmException {
		if (StringUtils.isBlank(httpMethod)) {
			httpMethod = "GET";
		}

		// 加入公共参数
		paramsMap.put("Format", "JSON".toUpperCase());// 返回值的类型
		paramsMap.put("Version", apiVersion);// API版本号
		paramsMap.put("AccessKeyId", accessKeyId);// 用户的访问服务所用的密钥ID
		paramsMap.put("SignatureMethod", "HMAC-SHA1");// 签名方式
		paramsMap.put("Timestamp", ParameterHelper.getISO8601Time(new Date())); // 请求的时间戳
		paramsMap.put("SignatureVersion", "1.0");// 签名算法版本
		paramsMap.put("SignatureNonce", UUID.randomUUID().toString());// 唯一随机数

		// 对参数进行排序，注意严格区分大小写
		String[] sortedKeys = paramsMap.keySet().toArray(new String[] {});
		Arrays.sort(sortedKeys);

		StringBuilder urlSeq = new StringBuilder();

		StringBuilder canonicalizedQueryString = new StringBuilder();
		try {
			for (String key : sortedKeys) {
				// 这里注意对key和value进行转码
				canonicalizedQueryString.append(SEPARATOR).append(percentEncode(key)).append("=")
						.append(percentEncode(paramsMap.get(key)));
			}

			// 生成stringToSign字符串
			StringBuilder stringToSign = new StringBuilder();
			stringToSign.append(httpMethod);
			stringToSign.append(SEPARATOR);
			stringToSign.append(percentEncode("/"));
			stringToSign.append(SEPARATOR);
			// 这里注意对canonicalizedQueryString 进行编码
			stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

			// 签名结果串
			// paramsMap.put("Signature",
			// percentEncode(getSignature(stringToSign.toString())));

			urlSeq.append("https://rds.aliyuncs.com/").append("?Signature=")
					.append(percentEncode(getSignature(stringToSign.toString())));
			for (String key : sortedKeys) {
				urlSeq.append("&").append(key).append("=").append(paramsMap.get(key));
			}

		} catch (UnsupportedEncodingException exp) {
			throw new RuntimeException("UTF-8 encoding is not supported.");
		}
		return urlSeq.toString();
		// return Joiner.on("&").withKeyValueSeparator("=").join(paramsMap);
	}

	/**
	 * @方法描述 : 编码规则
	 * @param queryString
	 * @return
	 */
	private String percentEncode(String value) throws UnsupportedEncodingException {
		return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20")
				.replace("*", "%2A").replace("%7E", "~") : null;
	}

	/**
	 * @方法描述 : 按照RFC2104的定义，使用上面的用于签名的字符串计算签名HMAC值
	 * @param stringToSign
	 */
	private String getSignature(String stringToSign)
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String urlSecretkey = accessKeySecret + SEPARATOR;
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(new SecretKeySpec(urlSecretkey.getBytes(ENCODING), ALGORITHM));
		byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));
		return new String(Base64.encodeBase64(signData));
		// return DatatypeConverter.printBase64Binary(signData);
	}
}
