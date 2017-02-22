package com.cdeledu.util.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @类描述: PBE——Password-based encryption（基于密码加密）
 *       <ul>
 *       <li>其特点在于口令由用户自己掌管,不借助任何物理媒体,采用随机数（这里我们叫做盐）杂凑多重加密等方法保证数据的安全性</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年5月23日 下午6:38:12
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PasswordUtil {
	/** ----------------------------------------------------- Fields start */
	/**
	 * 支持以下任意一种算法
	 * 
	 * <pre>
	 * PBEWithMD5AndDES 
	 * PBEWithMD5AndTripleDES 
	 * PBEWithSHA1AndDESede
	 * PBEWithSHA1AndRC2_40
	 * </pre>
	 */
	private static final String ALGORITHM = "PBEWITHMD5andDES";
	private static final String private_Key = "4cd2934d";// 密钥
	private static final int ITERATIONCOUNT = 1000; // 定义迭代次数为1000次

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 根据PBE密码生成一把密钥
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月23日 下午8:03:52
	 * @param password
	 *            生成密钥时所使用的密码
	 * @return PBE算法密钥
	 */
	private static Key getPBEKey(String password) {
		// 实例化使用的算法
		SecretKeyFactory keyFactory;
		SecretKey secretKey = null;
		try {
			keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			// 设置PBE密钥参数
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			// 生成密钥
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKey;
	}

	private static byte charToByte(char c) {
		// return (byte) "0123456789ABCDEF".indexOf(c);
		return (byte) "D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900".indexOf(c);
	}

	/**
	 * @方法描述: 将字节数组转换为十六进制字符串
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月23日 下午8:10:54
	 * @param src
	 * @return
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * @方法描述: 将十六进制字符串转换为字节数组
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月23日 下午8:12:40
	 * @param hexString
	 *            十六进制字符串
	 * @return
	 */
	private static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte[] getStaticSalt() {
		// 产出盐
		return private_Key.getBytes();
	}

	/**
	 * @方法描述: 加密明文字符串
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月23日 下午6:47:56
	 * @param plaintext
	 *            待加密的明文字符串
	 * @param password
	 *            生成密钥时所使用的密码
	 * @param salt
	 *            盐值
	 * @return 加密后的密文字符串
	 */
	public static String encrypt(String plaintext, String password) {
		Key key = getPBEKey(password);
		byte[] encipheredData = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

			encipheredData = cipher.doFinal(plaintext.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytesToHexString(encipheredData);
	}

	/**
	 * 
	 * @方法描述: 解密密文字符串
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年5月23日 下午6:48:45
	 * @param ciphertext
	 *            待解密的密文字符串
	 * @param password
	 *            生成密钥时所使用的密码(如需解密,该参数需要与加密时使用的一致)
	 * @param salt
	 *            盐值(如需解密,该参数需要与加密时使用的一致)
	 * @return 解密后的明文字符串
	 */
	public static String decrypt(String ciphertext, String password) {
		Key key = getPBEKey(password);
		byte[] passDec = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

			passDec = cipher.doFinal(hexStringToBytes(ciphertext));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(passDec);
	}
}
