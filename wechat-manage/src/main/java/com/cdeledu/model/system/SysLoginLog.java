package com.cdeledu.model.system;

import java.util.Date;

import com.cdeledu.common.base.BaseEntity;

/**
 * @类描述: 登录日志表
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年4月15日 上午11:55:37
 * @版本: V1.3
 * @since: JDK 1.7
 */
public class SysLoginLog extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 登录人
	private String userCode;
	// 登录时间
	private Date loginTime;
	// 登录的IP地址
	private String ipAddress;
	// 登录是否成功的标识位
	private Integer loginStatus;
	// 登录浏览器
	private String browser;
	// 日志级别
	private String logLeavel;
	// 日志类型
	private String opType;
	// 日志内容
	private String logContent;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public String getLogLeavel() {
		return logLeavel;
	}

	public void setLogLeavel(String logLeavel) {
		this.logLeavel = logLeavel;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
}
