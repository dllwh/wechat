package com.cdeledu.util.openplatform.douyutv;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.util.openplatform.douyutv.message.MessageClientUtil;
import com.cdeledu.util.openplatform.douyutv.message.MessageHandler;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 斗鱼弹幕服务API
 * 
 *       <pre>
 * 基于(斗鱼弹幕服务器第三方接入协议v1.6.2)开发
 *       </pre>
 * 
 *       <pre>
 *  斗鱼弹幕通讯协议是一种分布式的文本信息系统。
 *  斗鱼弹幕通讯协议是一种基于 TCP 服务的应用层协议。
 *  斗鱼弹幕通讯协议规定了斗鱼服务与获取弹幕客户端之间交互弹幕信息的格式及其他规定。
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月1日 上午11:09:44
 * @版本: V1.2
 * @since: JDK 1.7
 */
public final class DouYuMsgClient {
	/** ----------------------------------------------------- Fields start */
	private final static Logger logger = LoggerFactory.getLogger(DouYuMsgClient.class);
	/** 第三方弹幕协议服务器地址 */
	private static final String host = "openbarrage.douyutv.com";
	/** 第三方弹幕协议服务器端口 */
	private static final int port = 8601;
	/** 房间ID(房间号) */
	private int roomId;
	/** 通讯socket对象 */
	private Socket socket;
	/** 异步读取消息线程 */
	private Thread dataSyncThread;
	// 获取弹幕线程及心跳线程运行和停止标记
	private boolean readyFlag = false;

	private MessageHandler messageHandler;
	// 弹幕池分组号，海量模式使用-9999
	private static final int groupId = -9999;

	/** ----------------------------------------------------- Fields end */
	private DouYuMsgClient(int roomId) {
		this.roomId = roomId;
		this.init();
	}

	/**
	 * @方法描述 : 初始化Client，连接弹幕服务器
	 */
	private void init() {
		logger.info("初始化斗鱼SDK_Client...");
		this.connect();
	}

	/**
	 * @方法描述 : 打开socket连接
	 */
	private void connect() {
		try {
			logger.info("从服务器({}:{})获取弹幕服务器数据", host, port);
			socket = new Socket(host, port);
			messageHandler = new MessageHandler(socket);
			logger.debug("Server Connect Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Server Connect failed!");
		}
	}

	/**
	 * @方法描述 : 登入斗鱼弹幕服务器,登录指定房间
	 */
	public void login() {
		logger.info("登录房间 {}", roomId);
		try {
			messageHandler.send(MessageClientUtil.getLoginRequestData(roomId));

			if (messageHandler.parseLoginRespond()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Receive login response successfully!");
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.error("Receive login response failed!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述 : 获取弹幕客户端就绪标记
	 * @return
	 */
	public boolean getReadyFlag() {
		return readyFlag;
	}

	/**
	 * @方法描述 : 加入房间分组并接收房间消息
	 */
	public void joinGroup() {
		logger.info("开启海量弹幕接收模式,加入房间({})并开始接收消息", roomId);
		try {
			messageHandler.send(MessageClientUtil.getJoinGroupRequest(roomId, groupId));
			// 设置客户端就绪标记为就绪状态
			readyFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Send join group request failed!");
		}
	}

	/**
	 * @方法描述 : 开始同步并接收房间消息
	 */
	public void sync() {
		// 加入海量弹幕分组，接收所有消息
		this.joinGroup();
		// 开启异步线程接收房间消息
		dataSyncThread = new Thread(new Runnable() {

			@Override
			public void run() {
				long start = System.currentTimeMillis();
				while (true) {
					// 判断是否退出线程
					if (readyFlag == false) {
						break;
					}

					// 获取消息类型
					messageHandler.read();
					// 发送心跳消息保持通道
					long end = System.currentTimeMillis();
					// 设置间隔45秒再发送心跳协议
					if (end - start > 45000) {
						// 发送心跳保持协议给服务器端
						doKeepLive();
					}
				}
				// messageHandler.close();
			}
		});
		dataSyncThread.start();
	}

	/**
	 * @方法描述 : 发送心跳消息，保持通道
	 */
	public void doKeepLive() {
		// logger.info("发送心跳信息，保持通道中...");
		messageHandler.send(MessageClientUtil.getKeepAliveData());
	}

	/**
	 * @方法描述 : 发送登出消息，用于退出
	 */
	public void logout() {
		logger.info("发送登出消息中...");
		byte[] logoutRequestData = MessageClientUtil.getLogoutRequestData();
		// 发送退出请求数据包给弹幕服务器
		messageHandler.send(logoutRequestData);
	}

	/**
	 * @方法描述 : 退出
	 */
	public void exit() {
		readyFlag = false;
		logger.info("斗鱼弹幕SDK客户端正在退出中...");
	}

	public static void main(String[] args) {
		// 初始化客户端
		DouYuMsgClient client = new DouYuMsgClient(134000);
		// 登录斗鱼服务器
		client.login();
		// 开始同步到读取消息
		client.sync();
	}
}
