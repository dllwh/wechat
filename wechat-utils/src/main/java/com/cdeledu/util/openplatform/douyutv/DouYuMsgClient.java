package com.cdeledu.util.openplatform.douyutv;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class DouYuMsgClient {
	/** ----------------------------------------------------- Fields start */
	private final static Logger logger = LoggerFactory.getLogger(DouYuMsgClient.class);
	/** API Host */
	private String host;
	/** API 端口 */
	private int port;
	/** 房间ID(房间号) */
	private String roomId;
	/** 通讯socket对象 */
	private Socket socket;
	/** 异步读取消息线程 */
	private Thread dataSyncThread;
	/** 退出标记 */
	private Boolean isExitMark = false;

	/** ----------------------------------------------------- Fields end */
	private DouYuMsgClient(String host, int port, String roomId) {
		super();
		this.host = host;
		this.port = port;
		this.roomId = roomId;
		this.init();
	}

	/**
	 * @方法描述 : 初始化Client
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述 : 登入斗鱼弹幕服务器
	 */
	public void login() {
		logger.info("登录房间 {}", roomId);
		String content = String.format("type@=loginreq/roomid@=%s/", roomId);
	}

	/**
	 * @方法描述 : 加入房间分组并接收房间消息
	 */
	public void joinGroup() {
		logger.info("开启海量弹幕接收模式,加入房间({})并开始接收消息", roomId);
		String content = String.format("type@=joingroup/rid@=%s/gid@=-9999/", roomId);
	}

	/**
	 * @方法描述 : 开始同步并接收房间消息
	 */
	public void sync() {
		// 加入海量弹幕分组，接收所有消息
		this.joinGroup();
	}

	/**
	 * @方法描述 : 发送心跳消息，保持通道
	 */
	public void doKeepLive() {
		logger.info("发送心跳信息，保持通道中...");
		String content = String.format("type@=mrkl/");
	}

	/**
	 * @方法描述 : 发送登出消息，用于退出
	 */
	public void logout() {
		String content = String.format("type@=logout/");
		logger.info("发送登出消息中...");
	}

	/**
	 * @方法描述 : 退出
	 */
	public void exit() {
		isExitMark = true;
		logger.info("斗鱼弹幕SDK客户端正在退出中...");
	}

	public static void main(String[] args) {
		// 初始化客户端
		DouYuMsgClient client = new DouYuMsgClient("openbarrage.douyutv.com", 8601, "288016");
		// 登录斗鱼服务器
		client.login();
		// 开始同步到读取消息
		client.sync();
	}
}
