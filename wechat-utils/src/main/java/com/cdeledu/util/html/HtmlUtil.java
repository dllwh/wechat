package com.cdeledu.util.html;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * @类描述: HTML工具类:用来过滤一些html边去，防止xss攻击等
 *       <ul>
 *       <li>HTML工具类:用来过滤一些html边去，防止xss攻击等</li>
 *       <li>Jsoup 是一款 Java 的HTML 解析器,可直接解析某个URL地址、HTML文本内容</li>
 *       <li></li>
 *       </ul>
 * @创建者: 独泪了无痕
 * @创建时间: 2015年9月10日 下午4:39:22
 * @版本: V1.1
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class HtmlUtil {
	public static final String RE_HTML_MARK = "(<.*?>)|(<[\\s]*?/.*?>)|(<.*?/[\\s]*?>)";
	public static final String RE_SCRIPT = "<[\\s]*?script[^>]*?>.*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	// private static final String TAG_SCRIPT_START = "<script";
	// private static final String TAG_SCRIPT_END = "</script>";

	// private static final String TAG_STYLE_START = "<style";
	// private static final String TAG_STYLE_END = "</style>";
	/**
	 * 
	 * @Title: HTMLDecode
	 * @Description: 将HTML规则的字符串转成正常编码的字符串
	 * @param htmlStr
	 *            包含转义符的HTML内容
	 * @return 转换后的字符串
	 */
	public static String htmlDecode(String htmlStr) {
		if (StringUtils.isBlank(htmlStr)) {
			return htmlStr;
		}
		return htmlStr.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&")
				.replace("&quot;", "\"").replace("&#39;", "'").replace("<br/>", "\n")
				.replace("&nbsp;", " ") // 替换空格
				.replace("&nbsp;&nbsp;", "\t");// 替换跳格
	}

	/**
	 * @方法:将过滤掉Html标签
	 * @创建人:独泪了无痕
	 * @param content
	 * @return
	 */
	public static String cleanHtmlTag(String content) {
		return content.replaceAll(RE_HTML_MARK, "");
	}

	/**
	 * @方法描述: 只有纯文本可以通过
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月23日 下午8:00:25
	 * @param html
	 * @return
	 */
	public static String getText(String html) {
		if (StringUtils.isBlank(html)) {
			return null;
		}
		return Jsoup.clean(html, Whitelist.none());
	}

	/**
	 * @方法描述: 以下标签可以通过 :b, em, i, strong, u. 纯文本
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月23日 下午8:00:43
	 * @param html
	 * @return
	 */
	public static String getSimpleHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return null;
		}
		return Jsoup.clean(html, Whitelist.simpleText());
	}

	/**
	 * @方法描述:
	 * 		<ul>
	 *        <li>以下标签可以通过</li>
	 *        <li>a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol,
	 *        p, pre, q,small, strike, strong, sub, sup, u, ul</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月23日 下午8:01:52
	 * @param html
	 * @return
	 */
	public static String getBasicHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return null;
		}
		return Jsoup.clean(html, Whitelist.basic());
	}

	/**
	 * @方法描述: 在basic基础上 增加图片通过
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月23日 下午8:02:34
	 * @param html
	 * @return
	 */
	public static String getBasicHtmlandimage(String html) {
		if (StringUtils.isBlank(html)) {
			return null;
		}
		return Jsoup.clean(html, Whitelist.basicWithImages());
	}

	/**
	 * @方法描述:
	 * 		<ul>
	 *        <li>以下标签可以通过</li>
	 *        <li>a, b, blockquote, br, caption, cite, code, col, colgroup, dd,
	 *        dl, dt, em,</li>
	 *        <li>h1, h2, h3, h4, h5, h6, i, img, li, ol, p, pre, q, small,
	 *        strike, strong,</li>
	 *        <li>sub, sup, table, tbody, td, tfoot, th, thead, tr, u, ul</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月23日 下午8:01:52
	 * @param html
	 * @return
	 */
	public static String getFullHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return null;
		}
		return Jsoup.clean(html, Whitelist.relaxed());
	}

	/**
	 * @方法描述: 只允许指定的html标签
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月23日 下午8:03:26
	 * @param html
	 * @param tags
	 * @return
	 */
	public static String clearTags(String html, String... tags) {
		Whitelist wl = new Whitelist();
		return Jsoup.clean(html, wl.addTags(tags));
	}
}
