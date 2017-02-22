package com.cdeledu.util.security;

import java.security.MessageDigest;

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

	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";

	public static final String HMAC_SHA1 = "HmacSHA1";

	public static final String RIPEMD128 = "RIPEMD128";
	public static final String RIPEMD160 = "RIPEMD160";

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
}
