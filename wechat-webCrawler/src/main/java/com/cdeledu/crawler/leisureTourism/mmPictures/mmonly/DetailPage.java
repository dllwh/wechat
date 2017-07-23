package com.cdeledu.crawler.leisureTourism.mmPictures.mmonly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

/**
 * @类描述: 详情页面
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月23日 下午4:24:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
class DetailPage {
	/** ----------------------------------------------------- Fields start */
	private String pre_url;
	private Integer pages = 1;
	Pattern pattern1, pattern2;
	private static Pattern page_pattern = Pattern.compile("\\u5171(\\d+)+\\u9875");
	private static String page_regex = "\\u5171(\\d+)+\\u9875";
	LinkedList<String> srcs = new LinkedList<String>();

	/** ----------------------------------------------------- Fields end */
	public DetailPage(String pre_url) {
		super();
		this.pre_url = pre_url;
		pattern1 = Pattern.compile(GetEveryPictures.picture_regex[0]);
		pattern2 = Pattern.compile(GetEveryPictures.picture_regex[1]);
		initPages();
	}

	public int getTotal() {
		return pages;
	}

	/**
	 * @方法: 获取总个数
	 * @创建人:独泪了无痕
	 */
	private void initPages() {
		BufferedReader in = null;
		URL url = null;
		try {
			if (pre_url.endsWith(".html")) {
				url = new URL(pre_url);
			} else {
				url = new URL(pre_url + ".html");
			}

			in = new BufferedReader(new InputStreamReader(url.openStream(), "gb2312"));
			String line;
			while ((line = in.readLine()) != null) {
				Matcher matcher = page_pattern.matcher(line);
				if (matcher.find()) {
					pages = Integer.parseInt(matcher.group().replaceAll(page_regex, "$1"));
					return;
				}
			}
		} catch (Exception e) {
			pages = 0;
			return;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public void initSrcs() {
		URL url = null;
		for (int i = 1; i <= pages; i++) {
			BufferedReader in = null;
			String url_str = "";
			try {
				if (pre_url.endsWith(".html")) {
					url_str = pre_url.substring(0,pre_url.indexOf(".html"));
				} else {					
					url_str = pre_url;
				}
				
				if (i != 1) {
					url_str = url_str + "_" + i;
				}
				url = new URL(url_str + ".html");
				in = new BufferedReader(new InputStreamReader(url.openStream(), "gb2312"));
				String line;
				while ((line = in.readLine()) != null) {
					Matcher matcher = pattern1.matcher(line);
					if (matcher.find()) {
						Matcher matcher2 = pattern2.matcher(matcher.group());
						if (matcher2.find()) {
							srcs.add(matcher2.group());
							System.out.println(matcher2.group() + "添加成功");
						}
					}
				}
			} catch (Exception e) {
				System.out.println("已跳过" + url);
				continue;
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
	}
}
