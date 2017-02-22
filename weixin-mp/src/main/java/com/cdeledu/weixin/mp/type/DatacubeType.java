package com.cdeledu.weixin.mp.type;

import com.cdeledu.weixin.mp.datacube.UserSummary;

/**
 * @ClassName: DatacubeType
 * @Description: 数据统计类型
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午1:46:37
 * @version: V1.0
 * @since: JDK 1.7
 */
public enum DatacubeType {
	/** 【用户分析数据接口】 */
	/**
	 * 1.获取用户增减数据
	 */
	GETUSERSUMMARY(UserSummary.class),
	/**
	 * 2.获取累计用户数据
	 */
	GETUSERCUMULATE(UserSummary.class);
	/** 【图文分析数据接口】 */
	/**
	 * 3.获取图文群发每日数据
	 */
	/**
	 * 4.获取图文群发总数据
	 */
	/**
	 * 5.获取图文统计数据
	 */
	/**
	 * 6.获取图文统计分时数据
	 */
	/**
	 * 7.获取图文分享转发数据
	 */
	/**
	 * 8.获取图文分享转发分时数据
	 */
	/** 【消息分析数据接口】 */
	/**
	 * 9.获取消息发送概况数据
	 */
	/**
	 * 10.获取消息分送分时数据
	 */
	/**
	 * 11.获取消息发送周数据
	 */
	/**
	 * 12.获取消息发送月数据
	 */
	/**
	 * 13.获取消息发送分布数据
	 */
	/**
	 * 14.获取消息发送分布周数据
	 */
	/**
	 * 15.获取消息发送分布月数据
	 */
	/** 【接口分析数据接口】 */
	/**
	 * 16.获取接口分析数据
	 */
	/**
	 * 17.获取接口分析分时数据
	 */

	private Class<?> clazz;

	DatacubeType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}
}
