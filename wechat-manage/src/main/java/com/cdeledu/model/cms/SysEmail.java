package com.cdeledu.model.cms;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮件表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月2日 下午8:24:36
 * @版本: V1.0
 * @since: JDK 1.7
 */
@TableName("sys_email")
public class SysEmail extends BaseEntity {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */
	/** 邮件名称 */
	private String emailName;
	/** 使用发送 */
	private String sender;
	/** 发送标题 */
	private String emailTitle;
	/** 发送内容 */
	private String emailContent;

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
}
