package com.cdeledu.crawler.leisureTourism.mmPictures.mmonly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

class WebsitList {
	/** ----------------------------------------------------- Fields start */
	private String pre_url;
	private Integer num, begin, end;
	LinkedHashMap<String, String> urls = new LinkedHashMap<String, String>();

	/** ----------------------------------------------------- Fields end */
	public WebsitList(String pre_url, Integer num, Integer begin, Integer end) {
		super();
		this.pre_url = pre_url;
		this.num = num;
		this.begin = begin;
		this.end = end;
	}

	public void initUrls() throws Exception {
		URL url = null;
		for (int i = begin; i <= end; i++) {
			BufferedReader in = null;
			try {
				if (i != 1)
					url = new URL(pre_url + "list_" + num + "_" + i + ".html");
				else {
					url = new URL(pre_url);
				}
				in = new BufferedReader(new InputStreamReader(url.openStream(), "gb2312"));
				String line;
				while ((line = in.readLine()) != null) {
					matchAll(line);
				}
			} catch (Exception e) {
				System.out.println("已跳过" + url);
				continue;
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
	}

	private void matchAll(String line) {
		Pattern pattern1 = Pattern.compile(GetEveryPictures.base_url_regex[0]);
		Pattern pattern2 = Pattern.compile(GetEveryPictures.base_url_regex[1]);
		Matcher matcher1 = pattern1.matcher(line);
		Matcher title_matcher1 = Pattern.compile(GetEveryPictures.title_regex[0]).matcher(line);
		Pattern title_pattern2 = Pattern.compile(GetEveryPictures.title_regex[1]);

		String match, title_match, url_str, title;
		if (matcher1.find()) {
			match = matcher1.group();
			Matcher matcher2 = pattern2.matcher(match);
			if (matcher2.find()) {
				url_str = matcher2.group();
				if (title_matcher1.find()) {
					title_match = title_matcher1.group();
					Matcher title_matcher2 = title_pattern2.matcher(title_match);
					if (title_matcher2.find()) {
						title = title_matcher2.group();
						urls.put(url_str, title);
						// System.out.println("添加成功：" + title + url_str);
					}
				}
			}
		}
	}
}
