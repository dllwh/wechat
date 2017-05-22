package com.cdeledu.model.system;

import java.io.Serializable;
import java.util.Date;

/**
 * @类描述: 操作日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年4月14日 上午11:55:10
 * @版本: V1.1
 * @since: JDK 1.7
 */
public class OperateLog implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 操作人
	 */
	private Integer userCode;
	/**
	 * 日志标题
	 */
	private String title;
	/**
	 * Mac地址
	 */
	private String macAddress;
	/**
	 * 操作用户的IP地址
	 */
	private String remoteAddr;
	/**
	 * 操作的URI
	 */
	private String requestUri;
	/**
	 * 操作的方式
	 */
	private String method;
	/**
	 * 操作提交的数据
	 */
	private String params;
	/**
	 * 操作用户代理信息
	 */
	private String userAgent;
	/**
	 * 异常信息
	 */
	private String exception;
	/**
	 * 记录时间
	 */
	private Date createTime;
	/**
	 * 开始日期
	 */
	private Date beginDate;
	/**
	 * 结束日期
	 */
	private Date endDate;

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
