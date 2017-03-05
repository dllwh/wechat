package com.cdeledu.util.application.SysProperty;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.cdeledu.common.property.PropertyHelperUtils;
import com.google.common.collect.Lists;

/**
 * @类描述: 提供些获取系统信息相关的工具方法
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年3月5日 下午3:36:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
@SuppressWarnings("restriction")
public class SystemHelper {
	/**
	 * JVM的版本
	 */
	public static final String JVM_VERSION = PropertyHelperUtils
			.getSyskey(SysProperty.JAVA_VERSION);

	/**
	 * 主机架构
	 */
	public static String OS_ARCH = PropertyHelperUtils.getSyskey(SysProperty.OS_ARCH);
	/**
	 * 主机类型:取得当前OS的名称
	 */
	public static String OS_NAME = PropertyHelperUtils.getSyskey(SysProperty.OS_NAME);
	/**
	 * 主机类型版本:当前OS的版本
	 */
	public static String OS_VERSION = PropertyHelperUtils.getSyskey(SysProperty.OS_VERSION);
	/**
	 * 操作系统类型
	 */
	public static String SUN_DESKTOP = PropertyHelperUtils.getSyskey(SysProperty.SUN_DESKTOP);
	/**
	 * 当前用户
	 */
	public static String CURRENT_USER = PropertyHelperUtils.getSyskey(SysProperty.USER_NAME);

	/**
	 * 当前用户的家目录
	 */
	public static String CURRENT_USER_HOME = PropertyHelperUtils.getSyskey(SysProperty.USER_HOME);
	private static OperatingSystemMXBean osmxb;
	static {
		try {
			osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		} catch (Exception e) {
			System.out.println("获取系统信息失败");
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述: 获取当前所有的系统属性的名称
	 */
	public static List<Object> showKeys() {
		Properties props = System.getProperties();
		Enumeration<?> enu = props.propertyNames();
		List<Object> resultList = Lists.newArrayList();
		while (enu.hasMoreElements()) {
			Object key = enu.nextElement();
			resultList.add(key);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取主机IP
	 * @return
	 */
	public static String getHostIP() {
		String hostIP = "";
		try {
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
				if (null != netint.getHardwareAddress()) {
					List<InterfaceAddress> list = netint.getInterfaceAddresses();
					for (InterfaceAddress interfaceAddress : list) {
						InetAddress ip = interfaceAddress.getAddress();
						if (ip instanceof Inet4Address) {
							hostIP += interfaceAddress.getAddress().toString();
						}
					}
				}
			}
			hostIP = hostIP.replaceAll("null", "");
		} catch (Exception e) {
			System.out.println("获取服务器IP出错");
		}
		return hostIP;
	}

	/**
	 * 获取JVM内存总量:系统总内存空间
	 *
	 */
	public final static long JVMtotalMem() {
		return Runtime.getRuntime().totalMemory() / 1024;
	}

	/**
	 * 虚拟机空闲内存量:系统内存的空闲空间
	 *
	 */
	public final static long JVMfreeMem() {
		return Runtime.getRuntime().freeMemory() / 1024;
	}

	/**
	 * 虚拟机使用最大内存量
	 *
	 */
	public final static long JVMmaxMem() {
		return Runtime.getRuntime().maxMemory() / 1024;
	}

	/**
	 * Sets HTTP proxy settings.
	 */
	public final static void setHttpProxy(String host, String port, String username,
			String password) {
		System.getProperties().put(SysProperty.HTTP_PROXY_HOST, host);
		System.getProperties().put(SysProperty.HTTP_PROXY_PORT, port);
		System.getProperties().put(SysProperty.HTTP_PROXY_USER, username);
		System.getProperties().put(SysProperty.HTTP_PROXY_PASSWORD, password);
	}

	/**
	 * Sets HTTP proxy settings.
	 */
	public final static void setHttpProxy(String host, String port) {
		System.getProperties().put(SysProperty.HTTP_PROXY_HOST, host);
		System.getProperties().put(SysProperty.HTTP_PROXY_PORT, port);
	}

	public final static long getTotalMemorySize() {
		return osmxb.getTotalPhysicalMemorySize() / 1024;
	}

	public final static long getFreeMemorySize() {
		return osmxb.getFreePhysicalMemorySize() / 1024;
	}

	/**
	 * 已使用的物理内存
	 */
	public final static long usedMemory() {
		return (getTotalMemorySize() - getFreeMemorySize());
	}

	/**
	 * @方法描述: 匹配OS名称。
	 * @param osNamePrefix
	 * @return
	 */
	private final static boolean getOSMatches(String osNamePrefix) {
		return OS_NAME.startsWith(osNamePrefix);
	}

	/**
	 * @方法描述: 匹配OS名称
	 * @param osNamePrefix
	 *            OS名称前缀
	 * @param osVersionPrefix
	 *            OS版本前缀
	 * @return 如果匹配，则返回<code>true</code>
	 */
	private final static boolean getOSMatches(String osNamePrefix, String osVersionPrefix) {
		return OS_NAME.startsWith(osNamePrefix) && OS_NAME.startsWith(osVersionPrefix);
	}

	/**
	 * 判断当前OS的类型
	 */
	public static final boolean isLinux() {
		return getOSMatches("Linux") || getOSMatches("LINUX");
	}

	public final static boolean isMac() {
		return getOSMatches("Mac") || getOSMatches("os");
	}

	public final static boolean isWindows() {
		return getOSMatches("Windows") || getOSMatches("windows");
	}

	public final static boolean isWindowsME() {
		return getOSMatches("Windows", "4.9");
	}

	public final static boolean isWindowsNT() {
		return getOSMatches("Windows NT");
	}

	public final static boolean isWindowsXP() {
		return getOSMatches("Windows", "5.1");
	}
}
