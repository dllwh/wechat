package com.cdeledu.util.openplatform.baidu.developer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.network.URLEncodingUtil;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.cdeledu.util.openplatform.baidu.developer.entity.BaiduMusic;
import com.cdeledu.util.openplatform.baidu.developer.entity.MusicUrl;

/**
 * 
 * @ClassName: BaiduMusicService
 * @Description: 百度音乐搜索API操作类<br>
 *               box.zhangmen.baidu.com/x?op=12&count=1&title=歌名$$歌手
 * @author: 独泪了无痕
 * @date: 2015年11月10日 上午8:12:44
 * @version: V2.0
 * @since: JDK 1.7
 */
public class BaiduMusicHelper {
	/** -------------------------- 属性 begin ------------------------------- */
	/** 百度音乐搜索地址 */
	public final static String MUSIC_URL = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=%s$$%s";
	/** 歌词的请求地址 */
	public final static String IRC_URL = "http://box.zhangmen.baidu.com/bdlrc/%s/%s.lrc";
	/** 编码格式 */
	private final static Charset CHARSER = ConstantHelper.UTF_8;
	private static HttpURLConnHelper conn = null;
	static {
		conn = HttpURLConnHelper.getInstance(CHARSER.name());
	}

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/**
	 * 
	 * @Title: parseMusic
	 * @Description: 解析音乐参数
	 * @author: 独泪了无痕
	 * @param content
	 *            百度音乐搜索API返回的结果
	 */
	private static BaiduMusic parseMusic(String content) {
		BaiduMusic result = null;
		InputStream inputStream = null;
		List<MusicUrl> musicUrlList = null;
		MusicUrl musicUrl = null;

		try {
			inputStream = new ByteArrayInputStream(content.getBytes("utf-8"));
			// 使用dom4j解析xml字符串
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// count表示搜索到的歌曲数
			String count = root.element("count").getText();

			result = new BaiduMusic();
			result.setCount(Integer.parseInt(count));

			// 当搜索到的歌曲数大于0时
			if (!"0".equals(count)) {

				/**
				 * 普通品质 <br>
				 * <url>中包含了普通品质的音乐链接
				 */
				@SuppressWarnings("unchecked")
				List<Element> urlList = root.elements("url");
				/**
				 * 高品质<br>
				 * <durl>中包含了高品质音乐的链接
				 */
				@SuppressWarnings("unchecked")
				List<Element> durlList = root.elements("durl");

				/**
				 * ① 普通品质的encode、decode<br>
				 * ② <encode>中包含了加密后的音乐链接，实际上只是对音乐名称进行了加密，<decode>中包含了解密后的音乐名称
				 * <br>
				 * ③ 普通品质音乐链接最终的URL是有<encode>跟<decode>拼接起来
				 */
				// 普通品质音乐的URL
				String url = "";
				// 普通品质的encode、decode
				String urlEncode = "";
				String urlDecode = "";
				// 高音质音乐的URL
				String durl = "";
				// 高品质的encode、decode
				String durlEncode = "";
				String durlDecode = "";

				int index = 0;
				int endindex = 0;

				musicUrlList = new ArrayList<MusicUrl>();

				for (int i = 0; i < Integer.parseInt(count); i++) {

					urlEncode = urlList.get(i).element("encode").getText();
					urlDecode = urlList.get(i).element("decode").getText();
					index = urlEncode.lastIndexOf("/") + 1;
					if (urlDecode.lastIndexOf("&") != -1) {
						endindex = urlDecode.lastIndexOf("&");
						url = urlEncode.substring(0, index) + urlDecode.substring(0, endindex);
					} else {
						url = urlEncode.substring(0, index) + urlDecode;
					}
					// 默认情况下，高音质音乐的URL 等于 普通品质音乐的URL
					durl = url;

					// 判断高品质节点是否存在
					Element durlElement = durlList.get(i).element("encode");
					if (null != durlElement) {
						durlEncode = durlList.get(i).element("encode").getText();
						durlDecode = durlList.get(i).element("decode").getText();
						index = durlEncode.lastIndexOf("/") + 1;
						// 高品质音乐的URL
						if (durlDecode.lastIndexOf("&") != -1) {
							endindex = durlDecode.lastIndexOf("&");
							durl = durlEncode.substring(0, index)
									+ durlDecode.substring(0, endindex);
						} else {
							durl = durlEncode.substring(0, index) + durlDecode;
						}
					}

					musicUrl = new MusicUrl();
					// 设置普通品质音乐链接
					musicUrl.setUrl(url);
					// 设置高品质音乐链接
					musicUrl.setDurl(durl);
					musicUrlList.add(musicUrl);
				}
				result.setGetMusicUrl(musicUrlList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return result;
	}

	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * @Title: searchMusic
	 * @Description: 查询音乐信息
	 * @author: 独泪了无痕
	 * @param titile
	 *            音乐名称
	 * @param musicAuthor
	 *            作者
	 * @return
	 */
	public static BaiduMusic searchMusic(String titile, String musicAuthor) throws Exception {
		// 对音乐名称、作者进行URL编码
		titile = URLEncodingUtil.encoding(titile, CHARSER);
		musicAuthor = URLEncodingUtil.encoding(musicAuthor, CHARSER);
		// 百度音乐搜索地址
		String requestUrl = String.format(MUSIC_URL, titile, musicAuthor);
		// 处理名称、作者中间的空格
		requestUrl = requestUrl.replaceAll("\\+", "%20");
		// 查询并获取返回结果
		String html = conn.sendGetRequest(requestUrl);
		// 从返回结果中解析出Music
		return parseMusic(html);
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
