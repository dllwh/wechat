package com.cdeledu.util.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * @类名称：SecureUtil @功能说明： 安全相关的工具类，包括各种加密算法
 * 
 * @创建人：dell
 *
 */
public class SecureUtil {
	public static final String MD2 = "MD2";
	public static final String MD4 = "MD4";
	public static final String MD5 = "MD5";
	public static final String DES = "DES";

	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";

	public static final String HMAC_SHA1 = "HmacSHA1";

	public static final String RIPEMD128 = "RIPEMD128";
	public static final String RIPEMD160 = "RIPEMD160";
	/** 加密、解密key. */
	private static final String PASSWORD_CRYPT_KEY = "bdda4ecabbd6d5c324ac44abddd107ad";

	/** base64码表 */
	public static char[] base64EncodeTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
			'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '+', '/' };

	/**
	 * @方法描述: MD5加密
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月8日 上午11:05:20
	 * @param plainText
	 * @param num
	 * @return
	 */
	public static String Md5(String plainText, int num) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (num == 16) {
			return buf.toString().substring(8, 24);
		} else {
			return buf.toString();
		}
	}

	/**
	 * @方法描述: 对数据进行DES加密.
	 * @param datasource
	 *            DES加密数据
	 * @return 返回解密后的数据
	 */
	public static String encrypt(String datasource) {
		return encrypt(datasource, PASSWORD_CRYPT_KEY);
	}

	/**
	 * @方法描述: 用指定的key对数据进行DES加密.
	 * @param datasource
	 *            DES加密数据
	 * @param password
	 *            DES加密的key
	 * @return 返回解密后的数据
	 */
	public static String encrypt(String datasource, String key) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource.getBytes()).toString();
		} catch (Exception e) {

		}
		return "";
	}

	/**
	 * @方法描述: 用指定的key对数据进行DES解密.
	 * @param datasource
	 *            待解密的数据
	 * @param password
	 *            DES解密的key
	 * @return 返回DES解密后的数据
	 */
	public static String decrypt(String datasource, String key) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			// 现在，获取数据并解密
			// 正式执行解密操作
			return cipher.doFinal(datasource.getBytes()).toString();
		} catch (Exception e) {
		}
		return "";
	}
}
