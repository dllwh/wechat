package com.cdeledu.model.cms;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮件模版表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月2日 下午8:28:52
 * @版本: V1.0
 * @since: JDK 1.7
 */
@TableName("sys_email_template")
public class SysEmailTemplate extends BaseEntity {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */
	/** 邮件名称 */
	private String senderName;
	/** 发送邮件帐号 */
	private String senderAccount;

	/** 标题模版 */
	private String title;
	/** 内容模板 */
	private String template;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
