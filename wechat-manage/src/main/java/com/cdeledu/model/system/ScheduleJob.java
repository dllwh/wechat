package com.cdeledu.model.system;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 下午2:33:55
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ScheduleJob {
	private Integer id;
	@NotBlank(message = "bean名称不能为空")
	private String beanName;
	@NotBlank(message = "方法名称不能为空")
	private String methodName;
	@NotBlank(message = "cron表达式不能为空")
	private String cronExpression;
	/** 任务状态 0：正常 1：暂停 */
	private Integer status;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
}
