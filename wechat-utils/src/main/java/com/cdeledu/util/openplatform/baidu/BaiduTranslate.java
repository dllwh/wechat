package com.cdeledu.util.openplatform.baidu;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.exception.ExceptionHelper;
import com.cdeledu.common.network.URLEncodingUtil;
import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.apache.lang.RandomUtil;
import com.cdeledu.util.appConfig.ConfigUtil;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.cdeledu.util.openplatform.baidu.util.constants.BaiduLangType;
import com.cdeledu.util.openplatform.baidu.util.constants.ResultStatus;

/**
 * 
 * @ClassName: BaiduTranslate
 * @Description: 百度翻译(调用百度翻译 API)
 * @author: 独泪了无痕
 * @date: 2015年7月17日 下午1:38:06
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a
 *      href="http://api.fanyi.baidu.com/api/trans/product/apidoc">API接入文档</a>
 */
class BaiduTranslate {
	/** -------------------------- 私有属性 begin ------------------------------- */
	// 翻译API HTTP地址
	private final static String BAIDU_TRANS_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	// 编码格式
	private final static Charset charser = ConstantHelper.UTF_8;

	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */

	/**
	 * @Title: createSignature
	 * @Description: 签名生成
	 * @author: 独泪了无痕
	 * @param appid
	 *            APP ID
	 * @param q
	 *            请求翻译query
	 * @param salt
	 *            随机数
	 * @param secretKey
	 *            平台分配的密钥
	 * @return
	 */
	private static String createSignature(String appid, String query,
			String salt, String secretKey) {
		return DigestUtils.md5Hex(appid + query + salt + secretKey);
	}

	/**
	 * @Title: parse
	 * @Description: 请求并处理返回结果
	 * @author: 独泪了无痕
	 * @param content
	 *            请求翻译query
	 * @param from
	 *            翻译源语言(可设置为auto)
	 * @param to
	 *            译文语言(不可设置为auto)
	 * @param appid
	 *            APP ID
	 * @param salt
	 *            随机数
	 * @param sign
	 *            签名appid+q+salt+密钥 的MD5值
	 */
	private static List<String> parse(String content, String from, String to) {
		List<String> resultList = new ArrayList<String>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("q", URLEncodingUtil.encoding(content, charser));
		paramMap.put("from", from);
		paramMap.put("to", to);
		Map<String, String> appMap = ConfigUtil.getBaiduTrans();
		String appid = appMap.get("appid");
		String secretKey = appMap.get("secretKey");
		String salt = RandomUtil.generateStringByNumberChar(10);
		paramMap.put("appid", appid);
		paramMap.put("salt", salt);
		paramMap.put("sign", createSignature(appid, content, salt, secretKey));
		String realUrl = UrlHelper.appendParaToUrl(BAIDU_TRANS_URL,
				UrlHelper.formatParameters(paramMap));
		String _result = HttpURLConnHelper.sendGetRequest(realUrl,ConstantHelper.UTF_8);
		JSONObject object = new JSONObject(_result);
		if (object.has("error_code")) {
			ExceptionHelper.getExceptionHint("BaiduTranslate",
					ResultStatus.statusMap.get(object.get("error_code")));
		}
		JSONArray trans_result = (JSONArray) object.get("trans_result");
		for (int i = 0; i < trans_result.length(); i++) {
			String _str = String.valueOf(trans_result.get(i));
			JSONObject res = new JSONObject(_str);
			resultList.add(String.valueOf(res.get("dst")));
		}
		return resultList;
	}

	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 共有方法 begin ------------------------------- */
	/**
	 * @Title: translate
	 * @Description: <ul>
	 *               <li>将指定内容从指定源语言语种翻译为指定目标语言语种</li>
	 *               <li>UTF-8编码的PHP数组对应的标准JSON字符串</li>
	 *               <li>GET请求方式</li>
	 *               <li>只有发生错误时,返回的JSON中才包含error_code和error_msg字段</li>
	 *               </ul>
	 * @author: 独泪了无痕
	 * @param from
	 *            源语言语种
	 * @param to
	 *            目标语言语种
	 * @param source
	 *            待翻译内容
	 */
	public static List<String> translate(String from, String to, String source) {
		if (StringUtils.isBlank(from)) {
			from = BaiduLangType.AUTO.getDesc();
		}
		if (StringUtils.isBlank(to)) {
			ExceptionHelper.getExceptionHint("BaiduTranslate", "translate",
					"参数为空");
		}
		return parse(source, from, to);
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
