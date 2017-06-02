package com.cdeledu.crawler.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;

/**
 * @类描述: 从相关网抓取有关信息数据
 * @创建者: 独泪了无痕
 * @创建日期: 2016年3月23日 下午10:39:33
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CtripHelperUtil {
	/** ----------------------------------------------------- Fields start */
	// 编码格式
	private final static String CHARSER = ConstantHelper.UTF_8.name();
	private static HttpURLConnHelper conn = null;
	static {
		conn = HttpURLConnHelper.getInstance(CHARSER);
	}

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:历史上的今天查询服务
	 * @创建人:独泪了无痕
	 * @see <a href="http://www.rijiben.com/">历史上今天大事记</a>
	 * @return
	 */
	public static String getTodayInHistory() throws Exception {
		/**
		 * 1.发起http get请求获取指定url的网页源码
		 */
		String html = conn.sendGetRequest("http://www.rijiben.com");
		/**
		 * 2.利用正则表达式从网页源码中抽取"历史上的今天"信心数据<br/>
		 */
		StringBuffer sb = new StringBuffer();
		// 日期标签:用以确定区分今天还是昨天
		String dateTag = DateUtilHelper.getMonthDay(0);
		String regex = "(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		if (matcher.matches()) {
			// ① 因为不能保证网站上的数据一定在凌晨准时更新,所有必须判断截取到的数据是当天的还是前一天的
			if (matcher.group(3).contains(DateUtilHelper.getMonthDay(-1))) {
				dateTag = DateUtilHelper.getMonthDay(-1);
			}
			// ② 封装标题
			sb.append("== ").append("历史上的今天").append(dateTag).append(" ==").append("\n\n");
			// ③ 抽取内容
			for (String info : matcher.group(3).split("  ")) {
				info = info.replace(dateTag, "").replace("（图）", "").replaceAll("</?[^>]+>", "")
						.replace("&nbsp;&nbsp;", "").trim();
				// 在每行的末尾追加换行
				if (!"".equals(info)) {
					sb.append(info).append("\n\n");
				}
			}
		}
		return (null == sb) ? "" : sb.substring(0, sb.lastIndexOf("\n\n"));
	}

}
