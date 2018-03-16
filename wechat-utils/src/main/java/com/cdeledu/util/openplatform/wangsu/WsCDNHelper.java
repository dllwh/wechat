package com.cdeledu.util.openplatform.wangsu;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cdeledu.common.browser.UserAgentType;
import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.httpEntity.HttpHeaders;
import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.security.SecureUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 网宿CDN视频帮助类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年3月15日 上午9:39:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WsCDNHelper {
	/** ----------------------------------------------------- Fields start */
	private static HttpClient httpClient = HttpClients.createDefault();

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述 : 创建导播实例 API
	 * @param instanceName
	 *            实例名称
	 * @param inputSize
	 *            最大输入流个数 ,受限于用户账号的 inputLimit
	 * @param endTime
	 *            结束时间 13 位 Unix 时间戳
	 * @param portalId
	 *            用户名
	 * @param secretkey
	 * @param defaultOut
	 *            默认推流地址，用于展示导播画面
	 * @param callBackUrl
	 *            错误回调地址
	 * @return
	 * @throws Exception
	 */
	public static String createDirectInstance(String instanceName, int inputSize, Long endTime,
			String portalId, String secretKey, String defaultOut, String callBackUrl)
			throws Exception {
		long random = System.currentTimeMillis();
		Map<String, Object> paramsMap = Maps.newHashMap();
		paramsMap.put("instanceName", instanceName);
		if (inputSize <= 0) {
			paramsMap.put("inputSize", CDNConstHelper.INPUTSIZE);
		} else {
			paramsMap.put("inputSize", inputSize);
		}
		if (endTime == null || endTime <= random) {
			paramsMap.put("endTime", DateUtilHelper.todayLastDate());
		} else {
			paramsMap.put("endTime", endTime);
		}

		paramsMap.put("userId", portalId);
		paramsMap.put("random", random);
		paramsMap.put("defaultOut", defaultOut);

		if (StringUtils.isNotBlank(callBackUrl)) {
			paramsMap.put("callBackUrl", callBackUrl);
		} else {
			paramsMap.put("callBackUrl", CDNConstHelper.CALLBACK_URL);
		}
		paramsMap.put("sign",
				SecureUtil.encrypt(portalId + secretKey + random, null, SecureUtil.MD5, null, 32));
		return sendGetRequest(
				CDNConstHelper.CREATE_URL + "?" + UrlHelper.formatParameters(paramsMap));
	}

	/**
	 * @方法描述 : 导播控制之单音画模式 API—音画同步
	 * @param instanceId
	 * @param token
	 * @param videoIndex
	 *            需要显示的输入源顺序，从 1 开始
	 * @param audio
	 *            音量大小 0-100
	 * @throws Exception
	 */
	public static String singleMode(String instanceId, String token, int videoIndex, int audio)
			throws Exception {
		if (videoIndex < 1 || videoIndex > CDNConstHelper.INPUTSIZE) {
			videoIndex = 1;
		}
		if (audio < 0 || audio > 100) {
			audio = 100;
		}
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);

		String targetUrl = String.format(CDNConstHelper.SINGLE_URL, instanceId, videoIndex, audio);
		return getControlInterfaceReturn(sendPostRequest(targetUrl, headersMap, null));
	}

	/**
	 * @方法描述 : 导播控制之混合音画模式 API—音画不同步
	 * @param instanceId
	 * @param token
	 * @param videoIndex
	 *            混合音画的输入源
	 * @param audios
	 *            音量大小 0-100，逗号分隔,数量与输入源个数相同
	 * @throws Exception
	 */
	public static String mingleMode(String instanceId, String token, String[] videoIndex,
			String[] audios) throws Exception {
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
		String targetUrl = String.format(CDNConstHelper.MINGLE_URL, instanceId);
		List<NameValuePair> nvps = Lists.newArrayList();
		nvps.add(new BasicNameValuePair("videoIndex", StringUtils.join(videoIndex, ",")));
		nvps.add(new BasicNameValuePair("audios", StringUtils.join(audios, ",")));
		return sendPostRequest(targetUrl, headersMap, nvps);
	}

	/**
	 * @方法描述 : 导播控制之会议模式 API
	 * @param instanceId
	 * @param token
	 * @throws Exception
	 */
	public static String meetingMode(String instanceId, String token, String[] videoIndex,
			String[] audios) throws Exception {
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
		String targetUrl = String.format(CDNConstHelper.MEETING_URL, instanceId);
		List<NameValuePair> nvps = Lists.newArrayList();
		nvps.add(new BasicNameValuePair("videoIndex", StringUtils.join(videoIndex, ",")));
		nvps.add(new BasicNameValuePair("audios", StringUtils.join(audios, ",")));
		return sendPostRequest(targetUrl, headersMap, nvps);
	}

	/**
	 * @方法描述 : 导播控制之输入配置 API
	 * @param instanceId
	 *            实例 id
	 * @param index
	 *            输入源的顺序
	 * @param type
	 *            视频类别: 0 直播 1 点播,默认是0
	 * @param pullPath
	 *            拉流地址
	 * @param bufferTime
	 *            缓冲时间,秒数，默认是0。直播流设置缓冲时间为 0,点播文件根据需要设置 1~2S 的缓冲时间
	 * @param maxbufferTime
	 *            最大缓冲时间,秒数,默认是0
	 * @throws Exception
	 */
	public static String inPutConfig(String instanceId, String token, int index, int type,
			String pullPath, int bufferTime, int maxbufferTime) throws Exception {
		String targetUrl = String.format(CDNConstHelper.INPUT_URL, instanceId, index);
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
		if (type < 0) {
			type = 0;
		}
		if (bufferTime < 0) {
			bufferTime = 0;
		}
		if (maxbufferTime < 0) {
			maxbufferTime = 0;
		}

		if (bufferTime > maxbufferTime) {
			maxbufferTime = bufferTime;
			bufferTime = 0;
		}

		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("buffertime", bufferTime);
		paramMap.put("maxbuffertime", maxbufferTime);
		paramMap.put("type", type);
		paramMap.put("url", pullPath);

		String content = JsonMapper.toJsonString(paramMap);
		return getControlInterfaceReturn(sendPostRequestByJsonData(targetUrl, headersMap, content));
	}

	/**
	 * @方法描述 : 导播控制之输出配置 API
	 * @param instanceId
	 *            当前实例 ID
	 * @param token
	 *            当前实例 Token
	 * @param cbr
	 *            是否开启固定码率推,0 关闭 1 开启
	 * @param fps
	 *            帧率
	 * @param qualityvalue
	 *            提供超清、高清、标清标准参数供选择，如果有特殊需求可以选择“自定义”对分辨率、码率、帧率参数进行设置
	 * @param rate
	 *            码率 Kbps
	 * @param size_width
	 *            分辨率-宽
	 * @param size_height
	 *            分辨率-高
	 * @param urlList
	 *            输出源 url 列表
	 *            <ul>
	 *            <li>可以添加多条推流 URL，推流到不同地址</li>
	 *            <li>最多 5 条推流URL,如果超过5条则只取前面5条推流URL</li>
	 *            </ul>
	 * @throws Exception
	 */
	public static String outPutConfig(String instanceId, String token, int cbr, int fps,
			String qualityvalue, int rate, int width, int height, String[] urlList)
			throws Exception {
		String targetUrl = String.format(CDNConstHelper.OUTPUT_URL, instanceId);
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("cbr", cbr);
		paramMap.put("fps", fps);
		if (StringUtils.isNotBlank(qualityvalue)) {
			paramMap.put("qualityvalue", qualityvalue);
		} else {
			paramMap.put("qualityvalue", "custom");
		}
		String[] realUrlList = new String[] {};
		paramMap.put("rate", rate);
		paramMap.put("size", String.valueOf(width + "x" + height).toLowerCase());
		if (urlList.length < 0) {
			if (urlList.length <= 5) {
				paramMap.put("urlList", urlList);
			} else {
				for (int i = 0; i < urlList.length; i++) {
					realUrlList[i] = urlList[i];
					if (i > 4) {
						break;
					}
				}
			}
		} else {
			paramMap.put("urlList", "");
		}

		String content = JsonMapper.toJsonString(paramMap);
		return getControlInterfaceReturn(sendPostRequestByJsonData(targetUrl, headersMap, content));
	}

	/**
	 * @方法描述 : 停止导播 API
	 * @param instanceId
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static String stopDirectInstance(String instanceId, String token) throws Exception {
		String url = String.format(CDNConstHelper.STOP_URL, instanceId);
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
		return getControlInterfaceReturn(sendPostRequestByJsonData(url, headersMap, null));
	}

	/**
	 * @方法描述 : 延时导播 API
	 * @param instanceId
	 * @param token
	 * @param delayTime
	 *            毫秒数,最低延时10
	 * @return
	 * @throws Exception
	 */
	public static String delayDirectInstance(String instanceId, String token, int delayTime)
			throws Exception {
		String url = String.format(CDNConstHelper.DELAY_URL, instanceId);
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		if (delayTime < 10 * 60 * 1000) {
			delayTime = 10 * 60 * 1000;
		}
		paramMap.put("delayTime", delayTime);

		String content = JsonMapper.toJsonString(paramMap);
		return getControlInterfaceReturn(sendPostRequestByJsonData(url, headersMap, content));
	}

	/**
	 * @方法描述 : 弹幕（暂时未实现）
	 * @param instanceId
	 * @param token
	 * @param mode
	 *            模式-弹幕、字幕、滚动弹幕
	 *            <ul>
	 *            <li>1、弹幕模式下，从画面中自右向左滚动显示。</li>
	 *            <li>2、字幕模式下，输入内容不会滚动，但是会根据设置停留在画面中形成类似字幕的效果。</li>
	 *            <li>3、滚动弹幕模式，可以设置弹幕循环显示的次数，弹幕会根据循环次数在画面中循环滚动，循环次数选择无限时，
	 *            发送的弹幕会循环至导播实例结束。</li>
	 *            </ul>
	 * @param position
	 *            位置可选择顶部和底部
	 * @param showTime
	 *            留存时间
	 * @param fontSize
	 *            字号可以更改 大 中 小三种
	 * @param fontColor
	 *            颜色
	 * @param isBold
	 *            字体是否加粗
	 */

	public static void setBarrage(String instanceId, String token, int mode, int position,
			int showTime, int fontSize, int fontColor, boolean isBold) {
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);
	}

	/**
	 * @方法描述 : 云导播设置水印功能（暂时未实现）
	 * @param instanceId
	 * @param token
	 * @param markName
	 *            水印名称
	 * @param url
	 *            水印 URL，目前仅支持jpg/png格式
	 * @param position
	 *            水印位置 左上、上、右上、左、中、右、左下、下、右下
	 * @param transparency
	 *            水印透明度
	 * @param scaling
	 *            水印图片缩放比例
	 * @param adaptation
	 *            自适应
	 */
	public static void setwatermark(String instanceId, String token, String markName, String url,
			String position, int transparency, int scaling, boolean adaptation) {
		Map<String, Object> headersMap = Maps.newHashMap();
		headersMap.put("X-Direct-InstanceId", instanceId);
		headersMap.put("X-Direct-Token", token);

		if (transparency > 100 || transparency < 0) {
			transparency = 0;
		}
		if (scaling > 100 || scaling < 0) {
			scaling = 100;
		}
	}

	/** ----------------------------------------------------- Private Method */
	private static String sendGetRequest(String targetUrl) throws Exception {
		HttpGet httpGet = new HttpGet(targetUrl);
		httpGet.addHeader(HttpHeaders.CONNECTION, "keep-alive");
		httpGet.addHeader(HttpHeaders.ACCEPT, "*/*");
		httpGet.addHeader(HttpHeaders.CONTENT_TYPE,
				"application/x-www-form-urlencoded;charset=UTF-8");
		httpGet.addHeader("X-Requested-With", "XMLHttpRequest");
		httpGet.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
		httpGet.addHeader(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());

		HttpResponse response = httpClient.execute(httpGet);
		return getHttpResponseContent(response);
	}

	private static String sendPostRequest(String targetUrl, Map<String, Object> headersMap,
			List<NameValuePair> nvps) throws Exception {
		HttpPost httpPost = new HttpPost(targetUrl);
		httpPost.addHeader(HttpHeaders.CONNECTION, "keep-alive");
		httpPost.addHeader(HttpHeaders.ACCEPT, "*/*");
		httpPost.addHeader(HttpHeaders.CONTENT_TYPE,
				"application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
		httpPost.addHeader(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());
		if (MapUtils.isNotEmpty(headersMap)) {
			for (String key : headersMap.keySet()) {
				Object hKey = null == headersMap.get(key) ? "" : headersMap.get(key);
				httpPost.setHeader(key, String.valueOf(hKey));
			}
		}

		// nvps是包装请求参数的list
		if (CollectionUtils.isNotEmpty(nvps)) {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ConstantHelper.UTF_8.name()));
		}

		return getHttpResponseContent(httpClient.execute(httpPost));
	}

	private static String sendPostRequestByJsonData(String targetUrl,
			Map<String, Object> headersMap, String content) throws Exception {
		HttpPost httpPost = new HttpPost(targetUrl);
		httpPost.addHeader(HttpHeaders.CONNECTION, "keep-alive");
		httpPost.addHeader(HttpHeaders.ACCEPT, "*/*");
		httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
		httpPost.addHeader(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());

		if (MapUtils.isNotEmpty(headersMap)) {
			for (String key : headersMap.keySet()) {
				Object hKey = null == headersMap.get(key) ? "" : headersMap.get(key);
				httpPost.setHeader(key, String.valueOf(hKey));
			}
		}

		if (StringUtils.isNoneBlank(content)) {
			httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
		}
		return getHttpResponseContent(httpClient.execute(httpPost));
	}

	private static String getControlInterfaceReturn(String result) {
		JSONObject jsResult = JSONObject.fromObject(result);
		Map<String, Object> resultMap = Maps.newHashMap();
		int resultCode = 200;
		String msg = "成功";
		if (StringUtils.isNoneBlank(result)) {
			if (jsResult.has("code") && jsResult.getInt("code") == 1) {

			} else {
				resultCode = jsResult.getInt("code");
				msg = jsResult.getString("msg");
			}
		} else {
			resultCode = 900605;
			msg = "系统异常";
		}
		resultMap.put("resultCode", resultCode);
		resultMap.put("msg", msg);
		return JsonMapper.toJsonString(resultMap);
	}

	private static String getHttpResponseContent(HttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			return null;
		}
		String content = EntityUtils.toString(entity, ConstantHelper.UTF_8.name());
		EntityUtils.consume(entity);
		return content;
	}
}
