package com.cdeledu.util.application.email;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:JavaMail API 邮件
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年9月18日 下午6:21:09
 * @版本: V0.0.2
 * @since: JDK 1.7
 */
public final class JavaMailHelper {
	/** ----------------------------------------------------- Fields start */
	/** 发送邮件的props文件 */
	private transient Properties props = System.getProperties();
	private final String CHARSET = ConstantHelper.UTF_8.name();
	/** 是否开始调试模式 */
	private static boolean debug = false;
	/** 服务器是否要验证用户的身份信息 */
	private static boolean auth = false;
	/** 基本邮件会话(session)，是Java Mail API最高层入口类 */
	private static Session sendMailSession;

	/** ----------------------------------------------------- Fields end */
	/** 邮件服务器的用户名 */
	private String serverHost;
	/** 邮件服务器的用户名 */
	private String serverUserName;
	/** 登陆邮件服务器的密码 */
	private String password;
	/** 邮箱所使用的协议 */
	private String protocol;

	JavaMailHelper() {

	}

	public JavaMailHelper(String host, String userName, String password, String protocol) {
		this.serverHost = host;
		this.serverUserName = userName;
		this.password = password;
		this.protocol = protocol;
		init();
	}

	public static JavaMailHelper getInstance(String host, String userName) {
		auth = false;
		return new JavaMailHelper(host, userName, null, null);
	}

	public static JavaMailHelper getInstance(String host, String userName, String password) {
		auth = true;
		return new JavaMailHelper(host, userName, password, null);
	}

	public static JavaMailHelper getInstance(String host, String userName, String password,
			String protocol) {
		auth = true;
		return new JavaMailHelper(host, userName, password, protocol);
	}

	/************************************************************************/
	/******************* 邮件服务器配置 **************************************/
	/************************************************************************/
	/**
	 * @方法描述 : 初始化邮件发送服务器
	 * @param serverHost
	 */
	private void init() {
		// 指定邮件的发送服务器地址
		props.put("mail.host", serverHost);
		// 服务器是否要验证用户的身份信息
		props.put("mail.auth", String.valueOf(auth));
		props.put("mail.port", 465);
		// 是否输出DEBUG信息。默认为false
		props.put("mail.debug", debug);

		if (auth) { // 邮件服务器登录验证
			sendMailSession = Session.getDefaultInstance(props, new Authenticator() {
				// 授权凭证（用户名和密码）以及主机
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(serverUserName, password);
				}
			});
		} else {
			sendMailSession = Session.getDefaultInstance(props);
		}

		// 设置为debug模式, 可以查看详细的发送 log
		sendMailSession.setDebug(debug);
	}

	/**
	 * @方法描述 : 获取邮件服务器存储
	 * @return
	 * @throws MessagingException
	 */
	private Store getStoreClient(Session session) throws MessagingException {
		// 获取 Store 并连接到服务器
		// Store类实现特定邮件协议上的读、写、监视、查找等操作
		Store store = session.getStore(protocol);
		store.connect();
		return store;
	}

	/**
	 * @方法描述 : 关闭存储
	 * @param store
	 * @throws MessagingException
	 */
	private void closeStore(Store store) throws MessagingException {
		if (store != null && store.isConnected()) {
			store.close();
		}
	}

	/**
	 * @方法描述 : 关闭文件夹
	 * @param folder
	 * @param flag
	 *            false为不更新邮件，true为更新，一般在删除邮件后使用
	 * @throws MessagingException
	 */
	private void closeFolder(Folder folder, boolean flag) throws MessagingException {
		if (folder != null && folder.isOpen()) {
			folder.close(flag);
		}
	}

	/************************************************************************/
	/******************* 邮件发送 **************************************/
	/************************************************************************/
	/**
	 * @方法描述 : 创建邮件的实例对象
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件正文
	 * @param receiver
	 *            接收人
	 * @param ccReceiver
	 *            抄送人
	 * @param bccReceiver
	 *            密送人
	 * @param receivers
	 *            接收人列表
	 * @param ccReceivers
	 *            抄送人列表
	 * @param bccReceivers
	 *            密送人列表
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private MimeMessage createMimeMessage(String subject, String textMsg, String receiver,
			String ccReceiver, String bccReceiver, List<String> receivers, List<String> ccReceivers,
			List<String> bccReceivers) throws AddressException, MessagingException {
		MimeMessage mmessage = new MimeMessage(sendMailSession);
		mmessage.setFrom(new InternetAddress(serverUserName));

		if (receivers != null) {// To: 群发收件人
			int receiverNum = receivers.size();
			InternetAddress[] receiverAddress = new InternetAddress[receiverNum];
			for (int i = 0; i < receiverNum; i++) {
				receiverAddress[i] = new InternetAddress(receivers.get(i));
			}
			mmessage.setRecipients(MimeMessage.RecipientType.TO, receiverAddress);
		}
		if (StringUtils.isNotBlank(receiver)) { // To: 单发收件人
			mmessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver));
		}

		if (receivers != null) {// CC:群发 抄送人
			int ccReceiverNum = receivers.size();
			InternetAddress[] ccReceiverAddress = new InternetAddress[ccReceiverNum];
			for (int i = 0; i < ccReceiverNum; i++) {
				ccReceiverAddress[i] = new InternetAddress(ccReceivers.get(i));
			}
			mmessage.setRecipients(MimeMessage.RecipientType.CC, ccReceiverAddress);
		}
		if (StringUtils.isNotBlank(ccReceiver)) { // CC: 单发抄送人
			mmessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(ccReceiver));
		}

		if (receivers != null) {// BCC: 群发密送人
			int bccReceiverNum = receivers.size();
			InternetAddress[] bccReceiverNumAddress = new InternetAddress[bccReceiverNum];
			for (int i = 0; i < bccReceiverNum; i++) {
				bccReceiverNumAddress[i] = new InternetAddress(bccReceivers.get(i));
			}
			mmessage.setRecipients(MimeMessage.RecipientType.BCC, bccReceiverNumAddress);
		}
		if (StringUtils.isNotBlank(bccReceiver)) { // BCC: 单发密送人
			mmessage.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bccReceiver));
		}

		mmessage.setSubject(subject, CHARSET);
		mmessage.setContent(textMsg, "text/html;charset=UTF-8");
		mmessage.saveChanges();
		return mmessage;
	}

	/**
	 * @方法描述 : 发送邮件
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件内容
	 * @param receiver
	 *            收件人
	 * @param ccReceiver
	 *            抄送人
	 * @param bccReceiver
	 *            密送人
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public void send(String subject, String textMsg, String receiver, String ccReceiver,
			String bccReceiver) throws AddressException, MessagingException {
		Message msg = createMimeMessage(subject, textMsg, receiver, ccReceiver, bccReceiver, null,
				null, null);
		if (auth) {
			Transport transport = sendMailSession.getTransport();
			transport.addTransportListener(new TransportListener() {
				// 邮件部分发送成功
				public void messagePartiallyDelivered(TransportEvent transportEvent) {
				}

				// 邮件发送失败
				public void messageNotDelivered(TransportEvent transportEvent) {
				}

				// 邮件发送成功
				public void messageDelivered(TransportEvent transportEvent) {
				}
			});
			transport.connect(serverUserName, password);
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
			transport.close();
		} else {
			Transport.send(msg);
		}
	}

	/**
	 * @方法描述 : 群发邮件
	 * @param subject
	 *            邮件主题
	 * @param textMsg
	 *            邮件内容
	 * @param subject
	 *            邮件相关信息 - 邮件主题
	 * @param textMsg
	 *            邮件相关信息 - 邮件正文text
	 * @param receivers
	 *            邮件相关信息 - 接收人列表
	 * @param ccReceivers
	 *            邮件相关信息 - 抄送人列表
	 * @param bccReceivers
	 *            邮件相关信息 - 密送人列表
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public void send(String subject, String textMsg, List<String> receivers,
			List<String> ccReceivers, List<String> bccReceivers)
			throws AddressException, MessagingException {
		Message msg = createMimeMessage(subject, textMsg, "", "", "", receivers, ccReceivers,
				bccReceivers);
		Transport.send(msg);
	}

	/** 发送文本格式或Html格式的Email的方式 */
	public void sendTextEmail(String subject) {
	}

	/** 发送HTML格式的邮件 */
	public void sendHtmlMail(String subject) {
	}

	/** 发送带附件的电子邮件的应用 */
	public void sentAttacheEmail(String subject) {
	}

	/************************************************************************/
	/******************* 邮件接收 **************************************/
	/************************************************************************/
	public void receive() {

	}

	/**
	 * @方法描述 : 显示邮箱状况
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> showMailDetail() throws Exception {
		Map<String, Object> resultMap = Maps.newHashMap();
		Store store = null;
		Folder folder = null;
		try {
			store = getStoreClient(sendMailSession);
			// 获得收件箱
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			// 邮箱中总共有多少封信
			resultMap.put("messageCount", folder.getMessageCount());
			// 获取邮箱中新邮件的封数
			resultMap.put("newMessageCount", folder.getNewMessageCount());
			// 获取邮箱中未读邮件的封数
			resultMap.put("unreadMessageCount", folder.getUnreadMessageCount());
			// 获取邮箱中删除邮件数
			resultMap.put("deletedMessageCount", folder.getDeletedMessageCount());
		} finally {
			closeFolder(folder, false);
			closeStore(store);
		}
		return resultMap;
	}

	/**
	 * @方法描述 : 获取邮件夹列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> showFolderInfo() throws Exception {
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Store store = getStoreClient(sendMailSession);
		try {
			Folder rootFolder = store.getDefaultFolder();// 默认父目录
			Folder[] folders = rootFolder.list();// 默认目录列表
			Map<String, Object> resultMap = null;
			String folderName = "";
			for (Folder folder : folders) {
				resultMap = Maps.newHashMap();
				folderName = folder.getName();
				resultMap.put("folderName", folderName);
				resultMap.put("messageCount", store.getFolder(folderName).getMessageCount());
				resultList.add(resultMap);
			}
		} finally {
			// 关闭 store, 断开网络连接
			closeStore(store);
		}
		return resultList;
	}

	/**
	 * @方法描述 : 显示所有邮件
	 * @throws Exception
	 */
	public void showMessages() throws Exception {
		Store store = null;
		try {
			store = getStoreClient(sendMailSession);
			Folder[] folders = store.getDefaultFolder().list();// 默认目录列表
			Folder popFolder = null;
			for (Folder folder : folders) {
				try {
					popFolder = store.getFolder(folder.getName());
					// 设置对邮件帐户的访问权限
					popFolder.open(Folder.READ_ONLY);
					// 获取邮箱帐户中的所有邮件
					Message[] messages = popFolder.getMessages();
					// 取出来邮件数
					int msgCount = popFolder.getMessageCount();
					// 循环处理每个邮件并实现邮件转为新闻的功能
					Message message = null;
					for (int i = 0; i < msgCount; i++) {
						message = messages[i];
						message.getSentDate();
					}
				} finally {
					closeFolder(popFolder, false);
				}
			}
		} finally {
			closeStore(store);
		}
	}

	/************************************************************************/
	/******************* 邮件回复 **************************************/
	/************************************************************************/
	/**
	 * @方法描述 : 邮件回复
	 * @思路: 先获取要回复的邮件，然后获取邮件的各个详情，把详情附加给新建邮件，发送
	 */
	public void reply() {
		newReplyMail();
	}

	/**
	 * @方法描述 : 组装要回复的邮件
	 */
	private Message newReplyMail() {
		return null;
	}

	/************************************************************************/
	/******************* 邮件删除 **************************************/
	/************************************************************************/
	public void deleteMessage() {

	}

	/************************************************************************/
	/******************* 移动邮件 **************************************/
	/************************************************************************/
	public void moveMessage() {

	}

	/************************************************************************/
	/******************* 复制邮件 **************************************/
	/************************************************************************/
	public void copyMessage() {

	}
}
