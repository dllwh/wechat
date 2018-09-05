package com.cdeledu.controller.ecms;

import org.springframework.web.bind.annotation.RequestParam;

import com.cdeledu.common.base.ResponseBean;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: API通过API密钥使用。每个用户的API密钥都不一样，并且要作为API请求的一个参数
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月22日 上午9:15:23
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class OpManager {
	/**
	 * @方法描述 : TODO(这里用一句话描述这个方法的作用)
	 * @param apiKey
	 *            访问OpManager服务器的API密钥。
	 * @param serviceName
	 *            要添加的服务名称
	 * @param portNumber
	 *            服务的端口号
	 * @param timeOut
	 *            超时值，以秒为单位。默认值为0。
	 * @param failureThreshold
	 *            生成服务停止告警的连续轮询次数。默认值为1
	 * @param autoDiscovery
	 *            在发现的时候检查并添加该服务。false - 发现的时候不检查。
	 * @return
	 */
	public ResponseBean addServiceMonitor(String apiKey, String serviceName, String portNumber,
			@RequestParam(defaultValue = "0") long timeOut,
			@RequestParam(defaultValue = "1") int failureThreshold,
			@RequestParam(defaultValue = "true") boolean autoDiscovery) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	/**
	 * @方法描述 : 删除服务监视器
	 * @param apiKey
	 *            -访问OpManager服务器的API密钥
	 * @param serviceName
	 * @return
	 */
	public ResponseBean deleteServiceMonitor(String apiKey, String serviceName) {
		ResponseBean responseBean  = new ResponseBean();
		return responseBean ;
	}

	/**
	 * @方法描述 : 删除Windows服务监视器
	 * @param apiKey
	 *            访问OpManager服务器的API密钥
	 * @param serviceDisplayName
	 *            要删除服务的显示名称
	 * @return
	 */
	public ResponseBean deleteWindowsServiceMonitor(String apiKey, String serviceDisplayName) {
		ResponseBean responseBean  = new ResponseBean();
		return responseBean ;
	}

	/**
	 * @方法描述 : 更新服务监视器信息
	 * @param apiKey
	 * @param serviceDisplayName
	 * @return
	 */
	public ResponseBean updateServiceMonitor(String apiKey, String serviceDisplayName) {
		ResponseBean responseBean  = new ResponseBean();
		return responseBean ;
	}

	/**
	 * @方法描述 : 获取Windows服务监视器信息
	 * @param apiKey
	 *            访问OpManager服务器的API密钥。
	 * @param serviceDisplayName
	 *            服务的显示名称
	 */
	public void getWindowsServiceMonitor(String apiKey, String serviceDisplayName) {
	}

	/**
	 * @方法描述 : 更新Windows服务监视器信息
	 * @param apiKey
	 *            访问OpManager服务器的API密钥
	 * @param serviceDisplayName
	 *            服务的显示名称
	 * @param failureThreshold
	 *            要更新的重试次数
	 * @param actionType
	 *            要更新的动作类型。值可以是：0/1/2, 0 - 无动作；1 - 重启服务；2 - 重启服务器
	 */
	public void updateWindowsServiceMonitor(String apiKey, String serviceDisplayName,
			String failureThreshold, @RequestParam(defaultValue = "0") int actionType) {
	}

	/**
	 * @方法描述 : 获取凭证信息
	 * @param apiKey
	 *            访问OpManager服务器的API密钥
	 * @param credentialName
	 *            凭证名称。
	 * @param type
	 *            设备类型，例如：Windows、Linux、SNMP v1/v2、SNMP v3或Vmware
	 */
	public void getCredentialDetails(String apiKey, String credentialName, boolean type) {
	}

	/**
	 * @方法描述 : 删除服务监视器
	 * @param apiKey
	 *            访问OpManager服务器的API密钥。
	 * @param credentialName
	 *            凭证名称
	 */
	public void deleteCredential(String apiKey, String credentialName) {
	}

	/**
	 * @方法描述 : 获取服务监视器信息
	 * @param apiKey
	 * @param credentialName
	 */
	public void getServiceDetails(String apiKey, String serviceName) {
	}

	/**
	 * @方法描述 : 获取系统信息
	 * @param apiKey
	 *            访问OpManager服务器的API密钥
	 */
	public void getSystemInformation(String apiKey) {
	}
}
