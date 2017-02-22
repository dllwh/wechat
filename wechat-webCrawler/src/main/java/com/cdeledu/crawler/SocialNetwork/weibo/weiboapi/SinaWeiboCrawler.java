package com.cdeledu.crawler.SocialNetwork.weibo.weiboapi;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cdeledu.crawler.SocialNetwork.weibo.WeiboHelper.SinaWeiboCN;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class SinaWeiboCrawler extends BreadthCrawler {
	String cookie;

	public SinaWeiboCrawler(String crawlPath, boolean autoParse) throws Exception {
		super(crawlPath, autoParse);
		/* 获取新浪微博的cookie，账号密码以明文形式传输，请使用小号 */
		cookie = SinaWeiboCN.getSinaCookie("你的用户名", "你的密码");
	}

	@Override
	public void visit(Page page, CrawlDatums crawlDatum) {
		Elements weibos = page.select("div.c");
		/** 抽取微博 */
		for (Element weibo : weibos) {
			System.out.println(weibo.text());
		}
	}

	public static void main(String[] args) throws Exception {

	}
}
