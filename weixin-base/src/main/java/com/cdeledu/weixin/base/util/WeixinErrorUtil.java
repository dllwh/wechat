package com.cdeledu.weixin.base.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @ClassName: WeixinErrorUtil
 * @Description: 接口调用错误获取
 * @author: 独泪了无痕
 * @date: 2015年9月25日 下午4:15:56
 * @version: V1.0
 * @history:
 */
public class WeixinErrorUtil {
	private static byte[] errorXmlByteArray;
	/** 存储已经查询出的结果集,下次遇到相同的直接结果集,类似于缓存 */
	private final static Map<String, String> errorCacheMap;
	public final static String CONFIG_PATH = "/properties/weixin/error.xml";

	static {
		errorCacheMap = new HashMap<String, String>();
		try {
			errorXmlByteArray = IOUtils.toByteArray(WeixinErrorUtil.class.getResourceAsStream(CONFIG_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 自定义的解析类,通过此类中的startElement了解uri,localName,qName,Attributes的含义 */
	private static class ErrorTextHandler extends DefaultHandler {
		private String code;

		public ErrorTextHandler(String code) {
			this.code = code;
		}

		private String text;
		private boolean codeElement;
		private boolean textElement;
		private boolean findElement;

		/** 解析XML时,当开始读取XML文档时即开始解析 */
		@Override
		public void startDocument() {

		}

		/** 结束解析 */
		@Override
		public void endDocument() {

		}

		/**
		 * @注释: 解析XML时，当开始读到一个元素标签开始时
		 * @param: uri
		 *             元素的命名空间
		 * @param: localName
		 *             返回当前节点的本地名称（不带前缀）
		 * @param: qName
		 *             元素的限定名（带前缀）
		 * @param: attributes
		 *             元素的属性集合
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			// code 标签(编码)
			codeElement = qName.equalsIgnoreCase("code");
			// text 标签(内容)
			textElement = qName.equalsIgnoreCase("text");
		}

		/** 解析XML时,当读到结束一个元素标签时 */
		@Override
		public void endElement(String namespaceURI, String localName, String qName) {

		}

		/** 根据tagName获取标签的内容 */
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			// 1.读取标签里面的内容
			String _text = new String(ch, start, length);
			// 2.如果是code标签,则说明找到标签
			// 3.如果是text标签,则读取text标签的内容,此值就是想要获取的
			if (codeElement && _text.equalsIgnoreCase(code)) {
				findElement = true;
			} else if (textElement && findElement) {
				text = _text;
				findElement = false;
			}
		}

		public String getText() {
			return StringUtils.isBlank(text) ? "未知错误" : text;
		}
	}

	public static String getText(String code) {
		String text = errorCacheMap.get(code);
		try {
			if (StringUtils.isBlank(text)) {
				ErrorTextHandler textHandler = new ErrorTextHandler(code);
				XMLReader xmlReader = XMLReaderFactory.createXMLReader();
				xmlReader.setContentHandler(textHandler);
				xmlReader.parse(new InputSource(new ByteArrayInputStream(errorXmlByteArray)));
				text = textHandler.getText();
				errorCacheMap.put(code, text);
			}
		} catch (SAXException e) {
			e.printStackTrace();
			errorCacheMap.put(code, text);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return text;
	}

	public static void main(String[] args) {
		System.out.println(getText("PARAM_ERROR"));
	}
}
