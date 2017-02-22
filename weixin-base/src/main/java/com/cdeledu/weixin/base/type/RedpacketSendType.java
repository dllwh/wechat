package com.cdeledu.weixin.base.type;

/**
 * 
 * @ClassName: RedpacketSendType
 * @Description: 红包发放类型
 * @author: 独泪了无痕
 * @date: 2015年10月10日 上午9:47:35
 * @version: V1.0
 * @history:
 */
public enum RedpacketSendType {
	/**
	 * 通过API接口发放
	 */
	API,
	/**
	 * 通过上传文件方式发放
	 */
	UPLOAD,
	/**
	 * 通过活动方式发放
	 */
	ACTIVITY;
}
