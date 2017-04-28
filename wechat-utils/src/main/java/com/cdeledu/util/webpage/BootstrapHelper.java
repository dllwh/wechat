package com.cdeledu.util.webpage;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.cdeledu.util.application.regex.RegexUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @类描述: Bootstrap开发框架
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年4月28日 下午8:34:01
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class BootstrapHelper {
	/** ----------------------------------------------------- Fields start */
	private final static String regexList = "\\.((glyphicon|icon|fa).*?):before\\s*\\{";
	private final static String regexKeyWord = "(?<=\\.)(.+?)(?=\\:)";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: Bootstrap图标的提取
	 * @param filePathMap
	 *            文件地址集合
	 * @return
	 * @throws Exception
	 */
	public static final List<Map<String, String>> getBootstrapIconInfo(
			Map<String, String> filePathMap) throws Exception {
		List<Map<String, List<String>>> matchList = Lists.newArrayList();
		List<String> tempList = null;
		List<Map<String, String>> resultList = Lists.newArrayList();
		Map<String, List<String>> matchMap = null;
		Map<String, String> bootstrapIconInfo = null;

		String content = "";

		for (Map.Entry<String, String> entry : filePathMap.entrySet()) {
			tempList = Lists.newArrayList();
			matchMap = Maps.newConcurrentMap();
			content = FileUtils.readFileToString(new File(entry.getValue()));

			matchMap.put(entry.getKey(), RegexUtil.getList(regexList, content, 1, tempList));
			matchList.add(matchMap);
		}
		int matchSize = matchList.size();
		for (int i = 0; i < matchSize; i++) {
			for (Entry<String, List<String>> mapShow : matchList.get(i).entrySet()) {
				bootstrapIconInfo = Maps.newConcurrentMap();
				for (String item : mapShow.getValue()) {
					item = RegexUtil.getKeyWords(regexKeyWord, item);
					bootstrapIconInfo.put("displayName", item);// 名称
					System.out.println(item);
					bootstrapIconInfo.put("sourceType", mapShow.getKey());// 来源
					if (mapShow.getKey().equalsIgnoreCase("glyphicons")) {
						bootstrapIconInfo.put("className", "glyphicons " + item);
					}
					if (mapShow.getKey().equalsIgnoreCase("fontAwesome")) {
						bootstrapIconInfo.put("className", "fa " + item);
					}
					if (mapShow.getKey().equalsIgnoreCase("simpleLine")) {
						bootstrapIconInfo.put("className", item);
					}
				}
			}
			resultList.add(bootstrapIconInfo);
		}
		return resultList;
	}
}
