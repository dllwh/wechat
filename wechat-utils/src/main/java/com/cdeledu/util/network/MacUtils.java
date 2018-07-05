package com.cdeledu.util.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;

import org.apache.commons.io.IOUtils;

/**
 * @类描述: MAC地址工具
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月25日 下午5:49:12
 * @版本: V2.0
 * @since: JDK 1.7
 */
public final class MacUtils {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 获取Unix网卡的mac地址
	 */
	public static String getWindowsMACAddress() {
		String mac = "";
		BufferedReader bufferedReader = null;
		try {
			// 首先获取想要查看的ip地址，这个地址唯一对应一个网卡信息
			InetAddress ip = InetAddress.getLocalHost();
			// 根据ip地址获得对应的网卡信息
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);
			// 获取网卡的mac地址字节数组，这个字节数组的长度是6，读者可以自行断点查看
			byte[] macAddr = networkInterface.getHardwareAddress();
			// 将字节数组转成16进制表示
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < macAddr.length; i++) {
				// 将每个字节的值转为16进制数
				String byteToHex = Integer.toHexString(macAddr[i] & 0xff);
				sb.append(byteToHex);
				// 使用-来区分每个字节的16进制数表示
				if (i != macAddr.length - 1) {
					sb.append("-");
				}
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bufferedReader);
		}
		return mac;
	}

}