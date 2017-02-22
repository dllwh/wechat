package com.cdeledu.util.message.i18n;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @ClassName: I18nHelper
 * @Description: <ul>
 *               <li>Java实现程序国际化</li>
 *               <li>①.java.util.ResourceBundle:用于加载一个国家、语言资源包</li>
 *               <li>②.java.util.Locale:用于封装一个特定的国家/区域、语言环境</li>
 *               <li>③.Locale(String language):根据指定语言创建一个语言环境</li>
 *               <li>④.Locale(String language,String country):根据指定国家和语言创建一个语言环境</li>
 *               <li>⑤.java.text.MessageFormat：用于格式化带占位符的字符串</li>
 *               <li>⑥.</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年9月10日 上午9:23:24
 * @version: V1.0
 * @history:
 */
public class I18nHelper {
	/** -------------------------- 私有属性 begin ------------------------------- */
	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * 
	 * @Title: getCountryLocale
	 * @Description: <ul>
	 *               <li>获取Java所支持的语言和国家(获取所有已经安装的语言环境)</li>
	 *               <li>① getCountry() 获取区域环境名称</li>
	 *               <li>② getDisplayCountry() 获取区域环境对应的代码</li>
	 *               <li>③ getLanuage() 获取区域环境下语言环称</li>
	 *               <li>④ getDiaplayLanuage() 获取区域环境下语言环境代码</li>
	 *               </ul>
	 * @return
	 * @return:List<Map<String,String>> 返回类型
	 */
	public static List<Map<String, String>> getCountryLocale() {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		Map<String, String> resMap = null;
		// 返回Java所支持的全部国家和语言的数组
		Locale[] localeList = Locale.getAvailableLocales();
		// 遍历数组的每个元素，依次获取所支持的国家和语言
		for (Locale locale : localeList) {

			resMap = new HashMap<String, String>();
			// 国家
			resMap.put("country", locale.getDisplayCountry());
			// 国家对应码
			resMap.put("countryCode", locale.getCountry());
			// 语言
			resMap.put("language", locale.getDisplayLanguage());
			// 语言对应码
			resMap.put("languageCode", locale.getLanguage());
			resultList.add(resMap);
		}
		return resultList;
	}

	/**
	 * 
	 * @Title: getDefaultLocale
	 * @Description: 获取当前Java虚拟机线程默认的国家和语言信息
	 * @return
	 * @return:Map<String,String> 返回类型
	 */
	public static Map<String, String> getDefaultLocale() {
		Map<String, String> resultMap = new HashMap<String, String>();

		// 获得此Java虚拟机当前线程默认的语言环境值
		Locale defaultLocale = Locale.getDefault();
		// 1.返回国家地区代码
		resultMap.put("countryCode", defaultLocale.getCountry());
		// 2.返回国家的语言
		resultMap.put("languageCode", defaultLocale.getLanguage());
		// 3.返回适合向用户显示的国家信息
		resultMap.put("country", defaultLocale.getDisplayCountry());
		// 4.返回适合向用户展示的语言信息
		resultMap.put("language", defaultLocale.getDisplayLanguage());
		// 5.返回适合向用户展示的语言环境名
		resultMap.put("displayName", defaultLocale.getDisplayName());
		return resultMap;
	}

	/**
	 * -------------------------- 公有方法 end -------------------------------
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// getCountryLocale();
		/**
		 * 1.取得系统默认的国家/语言环境
		 */
		Locale defaultLocale = Locale.getDefault();
		/**
		 * 1. 根据指定国家/语言环境加载资源文件<BR/>
		 * 2.1 获取当前系统所使用的区域环境获得指定资源文件<BR/>
		 * 2.1 根据指定的区域获取对应的资源文件
		 */
		ResourceBundle bundle = ResourceBundle.getBundle(
				"properties.myResources.messages", defaultLocale);
		// 3.通过资源包获取locale相关信息
		String Welcome = bundle.getString("greeting.afternoon");
		Object[] params = { "独泪了无痕", new GregorianCalendar().getTime() };
		// 4.格式化数据功能
		System.out.println(MessageFormat.format(Welcome, params));
	}
}
