package com.cdeledu.util.xml;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.exception.RuntimeExceptionHelper;
import com.cdeledu.util.apache.lang.StringUtilHelper;

/**
 * 
 * @ClassName: XmlUtils
 * @Description: 解析xml工具类(XML的内部操作)
 * @author: 独泪了无痕
 * @date: 2015年6月17日 下午1:33:04
 * @version: V1.0
 * @history:
 */
public class XmlUtils {
	/** 在XML中无效的字符 正则 */
	public final static String INVALID_REGEX = "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]";

	/**
	 * 
	 * @Title：readDoc
	 * @Description：从文件获得DOM对象
	 * @param xmlPathBaseClassLoader
	 *            相对路径（相对于当前项目的classes路径）
	 * @return
	 * @throws DocumentException
	 * @return：Document 返回类型
	 */
	public static org.dom4j.Document readDoc(String xmlPathBaseClassLoader)
			throws DocumentException {
		return new SAXReader().read(XmlUtils.class.getClassLoader()
				.getResourceAsStream(xmlPathBaseClassLoader));
	}
	
	/*--------------------------私有方法 start-------------------------------*/
	/**
	 * 
	 * @方法名称: cleanInvalid
	 * @方法描述:
	 * 		<ul>
	 *        <li>去除XML文本中的无效字符</li>
	 *        <li>当传入为null\""时返回null</li>
	 *        </ul>
	 * @param xmlContent
	 *            XML文本
	 * @return
	 */
	public static String cleanInvalid(String xmlContent) {
		if (StringUtils.isBlank(xmlContent)) {
			return "";
		} else {
			return xmlContent.replaceAll(INVALID_REGEX, "");
		}

	}

	/*--------------------------私有方法 end-------------------------------*/

	/*--------------------------公有方法 start-------------------------------*/
	/**
	 * @方法:将String类型的XML转换为XML文档
	 * @创建人:独泪了无痕
	 * @param xmlStr
	 *            XML字符串
	 * @return XML文档
	 * @throws DocumentException
	 */
	public static Document parseDoc(String xmlStr) {
		if (StringUtils.isBlank(xmlStr)) {
			throw new RuntimeExceptionHelper("Xml content string is empty !");
		}
		xmlStr = cleanInvalid(xmlStr);

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder builder = dbf.newDocumentBuilder();
			return builder.parse(new InputSource(StringUtilHelper.getReader(xmlStr)));
		} catch (Exception e) {
			throw new RuntimeExceptionHelper("Parse xml file [" + xmlStr + "] error!", e);
		}
	}

	/**
	 * @方法:读取解析XML文件
	 * @创建人:独泪了无痕
	 * @param absoluteFilePath
	 *            XML文件绝对路径
	 * @return
	 * @throws DocumentException
	 */
	public static Document readXML(String absoluteFilePath) throws DocumentException {
		return readXML(new File(absoluteFilePath));
	}

	/**
	 * 
	 * @方法名称: readXML
	 * @方法描述: 从文件获得DOM对象
	 * 
	 * @param xmlFile
	 *            XML文件对象
	 * @return DOM对象
	 * @throws DocumentException
	 */
	public static Document readXML(File xmlFile) throws DocumentException {
		if (xmlFile == null) {
			throw new NullPointerException("Xml file is null !");
		}
		if (xmlFile.exists() == false) {
			throw new RuntimeExceptionHelper("File [" + xmlFile.getAbsolutePath() + "] not a exist!");
		}
		if (xmlFile.isFile() == false) {
			throw new RuntimeExceptionHelper("[" + xmlFile.getAbsolutePath() + "] not a file!");
		}
		try {
			xmlFile = xmlFile.getCanonicalFile();
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = dbf.newDocumentBuilder();
			return builder.parse(xmlFile);
		} catch (Exception e) {
			throw new RuntimeExceptionHelper("Parse xml file [" + xmlFile.getAbsolutePath() + "] error!", e);
		}
	}

	/**
	 * @方法:将XML文档转换为String
	 * @创建人:独泪了无痕
	 * @param doc
	 *            XML文档
	 * @return XML字符串
	 */
	public static String toStr(Document doc) {
		return toStr(doc, ConstantHelper.UTF_8.name());
	}

	/**
	 * 将XML文档转换为String<br>
	 * 此方法会修改Document中的字符集
	 * 
	 * @param doc
	 *            XML文档
	 * @param charset
	 *            自定义XML的字符集
	 * @return XML字符串
	 */
	public static String toStr(Document doc, String charset) {
		try {
			StringWriter writer = StringUtilHelper.getWriter();

			final Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, charset);
			xformer.setOutputProperty(OutputKeys.INDENT, "yes");
			xformer.transform(new DOMSource(doc), new StreamResult(writer));

			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeExceptionHelper("Trans xml document to string error!", e);
		}
	}

	/**
	 * @方法:将XML文档写入到文件
	 * @创建人:独泪了无痕
	 * @param doc
	 *            XML文档
	 * @param absolutePath
	 *            文件绝对路径，不存在会自动创建
	 * @param charset
	 *            自定义XML文件的编码
	 */
	public static void toFile(Document doc, String absolutePath, String charset) {
	}

	public static String createXml(Map<?, ?> map) throws Exception {
		// 创建root
		Element root = DocumentHelper.createDocument().addElement("xml");

		for (Object key : map.keySet()) {
			// 生成root的一个接点
			Element param = root.addElement(String.valueOf(key));
			// 为节点添加文本, 也可以用addText()
			param.addCDATA(String.valueOf(map.get(key)));
		}

		OutputFormat xmlFormat = new OutputFormat();
		// 设置文件编码
		xmlFormat.setEncoding("UTF-8");
		// 设置换行
		xmlFormat.setNewlines(false);
		// 生成缩进
		xmlFormat.setIndent(false);
		// 使用4个空格进行缩进, 可以兼容文本编辑器
		// xmlFormat.setIndent(" ");

		// 创建写文件方法
		XMLWriter xmlWriter = new XMLWriter(xmlFormat);
		/** xmlWriter.write(document); */
		xmlWriter.write(root);
		xmlWriter.close();
		return xmlWriter.toString();
	}

	/**
	 * 检验指定的XML是否符合
	 * 
	 * @param doc
	 *            被检验的XML文档
	 * @param schemePath
	 *            schema的路径
	 * @return 如果出错，返回错误信息，否则返回null
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws DocumentException
	 */
	public static String validateBySchema(Document doc, String schemePath)
			throws ParserConfigurationException, SAXException, DocumentException {
		XMLErrorHandler errorHandler = new XMLErrorHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		SAXParser parser = factory.newSAXParser();

		parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + schemePath);

		SAXValidator validator = new SAXValidator(parser.getXMLReader());
		validator.setErrorHandler(errorHandler);
		// validator.validate(doc);

		if (errorHandler.getErrors().hasContent()) {
			return errorHandler.getErrors().asXML();
		}
		return null;
	}

	/**
	 * 
	 * @方法名称: XmlToBean
	 * @方法描述: 将xml转换成javabean
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 */
	public static <T> T xmlToBean(String xml, Class<T> clazz) {
		return null;
	}

	/*--------------------------公有方法 end-------------------------------*/
	public static void main(String[] args) throws DocumentException {
		// String xml =
		// "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType></xml>";
		// System.out.println(XmlToBean(xml, WeixinMsg.class));
		String result = "<xml><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2008-6-15</date><class>设备工程监理服务合同(试行)</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2009-5-15</date><class>项目管理指南</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2010-6-25</date><class>设备监理单位资格基本要求</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2011-7-15</date><class>产品质量法律法规在设备监理中的应用</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2011-9-13</date><class>大型系统设备工程在形成过程中监理工作要点</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2011-9-9</date><class>合同法及设备监理服务合同解析</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2012-3-23</date><class>设备工程监理规范</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2013-3-22</date><class>设备监理服务技能与实务</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2013-11-20</date><class>2013年设备工程监理国际合作论坛</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2014-7-29</date><class>城市轨道交通—电力及牵引供电设备监理</class><city>北京</city><score>20</score></row><row><userpid>CAPEC-RM0400025</userpid><name>吴学文</name><no>CAPEC 01 003202</no><date>2014-9-2</date><class>设备工程与设备监理的规律及特点</class><city>北京</city><score>20</score></row></xml>";
		readDoc(result);
	}

}
