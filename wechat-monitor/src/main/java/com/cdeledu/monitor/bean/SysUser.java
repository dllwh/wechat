package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取系统用户信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月31日 下午4:19:37
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 当前系统进程表中的用户名 */
	private String user;
	/** 用户控制台 */
	private String device;
	/** 用户host */
	private String host;
	private long time = 0L;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SysUser [user=" + user + ", device=" + device + ", host=" + host + ", time=" + time
				+ "]";
	}

}
