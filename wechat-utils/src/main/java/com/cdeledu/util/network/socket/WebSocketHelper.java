package com.cdeledu.util.network.socket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.cdeledu.util.apache.lang.DateUtilHelper;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: WebSocketHelper
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月11日 下午3:59:26
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WebSocketHelper {
	/** ----------------------------------------------------- Fields start */
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<WebSocketHelper> webSocketSet = new CopyOnWriteArraySet<>();
	private Session session;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 有人进入房间
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
		System.out.println("有新用户加入!当前在线人数为:" + getOnlineCount());

	}

	/**
	 * @方法描述: 有人离开房间
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		subOnlineCount();
		System.out.println("有一用户关闭!当前在线人数为" + getOnlineCount());
	}

	/**
	 * @方法描述: 发消息
	 * @param message
	 * @throws Exception
	 */
	@OnMessage
	public void onMessage(String message) throws Exception {
		String date = "<font color='green'>" + DateUtilHelper.getCurrentTime() + "</font></br>";
		for (WebSocketHelper item : webSocketSet) {
			item.sendMessage(date + message);
		}
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取在线人数
	 */
	private static synchronized int getOnlineCount() {
		return WebSocketHelper.onlineCount;
	}

	/**
	 * @方法描述: 添加在线人数
	 */
	private static synchronized void addOnlineCount() {
		WebSocketHelper.onlineCount++;
	}

	/**
	 * @方法描述: 减少在线人数
	 */
	private static synchronized void subOnlineCount() {
		WebSocketHelper.onlineCount--;
	}

	/**
	 * @方法描述: 发送消息
	 * @param message
	 * @throws Exception
	 */
	private void sendMessage(String message) throws Exception {
		this.session.getBasicRemote().sendText(message);
	}
	/** ----------------------------------------------- [私有方法] */

}
