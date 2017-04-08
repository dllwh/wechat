package com.cdeledu.util.security;

/**
 * @类描述: 16进制工具类:十六进制与一般数据类型之间的互相转换
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月5日 下午4:36:17
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HexUtilHelper {
	/** ----------------------------------------------------- Fields start */
	/**
	 * 用于将输出作为十六进制
	 */
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * 用于将输出作为十六进制
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F' };

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述:将字节数组转换为十六进制字符数组
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:10:46
	 * @param data
	 * @param toDigits
	 *            用于控制输出的char[]
	 * @return 十六进制char[]
	 */
	protected static char[] encodeHex(byte[] data, char[] toDigits) {
		int l = data.length;
		char[] out = new char[l << 1];
		// 从十六进制值的两个字符.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	/**
	 * @方法描述: 将字节数组转换为十六进制字符串
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:12:43
	 * @param data
	 * @param toDigits
	 *            用于控制输出的char[]
	 * @return 十六进制String
	 */
	protected static String encodeHexStr(byte[] data, char[] toDigits) {
		return new String(encodeHex(data, toDigits));
	}

	/**
	 * @方法描述: 将十六进制字符转换成一个整数
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:14:48
	 * @param ch
	 *            十六进制char
	 * @param index
	 *            十六进制字符在字符数组中的位置
	 * @return 一个整数
	 * @throws RuntimeException
	 *             当ch不是一个合法的十六进制字符时，抛出运行时异常
	 */
	protected static int toDigit(char ch, int index) {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new RuntimeException(
					"Illegal hexadecimal character " + ch + " at index " + index);
		}
		return digit;
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 将字节数组转换为十六进制字符数组
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:11:12
	 * @param data
	 * @return
	 */
	public static char[] encodeHex(byte[] data) {
		return encodeHex(data, true);
	}

	/**
	 * @方法描述: 将字节数组转换为十六进制字符数组
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:11:24
	 * @param data
	 * @param toLowerCase
	 *            <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
	 * @return
	 */
	public static char[] encodeHex(byte[] data, boolean toLowerCase) {
		return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	/**
	 * @方法描述: 将字节数组转换为十六进制字符串
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:11:57
	 * @param data
	 * @return
	 */
	public static String encodeHexStr(byte[] data) {
		return encodeHexStr(data, true);
	}

	/**
	 * @方法描述: 将字节数组转换为十六进制字符串
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:12:17
	 * @param data
	 * @param toLowerCase
	 *            <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
	 * @return
	 */
	public static String encodeHexStr(byte[] data, boolean toLowerCase) {
		return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	/**
	 * @方法描述: 将十六进制字符数组转换为字节数组
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午7:15:32
	 * @param data
	 *            十六进制char[]
	 * @return
	 * @throws RuntimeException
	 *             如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
	 */
	public static byte[] decodeHex(char[] data) {

		int len = data.length;

		if ((len & 0x01) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}

		byte[] out = new byte[len >> 1];

		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f = f | toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}
}
