package com.cdeledu.util.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.common.property.SystemHelper;
import com.cdeledu.util.appConfig.ConfigUtil;
import com.cdeledu.util.application.regex.RegexUtil;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;

import net.sf.json.JSONObject;

/**
 * @title : IpUtilHelper
 * @author : 独泪了无痕
 * @方法描述 :IP地址工具类
 */
public class IpUtilHelper {
	/*--------------------------私有方法 start-------------------------------*/
	private static Logger log = Logger.getLogger(IpUtilHelper.class);
	/** 新浪Ip查询接口 */
	private final static String SINA_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php";
	/** 淘宝Ip查询接口 */
	private static String TAO_BAO_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=%s";
	/** API调试工具--接口地址 */
	private static String IP_LOOKUP_SERVICE = "http://apis.baidu.com/apistore/iplookupservice/iplookup?ip=%s";
	private static Map<String, String> headerMap = new HashMap<String, String>();

	static {
		headerMap.put("apikey", ConfigUtil.getApiStoreAkValue());
	}

	/*--------------------------私有方法 end-------------------------------*/
	/*--------------------------公有方法 start-------------------------------*/
	/**
	 * @方法描述: 获取客户端请求ip地址
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月12日 上午9:59:06
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = null;
		/**
		 * Cdn-Src-Ip : 网宿cdn的真实ip HTTP_CLIENT_IP : 蓝讯cdn的真实ip X-Forwarded-For :
		 * 获取代理ip Proxy-Client-IP : 获取代理ip WL-Proxy-Client-IP : 获取代理ip
		 */
		if (null != request) {
			String proxs[] = { "Cdn-Src-Ip", "X-Forwarded-For", "Proxy-Client-IP",
					"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "x-real-ip" };
			for (String prox : proxs) {
				ip = request.getHeader(prox);
				if (StringUtils.isBlank(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					continue;
				} else {
					break;
				}
			}

			if (StringUtils.isBlank(ip)) {
				ip = request.getRemoteAddr();// 获取真实ip
			}
			if (ip.equals("0:0:0:0:0:0:0:1")) {
				try {
					ip = getRealIp();
				} catch (Exception e) {
					ip = "127.0.0.1";
				}
			}

			// 如果通过多级反向代理检测的话,其值并不止一个，而是一串IP值,取第一个非unknown的有效IP字符串
			if (ip != null && ip.split(",").length > 1) {
				ip = ip.split(",")[0];
			}
		}
		return ip;
	}

	/**
	 * 
	 * @Title：getRealIp
	 * @Description：本机IP
	 * @return
	 * @throws SocketException
	 * @return：String 返回类型
	 */
	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

		InetAddress ip;
		boolean finded = false;// 是否找到外网IP

		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	/**
	 * 
	 * @Title：getIpInfoBySinaUrl
	 * @Description：新浪接口(IP值为空时获取本地) @param ip
	 * @return
	 * 		<ul>
	 *         <li>ret : 有且仅当tet的值是1才有效</li>
	 *         <li>country: 国家</li>
	 *         <li>province: 省份</li>
	 *         <li>city: 城市</li>
	 *         <li>district: 区域</li>
	 *         <li>ISP: 运营商</li>
	 *         <li>type: 类型</li>
	 *         <li>desc : 其他</li>
	 *         </ul>
	 * @return：String 返回类型
	 */
	public static String getIpInfoBySinaUrl(String ip) throws Exception {
		if (RegexUtil.isIp(ip)) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("format", "json");
			paramsMap.put("ip", ip);

			String url = UrlHelper.formatParameters(SINA_URL, paramsMap);
			HttpURLConnHelper conn = HttpURLConnHelper.getInstance();
			return conn.sendGetRequest(url);
		}
		return "";
	}

	/**
	 * 
	 * @Title：getIpInfoByTaoBaoUrl
	 * @Description：淘宝ip查询接口
	 * @param ip
	 * @return
	 * 		<ul>
	 *         <li>code : 0：成功，1：失败</li>
	 *         <li>country: 国家</li>
	 *         <li>area: 区域</li>
	 *         <li>region: 省份</li>
	 *         <li>city: 城市</li>
	 *         <li>region: 县</li>
	 *         <li>ISP: 运营商</li>
	 *         <li>type: 类型</li>
	 *         </ul>
	 * @return：String 返回类型
	 */
	public static String getIpInfoByTaoBaoUrl(String ip) throws Exception {
		String url = String.format(TAO_BAO_URL, ip);
		return HttpURLConnHelper.getInstance().sendGetRequest(url, null);
	}

	/**
	 * 
	 * @Title：iplookup
	 * @Description：IP地址查询接口
	 * @param ip
	 * @return 根据提供ip地址就能得到IP相关的信息
	 * @return：JSONObject 返回类型
	 */
	public static JSONObject iplookup(String ip) throws Exception {
		String url = String.format(IP_LOOKUP_SERVICE, ip);
		HttpURLConnHelper conn = HttpURLConnHelper.getInstance(ConstantHelper.UTF_8.name());
		String result = conn.sendGetRequest(url, headerMap);
		// 转化为JSON类
		JSONObject json = JSONObject.fromObject(result);

		// 得到错误码并判断
		if (json.getInt("errNum") == 0) {
			// 根据需要取得数据
			return json.getJSONObject("retData");
		}
		return null;
	}

	/**
	 * @方法描述: 检测 ip地址 是否是 通的 ，即 Ping 操作
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月12日 上午11:03:51
	 * @param ip
	 * @return
	 */
	public static final boolean isPing(String ip) {
		boolean status = false;
		if (StringUtils.isNotBlank(ip)) {
			Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
			Process process = null; // 声明处理类对象
			String line = null; // 返回行信息
			InputStreamReader isr = null; // 字节流
			BufferedReader br = null;
			try {
				process = runtime.exec("ping " + ip); // PING
				isr = new InputStreamReader(process.getInputStream());// 把输入流转换成字节流
				br = new BufferedReader(isr);// 从字节中读取文本
				while ((line = br.readLine()) != null) {
					if (line.contains("TTL")) {
						status = true;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				runtime.exit(1);
			} finally {
				IOUtils.closeQuietly(br);
				IOUtils.closeQuietly(isr);
			}
		}
		return status;
	}

	/**
	 * 
	 * @方法描述: 获取IP地址
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月12日 上午11:29:38
	 * @param ip
	 * @return
	 */
	public static final String getHostAddress(String ip) {
		String hostAddress = null;
		if (StringUtils.isNotBlank(ip)) {
			try {
				hostAddress = InetAddress.getByName(ip).getHostAddress();
			} catch (Exception e) {

			}
		}
		return hostAddress;
	}

	/**
	 * @方法描述: 获取IP库中的IP
	 * @return
	 */
	public static Map<String, Object> getProxyIp() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String proxyIp = "";
		String proxyPort = "";
		String userName = "";
		String password = "";
		try {
			List<String> ipList = Lists.newArrayList();
			BufferedReader proxyIpReader = new BufferedReader(new InputStreamReader(
					IpUtilHelper.class.getResourceAsStream("/ip/proxyip.txt")));
			String ip;
			while ((ip = proxyIpReader.readLine()) != null) {
				ipList.add(ip);
			}

			Random random = new Random();
			int randomInt = random.nextInt(ipList.size());

			List<String> resultList = Arrays.asList(ipList.get(randomInt).split(":"));
			proxyIp = resultList.get(0);
			proxyPort = resultList.get(1);
			if (resultList.size() == 4) {
				userName = resultList.get(2);
				password = resultList.get(3);
			}
			resultMap.put("proxyIp", proxyIp);
			resultMap.put("proxyPort", proxyPort);
			resultMap.put("userName", userName);
			resultMap.put("password", password);
			return resultMap;
		} catch (Exception ipEx) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error("重新设置代理ip", ipEx);
			}
			return getProxyIp();
		}

	}

	/**
	 * 
	 * @方法描述: 设置代理IP:设置于http请求中
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月16日 下午8:21:00
	 */
	public static void setProxyIp() {
		Map<String, Object> ipMap = getProxyIp();

		String proxyIp = String.valueOf(ipMap.get("proxyIp"));
		String proxyPort = String.valueOf(ipMap.get("proxyPort"));

		System.setProperty("http.maxRedirects", "50");
		SystemHelper.setHttpProxy(proxyIp, proxyPort);
	}

	/**
	 * @方法描述: 在线查询IP工具
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月9日 下午4:58:57
	 * @param ip
	 * @param code
	 *            :查询结果 0:成功;
	 * @param country
	 *            :国家
	 * @param country_id
	 *            :国家代码
	 * @param area
	 *            :地区
	 * @param area_id
	 *            :地区代码
	 * @param region
	 *            :省
	 * @param region_id
	 *            :省代码
	 * @param city
	 *            : 城市
	 * @param city_id
	 *            :城市代码
	 * @param county
	 *            :县
	 * @param county_id
	 *            :县代码
	 * @param isp
	 *            :isp
	 * @param isp_id:isp代码
	 * @param ip
	 * @return
	 */
	public static final String getIpInfo(String ip) {
		String url = "http://www.bejson.com/api/bejson/ipinfo.php?ip=%s";
		String result = "";
		try {
			HttpURLConnHelper conn = HttpURLConnHelper.getInstance(ConstantHelper.UTF_8.name());
			result = conn.sendGetRequest(String.format(url, ip));
		} catch (Exception e) {
		}
		return result;
	}
	/** --------------------------公有方法 end------------------------------- */
}
