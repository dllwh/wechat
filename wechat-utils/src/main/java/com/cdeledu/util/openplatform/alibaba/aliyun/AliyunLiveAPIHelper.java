package com.cdeledu.util.openplatform.alibaba.aliyun;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import com.aliyuncs.utils.ParameterHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.google.common.collect.Maps;

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
	private static final String live_api = "https://live.aliyuncs.com/?";
	/** API 版本号 */
	private static final String apiVersion = "2016-11-01";
	/** 用户的访问服务所用的密钥,用于标识访问者的身份 */
	private String accessKeyId;
	/** 用于加密签名、验证签名字符串的密钥 */
	private String accessKeySecret;
	/** 直播流所属应用名称 */
	private String appName;
	/** 拉流域名为用户的播放域名 */
	private String domainName;

	/** ----------------------------------------------------- Fields end */
	public AliyunLiveAPIHelper(String accessKeyId, String accessKeySecret) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}

	public AliyunLiveAPIHelper(String accessKeyId, String accessKeySecret, String appName) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.appName = appName;
	}

	public AliyunLiveAPIHelper(String accessKeyId, String accessKeySecret, String appName,
			String domainName) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.appName = appName;
		this.domainName = domainName;
	}

	/**
	 * @方法:添加直播拉流信息，仅支持拉取直播流
	 * @创建人:独泪了无痕
	 * @param startTime
	 *            拉流开始时间,UTC格式,StartTime和EndTime时间间隔在7天内
	 * @param endTime
	 *            拉流结束时间,UTC格式,StartTime和EndTime时间间隔在7天内，且EndTime超过当前时间。
	 * @param sourceUrl
	 *            户的直播流所在的源站。 多个源站可以填多个地址，用分号分隔
	 * @param streamName
	 *            直播流名
	 */
	public String createLivePullStream(Date startTime, Date endTime, String sourceUrl,
			String streamName) {
		if (DateUtilHelper.getDistanceOfTwoDate(startTime, endTime) < 7
				&& endTime.getTime() < System.currentTimeMillis()) {
			return "";
		}

		Map<String, String> paramsMap = Maps.newHashMap();
		paramsMap.put("Action", "AddLivePullStreamInfoConfig"); // 系统规定参数
		paramsMap.put("AppName", appName);
		paramsMap.put("DomainName", domainName);
		paramsMap.put("StartTime", ParameterHelper.getISO8601Time(startTime));
		paramsMap.put("EndTime", ParameterHelper.getISO8601Time(endTime));
		paramsMap.put("SourceUrl", sourceUrl);
		paramsMap.put("StreamName", streamName);
		SecurityTokenHelper accessSecretHelper = new SecurityTokenHelper(accessKeyId,
				accessKeySecret);

		String result = "";
		try {
			result = live_api + accessSecretHelper.createSecurityToken(paramsMap, null, apiVersion);
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
