package com.cdeledu.util.security;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * 
 * @类描述: 安全相关的工具类，包括各种加密算法
 * @创建者: 皇族灬战狼
 * @时间: 2017年4月8日 下午12:14:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SecureUtil {
	/**
	 * 加密算法
	 */
	public final static String MD2 = "MD2";
	public final static String MD4 = "MD4";
	public final static String MD5 = "MD5";
	public final static String DES = "DES";
	public final static String SHA1 = "SHA-1";
	public final static String SHA256 = "SHA-256";
	/**
	 * DES<BE/>
	 * 
	 * <pre>
	 *加密解密模式:ECB,CBC,CTR,OFB,CFB
	 *常见的填充模式有： 'pkcs5','pkcs7','iso10126','ansix923','zero' 类型
	 * </pre>
	 */
	public final static String DES_ECB_PKCS5 = "DES/ECB/PKCS5Padding";
	public final static String DES_ECB_PKCS7 = "DES/ECB/PKCS7Padding";
	public final static String DES_ECB_ZERO = "DES/ECB/zeropadding";
	public final static String DES_ECB_ISO10126 = "DES/ECB/iso10126";
	public final static String DES_ECB_ANSIX923 = "DES/ECB/ansix923";
	public final static String DES_CBC_PKCS5 = "DES/CBC/PKCS5Padding";
	public final static String DES_CBC_PKCS7 = "DES/CBC/PKCS7Padding";
	public final static String DES_CBC_ZERO = "DES/CBC/zeropadding";
	public final static String DES_CBC_ISO10126 = "CBC/ECB/iso10126";
	public final static String DES_CBC_ANSIX923 = "CBC/ECB/ansix923";
	public final static String DES_CTR_PKCS5 = "DES/CTR/PKCS5Padding";
	public final static String DES_CTR_PKCS7 = "DES/CTR/PKCS7Padding";
	public final static String DES_CTR_ZERO = "DES/CTR/zeropadding";
	public final static String DES_CTR_ISO10126 = "DES/CTR/iso10126";
	public final static String DES_CTR_ANSIX923 = "DES/CTR/ansix923";
	public final static String DES_OFB_PKCS5 = "DES/OFB/PKCS5Padding";
	public final static String DES_OFB_PKCS7 = "DES/OFB/PKCS7Padding";
	public final static String DES_OFB_ZERO = "DES/OFB/zeropadding";
	public final static String DES_OFB_ISO10126 = "DES/OFB/iso10126";
	public final static String DES_OFB_ANSIX923 = "DES/OFB/ansix923";
	public final static String DES_CFB_PKCS5 = "DES/CFB/PKCS5Padding";
	public final static String DES_CFB_PKCS7 = "DES/CFB/PKCS7Padding";
	public final static String DES_CFB_ZERO = "DES/CFB/zeropadding";
	public final static String DES_CFB_ISO10126 = "DES/CFB/iso10126";
	public final static String DES_CFB_ANSIX923 = "DES/CFB/ansix923";

	/** base64码表 */
	public static char[] base64EncodeTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
			'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '+', '/' };
	// 加解密统一使用的编码方式
	public final static String ENCODING = ConstantHelper.UTF_8.name();

	/**
	 * @方法描述: 对数据进行加密.
	 * @param datasource
	 *            加密数据
	 * @param key
	 *            加密密钥
	 * @param encryptType
	 *            加密类型
	 * @param length
	 *            长度
	 * @return 返回加密后的数据
	 */
	public static String encrypt(String dataSource, String key, String algorithmName,  String fillType,Integer length)
			throws Exception {
		String result = "";
		switch (algorithmName) {
		case MD2:
			break;
		case MD4:
			break;
		case MD5:
			result = Md5Helper.md5(dataSource, length);
			break;
		case DES:{
			if(StringUtils.isBlank(fillType)){
				fillType = DES_CBC_PKCS5;
			}
			result = DesHelper.encrypt(dataSource, key,fillType);
			break;
		}
		case SHA1:
			break;
		case SHA256:
			break;
		default:
			result = dataSource;
			break;
		}
		return result;
	}

	/**
	 * @方法描述: 对数据进行解密
	 * @param dataSource
	 *            解密数据
	 * @param key
	 *            解密密钥
	 * @param decryptType
	 *            解密类型
	 * @param length
	 *            长度
	 * @return 返回解密后的数据
	 */
	public static String decrypt(String dataSource, String key, String algorithmName, String fillType,Integer length)
			throws Exception {
		String result = "";
		switch (algorithmName) {
		case MD2:
			break;
		case MD4:
			break;
		case MD5:
			break;
		case DES:{
			if(StringUtils.isBlank(fillType)){
				fillType = DES_CBC_PKCS5;
			}
			result = DesHelper.decrypt(dataSource, key,fillType);
			break;
		}
		case SHA1:
			break;
		case SHA256:
			break;
		default:
			result = dataSource;
			break;
		}
		return result;
	}
}
