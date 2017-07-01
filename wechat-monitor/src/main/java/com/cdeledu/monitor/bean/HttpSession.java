package com.cdeledu.monitor.bean;

import java.io.Serializable;
import java.util.List;

import com.cdeledu.monitor.listener.SessionInformations;

/**
 * @类描述: 获取Session请求信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:45:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HttpSession implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 当前在线人数 */
	private int onlineUserCount;
	/** 当前在线人数在线总时长 */
	private long getSessionAgeSum;
	/** Session详情 */
	private List<SessionInformations> sessionDetailList;

	public int getOnlineUserCount() {
		return onlineUserCount;
	}

	public void setOnlineUserCount(int onlineUserCount) {
		this.onlineUserCount = onlineUserCount;
	}

	public long getGetSessionAgeSum() {
		return getSessionAgeSum;
	}

	public void setGetSessionAgeSum(long getSessionAgeSum) {
		this.getSessionAgeSum = getSessionAgeSum;
	}

	public List<SessionInformations> getSessionDetailList() {
		return sessionDetailList;
	}

	public void setSessionDetailList(List<SessionInformations> sessionDetailList) {
		this.sessionDetailList = sessionDetailList;
	}
}
