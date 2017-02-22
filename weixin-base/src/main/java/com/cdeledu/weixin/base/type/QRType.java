package com.cdeledu.weixin.base.type;

/**
 * @ClassName: QRType
 * @Description: 二维码类型
 * @author: 独泪了无痕
 * @date: 2015年9月17日 上午10:40:52
 * @version: V1.0
 * @history:
 */
public enum QRType {
	/**
	 * 临时二维码
	 */
	QR_SCENE,
	/**
	 * 永久二维码(场景值为数字范围在1-100000之间)
	 */
	QR_LIMIT_SCENE,
	/**
	 * 永久二维码(场景值为字符串长度在1-64之间)
	 */
	QR_LIMIT_STR_SCENE;
}
