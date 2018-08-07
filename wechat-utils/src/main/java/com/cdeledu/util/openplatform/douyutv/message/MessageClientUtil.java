package com.cdeledu.util.openplatform.douyutv.message;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 客户端消息处理助手
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月7日 下午9:33:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class MessageClientUtil {
	/**
	 * @方法描述 : 生成登录请求数据包
	 * @param roomId
	 *            所登录房间的 ID
	 */
	public static byte[] getLoginRequestData(int roomId) {
		MessageFormatUtil helper = new MessageFormatUtil();
		helper.addItem("type", "loginreq");
		helper.addItem("roomid", roomId);
		return helper.getByte(helper.getResult());
	}

	/**
	 * @方法描述 : 生成加入弹幕分组池数据包
	 * @param roomId
	 *            所登录的房间号
	 * @param groupId
	 *            分组号，第三方平台建议选择-9999（即海量弹幕模式）
	 */
	public static byte[] getJoinGroupRequest(int roomId, int groupId) {
		MessageFormatUtil helper = new MessageFormatUtil();
		helper.addItem("type", "joingroup");
		helper.addItem("rid", roomId);
		helper.addItem("gid", groupId);
		return helper.getByte(helper.getResult());
	}

	/**
	 * @方法描述 : 生成心跳协议数据包
	 */
	public static byte[] getKeepAliveData() {
		MessageFormatUtil helper = new MessageFormatUtil();
		helper.addItem("type", "mrkl");
		return helper.getByte(helper.getResult());
	}

	/**
	 * @方法描述 : 生成登出消息数据包
	 */
	public static byte[] getLogoutRequestData() {
		MessageFormatUtil helper = new MessageFormatUtil();
		helper.addItem("type", "logout");
		return helper.getByte(helper.getResult());
	}
}
