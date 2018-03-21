package com.cdeledu.util;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 验证码
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月8日 上午11:46:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class VerifyCodeHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */

	/**
	 * @方法描述 : 生成校验码
	 * @param 验证码来源
	 */
	public static void generateVerifyCode(String sources) {

	}

	/**
	 * @方法描述 : 清除验证码
	 */
	public static void clearVerifyCode() {

	}

	/**
	 * @方法描述 : 校验验证码
	 * @param code
	 * @return
	 */
	public static boolean verifyCode(String code) {
		// 验证是否验证码，正确后清除验证码会话，防止重复利用攻击API
		clearVerifyCode();
		return false;
	}

	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述 : 生成随机验证码文件,并返回验证码值
	 * @return
	 */
	public static String outputVerifyImage() {
		String verifyCode = "";
		return verifyCode;
	}

	/**
	 * @方法描述 : 生成指定验证码图像文件
	 */
	public static void outputImage() {

	}
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
