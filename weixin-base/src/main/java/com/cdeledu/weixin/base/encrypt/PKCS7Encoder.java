/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

package com.cdeledu.weixin.base.encrypt;

import java.util.Arrays;

import com.cdeledu.common.weixin.WeiXinConstants;

/**
 * @类描述: 提供基于PKCS7算法的加解密接口.
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年7月12日 下午8:36:52
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://mp.weixin.qq.com/wiki/2/3478f69c0d0bbe8deb48d66a3111ff6e.html">
 *      阅读须知</a>
 */
class PKCS7Encoder {
	/** ----------------------------------------------------- Fields start */
	static int BLOCK_SIZE = 32;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 将数字转化成ASCII码对应的字符，用于对明文进行补码
	 * @创建者: 皇族灬战狼
	 * @param a
	 *            需要转化的数字
	 * @return 转化得到的字符
	 */
	static char chr(int a) {
		byte target = (byte) (a & 0xFF);
		return (char) target;
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * 获得对明文进行补位填充的字节.
	 * 
	 * @param count
	 *            需要进行填充补位操作的明文字节个数
	 * @return 补齐用的字节数组
	 */
	static byte[] encode(int count) {
		// 计算需要填充的位数
		int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
		if (amountToPad == 0) {
			amountToPad = BLOCK_SIZE;
		}
		// 获得补位所用的字符
		char padChr = chr(amountToPad);
		String tmp = new String();
		for (int index = 0; index < amountToPad; index++) {
			tmp += padChr;
		}
		return tmp.getBytes(WeiXinConstants.UTF_8);
	}

	/**
	 * @方法描述: 删除解密后明文的补位字符
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午8:43:48
	 * @param decrypted
	 *            解密后的明文
	 * @return 删除补位字符后的明文
	 */
	static byte[] decode(byte[] decrypted) {
		int pad = (int) decrypted[decrypted.length - 1];
		if (pad < 1 || pad > 32) {
			pad = 0;
		}
		return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
	}

}
