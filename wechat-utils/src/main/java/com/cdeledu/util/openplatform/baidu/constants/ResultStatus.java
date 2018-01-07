package com.cdeledu.util.openplatform.baidu.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: ResultStatus
 * @Description: 返回结果状态码
 * @author: 独泪了无痕
 * @date: 2015年8月31日 上午10:45:26
 * @version: V1.0
 * @history:
 */
public class ResultStatus {
	public static Map<String, String> statusMap = new HashMap<String, String>();
	static {
		statusMap.put("0", "正常");
		statusMap.put("1", "服务器内部错误");
		statusMap.put("2", "请求参数非法");
		statusMap.put("3", "权限校验失败");
		statusMap.put("4", "配额校验失败");
		statusMap.put("5", "ak不存在或者非法");
		statusMap.put("10", "Post上传数据不能超过8M");
		statusMap.put("101", "请求消息没有携带AK参数 (服务禁用)");
		statusMap.put("102", "不通过白名单或者安全码不对");
		statusMap.put("2xx", "无权限");
		statusMap.put("200", "根据请求的ak，找不到对应的APP");
		statusMap.put("201", "AK有误请检查再重试(APP被用户禁用)");
		statusMap.put("202", "AK有误请检查再重试(APP被管理员删除)");
		statusMap.put("203", "APP类型错误 ");
		statusMap.put("210", "APP IP校验失败,请检查请求校验方式");
		statusMap.put("211", "APP SN校验失败");
		statusMap.put("220", "APP Referer校验失败");
		statusMap.put("230", "APP Mcode码校验失败 ");
		statusMap.put("240", "APP 服务被禁用 ");
		statusMap.put("250", "请求的用户不存在");
		statusMap.put("251", "该用户处于未激活状态 ");
		statusMap.put("252", "恶意用户被加入黑名单");
		statusMap.put("260", "服务不存在(服务器解析不到用户请求的服务名称)");
		statusMap.put("261", " 服务被禁用(该服务已下线)");
		statusMap.put("3xx", "配额错误 ");
		statusMap.put("301", "限制访问");
		statusMap.put("302", "限制访问");
		statusMap.put("401", "限制访问");
		statusMap.put("402", "限制访问");

		/** 百度翻译翻译返回状态码 */
		statusMap.put("52001", "TIMEOUT:请求超时");
		statusMap.put("52002", "SYSTEM ERROR：翻译系统错误");
		statusMap.put("52003", "未授权的用户【请检查是否将api key输入错误】");
		statusMap.put("54000", "必填参数为空【请检查是否相关参数未填写完整】");
		statusMap.put("58000", "客户端IP非法【检查您填写的IP地址是否正确】");
		statusMap.put("54001", "签名错误【请检查您的签名生成方法】");
		statusMap.put("54003", "访问频率受限【请降低您的调用频率】");
		statusMap.put("58001", "译文语言方向不支持【检查译文语言是否在语言列表里】");
		statusMap.put("54004", "账户余额不足【请为账户充值】");
		statusMap.put("54005", "长query请求频繁【请降低长query的发送频率，3s后再试】");
	}
}
