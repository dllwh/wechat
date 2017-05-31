package com.cdeledu.util.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cdeledu.common.property.SystemHelper;

/**
 * @类描述: MAC地址工具
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月25日 下午5:49:12
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class MacUtils {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 获取Unix网卡的mac地址
	 */
	private static String getUnixMACAddress() {
		String mac = "";
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			/**
			 * Unix下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
			 */
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				/**
				 * 寻找标示字符串[hwaddr]
				 */
				index = line.toLowerCase().indexOf("hwaddr");
				/**
				 * 找到了
				 */
				if (index != -1) {
					/**
					 * 取出mac地址并去除2边空格
					 */
					mac = line.substring(index + "hwaddr".length() + 1).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}

	/**
	 * @方法描述: 获取Linux网卡的mac地址.
	 */
	private static String getLinuxMACAddress() {
		String mac = "";
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			/**
			 * linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
			 */
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("硬件地址");
				/**
				 * 找到了
				 */
				if (index != -1) {
					/**
					 * 取出mac地址并去除2边空格
					 */
					mac = line.substring(index + 4).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		// 取不到，试下Unix取发
		if (mac == null) {
			return getUnixMACAddress();
		}
		return mac;
	}

	/**
	 * @方法描述: 获取widnows网卡的mac地址
	 */
	private static String getWindowsMACAddress() {
		String mac = "";
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			/**
			 * windows下的命令，显示信息中包含有mac地址信息
			 */
			process = Runtime.getRuntime().exec("ipconfig /all");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				/**
				 * 寻找标示字符串[physical address]
				 */
				// index = line.toLowerCase().indexOf("physical address");
				// if (index != -1) {
				if (line.split("-").length == 6) {
					index = line.indexOf(":");
					if (index != -1) {
						/**
						 * 取出mac地址并去除2边空格
						 */
						mac = line.substring(index + 1).trim();
					}
					break;
				}
				index = line.toLowerCase().indexOf("物理地址");
				if (index != -1) {
					index = line.indexOf(":");
					if (index != -1) {
						/**
						 * 取出mac地址并去除2边空格
						 */
						mac = line.substring(index + 1).trim();
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取网卡的mac地址.
	 */
	public static String getMac() {
		// 获取当前操作系统名称. return 操作系统名称 例如:windows,Linux,Unix等.
		String osName = SystemHelper.OS_NAME.toLowerCase();
		String mac = "";
		if (osName.startsWith("windows")) {
			mac = getWindowsMACAddress();
		} else if (osName.startsWith("linux")) {
			mac = getLinuxMACAddress();
		} else {
			mac = getUnixMACAddress();
		}
		return mac;
	}

	public static void main(String[] args) {
		System.out.println(getMac());
	}

}
