/**
 * 
 */
package com.cdeledu.common.property;

import java.io.IOException;
import java.util.Scanner;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年8月19日 下午6:33:30
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class ServerUtilsHelper {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */

	public static String getCpuId() {
		String serial = "";
		Scanner sc = null;
		Process process = null;
		try {
			process = Runtime.getRuntime()
					.exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
			process.getOutputStream().close();
			sc = new Scanner(process.getInputStream());
			sc.next();
			serial = sc.next();
		} catch (IOException e) {
		}
		return serial;
	}
}
