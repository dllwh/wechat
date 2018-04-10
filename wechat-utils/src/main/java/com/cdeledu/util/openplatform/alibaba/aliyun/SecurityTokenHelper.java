package com.cdeledu.util.openplatform.alibaba.aliyun;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.aliyuncs.auth.AcsURLEncoder;
import com.aliyuncs.auth.ShaHmac1;
import com.aliyuncs.utils.ParameterHelper;
import com.google.common.base.Joiner;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 签名机制
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月6日 下午8:34:43
 * @版本: V 2.0
 * @since: JDK 1.7
 * @see <a href="help.aliyun.com/document_detail/26225.html">签名机制</a>
 * @see <a href="help.aliyun.com/document_detail/29819.html">调用说明</a>
 */
final class SecurityTokenHelper {
	/** ----------------------------------------------------- Fields start */
	private final static String SEPARATOR = "&";
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
	 *            业务API请求参数
	 * @param httpMethod
	 *            提交请求用的HTTP方法,默认是GET
	 * @param apiVersion
	 *            API版本号，为日期形式：YYYY-MM-DD，不同产品的API版本号不一样
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public String createSecurityToken(Map<String, String> paramsMap, String httpMethod,
			String apiVersion) throws InvalidKeyException, NoSuchAlgorithmException {

		if (StringUtils.isBlank(httpMethod)) {
			httpMethod = "GET";
		}

		/**
		 * 1. 系统公共参数
		 */
		paramsMap.put("Format", "JSON".toUpperCase());// 返回值的类型
		paramsMap.put("AccessKeyId", accessKeyId);// 用户的访问服务所用的密钥ID
		paramsMap.put("SignatureMethod", "HMAC-SHA1");// 签名方式
		paramsMap.put("Timestamp", ParameterHelper.getISO8601Time(new Date())); // 请求的时间戳
		paramsMap.put("SignatureVersion", "1.0");// 签名算法版本
		paramsMap.put("SignatureNonce", UUID.randomUUID().toString());// 唯一随机数

		/**
		 * 2. 业务API参数
		 */
		paramsMap.put("Version", apiVersion);// API版本号

		/**
		 * 3. 去除签名关键字Key
		 */
		if (paramsMap.containsKey("Signature")) {
			paramsMap.remove("Signature");
		}

		try {
			/**
			 * 5.使用请求参数构造规范化的请求字符串
			 */
			StringBuilder createQueryString = new StringBuilder();
			// 5.1、按照参数名称的字典顺序对请求中所有的请求参数进行排序，注意严格区分大小写
			String[] sortedKeys = paramsMap.keySet().toArray(new String[] {});
			Arrays.sort(sortedKeys);

			for (String key : sortedKeys) {
				// 5.2、对每个请求参数的名称和值进行编码名称和值要使用UTF-8字符集进行URL编码
				// 5.3、对编码后的参数名称和值使用英文等号（=）进行连接
				// 5.4、再把英文等号连接得到的字符串按参数名称的字典顺序依次使用&符号连接
				createQueryString.append(SEPARATOR).append(AcsURLEncoder.percentEncode(key))
						.append("=").append(AcsURLEncoder.percentEncode(paramsMap.get(key)));
			}
			String canonicalizedQueryString = createQueryString.toString().substring(1);

			/**
			 * 6. 构造用于计算签名的字符串
			 */
			StringBuilder stringToSign = new StringBuilder();
			stringToSign.append(httpMethod);// 提交请求用的HTTP方法，比GET
			stringToSign.append(SEPARATOR);
			stringToSign.append(AcsURLEncoder.percentEncode("/"));// 按照URL编码规则对字符“/”进行编码
			stringToSign.append(SEPARATOR);
			// 这里注意对canonicalizedQueryString 进行编码
			stringToSign.append(AcsURLEncoder.percentEncode(canonicalizedQueryString));

			/**
			 * 7. 按照RFC2104的定义，使用上面的用于签名的字符串计算签名HMAC值
			 */

			String signature = getSignature(stringToSign.toString());
			/**
			 * 8. 将得到的签名值作为Signature参数添加到请求参数中，即完成对请求签名的过程
			 */
			paramsMap.put("Signature", AcsURLEncoder.percentEncode(signature));
		} catch (UnsupportedEncodingException exp) {
			throw new RuntimeException("UTF-8 encoding is not supported.");
		}
		return Joiner.on("&").withKeyValueSeparator("=").join(paramsMap);
	}

	/**
	 * @方法描述 : 按照RFC2104的定义，使用上面的用于签名的字符串计算签名HMAC值
	 * @param stringToSign
	 */
	private String getSignature(String stringToSign)
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String urlSecretkey = accessKeySecret + SEPARATOR;
		return new ShaHmac1().signString(stringToSign, urlSecretkey);

	}
}