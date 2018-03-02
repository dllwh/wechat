package com.cdeledu.model.cms;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮件配置表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月2日 下午8:24:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
@TableName("sys_email_config")
public class SysEmailConfig extends BaseEntity {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */
	/** SMTP服务器  */
	private String smtpHost;
	/** SMTP服务器端口 */
	private String smtpPort;
	/** 发送方式  */
	private String sendMethod;
	/** 名称 */
	private String senderName;
	/**  发邮件邮箱账号 */
	private String senderAccount;
	/** 发邮件邮箱密码 */
	private String senderPassword;
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSendMethod() {
		return sendMethod;
	}
	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}
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
	public String getSenderPassword() {
		return senderPassword;
	}
	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}
}
