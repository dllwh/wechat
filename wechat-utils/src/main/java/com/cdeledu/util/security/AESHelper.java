package com.cdeledu.util.security;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 高级加密标准，是下一代的加密算法标准，速度快，安全级别高；AES是一个使用128为分组块的分组加密算法，分组块和128、
 *       192或256位的密钥一起作为输入，对4×4的字节数组上进行操作。众所周之AES是种十分高效的算法，尤其在8位架构中，这源于它面向字节的设计。
 *       AES 适用于8位的小型单片机或者普通的32位微处理器,并且适合用专门的硬件实现，硬件实现能够使其吞吐量（每秒可以到达的加密/解密bit数）
 *       达到十亿量级。同样，其也适用于RFID系统。
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月24日 上午8:56:18
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class AESHelper {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	private static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
	}

	/**
	 * @方法描述 : 加密数据解密算法
	 * @param encryptedData
	 *            解密数据
	 * @param iv
	 *            偏移量
	 * @param key
	 *            解密秘钥
	 * @param decryptType
	 *            解密类型
	 * @param decryptMode
	 *            解密模式
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws Exception
	 */
	public static String decrypt(String decryptData, String iv, String key, String decryptType,
			String decryMode)
			throws InvalidKeyException, InvalidAlgorithmParameterException, Exception {
		byte[] sessionKeyByte = Base64.decodeBase64(key);
		byte[] ivByte = Base64.decodeBase64(iv);
		byte[] encryptDataByte = Base64.decodeBase64(decryptData);
		// 初始化
		Security.addProvider(new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(decryptType, decryMode);
		Key sKeySpec = new SecretKeySpec(sessionKeyByte, SecureUtil.AES);
		AlgorithmParameters parameters = AlgorithmParameters.getInstance(SecureUtil.AES);
		parameters.init(new IvParameterSpec(ivByte));
		// 初始化
		cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));
		return new String(cipher.doFinal(encryptDataByte), "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		// 偏移量
		String iv = "r7BXXKkLb8qrSNn05n0qiA==";
		// 加密秘钥
		String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
		// 被加密的数据
		String decryptData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
		String result = decrypt(decryptData, iv, sessionKey, SecureUtil.AES_CBC_PKCS7, "BC");
		System.out.println(result);
	}
}
