package com.cdeledu.util.application.email.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: Mail
 * @Description: 邮件类的基本信息
 *               <ul>
 *               <li>设置:账户名和密码、收件人、抄送(可选)、暗送(可选)、主题、内容，以及附件(可选)</li>
 *               <li>1.在创建了Mail对象之后</li></li>
 *               <li>调用它的setSubject()设置主题</li>
 *               <li>调用它的setContent()设置正文</li>
 *               <li>调用setFrom()设置发件人</li>
 *               <li>调用addToAddress()添加收件人</li>
 *               <li>调用addAttch()添加附件</li>
 *               <li>创建AttachBean:new AttachBean(new File("..."), "fileName")</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年7月17日 上午9:50:54
 * @version: V1.0
 */
public class EmailInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ---------------邮件发送服务端设置 start------------------ */
	// 发送邮件的服务器的IP
	private String mailServerHost;
	// 发送邮件的服务器的端口号
	private String mailServerPort;
	// 登陆邮件发送服务器的用户名
	private String userName;
	// 登陆邮件发送服务器的密码
	private String password;
	// 是否需要身份的验证:ture认证，false不认证信息
	private boolean validate = false;
	// 发件人(邮件发送者的地址)
	private String fromAddress;
	// 收件人(邮件接收者的地址)
	private StringBuilder toAddress = new StringBuilder();
	// 抄送
	private StringBuilder ccAddress = new StringBuilder();
	// 暗送
	private StringBuilder bccAddress = new StringBuilder();
	// 邮件主题
	private String subject;
	// 正文(邮件的内容,分为文本、超文本、html等形式)
	private String content;
	/** ---------------邮件发送服务端设置 stop------------------ */
	// 附件列表
	private List<AttachBean> attachList = new ArrayList<AttachBean>();

	// 接收邮件的时候附件保存的路径
	private String receivepath;
	// 接收时间
	private Date receivedate;
	// 是否读过，true读过，false没有阅读过
	private boolean read;
	// 是否有附件true有，false没有
	private boolean ExistsFile;
	// 是否有回执
	private boolean replysign;
	// 消息ID
	private String messageid;
	// 邮件数目，一个用户的总邮件数
	private int mailnumber;

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * 添加收件人,可以是多个收件人
	 * 
	 * @param to
	 */
	public void addToAddress(String to) {
		if (this.toAddress.length() > 0) {
			this.toAddress.append(",");
		}
		this.toAddress.append(to);
	}

	/**
	 * 添加抄送人，可以是多个抄送人
	 * 
	 * @param cc
	 */
	public void addCcAddress(String cc) {
		if (this.ccAddress.length() > 0) {
			this.ccAddress.append(",");
		}
		this.ccAddress.append(cc);
	}

	/**
	 * 添加暗送人，可以是多个暗送人
	 * 
	 * @param bcc
	 */
	public void addBccAddress(String bcc) {
		if (this.bccAddress.length() > 0) {
			this.bccAddress.append(",");
		}
		this.bccAddress.append(bcc);
	}

	/**
	 * 添加附件，可以添加多个附件
	 * 
	 * @param attachBean
	 */
	public void addAttach(AttachBean attachBean) {
		this.attachList.add(attachBean);
	}

	/**
	 * 获取所有附件
	 * 
	 * @return
	 */
	public List<AttachBean> getAttachs() {
		return this.attachList;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * 获取收件人
	 * 
	 * @return
	 */
	public String getToAddress() {
		return toAddress.toString();
	}

	public void setToAddress(StringBuilder toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * 获取抄送
	 * 
	 * @return
	 */
	public String getCcAddress() {
		return ccAddress.toString();
	}

	public void setCcAddress(StringBuilder ccAddress) {
		this.ccAddress = ccAddress;
	}

	/**
	 * 获取暗送
	 * 
	 * @return
	 */
	public String getBccAddress() {
		return bccAddress.toString();
	}

	public void setBccAddress(StringBuilder bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取主题内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置主题内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public List<AttachBean> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachBean> attachList) {
		this.attachList = attachList;
	}

	public String getReceivepath() {
		return receivepath;
	}

	public void setReceivepath(String receivepath) {
		this.receivepath = receivepath;
	}

	public Date getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isExistsFile() {
		return ExistsFile;
	}

	public void setExistsFile(boolean existsFile) {
		ExistsFile = existsFile;
	}

	public boolean isReplysign() {
		return replysign;
	}

	public void setReplysign(boolean replysign) {
		this.replysign = replysign;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public int getMailnumber() {
		return mailnumber;
	}

	public void setMailnumber(int mailnumber) {
		this.mailnumber = mailnumber;
	}
}
