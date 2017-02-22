package com.cdeledu.common.model;

import java.io.Serializable;

/**
 * @类描述: 程序版本控制表
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月24日 上午11:52:54
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Version implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	private String versionName;// 版本名称
	private String versionCode;// 版本编码
	private String loginPage;// 登陆入口页面
	private String versionNum;// 版本号
	/** ----------------------------------------------------- Fields end */
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
}
