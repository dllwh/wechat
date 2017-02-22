package com.cdeledu.weixin.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: statusMap
 * @Description: 微信公众平台开发模式(JAVA):微信接口全局返回码说明
 *               <ul>
 *               <li>公众号每次调用接口时,可能获得正确或错误的返回码,可以根据返回码信息调试接口，排查错误</li>
 *               <li></li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年8月21日 下午5:32:26
 * @version: V1.0
 * @history:
 */
public class WeixinStatus {
	/*-------------------------- 私有属性 begin -------------------------------*/
	private static Map<Integer, String> statusMap = new HashMap<Integer, String>();

	static {
		statusMap.put(0, "请求成功");
		statusMap.put(40001, "获取access_token时AppSecret错误，或者access_token无效");
		statusMap.put(40002, "不合法的凭证类型");
		statusMap.put(40003, "不合法的OpenID");
		statusMap.put(40004, "不合法的媒体文件类型");
		statusMap.put(40005, "不合法的文件类型");
		statusMap.put(40006, "不合法的文件大小");
		statusMap.put(40007, "不合法的媒体文件id");
		statusMap.put(40008, "不合法的消息类型");
		statusMap.put(40009, "不合法的图片文件大小");
		statusMap.put(40010, "不合法的语音文件大小");
		statusMap.put(40011, "不合法的视频文件大小");
		statusMap.put(40012, "不合法的缩略图文件大小");
		statusMap.put(40013, "不合法的APPID");
		statusMap.put(40014, "不合法的access_token");
		statusMap.put(40015, "不合法的菜单类型");
		statusMap.put(40016, "不合法的按钮个数");
		statusMap.put(40017, "不合法的按钮个数");
		statusMap.put(40018, "不合法的按钮名字长度");
		statusMap.put(40019, "不合法的按钮KEY长度");
		statusMap.put(40020, "不合法的按钮URL长度");
		statusMap.put(40021, "不合法的菜单版本号");
		statusMap.put(40022, "不合法的子菜单级数");
		statusMap.put(40023, "不合法的子菜单按钮个数");
		statusMap.put(40024, "不合法的子菜单按钮类型");
		statusMap.put(40025, "不合法的子菜单按钮名字长度");
		statusMap.put(40026, "不合法的子菜单按钮KEY长度");
		statusMap.put(40027, "不合法的子菜单按钮URL长度");
		statusMap.put(40028, "不合法的自定义菜单使用用户");
		statusMap.put(40029, "不合法的oauth_code");
		statusMap.put(40030, "不合法的refresh_token");
		statusMap.put(40031, "不合法的openid列表");
		statusMap.put(40032, "不合法的openid列表长度");
		statusMap.put(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符");
		statusMap.put(40035, "不合法的参数");
		statusMap.put(40038, "不合法的请求格式");
		statusMap.put(40039, "不合法的URL长度");
		statusMap.put(40050, "不合法的分组id");
		statusMap.put(40051, "分组名字不合法");
		statusMap.put(40117, "缺少access_token参数");
		statusMap.put(40118, "media_id大小不合法");
		statusMap.put(40119, "button类型错误");
		statusMap.put(40120, "button类型错误");
		statusMap.put(40121, "不合法的media_id类型");
		statusMap.put(40132, "微信号不合法");
		statusMap.put(40137, "不支持的图片格式");
		statusMap.put(41001, "缺少access_token参数");
		statusMap.put(41002, "缺少appid参数");
		statusMap.put(41003, "缺少refresh_token参数");
		statusMap.put(41004, "缺少secret参数");
		statusMap.put(41005, "缺少多媒体文件数据");
		statusMap.put(41006, "缺少media_id参数");
		statusMap.put(41007, "缺少子菜单数据");
		statusMap.put(41008, "缺少oauth code");
		statusMap.put(41009, "缺少openid");
		statusMap.put(42001, "access_token超时");
		statusMap.put(42002, "refresh_token超时");
		statusMap.put(42003, "oauth_code超时");
		statusMap.put(43001, "需要GET请求");
		statusMap.put(43002, "需要POST请求");
		statusMap.put(43003, "需要HTTPS请求");
		statusMap.put(43004, "需要接收者关注");
		statusMap.put(43005, "需要好友关系");
		statusMap.put(44001, "多媒体文件为空");
		statusMap.put(44002, "POST的数据包为空");
		statusMap.put(44003, "图文消息内容为空");
		statusMap.put(44004, "文本消息内容为空");
		statusMap.put(45001, "多媒体文件大小超过限制");
		statusMap.put(45002, "消息内容超过限制");
		statusMap.put(45003, "标题字段超过限制");
		statusMap.put(45004, "描述字段超过限制");
		statusMap.put(45005, "链接字段超过限制");
		statusMap.put(45006, "图片链接字段超过限制");
		statusMap.put(45007, "语音播放时间超过限制");
		statusMap.put(45008, "图文消息超过限制");
		statusMap.put(45009, "接口调用超过限制");
		statusMap.put(45010, "创建菜单个数超过限制");
		statusMap.put(45015, "回复时间超过限制");
		statusMap.put(45016, "系统分组，不允许修改");
		statusMap.put(45017, "分组名字过长");
		statusMap.put(45018, "分组数量超过上限");
		statusMap.put(46001, "不存在媒体数据");
		statusMap.put(46002, "不存在的菜单版本");
		statusMap.put(46003, "不存在的菜单数据");
		statusMap.put(46004, "不存在的用户");
		statusMap.put(47001, "解析JSON/XML内容错误");
		statusMap.put(48001, "api功能未授权");
		statusMap.put(50001, "用户未授权该api");
		statusMap.put(50002, "用户受限，可能是违规后接口被封禁");
		statusMap.put(61451, "参数错误(invalid parameter)");
		statusMap.put(61452, "无效客服账号(invalid kf_account)");
		statusMap.put(61453, "客服帐号已存在(kf_account exsited)");
		statusMap.put(61454, "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)");
		statusMap.put(61455, "客服帐号名包含非法字符(仅允许英文+数字)");
		statusMap.put(61456, "客服帐号个数超过限制(10个客服账号)");
		statusMap.put(61457, "无效头像文件类型");
		statusMap.put(61450, "系统错误(system error)");
		statusMap.put(61500, "日期格式错误");
		statusMap.put(61501, "日期范围错误");
		statusMap.put(9001001, "POST数据参数不合法");
		statusMap.put(9001002, "远端服务不可用");
		statusMap.put(9001003, "Ticket不合法");
		statusMap.put(9001004, "获取摇周边用户信息失败");
		statusMap.put(9001005, "获取商户信息失败");
		statusMap.put(9001006, "获取OpenID失败");
		statusMap.put(9001007, "上传文件缺失");
		statusMap.put(9001008, "上传素材的文件类型不合法");
		statusMap.put(9001009, "上传素材的文件尺寸不合法");
		statusMap.put(9001010, "上传失败");
		statusMap.put(9001020, "帐号不合法");
		statusMap.put(9001021, "已有设备激活率低于50%，不能新增设备");
		statusMap.put(9001022, "设备申请数不合法，必须为大于0的数字");
		statusMap.put(9001023, "已存在审核中的设备ID申请");
		statusMap.put(9001024, "一次查询设备ID数量不能超过50");
		statusMap.put(9001025, "设备ID不合法");
		statusMap.put(9001026, "页面ID不合法");
		statusMap.put(9001027, "页面参数不合法");
		statusMap.put(9001028, "一次删除页面ID数量不能超过10");
		statusMap.put(9001029, "页面已应用在设备中，请先解除应用关系再删除");
		statusMap.put(9001030, "一次查询页面ID数量不能超过50");
		statusMap.put(9001031, "时间区间不合法");
		statusMap.put(9001032, "保存设备与页面的绑定关系参数错误");
		statusMap.put(9001033, "门店ID不合法");
		statusMap.put(9001034, "设备备注信息过长");
		statusMap.put(9001035, "设备申请参数不合法");
		statusMap.put(9001036, "查询起始值begin不合法");
	}

	/*-------------------------- 私有属性 end   -------------------------------*/
	/*-------------------------- 私有方法 begin -------------------------------*/
	/*-------------------------- 私有方法 end   -------------------------------*/
	/*-------------------------- 公有方法 begin -------------------------------*/
	/**
	 * 
	 * @Title：getErrorInfo
	 * @Description：错误信息
	 * @param code
	 * @return
	 * @return：String 返回类型
	 */
	public static String getErrorInfo(int code) {
		if (statusMap.containsKey(code)) {
			return statusMap.get(code);
		}
		return "【" + code + "】未知错误!";
	}

	/*-------------------------- 公有方法 end   -------------------------------*/
}
