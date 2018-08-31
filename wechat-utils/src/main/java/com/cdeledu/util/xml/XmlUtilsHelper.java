package com.cdeledu.util.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.common.collect.Maps;

/**
 * @ClassName: XmlUtilsHelper
 * @Description: xml与其他文件的相关操作(外部)
 * @author: 独泪了无痕
 * @date: 2015年9月18日 上午11:35:43
 * @version: V1.1.1
 * @history:
 */
public class XmlUtilsHelper {
	/***
	 * 
	 * @Title: parseXml
	 * @Description: 利用解析微信发来的请求
	 * @author: 独泪了无痕
	 * @return
	 * @throws WeixinException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, Object> parseXml(InputStream inputStream) throws Exception {
		// 将解析结果存放在HashMap中
		Map<String, Object> resultMap = new HashMap<String, Object>();

		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);// 得到Document对象
		Element root = document.getRootElement(); // 得到xml根元素
		Iterator<?> iterator = root.elementIterator(); // 遍历其子节点
		while (iterator.hasNext()) {
			Element child = (Element) iterator.next();
			if (child.isTextOnly()) {
				resultMap.put(child.getName(), child.getTextTrim());
			} else {
				resultMap.put(child.getName(), parseMap(child));
			}
		}
		return resultMap;
	}

	private static Map<String, Object> parseMap(Element rootElement) throws Exception {
		Map<String, Object> resultMap = Maps.newHashMap();
		// List<Element> elementList = rootElement.elements();
		Iterator<?> iterator = rootElement.elementIterator();
		while (iterator.hasNext()) {
			Element ele = (Element) iterator.next();
			if (ele.isTextOnly()) {
				resultMap.put(ele.getName(), ele.getTextTrim());
			} else {
				resultMap.put(ele.getName(), parseMap(ele));
			}
		}
		return resultMap;
	}

	public static Map<String, Object> parseXml(String content, String charset) throws Exception {
		InputStream inputStream = null;
		if (StringUtils.isNotBlank(charset)) {
			inputStream = new ByteArrayInputStream(content.getBytes(charset));
		} else {
			inputStream = new ByteArrayInputStream(content.getBytes());
		}
		return parseXml(inputStream);
	}
}
