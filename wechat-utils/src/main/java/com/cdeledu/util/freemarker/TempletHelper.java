package com.cdeledu.util.freemarker;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @类描述: 简单的Java模板工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月16日 上午9:55:37
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="https://my.oschina.net/haogrgr/blog/222349"></a>
 */
public class TempletHelper {
	/** ----------------------------------------------------- Fields start */
	/** 默认分割符 */
	public static final String DEFAULT_SPLIT = "$$";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 根据分割符从模板中取得变量的名字($$变量名$$)
	 * @eg: $$aaa$$$$bbb$$ccc$$ 返回 aaa,bbb
	 * @param templet
	 *            模板
	 * @param begin
	 *            包围变量名的字符串开始
	 * @param finish
	 *            包围变量名的字符串结束
	 * @return 模板中的变量名
	 */

	private static Set<String> getParamNames(String content, String begin, String finish) {

		if (StringUtils.isBlank(content) || StringUtils.isBlank(begin)
				|| StringUtils.isBlank(finish)) {
			return null;
		}

		Set<String> paramNames = new HashSet<String>();

		int start = 0, end = 0;
		while (end < content.length()) {

			start = content.indexOf(begin, end) + begin.length();
			if (start == -1) {
				break;
			}
			end = content.indexOf(finish, start);
			if (end == -1) {
				break;
			}
			paramNames.add(content.substring(start, end));
			end = end + finish.length();
		}

		return paramNames;
	}

	/**
	 * @方法描述: 根据分割符从模板中取得变量的名字($$变量名$$)
	 * @eg: $$aaa$$$$bbb$$ccc$$ 返回 aaa,bbb
	 * @param templet
	 *            模板
	 * @param split
	 *            包围变量名的字符串
	 * @return 模板中的变量名
	 */
	private static Set<String> getParamNames(String templet, String split) {
		if (StringUtils.isBlank(templet) || StringUtils.isBlank(split)) {
			return null;
		}
		return getParamNames(templet, split, split);
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 使用context中对应的值替换templet中用split包围的变量名
	 * @param templet
	 *            模板
	 * @param split
	 *            用于标识变量名的标志
	 * @param context
	 *            用于替换模板中的变量
	 * @return
	 */
	public static String render(String templet, String split, Map<String, Object> context) {
		Objects.requireNonNull(templet, "模板为空");
		Objects.requireNonNull(split, "用于标识变量名的标志为空");
		Objects.requireNonNull(context, "用于替换模板中的变量为空");
		Set<String> paramNames = getParamNames(templet, split);
		for (String name : paramNames) {
			Object val = context.get(name);
			String value = null == val ? "" : val.toString();
			String regex = "\\Q" + split + name + split + "\\E";
			templet = templet.replaceAll(regex, value);
		}
		return templet;
	}

	public static void main(String[] args) {
		// String t1 = "你好 $$name$$, 您的验证码是:$$code$$";
		// String t2 = "你好 #{name}, 您的验证码是:${code}";
		// String t3 =
		// "亲爱的{buyname}:您于{submittime}在{shooName}提交订单,订单编号{ordersn}请及时进行后续处理,具体操作请访问{shopurl}";
	}
}
