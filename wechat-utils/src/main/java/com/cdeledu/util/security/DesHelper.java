package com.cdeledu.util.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @类描述: DES全称为Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年4月8日 上午11:12:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
class DesHelper {
	/** ----------------------------------------------------- Fields start */
	// 加解密统一使用的编码方式
	public final static String ENCODING = ConstantHelper.UTF_8.name();

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 用指定的key对数据进行DES加密.
	 * @param datasource
	 *            DES加密数据
	 * @param password
	 *            DES加密的key
	 * @return 返回解密后的数据
	 */
	public static String encrypt(String dataSource, String key, String type) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(key.getBytes(ENCODING));
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SecureUtil.DES);
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(type);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
		// 正式执行加密操作
		return Base64Helper.encode(cipher.doFinal(dataSource.getBytes(ENCODING)));
	}

	/**
	 * @方法描述: 用指定的key对数据进行DES解密.
	 * @param datasource
	 *            待解密的数据
	 * @param password
	 *            DES解密的key
	 * @return 返回DES解密后的数据
	 */
	public static String decrypt(String datasource, String key, String type) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes(ENCODING));
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SecureUtil.DES);
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(type);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 正式执行解密操作
		/**
		 * 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be
		 * multiple of 8 when decrypting with padded cipher异常，
		 * 不能把加密后的字节数组直接转换成字符串
		 */
		byte[] encryptBytes = cipher.doFinal(Base64Helper.decode(datasource.toCharArray()));

		return new String(encryptBytes);
	}

	public static void main(String[] args) throws Exception {
		String input = "cy11Xlbrmzyh:604:301:1353064296";
		String key = "37d5aed075525d4fa0fe635231cba447";
		String result = encrypt(input, key, SecureUtil.DES_ECB_PKCS5);
		System.out.println("加密后:" + result);
		System.out.println(decrypt(result, key, SecureUtil.DES_ECB_PKCS5));
	}
}
