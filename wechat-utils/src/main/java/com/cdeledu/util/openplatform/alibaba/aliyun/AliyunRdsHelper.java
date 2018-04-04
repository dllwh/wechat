package com.cdeledu.util.openplatform.alibaba.aliyun;

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
	private static final String RDS_API = "https://rds.aliyuncs.com/";
	/** API 版本号 */
	private static final String apiVersion = "2014-08-15";
	/** 用户的访问服务所用的密钥,用于标识访问者的身份 */
	private String accessKeyId;

	/** ----------------------------------------------------- Fields end */
	public AliyunRdsHelper(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

}
