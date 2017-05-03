package com.cdeledu.crawler.other.crawlerMail;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;

/**
 * @类描述: 获得物流单号的跟踪信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月19日 下午8:54:04
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CrawlerMailHelper {
	/** ----------------------------------------------------- Fields start */
	private static final String baseUrl = "http://www.kuaidi100.com/";
	// 查询的快递公司代码
	private static String getComCodeUrl = baseUrl + "autonumber/autoComNum?text=%s";
	// 获得物流单号的跟踪信息
	private static String getComInfoUrl = baseUrl
			+ "query?type=%s&postid=%s&show=1&muti=%s&order=%s";
	// 快递单当前的状态
	private static Map<Integer, Object> statuMap = new HashMap<Integer, Object>();
	// 查询结果状态
	private static Map<Integer, Object> statusMap = new HashMap<Integer, Object>();

	static {
		statuMap.put(0, "在途:即货物处于运输过程中");
		statuMap.put(1, "揽件:货物已由快递公司揽收");
		statuMap.put(2, "疑难:货物寄送过程出了问题");
		statuMap.put(3, "签收:收件人已签收");
		statuMap.put(4, "退签:货物由于用户拒签、超区等原因退回，而且发件人已经签收");
		statuMap.put(5, "派件:快递正在进行同城派件");
		statuMap.put(6, "退回:货物正处于退回发件人的途中");
		statusMap.put(0, "物流单暂无结果");
		statusMap.put(1, "查询成功");
		statusMap.put(2, "接口出现异常");
		statusMap.put(200, "查询成功");
	}
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 快递接口
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年4月27日 下午4:30:18
	 * @param type
	 *            快递公司代号
	 *            <ul>
	 *            <li>申通:shentong;EMS:ems;顺丰:shunfeng;圆通:yuantong;</li>
	 *            <li>中通:zhongtong;韵达:yunda;天天:tiantian;</li>
	 *            <li>汇通:huitongkuaidi;全峰:quanfengkuaidi</li>
	 *            <li>德邦:debangwuliu;宅急送:zhaijisong</li>
	 *            </ul>
	 * @param postid
	 *            快递单号
	 * @param show
	 *            返回类型:默认返回json字符串
	 *            <ul>
	 *            <li>0:返回json字符串</li>
	 *            <li>1:返回xml对象</li>
	 *            <li>2:返回html对象</li>
	 *            <li>3:返回text文本</li>
	 *            </ul>
	 * @param muti
	 *            返回信息数量:默认返回多行
	 *            <ul>
	 *            <li>1:返回多行完整的信息</li>
	 *            <li>0:只返回一行信息</li>
	 *            </ul>
	 * @param order
	 *            排序:默认是0
	 *            <ul>
	 *            <li>0:desc：按时间由新到旧排列</li>
	 *            <li>1:asc：按时间由旧到新排列</li>
	 *            </ul>
	 * @return
	 */
	public static String getComInfo(String type, String postid, int muti, int order) {
		String result = "";
		if (muti != 0 || muti != 1) {
			muti = 1;
		}
		if (order != 0 || order != 1) {
			order = 0;
		}
		String orderBy = "desc";
		if (order != 0) {
			orderBy = "asc";
		}
		if (StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(postid)) {
			String url = String.format(getComInfoUrl, type, postid, muti, orderBy);
			try {
				result = HttpURLConnHelper.sendGetRequest(url,ConstantHelper.UTF_8);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * @方法:获得物流单号的快递公司代码
	 * @创建人:独泪了无痕
	 * @param postid
	 *            快递单号
	 * @return
	 */
	public static String getComCode(String postid) {
		int state = 0;
		String comCode = "";
		try {
			String url = String.format(getComCodeUrl, postid);
			String queryResult = HttpURLConnHelper.sendGetRequest(url,ConstantHelper.UTF_8);
			JSONObject json = JSONObject.parseObject(queryResult);
			String auyo = json.getString("auto");
			if (StringUtils.isNotBlank(auyo)) {
				JSONArray _array = JSONArray.parseArray(auyo);
				if (null != _array) {
					JSONObject _json = JSONObject.parseObject(String.valueOf(_array.get(0)));
					if (null != _json && _json.containsKey("comCode")) {
						comCode = _json.getString("comCode");
						state = 1;
					}
				}
			}
		} catch (Exception e) {

		}
		return "{\"state\":" + state + ",\"comCode\":\"" + comCode + "\"}";
	}
}
