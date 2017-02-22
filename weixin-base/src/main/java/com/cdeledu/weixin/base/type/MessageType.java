package com.cdeledu.weixin.base.type;

/**
 * @ClassName: MessageType
 * @Description: 消息类型
 * @author: 独泪了无痕
 * @date: 2015年10月17日 下午2:17:42
 * @version: V1.0
 * @history:
 */
public enum MessageType {
	/**
	 * 消息类型:空回复(文字消息 )
	 */
	blank,
	/**
	 * 消息类型：文字消息(文本消息)
	 * 
	 * @see TextMessage
	 */
	text,
	/**
	 * 消息类型：图片消息
	 * 
	 * @see ImageMessage
	 */
	image,
	/**
	 * 消息类型：图文消息
	 * 
	 * @see NewsRespose
	 */
	news,
	/**
	 * 消息类型：语音消息
	 * 
	 * @see VoiceMessage
	 */
	voice,
	/**
	 * 消息类型：视频消息
	 * 
	 * @see VideoMessage
	 */
	video,
	/**
	 * 消息类型：小视频消息
	 * 
	 * @see VideoMessage
	 */
	shortvideo,
	/**
	 * 消息类型：位置消息
	 * 
	 * @see LocationMessage
	 */
	location,
	/**
	 * 消息类型：链接消息
	 * 
	 * @see LinkMessage
	 */
	link,
	/**
	 * 消息类型：事件(推送)消息
	 * 
	 * @see EventMessage
	 */
	event,
	/**
	 * 消息类型：音乐消息
	 * 
	 * @see MusicMessage
	 */
	music;
}
