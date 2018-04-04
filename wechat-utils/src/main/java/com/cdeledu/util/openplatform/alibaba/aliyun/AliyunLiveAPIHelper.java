package com.cdeledu.util.openplatform.alibaba.aliyun;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 视频直播服务提供Web管理控制台、API和软件开发工具包，通过它们使用、管理视频直播服务。
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月4日 上午8:09:04
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="help.aliyun.com/document_detail/48207.html">直播API概述</a>
 */
public final class AliyunLiveAPIHelper {
	/** ----------------------------------------------------- Fields start */
	/** API的服务接入地址 */
	private static final String RDS_API = "https://live.aliyuncs.com/";
	/** API 版本号 */
	private static final String apiVersion = "2016-11-01";
	/** 用户的访问服务所用的密钥ID */
	private String accessKeyId;
	/** ----------------------------------------------------- Fields end */
	public AliyunLiveAPIHelper(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
}
