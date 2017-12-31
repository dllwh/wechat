package com.cdeledu.crawler.SocialNetwork.weibo.WeiboHelper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 新浪代码说明
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年12月30日 下午11:50:19
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://open.weibo.com/wiki/Error_code">错误返回值格式</a>
 */
public class SinaCodeHelper {
	/** ----------------------------------------------------- Fields start */
	/**  错误代码 */
	private static final String url = "http://open.weibo.com/wiki/Error_code";
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法: 
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static List<Map<String, Object>> getErrorCode() {
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Map<String, Object> resultMap = null;
		try {
			String result = HttpURLConnHelper.getInstance().sendPostRequest(url);
			
			Document document = Jsoup.parse(result);
			Elements wList = document.select("table.parameters");
			for (int i = 1; i < wList.size(); i++) {
				Elements trList = wList.get(i).getElementsByTag("tr");
				for (Element element : trList) {
					resultMap = Maps.newConcurrentMap();
					Elements tdList = element.getElementsByTag("td");
					String errorCode = tdList.get(0).text().trim();
					try {
						if (StringUtils.isNumeric(errorCode)) {
							resultMap.put("errorCode", errorCode);
							resultMap.put("errorMsg", tdList.get(2).text().trim());
							resultList.add(resultMap);
						}
					} catch (Exception e) {
						
					}
				}
			}
		} catch (Exception e) {
			
		}
		return resultList;
	}
}
