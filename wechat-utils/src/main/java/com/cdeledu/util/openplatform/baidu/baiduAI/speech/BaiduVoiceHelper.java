package com.cdeledu.util.openplatform.baidu.baiduAI.speech;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 百度语音-语音识别API
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午11:10:24
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="https://cloud.baidu.com/doc/SPEECH/ASR-API.html">文档-简介</a>
 */
public class BaiduVoiceHelper {
	/** ----------------------------------------------------- Fields start */
	public String TEXT2AUDIO_URL = "http://vop.baidu.com/server_api";
	/**
	 * <pre>
	 * 	第一步下载语音合成的SDK 
	 * 	第二步导入项目SDK，并初始化一个AipSpeech对象 
	 * 	第三步 调用合成的方法，并设置一些参数进行文字合成语音
	 * 	第四步 合成的二进制数据存入文件中
	 * </pre>
	 */
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:MP3转换PCM文件方法
	 * @创建人:独泪了无痕
	 * @param mp3filepath
	 *            原始文件路径
	 * @param pcmfilepath
	 *            转换文件的保存路径
	 * @throws Exception
	 */
	public static void mp3Convertpcm(String mp3filepath, String pcmfilepath) throws Exception {

	}

	/**
	 * @方法:获取语音识别内容
	 * @创建人:独泪了无痕
	 * @param requestUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postASR(String requestUrl, String params) throws Exception {
		String result = "";
		System.out.println(params);
		// 这里不需要对接口地址拼接access_token参数 切记！！！
		String generalUrl = requestUrl;
		System.out.println("发送的连接为:" + generalUrl);
		return result;
	}
}
