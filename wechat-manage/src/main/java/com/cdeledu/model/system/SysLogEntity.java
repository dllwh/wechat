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
	/** IP地址 */
	private String ipAddress;
	/** 日志描述类型(操作代码) */
	private String opType;
	/** 响应时间 */
	private Long time;
	/** 请求方法 */
	private String method;
	/** 浏览器信息 */
	private String broswer;
	/** 请求参数 */
	private String params;
	/** 日志操作表 */
	private String tableName;
	/** 备注信息，一些其他的需要说明的信息 */
	private String remark;
	/** 异常码 */
	private String exceptionCode;
	/** 异常原因 */
	private String exceptionDetail;
	/** 操作日志:0:正常操作日志,默认值;1:异常日志 */
	private Integer logType = 0;
	/** 操作结果 */
	private String opResult;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
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

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getOpResult() {
		return opResult;
	}

	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysLogEntity [id=" + id + ", userCode=" + userCode + ", ipAddress=" + ipAddress
				+ ", opType=" + opType + ", time=" + time + ", method=" + method + ", broswer="
				+ broswer + ", params=" + params + ", tableName=" + tableName + ", remark=" + remark
				+ ", exceptionCode=" + exceptionCode + ", exceptionDetail=" + exceptionDetail
				+ ", logType=" + logType + ", opResult=" + opResult + ", createTime=" + createTime
				+ "]";
	}

}
