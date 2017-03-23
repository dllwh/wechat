package com.cdeledu.util.security;

import java.security.SecureRandom;
import java.util.UUID;

import com.cdeledu.util.message.unicode.EncodesHelper;

/**
 * @类描述: 封装各种生成唯一性ID算法的工具类.
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月25日 下午4:06:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class IdGenHelperUtils {
	/** ----------------------------------------------------- Fields start */
	private static SecureRandom random = new SecureRandom();

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static final String getUUid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodesHelper.encodeBase62(randomBytes);
	}
}
