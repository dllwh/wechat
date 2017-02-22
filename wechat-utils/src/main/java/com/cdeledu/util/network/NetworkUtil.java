package com.cdeledu.util.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import com.cdeledu.common.exception.RuntimeExceptionHelper;

/**
 * @类描述: 网络相关操作辅助类
 * 
 *       <pre>
 *本辅助类主要是用来方便实现网络相关操作，可以对IP或者域名进行相互解析，基于Socket的TCP/UDP相关操作，检测本机是否联网等相关的网络操作。
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月9日 下午8:21:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class NetworkUtil {
	/** ----------------------------------------------------- Fields start */
	public final static String LOCAL_IP = "127.0.0.1";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 获取本地机器IP地址
	 * @return
	 * @throws UnknownHostException
	 */
	public final static String getLocalIP() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	/**
	 * @方法描述: 通过域名获取IP地址
	 * @return
	 * @throws UnknownHostException
	 */
	public final static String getLocalIP(String hostName) throws UnknownHostException {
		return InetAddress.getByName(hostName).getHostAddress();
	}

	/**
	 * @方法:获得本机的IP地址列表
	 * @创建人:独泪了无痕
	 * @return
	 */
	public final static Set<String> localIpv4s() {
		Enumeration<NetworkInterface> networkInterfaces = null;
		try {
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			throw new RuntimeExceptionHelper(e.getMessage(), e);
		}

		if (networkInterfaces == null) {
			throw new RuntimeExceptionHelper("Get network interface error!");
		}

		final HashSet<String> ipSet = new HashSet<String>();

		while (networkInterfaces.hasMoreElements()) {
			final NetworkInterface networkInterface = networkInterfaces.nextElement();
			final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			while (inetAddresses.hasMoreElements()) {
				final InetAddress inetAddress = inetAddresses.nextElement();
				if (inetAddress != null && inetAddress instanceof Inet4Address) {
					ipSet.add(inetAddress.getHostAddress());
				}
			}
		}

		return ipSet;
	}

	/**
	 * @方法描述: 检查设置的IP地址是否正确，并返回正确的IP地址,无效IP地址返回"-1"
	 * @param ip
	 *            设置的IP地址
	 * @return 非法IP 则返回 -1
	 */
	public final String getValidIP(String ip) {
		return null;
	}

	/**
	 * @方法描述: 检查设置的端口号是否正确
	 * @param port
	 *            设置的端口号
	 * @return
	 */
	public final boolean isValidPort(int port) {
		// 有效端口是0～65535
		return port >= 0 && port <= 0xFFFF;
	}

	/**
	 * @方法描述: 获取本机的计算机名
	 * @return
	 * @throws UnknownHostException
	 */
	public final String getLocalHostName() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

	/**
	 * @方法描述: 获取本机的局域网IP
	 * @return
	 */
	public final String getLANIP() {
		return null;
	}

	/**
	 * @方法描述: 获取本机在Internet网络的广域网IP
	 * @return
	 */
	public final String getWANIP() {
		return null;
	}

	/**
	 * @方法描述: 判断网络是否连接
	 * @return
	 */
	public final boolean isConnected() {
		return false;
	}

	/**
	 * @方法描述: 判断是否是wifi连接
	 * @return
	 */
	public final boolean isWifi() {
		return false;
	}

	/**
	 * @方法描述: 获取远程客户机的IP地址
	 * @param clientSocket
	 *            客户端的socket对象
	 * @return
	 */
	public final String getClientIP(Socket clientSocket) {
		return null;
	}

	/**
	 * @方法描述: 创建一个基于TCP协议的Socket对象
	 * @return
	 */
	public final Socket createTcpSocket() {
		return null;
	}

	/**
	 * @方法描述: 创建一个基于UDP协议的Socket对象
	 * @return
	 */
	public final Socket createUdpSocket() {
		return null;
	}

	/**
	 * @方法描述: 指定Socket对象执行监听，默认允许的最大挂起连接数为100
	 * @param socket
	 *            执行监听的Socket对象
	 * @param port
	 *            监听的端口号
	 */
	public final void startListen(Socket socket, int port) {
	}

	/**
	 * @方法描述: 指定Socket对象执行监听
	 * @param socket
	 *            执行监听的Socket对象
	 * @param port
	 *            监听的端口号
	 * @param maxConnection
	 *            允许的最大挂起连接数
	 */
	public final void startListen(Socket socket, int port, int maxConnection) {
	}

	/**
	 * @方法描述: 指定Socket对象执行监听
	 * @param socket
	 *            执行监听的Socket对象
	 * @param ip
	 *            监听的IP地址
	 * @param port
	 *            监听的端口号
	 * @param maxConnection
	 *            允许的最大挂起连接数
	 */
	public final void startListen(Socket socket, String ip, int port, int maxConnection) {
	}

	/**
	 * @方法描述: 连接到基于TCP协议的服务器,连接成功返回true，否则返回false
	 * @param socket
	 *            Socket对象
	 * @param ip
	 *            服务器IP地址
	 * @param port
	 *            服务器端口号
	 * @return
	 */
	public final boolean connect(Socket socket, String ip, int port) {
		return false;
	}

	/**
	 * @方法描述: 以同步方式向指定的Socket对象发送消息
	 * @param socket
	 *            socket对象
	 * @param msg
	 *            发送的消息
	 */
	public final void sendMsg(Socket socket, byte[] msg) {
	}

	/**
	 * @方法描述: 使用UTF8编码格式以同步方式向指定的Socket对象发送消息
	 * @param socket
	 * @param msg
	 */
	public final void sendMsg(Socket socket, String msg) {
	}

	/**
	 * @方法描述: 以同步方式接收消息
	 * @param socket
	 *            socket对象
	 * @param buffer
	 *            接收消息的缓冲区
	 */
	public final void receiveMsg(Socket socket, byte[] buffer) {

	}

	/**
	 * @方法描述: 以同步方式接收消息，并转换为UTF8编码格式的字符串,使用5000字节的默认缓冲区接收
	 * @param socket
	 *            socket对象
	 * @return
	 */
	public final String receiveMsg(Socket socket) {
		return null;
	}

	/**
	 * @方法描述: 关闭基于Tcp协议的Socket对象
	 * @param socket
	 *            要关闭的Socket对象
	 */
	public final void close(Socket socket) {

	}

	/**
	 * @方法描述: 检测本机是否联网
	 * @return
	 */
	public final boolean isConnectedInternet() {
		return false;
	}

	/**
	 * @方法描述: 转换主机域名DNS到IP地址
	 * @param hostname
	 *            主机域名DNS
	 * @return
	 */
	public final String convertDnsToIp(String hostName) {
		return null;
	}

	/**
	 * @方法描述: 转换主机IP地址到DNS域名
	 * @param ipAddress
	 *            主机IP地址
	 * @return
	 */
	public final String convertIpToDns(String ipAddress) {
		return null;
	}

	/**
	 * @方法描述: 根据主机IP获取主机名称
	 * @param hostIP
	 *            主机IP
	 * @return
	 */
	public final String getHostName(String hostIP) {
		return null;
	}

	/**
	 * @方法描述: 测试方法
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		// System.out.println("获取本机IP地址:"+getLocalIP());
	}
}
