package com.cdeledu.model.system;

import java.util.Date;

import com.cdeledu.common.base.BaseEntity;

/**
 * @类描述: 登录日志表
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年4月15日 上午11:55:37
 * @版本: V1.2
 * @since: JDK 1.7
 * @see <a href=""></a>
 */
public class LoginLog extends BaseEntity<LoginLog> {
	private static final long serialVersionUID = 1L;
	// 登录人
	private Integer userCode;
	// 登录时间
	private Date loginTime;
	// 登录的IP地址
	private String ipAddress;
	// 登录是否成功的标识位
	private Integer loginStatus;
	// 登录浏览器
	private Integer brower;
	// 日志级别
	private Integer logLeavel;
	// 日志类型
	private Integer opType;
	// 日志内容
	private String logContent;
	// 记录时间
	private String createTime_start;
	private String createTime_end;

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
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

	public Integer getBrower() {
		return brower;
	}

	public void setBrower(Integer brower) {
		this.brower = brower;
	}

	public Integer getLogLeavel() {
		return logLeavel;
	}

	public void setLogLeavel(Integer logLeavel) {
		this.logLeavel = logLeavel;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getCreateTime_start() {
		return createTime_start;
	}

	public void setCreateTime_start(String createTime_start) {
		this.createTime_start = createTime_start;
	}

	public String getCreateTime_end() {
		return createTime_end;
	}

	public void setCreateTime_end(String createTime_end) {
		this.createTime_end = createTime_end;
	}
}
