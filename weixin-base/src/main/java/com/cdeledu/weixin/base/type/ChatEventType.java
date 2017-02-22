package com.cdeledu.weixin.base.type;

/**
 * @ClassName: ChatEventType
 * @Description: 会话事件
 * @author: 独泪了无痕
 * @date: 2015年10月17日 下午6:29:47
 * @version: V1.0
 */
public enum ChatEventType {
	/**
	 * 创建会话
	 */
	create_chat,
	/**
	 * 修改会话
	 */
	update_chat,
	/**
	 * 退出会话
	 */
	quit_chat,
	/**
	 * 订阅事件
	 */
	subscribe,
	/**
	 * 取消订阅事件
	 */
	unsubscribe;
}
