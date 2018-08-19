package com.cdeledu.util.openplatform.douyutv.message;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.util.openplatform.douyutv.constants.MsgType;
import com.cdeledu.util.openplatform.douyutv.factory.DouyuTCPDecoder;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 消息处理助手
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月1日 下午2:22:08
 * @版本: V1.1
 * @since: JDK 1.7
 */
public final class MessageHandler {
	/** ----------------------------------------------------- Fields start */
	private final static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	/** socket相关配置 */
	private Socket socket;
	/** 设置字节获取buffer的最大值 */
	private static final int MAX_BUFFER_LENGTH = 8192;

	/** ----------------------------------------------------- Fields end */

	public MessageHandler(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @方法描述 : 发送消息
	 * @param content
	 */
	public void messageSend(byte[] requestData) {
		try {
			if (socket != null && socket.isConnected()) {
				// 发送登陆请求数据包给弹幕服务器
				BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
				out.write(requestData, 0, requestData.length);
				out.flush();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("发送消息出现异常:", e);
			}
		}
	}

	/**
	 * @方法描述 : 获取服务器返回信息
	 */
	public String getServerMsg() {
		// 定义服务器返回信息的字符串
		String resultDate = "";

		try {
			// 初始化获取弹幕服务器返回信息包大小
			byte[] recvByte = new byte[MAX_BUFFER_LENGTH];
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			// 读取服务器返回信息，并获取返回信息的整体字节长度
			int recvLen = bis.read(recvByte, 0, recvByte.length);
			// 根据实际获取的字节数初始化返回信息内容长度
			resultDate = new String(Arrays.copyOfRange(recvByte, 0, recvLen), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDate;
	}

	/**
	 * @方法描述 : 读取消息,解析从服务器接受的协议，并根据需要订制业务需求
	 */
	public void messageReceived() {

		try {
			// 读取弹幕消息
			String result = getServerMsg();
			// 获取消息类型
			String msgType = DouyuTCPDecoder.getMsgType(result);

			if (StringUtils.isNotBlank(msgType)) {
				Map<String, Object> parseRespondMap = DouyuTCPDecoder.parseRespond(result);

				/*** @TODO 根据业务需求来处理获取到的所有弹幕及礼物信息 ***********/
				// 判断消息类型
				if (MsgType.CHAT_MSG.equals(msgType)) {// 弹幕消息
					logger.debug("弹幕消息===>" + parseRespondMap.toString());
				} else if (MsgType.DGB.equals(msgType)) {// 赠送礼物信息
					logger.debug("礼物消息===>" + parseRespondMap.toString());
				} else if (MsgType.UENTER.equals(msgType)) {
					logger.debug("用户进房通知消息===>" + parseRespondMap.toString());
				} else if (DouyuTCPDecoder.ifErrorRespond(result)) { // 服务器反馈错误信息
					// 结束心跳和获取弹幕线程
					logger.debug("服务器反馈错误信息===>" + parseRespondMap.toString());
				} else  {
					logger.debug("其他消息===>" + parseRespondMap.toString());
				}
				// @TODO 其他业务信息根据需要进行添加

				/*************************************************************/
			} else {
				logger.error("获取消息类型失败，消息:{}", result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述 : 关闭socket通道
	 */
	public void close() {
		if (socket != null && socket.isConnected()) {
			try {
				socket.close();
			} catch (IOException e) {
				if (logger.isDebugEnabled()) {
					logger.warn("socket通道关闭异常", e);
				}
			}
		}
	}

}
