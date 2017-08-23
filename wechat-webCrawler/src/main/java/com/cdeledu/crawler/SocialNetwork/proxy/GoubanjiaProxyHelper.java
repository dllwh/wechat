package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
import com.google.common.collect.Maps;

/**
 * 
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月20日 下午9:31:21
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.goubanjia.com/">全网代理IP-高匿HTTP代理IP服务器供应商</a>
 */
public class GoubanjiaProxyHelper {
	/** ----------------------------------------------------- Fields start */
	private final String basicErrorMessage = "从接口获取代理IP失败，请检查网络连接";
	private final String PUBLIC_API = "http://api.goubanjia.com/api/get.shtml?order=orderreplace&num=200&carrier=0&protocol=0&sort=1&system=1&distinct=0&rettype=1&seprator=;";
	private final String DYNAMIC_API = "http://dynamic.goubanjia.com/dynamic/get/orderreplace.html?ttl";
	public Map<String, Integer> proxyIpListMap = Maps.newConcurrentMap();

	/** 最大重连次数 */
	private int maxTryTime = 3;
	
	private boolean isDynamic = false;
	private boolean debug = true;

	/** ----------------------------------------------------- Fields end */

	public String proxyGetFromUrl(String url, boolean userProxy) throws Exception {
		return proxyGetFromUrl(url, 1, userProxy, ConstantHelper.UTF_8.name());
	}

	public String proxyGetFromUrl(String url, int tryTime, boolean userProxy, String charset)
			throws Exception {
		if (tryTime > maxTryTime) {
			throw new Exception("尝试了" + maxTryTime + "次抓取都失败，放弃抓取...");
		}
		System.out.println("尝试第 " + tryTime + " 次");
		return null;
	}

	public void removeIp(String ipPort) {
		if (StringUtils.isBlank(ipPort)) {
			return;
		}

		Integer depth = proxyIpListMap.get(ipPort);
		if (depth == null) {
			depth = Integer.valueOf(0);
		}
		if (depth.intValue() == 0) {
			System.out.println(("代理IP已经失效...删除, 剩余代理IP " + proxyIpListMap.size()));
		} else {
			if (depth.intValue() > 1000) {
				proxyIpListMap.clear();
			}
			System.out.println(("代理IP降权..., 重置权重为0 , 剩余代理IP " + proxyIpListMap.size()));
			proxyIpListMap.put(ipPort, Integer.valueOf(0));
		}
	}
	public String getProxy() {
		Object localObject;
		if(proxyIpListMap == null || proxyIpListMap.size() ==0 || isDynamic){
			try {
				Thread.sleep(1000L);
				String ip;
				if(isDynamic){
					
				}
			} catch (Exception e) {
				
			}
		}
		return null;
	}
}
