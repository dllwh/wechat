package com.cdeledu.util.openplatform.alibaba.aliyun;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 云数据库 RDS 版 > 开发指南
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月4日 上午8:07:34
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="help.aliyun.com/document_detail/54053.html">云数据库 RDS 版 </a>
 */
public final class AliyunRdsHelper {
	/** ----------------------------------------------------- Fields start */
	/** RDS API的服务接入地址 */
	private static final String RDS_API = "https://rds.aliyuncs.com/?";
	/** API 版本号 */
	private static final String apiVersion = "2014-08-15";
	/** 用户的访问服务所用的密钥,用于标识访问者的身份 */
	private String accessKeyId;
	/** 用于加密签名、验证签名字符串的密钥 */
	private String accessKeySecret;
	private SecurityTokenHelper accessSecretHelper = null;

	/** ----------------------------------------------------- Fields end */
	public AliyunRdsHelper(String accessKeyId, String accessKeySecret) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}

	/**
	 * @方法:查找指定实例、指定数据库的账号列表信息或某个指定账号的信息
	 * @创建人:独泪了无痕
	 * @param dBInstanceId
	 *            实例的名称,不能为空
	 * @param accountName
	 *            账号的名称,能为空
	 * @param ownerAccount
	 *            用户的主账号,能为空
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public String describeAccounts(String dBInstanceId, String accountName, String ownerAccount)
			throws InvalidKeyException, NoSuchAlgorithmException {
		accessSecretHelper = new SecurityTokenHelper(accessKeyId, accessKeySecret);
		Map<String, String> paramsMap = Maps.newHashMap();
		if (StringUtils.isBlank(dBInstanceId)) {
			return "";
		}
		paramsMap.put("Action", "DescribeAccounts");
		paramsMap.put("DBInstanceId", dBInstanceId);
		return RDS_API + accessSecretHelper.createSecurityToken(paramsMap, "GET", apiVersion);
	}
}
