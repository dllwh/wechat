package com.cdeledu.util.openplatform.baidu.baiduAI.speech;

import java.net.URLEncoder;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 百度语音-语音合成API
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午11:10:24
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="https://cloud.baidu.com/doc/SPEECH/TTS-Online-Java-SDK.html">文档
 *      -简介</a>
 */
public class BaiduSpeechHelper {
	/** ----------------------------------------------------- Fields start */
	/** 语音合成:请求地址 */
	public String TEXT2AUDIO_URL = "http://tsn.baidu.com/text2audio";

	/** ----------------------------------------------------- Fields end */
	public static void main(String[] args) {
		// 初始化一个AipSpeech
		AipSpeech client = new AipSpeech("6740700", "Bz1g0IKgxn9jNM4O071QcqPK",
				"p2zYUHtkfywcKgMmWyRMWD20NGjTfbnj");

		// 可选：建立连接的超时时间（单位：毫秒）
		client.setConnectionTimeoutInMillis(2000);
		// 可选：通过打开的连接传输数据的超时时间（单位：毫秒）
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址
		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理
		TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
		System.out.println(res.getResult());
	}

	/**
	 * @方法: JSON方式上传 -- 所有参数方法
	 * @创建人:独泪了无痕
	 * @param tex
	 *            必填 合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
	 * @param lang
	 *            必填 语言选择,填写zh
	 * @param tok
	 *            必填 开放平台获取到的开发者access_token
	 * @param ctp
	 *            必填 客户端类型选择，web端填写1
	 * @param cuid
	 *            必填 用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
	 * @param spd
	 *            选填 语速，取值0-9，默认为5中语速
	 * @param pit
	 *            选填 音调，取值0-9，默认为5中语调
	 * @param vol
	 *            选填 音量，取值0-9，默认为5中音量
	 * @param per
	 *            选填 发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
	 * @throws Exception
	 */
	public void text2Audio(String tex, String tok, String ctp, String cuid, String spd, String pit,
			String vol, String per) throws Exception {
		String params = "tex=" + URLEncoder.encode(tex, "UTF-8") + "&lan=zh&cuid=" + cuid
				+ "&ctp=1&tok=" + tok + "&spd=" + spd + "&pit=" + pit + "&vol=" + vol + "&per="
				+ per;
		postVoice(TEXT2AUDIO_URL, params);
	}

	/**
	 * @方法:JSON方式上传 -- 必填参数方法
	 * @创建人:独泪了无痕
	 * @param tex
	 *            必填 合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
	 * @param lan
	 *            必填 语言选择,填写zh
	 * @param tok
	 *            必填 开放平台获取到的开发者access_token
	 * @param ctp
	 *            必填 客户端类型选择，web端填写1
	 * @param cuid
	 *            必填 用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
	 * @throws Exception
	 */
	public void text2Audio(String tex, String tok, int ctp, String cuid) throws Exception {
		String params = "tex=" + URLEncoder.encode(tex, "UTF-8") + "&lan=zh&cuid=" + cuid
				+ "&ctp=1&tok=" + tok;
		postVoice(TEXT2AUDIO_URL, params);
	}

	/**
	 * @方法:语音合成HTTP方法
	 * @创建人:独泪了无痕
	 * @param requestUrl
	 *            请求的接口地址 拼接access_token后的
	 * @param params
	 *            语音合成的参数
	 * @return
	 * @throws Exception
	 */
	public static String postVoice(String requestUrl, String params) throws Exception {
		String result = "";
		System.out.println(params);
		// 这里不需要对接口地址拼接access_token参数 切记！！！
		String generalUrl = requestUrl;
		System.out.println("发送的连接为:" + generalUrl);
		return result;
	}
}
