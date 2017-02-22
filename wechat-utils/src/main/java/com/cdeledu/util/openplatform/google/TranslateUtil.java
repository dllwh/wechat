package com.cdeledu.util.openplatform.google;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @类描述: google translate
 * @创建者: 独泪了无痕
 * @创建日期: 2014年10月2日 上午12:25:37
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class TranslateUtil {

	protected static final String URL_TEMPLATE = "http://translate.google.cn/?langpair={0}&text={1}";
	protected static final String ID_RESULTBOX = "result_box";
	protected static final String ENCODING = "UTF-8";

	protected static final String AUTO = "auto"; // google自動判斷來源語系
	protected static final String TAIWAN = "zh-TW"; // 繁中
	protected static final String CHINA = "zh-CN"; // 簡中
	protected static final String ENGLISH = "en"; // 英
	protected static final String JAPAN = "ja"; // 日语
	protected static final String Korean = "ko"; // 日语
	protected static final String Grerman = "de"; // 德语
	protected static final String Russian = "ru"; // 俄语
	protected static final String French = "fr"; // 法语
	protected static final String Arabic = "ar"; // 阿拉伯语
	protected static final String Bulgrian = "bg"; // 保加利亚语
	protected static final String Poland = "pl"; // 波兰语
	protected static final String NetherLands = "pl"; // 荷兰语
	protected static final String Italy = "it"; // 意大利语

	/**
	 * <pre>
	 * Google翻译 
	 * PS: 交由google自動判斷來源語系
	 * </pre>
	 * 
	 * @param text
	 * @param target_lang
	 *            ：目标语系
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text, final String target_lang) throws Exception {
		return translate(text, AUTO, target_lang);
	}

	/**
	 * 
	 * @Title translate
	 * 
	 * 		@方法描述：
	 * 
	 *        <pre>
	 *        Google翻译
	 *        </pre>
	 * 
	 * @param text
	 * @param src_lang
	 *            ：来源语系
	 * @param target_lang
	 *            ：目标语系
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text, final String src_lang, final String target_lang)
			throws Exception {
		InputStream is = null;
		Document doc = null;
		Element ele = null;
		try {
			// create URL string
			String url = MessageFormat.format(URL_TEMPLATE, URLEncoder.encode(src_lang + "|" + target_lang, ENCODING),
					URLEncoder.encode(text, ENCODING));

			// connect & download html
			is = HttpClientUtil.downloadAsStream(url);

			// parse html by Jsoup
			doc = Jsoup.parse(is, ENCODING, "");
			ele = doc.getElementById(ID_RESULTBOX);
			String result = ele.text();
			return result;

		} finally {
			IOUtils.closeQuietly(is);
			is = null;
			doc = null;
			ele = null;
		}
	}

	/**
	 * 
	 * @Title cn2tw
	 * 
	 * 		@方法描述：
	 * 
	 *        <pre>
	 * Google翻译: 中文简体-->中文繁体
	 *        </pre>
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String zh_CNTOzh_TW(final String text) throws Exception {
		return translate(text, CHINA, TAIWAN);
	}

	/**
	 * 
	 * @Title tw2cn
	 * 
	 * 		@方法描述：
	 * 
	 *        <pre>
	 * Google翻译: 中文繁体-->中文简体
	 *        </pre>
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String zh_TWTOzh_CN(final String text) throws Exception {
		return translate(text, TAIWAN, CHINA);
	}

	/**
	 * 
	 * @Title EnTOzh_TW
	 * 
	 * 		@方法描述：
	 * 
	 *        <pre>
	 * Google翻譯: 英文-->中文繁体
	 *        </pre>
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String EnTOzh_TW(final String text) throws Exception {
		return translate(text, ENGLISH, TAIWAN);
	}

	/**
	 * 
	 * @Title zh_TWToEn
	 * 
	 * 		@方法描述：
	 * 
	 *        <pre>
	 * Google翻譯: 繁中-->英文
	 *        </pre>
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String zh_TWToEn(final String text) throws Exception {
		return translate(text, TAIWAN, ENGLISH);
	}

	public static void main(String[] args) {
		try {
			System.out.println(TranslateUtil.translate("机构", "zh-CN", "en"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}