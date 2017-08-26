package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.crawler.SocialNetwork.proxy.entity.ProxyPool;
import com.cdeledu.util.apache.collection.MapUtilHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @类描述: 高质量的IP供应商
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月20日 下午9:43:25
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.xdaili.cn/">讯代理-爬虫代理-HTTP代理-代理服务器</a>
 */
public class XdailiProxyHelper {
	/** ----------------------------------------------------- Fields start */
	protected static Logger logger = Logger.getLogger(XdailiProxyHelper.class);
	private static final String BASEURL = "http://www.xdaili.cn/";
	private static String getFreeIpCountUrl = BASEURL + "ipagent/freeip/getIpCount";
	private static String getFreeIpListUrl = BASEURL + "ipagent/freeip/getFreeIps?page=%s&rows=10";

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 获取免费代理IP总数
	 * @return
	 */
	public static int getFreeIpCount() throws Exception {
		int resultNum = 0;
		String result = HttpURLConnHelper.getInstance().sendGetRequest(getFreeIpCountUrl);

		JSONObject _result = JSONObject.fromObject(result);
		if (_result.has("ERRORCODE")) {
			if ("0".equals(_result.getString("ERRORCODE"))) {
				resultNum = _result.getInt("RESULT");
			}
		}
		return resultNum;
	}

	/**
	 * @方法描述: 获取免费代理IP
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public static String getFreeIps(int page) throws Exception {
		if (page < 1) {
			page = 1;
		}
		getFreeIpListUrl = String.format(getFreeIpListUrl, page);
		String result = HttpURLConnHelper.getInstance().sendGetRequest(getFreeIpListUrl);
		JSONObject _result = JSONObject.fromObject(result);
		List<Map<String, Object>> resultList = Lists.newArrayList();
		if (_result.has("ERRORCODE")) {
			if ("0".equals(_result.getString("ERRORCODE"))) {
				String childResult = _result.getString("RESULT");
				if (StringUtils.isNoneBlank(childResult)) {
					JSONObject _cResult = JSONObject.fromObject(childResult);
					JSONArray _Jarray = JSONArray.fromObject(_cResult.getString("rows"));
					JSONObject job = null;
					ProxyPool proxyIP = null;
					for (int i = 0; i < _Jarray.size(); i++) {
						try {
							job = _Jarray.getJSONObject(i);
							proxyIP = new ProxyPool();
							proxyIP.setIp(job.getString("ip"));
							proxyIP.setPort(Integer.valueOf(job.getString("port")));
							String[] position = job.getString("position").split(" ");
							proxyIP.setPosition(position[0]);
							proxyIP.setIsp(position[1]);
							proxyIP.setProtocolType(job.getString("type").split("/"));
							resultList.add(MapUtilHelper.BeanToMap(proxyIP));
						} catch (Exception e) {
							logger.error(e);
						}
						System.err.println();
					}
				}

			}
		}
		return JsonMapper.toJsonString(resultList);
	}
}
