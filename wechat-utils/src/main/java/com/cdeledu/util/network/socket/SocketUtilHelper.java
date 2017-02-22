package com.cdeledu.util.network.socket;

import com.cdeledu.util.application.regex.RegexUtil;

/**
 * @描述: 套接字相关工具类
 * @author: 独泪了无痕
 * @date: 2015年9月10日 下午4:54:54
 * @version: V1.0
 * @history:
 */
public class SocketUtilHelper {
	/** -------------------------- 私有属性 begin ------------------------------- */
	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * 根据long值获取ip v4地址
	 * 
	 * @param longIP
	 *            IP的long表示形式
	 * @return IP V4 地址
	 */
	public static String longToIpv4(long longIP) {
		StringBuffer sb = new StringBuffer();
		// 直接右移24位
		sb.append(String.valueOf(longIP >>> 24));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIP & 0x000000FF));
		return sb.toString();
	}

	/**
	 * 根据ip地址计算出long型的数据
	 * 
	 * @param strIP
	 *            IP V4 地址
	 * @return long值
	 */
	public static long ipv4ToLong(String strIP) {
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
	/** -------------------------- 公有方法 end ------------------------------- */
}
