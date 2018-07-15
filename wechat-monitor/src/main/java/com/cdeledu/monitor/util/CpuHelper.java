package com.cdeledu.monitor.util;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CPU
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年7月15日 下午9:12:37
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CpuHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法: CPU数量（单位：个）
	 * @return
	 * @throws SigarException
	 */
	public static int getCpuCount() throws SigarException {
		Sigar sigar = new Sigar();
		try {
			return sigar.getCpuInfoList().length;
		} finally {
			sigar.close();
		}
	}
}
