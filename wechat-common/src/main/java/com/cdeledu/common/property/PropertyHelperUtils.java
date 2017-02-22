package com.cdeledu.common.property;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @描述:
 * 		<ul>
 *      <li>用于读取Java的配置文件</li>
 *      <li>Properties 类存在于包 Java.util 中，该类继承自 Hashtable</li>
 *      <li>1.load(inputStream inStream):从输入流中读取属性列表（键和元素对）</li>
 *      <li>2.getProperty(String key):用指定的键在此属性列表中搜索属性</li>
 *      <li>3.setProperty(String key, String value):调用基类的put()方法设置键-值对</li>
 *      <li></li>
 *      <li>总结：</li>
 *      <li>java的properties文件需要放到classpath下面. classpath实际上就是java类或者库的存放路径</li>
 *      </ul>
 * @author: 独泪了无痕
 * @date: 2015-8-20 下午10:03:05
 * @version: V1.2
 */
public class PropertyHelperUtils {
	private static final Log log = LogFactory.getLog(PropertyHelperUtils.class);

	/**
	 * @方法:获取.properties属性文件所有属性
	 * @创建人:独泪了无痕
	 * @param filePath
	 * @return
	 */
	public static Properties getProps(String filePath) {
		InputStream ist = null;
		InputStreamReader isr = null;
		URL fileUrl = null;
		Properties props = new Properties();

		try {
			fileUrl = PropertyHelperUtils.class.getClassLoader().getResource(filePath);

			if (fileUrl != null)
				ist = fileUrl.openStream();
			else
				ist = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

			if (ist == null)
				ist = PropertyHelperUtils.class.getResourceAsStream(filePath);
			isr = new InputStreamReader(ist, "UTF-8");
			props.load(isr);
		} catch (Exception e) {
			log.info("读取Property文件出错：", e);
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(isr);
			IOUtils.closeQuietly(ist);
		}

		return props;
	}

	/**
	 * @方法:获取properties属性的Map集合
	 * @创建人:独泪了无痕
	 * @param filePath
	 * @return
	 */
	public static Map<String, String> getMapByProperties(String filePath) {
		Properties properties = getProps(filePath);
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Set<String> keySets = properties.stringPropertyNames();
		Object[] keys = keySets.toArray();
		for (int i = 0; i < keys.length; i++) {
			String key = (String) keys[i];
			resultMap.put(key, properties.getProperty(key));
		}
		return resultMap;
	}

	/**
	 * @方法:获取properties属性的Map集合
	 * @创建人:独泪了无痕
	 * @param properties
	 * @return
	 */
	public static Map<String, String> getPropertyMap(Properties properties) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Set<String> keySets = properties.stringPropertyNames();
		Object[] keys = keySets.toArray();
		for (int i = 0; i < keys.length; i++) {
			String key = (String) keys[i];
			resultMap.put(key, properties.getProperty(key));
		}
		return resultMap;
	}

	/**
	 * @方法:读取基本属性文件
	 * @创建人:独泪了无痕
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getProperty(Properties props, String key) {
		return props.getProperty(key);
	}

	/**
	 * @方法:读取Integer类型的属性文件
	 * @创建人:独泪了无痕
	 * @param props
	 * @param key
	 * @return
	 */
	public static int getIntValue(Properties props, String key) {
		String result = getProperty(props, key);
		return StringUtils.isBlank(result) ? 0 : Integer.parseInt(result);
	}

	/**
	 * @方法:读取Boolean类型的属性文件
	 * @创建人:独泪了无痕
	 * @param props
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(Properties props, String key) {
		String result = getProperty(props, key);
		return StringUtils.isBlank(result) ? false : Boolean.parseBoolean(result);
	}

	/**
	 * @方法:读取Long类型的属性文件
	 * @创建人:独泪了无痕
	 * @param props
	 * @param key
	 * @return
	 */
	public static long getLongValue(Properties props, String key) {
		String result = getProperty(props, key);
		return StringUtils.isBlank(result) ? 0 : Long.parseLong(result);
	}

	/**
	 * @方法:读取Double类型的属性文件
	 * @创建人:独泪了无痕
	 * @param props
	 * @param key
	 * @return
	 */
	public static double getDoubleValue(Properties props, String key) {
		String result = getProperty(props, key);
		return StringUtils.isBlank(result) ? 0.0d : Double.parseDouble(result);
	}
}
