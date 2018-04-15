package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播间的代码(链接)信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月13日 下午1:15:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveRoomCodeResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 直播间id */
	private String roomId;
	/** 客户端登录地址 */
	private String clientLoginUrl;
	/** 助教端登录地址 */
	private String assistantLoginUrl;
	/** 观看地址 */
	private String viewUrl;
	/** 推流地址，第三方推流用户可以获取到此参数 */
	private String publishUrl;
	/** 如果直播间为主持人模式，则返回主持人登录地址 */
	private String hostLoginUrl;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getClientLoginUrl() {
		return clientLoginUrl;
	}

	public void setClientLoginUrl(String clientLoginUrl) {
		this.clientLoginUrl = clientLoginUrl;
	}

	public String getAssistantLoginUrl() {
		return assistantLoginUrl;
	}

	public void setAssistantLoginUrl(String assistantLoginUrl) {
		this.assistantLoginUrl = assistantLoginUrl;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getPublishUrl() {
		return publishUrl;
	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}

	public String getHostLoginUrl() {
		return hostLoginUrl;
	}

	public void setHostLoginUrl(String hostLoginUrl) {
		this.hostLoginUrl = hostLoginUrl;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n RoomCodeResponse [roomId=" + roomId + ", clientLoginUrl="
				+ clientLoginUrl + ", assistantLoginUrl=" + assistantLoginUrl + ", viewUrl="
				+ viewUrl + ", publishUrl=" + publishUrl + ", hostLoginUrl=" + hostLoginUrl + "]";
	}

}
