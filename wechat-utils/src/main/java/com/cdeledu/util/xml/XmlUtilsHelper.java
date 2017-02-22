package com.cdeledu.util.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @ClassName: XmlUtilsHelper
 * @Description: xml与其他文件的相关操作(外部)
 * @author: 独泪了无痕
 * @date: 2015年9月18日 上午11:35:43
 * @version: V1.0
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
	public static Map<String, Object> parseXml(InputStream inputStream)
			throws Exception, IOException, DocumentException {
		// 将解析结果存放在HashMap中
		Map<String, Object> resultMap = new HashMap<String, Object>();

		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 获取根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element ele : elementList) {
			resultMap.put(ele.getName(), ele.getText());
		}
		return resultMap;
	}
}
