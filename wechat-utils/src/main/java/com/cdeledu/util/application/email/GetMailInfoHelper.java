package com.cdeledu.util.application.email;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * @Description: 获取某封邮件的详细信息
 * @author: 独泪了无痕
 * @date: 2015年9月15日 上午10:25:30
 * @version: V1.1
 * @history:
 */
public class GetMailInfoHelper {
	/**
	 * @方法描述 : 解析邮件
	 * @param message
	 *            邮件对象
	 * @throws Exception
	 */
	public static void mailReceiver(Message message) throws Exception {
		// 发件人信息
		Address[] froms = message.getFrom();
		if (froms != null) {
			// 发件人地址信息
			InternetAddress addr = (InternetAddress) froms[0];
			// 发件人地址
			addr.getAddress();
			// 发件人显示名
			MimeUtility.decodeText(addr.getPersonal());
		}
		// 获得邮件发送时间
		message.getSentDate();
		// 判断邮件是否已读
		message.getFlags().contains(Flags.Flag.SEEN);
		// 判断邮件是否需要阅读回执
		message.getHeader("Disposition-Notification-To");
		// 邮件大小
		message.getSize();
		// 收件人
		getReceiveAddress(message, null);
		// 邮件主题:需要注意的是主题的内容可能没有
		MimeUtility.decodeText(message.getSubject());

		message.writeTo(System.out);// 输出邮件内容到控制台

		// getContent() 是获取包裹内容, Part相当于外包装
		Object content = message.getContent();
		// 邮件的类型
		message.getContentType();
		if (content instanceof Multipart) { // 附件
			Multipart multipart = (Multipart) content;
			reMultipart(multipart);
		} else if (content instanceof Part) {
			Part part = (Part) content;
			rePart(part);
		}
	}

	/**
	 * @方法描述 : 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
	 * 
	 *       <pre>
	 *  
	 * Message.RecipientType.TO  收件人
	 * Message.RecipientType.CC  抄送
	 * Message.RecipientType.BCC 密送
	 *       </pre>
	 * 
	 * @param msg
	 * @param type
	 * @return
	 * @throws MessagingException
	 */
	private static void getReceiveAddress(Message message, Message.RecipientType recipientType)
			throws MessagingException {
		if (recipientType == null) {
			message.getAllRecipients();
		} else {
			message.getRecipients(recipientType);
		}
	}

	/**
	 * @param part
	 *            解析内容
	 * @throws Exception
	 */
	private static void rePart(Part part) throws MessagingException, UnsupportedEncodingException,
			IOException, FileNotFoundException {
		if (part.getDisposition() != null) {

			// MimeUtility.decodeText解决附件名乱码问题
			String strFileNmae = MimeUtility.decodeText(part.getFileName());
			System.out.println("发现附件: " + strFileNmae);
			// 内容类型
			MimeUtility.decodeText(part.getContentType());
			// 附件内容
			part.getContent();
			InputStream in = part.getInputStream();
			FileOutputStream out = new FileOutputStream(strFileNmae);
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
			in.close();
			out.close();
		} else {
			String contentType = part.getContentType();
			if (contentType.equals("multipart/alternative")) { // HTML
				System.out.println("超文本:" + part.getContent().toString());
			} else if (contentType.startsWith("text/plain")) { // 纯文本
				System.out.println("纯文本:" + part.getContent().toString());
			} else if (contentType.startsWith("text/html")) {// HTML标签元素
				System.out.println("文本内容：" + part.getContent());
			} else if (part.getContentType().startsWith("multipart/related")) {
				// 内嵌资源 (包涵文本和超文本组合)
				System.out.println("内嵌资源:" + part.getContent().toString());
			} else if (part.getContentType().startsWith("application/")) {
				// 应用附件 （zip、xls、docx等）
				System.out.println("应用文件：" + part.getContent().toString());
			} else if (part.getContentType().startsWith("image/")) {
				// 图片附件 （jpg、gpeg、gif等）
				System.out.println("图片文件：" + part.getContent().toString());
			}
		}
	}

	/**
	 * @param multipart
	 *            接卸包裹（含所有邮件内容(包裹+正文+附件)）
	 * @throws Exception
	 */
	private static void reMultipart(Multipart multipart) throws Exception {
		// 部件个数
		int count = multipart.getCount();
		// 依次处理各个部分
		for (int j = 0; j < count; j++) {
			// 解包, 取出 MultiPart的各个部分,每部分可能是邮件内容,也可能是另一个小包裹(MultipPart)
			Part part = multipart.getBodyPart(j);
			// 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
			if (part.getContent() instanceof Multipart) {
				// 转成小包裹
				Multipart p = (Multipart) part.getContent();
				/**
				 * MIME类型 :BodyPart.getContentType();
				 * <p>
				 * 1、当MIME类型表示的是图片、声音或附件等二进制数据时，
				 * 此时应调用BodyPart对象的getDataHandler方法得到封装了数据的DataHandler对象，
				 * 然后调用DataHandler对象的getInputStream方法获得与数据相关的InputStream对象，
				 * 通过这个InputStream对象中即可获得原始的二进制数据内容
				 * </p>
				 * <p>
				 * 2、当MIME类型为”text/*”时，表示BodyPart对象中保存的是纯文本数据，
				 * 此时调用BodyPart对象的getContent方法并将返回的对象转换成String输出给显示软键显示即可
				 * </p>
				 * 
				 * <p>
				 * 3、当MIME类型是”multipart/mixed”时，表示BodyPart对象中保存的是一个复合MIME消息，
				 * 此时调用BodyPart对象中的getContent方法得到封装复合MIME消息的对象并将它转换成Multipart类型
				 * </p>
				 */

				// 递归迭代
				reMultipart(p);
			} else {
				rePart(part);
			}
		}
	}
}
