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
import com.cdeledu.util.application.regex.RegexUtil;

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
	/**
	 * @方法描述: 根据ip地址计算出long型的数据
	 * @param strIP
	 *            IP V4 地址
	 * @return
	 */
	private static long ipv4ToLong(String strIP) {
		if (RegexUtil.isIpv4(strIP)) {
			long[] ip = new long[4];
			// 先找到IP地址字符串中.的位置
			int position1 = strIP.indexOf(".");
			int position2 = strIP.indexOf(".", position1 + 1);
			int position3 = strIP.indexOf(".", position2 + 1);
			// 将每个.之间的字符串转换成整型
			ip[0] = Long.parseLong(strIP.substring(0, position1));
			ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
			ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
			ip[3] = Long.parseLong(strIP.substring(position3 + 1));
			return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
		}
		return 0;
	}

	/**
	 * @方法描述: 指定IP的long是否在指定范围内
	 * @param userIp  用户IP
	 * @param begin 开始IP
	 * @param end 结束IP
	 * @return 是否在范围内
	 */
	private static boolean isInner(long userIp, long begin, long end){
		return (userIp >= begin) && (userIp <= end);
	}
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
	 * @方法描述:
	 * 
	 *        <pre>
	 *        判定是否为内网IP
	 *        私有IP： A类 10.0.0.0-10.255.255.255
	 *               B类 172.16.0.0-172.31.255.255
	 *               C类 192.168.0.0-192.168.255.255
	 *               当然，还有127这个网段是环回地址
	 *        </pre>
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static boolean isInnerIP(String ipAddress) {
		boolean isInnerIp = false;
		long ipNum = ipv4ToLong(ipAddress);
		
		long aBegin = ipv4ToLong("10.0.0.0");
		long aEnd = ipv4ToLong("10.255.255.255");

		long bBegin = ipv4ToLong("172.16.0.0");
		long bEnd = ipv4ToLong("172.31.255.255");

		long cBegin = ipv4ToLong("192.168.0.0");
		long cEnd = ipv4ToLong("192.168.255.255");
		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd)||isInner(ipNum, cBegin, cEnd) || "127.0.0.1".equalsIgnoreCase(ipAddress);
		return isInnerIp;
	}

	/**
	 * @方法描述: 检查设置的IP地址是否正确，并返回正确的IP地址,无效IP地址返回"-1"
	 * @param ip
	 *            设置的IP地址
	 * @return 非法IP 则返回 -1
	 */
	public final boolean getValidIP(String ip) {
		return false;
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
