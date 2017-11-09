package com.cdeledu.model.system;

import java.io.Serializable;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 下午3:36:09
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysLogEntity implements Serializable {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */
	/** */
	private int id;
	/** 操作人的信息 */
	private int userCode;
	/** 操作人的信息 */
	private String userName;
	/** IP地址 */
	private String ipAddress;
	/** Mac地址 */
	private String macAddress;
	/** 日志描述类型(操作代码) */
	private String opType;
	/** 响应时间 */
	private int time;
	/** 请求方法 */
	private String method;
	/** 浏览器信息 */
	private String broswer;
	/** 浏览器 */
	private String broswerType;
	/** 请求参数 */
	private String params;
	/** 日志操作表 */
	private String tableName;
	/** 备注信息，一些其他的需要说明的信息 */
	private String remark;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBroswer() {
		return broswer;
	}

	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}

	public String getBroswerType() {
		return broswerType;
	}

	public void setBroswerType(String broswerType) {
		this.broswerType = broswerType;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysLogEntity [id=" + id + ", userCode=" + userCode + ", userName=" + userName
				+ ", ipAddress=" + ipAddress + ", macAddress=" + macAddress + ", opType=" + opType
				+ ", time=" + time + ", method=" + method + ", broswer=" + broswer
				+ ", broswerType=" + broswerType + ", params=" + params + ", tableName=" + tableName
				+ ", remark=" + remark + ", createTime=" + createTime + "]";
	}

}
