package com.cdeledu.model.system;

import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 下午2:36:18
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class ScheduleJobLog {

	private int id;
	private Long jobId;
	/** spring bean名称 */
	private String beanName;
	/** 方法名 */
	private String methodName;
	/** 参数 */
	private String params;
	/** 任务状态 0：成功 1：失败 */
	private Integer status;
	/** 失败信息 */
	private String error;
	/** 耗时(单位：毫秒) */
	private Integer times;
	/** 创建时间 */
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
