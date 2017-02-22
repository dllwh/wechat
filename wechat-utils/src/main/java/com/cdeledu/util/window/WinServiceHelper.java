package com.cdeledu.util.window;

/**
 * @类描述: Window服务辅助类WinServiceHelper
 * 
 *       <pre>
 * 本辅助类主要是用来方便实现Window服务的各种操作，包括安装、卸载、启动、停止、重新启动、判断服务是否存在等操作。
 * Window服务适用于个各种定时服务，或者数据同步的操作中。
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月11日 下午4:18:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WinServiceHelper {
	/**
	 * @方法描述: 安装Windows服务
	 * @param serviceName
	 *            服务名称
	 * @param serviceFileName
	 *            服务文件路径
	 * @return
	 */
	public boolean installService(String serviceName, String serviceFileName) {
		return false;
	}

	/**
	 * @方法描述: 卸载Windows服务
	 * @param serviceName
	 *            服务名称
	 * @param serviceFileName
	 *            服务文件路径
	 * @return
	 */
	public boolean unInstallService(String serviceName, String serviceFileName) {
		return false;
	}

	/**
	 * @方法描述: 判断window服务是否存在
	 * @param serviceName
	 *            window服务名称
	 * @return 存在返回 true,否则返回 false;
	 */
	public boolean serviceIsExisted(String serviceName) {
		return false;
	}

	/**
	 * @方法描述: 启动window服务
	 * @param serviceName
	 *            window服务名称
	 * @return
	 */
	public boolean startService(String serviceName) {
		return false;
	}

	/**
	 * 
	 * @方法描述: 停止Windows服务
	 * @param serviceName
	 *            程序的服务名
	 * @return 停止成功返回 true,否则返回 false;
	 */
	public boolean stopService(String serviceName) {
		if (serviceIsExisted(serviceName)) { // 判断服务是否存在

		}
		return false;
	}

	/**
	 * @方法描述: 重启Windows服务
	 * @param serviceName
	 *            程序的服务名
	 * @return 重启成功返回 true,否则返回 false;
	 */
	public boolean refreshService(String serviceName) {
		if (serviceIsExisted(serviceName)) { // 判断服务是否存在
			if (serviceIsRunning(serviceName)) { // 判断服务是否启动

			} else { // 若服务没有启动，则启动服务
				startService(serviceName);
			}
		}
		return false;
	}

	/**
	 * @方法描述: 验证服务是否启动
	 * @param serviceName
	 *            window服务名称
	 * @return
	 */
	public boolean serviceIsRunning(String serviceName) {
		boolean isRun = false;
		if (serviceIsExisted(serviceName)) { // 判断服务是否存在

		}
		return isRun;
	}

	/**
	 * @方法描述: 获取服务启动类型 2为自动 3为手动 4 为禁用
	 * @param serviceName
	 * @return
	 */
	public Integer getServiceStartType(String serviceName) {
		String startType = "";
		Integer startTypeStatus = 0;
		if (serviceIsExisted(serviceName)) { // 判断服务是否存在
			if (startType == "2") { // "自动"
				startTypeStatus = 2;
			} else if (startType == "3") { // "手动"
				startTypeStatus = 3;
			} else if (startType == "4") { // "禁用"
				startTypeStatus = 4;
			}
		}
		return startTypeStatus;
	}

	/**
	 * @方法描述: 修改服务的启动项 2为自动,3为手动
	 * @param startType
	 * @param serviceName
	 * @return
	 */
	public boolean changeServiceStartType(int startType, String serviceName) {
		if (serviceIsExisted(serviceName)) { // 判断服务是否存在

		}
		return false;
	}
}
