package com.cdeledu.crawler.lifeServices.moivesSpider;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cdeledu.crawler.lifeServices.moivesSpider.dytt8Moive.FloorWorkThread;
import com.cdeledu.crawler.lifeServices.moivesSpider.dytt8Moive.TaskQueue;
import com.cdeledu.crawler.lifeServices.moivesSpider.dytt8Moive.TopWorkThread;
import com.google.common.collect.Lists;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 电影天堂的最新电影爬取
 * 
 *       <pre>
 *       1）请求栏目的首页来获取到分页的总数以及推测出每个分页的 url 地址      
 *       2）将获取到的分页 url 存放到名为 floorQueue 队列中       
 *       3）从 floorQueue 中依次取出分页url，然后利用多线程发起请求    
 *       4）将获取到的电影页面 url 存入到名为 middleQueue 的队列    
 *       5）从 middleQueue 中依次取出电影页面 url，再利用多线程发起请求 
 *       6）将请求结果使用 Xpath 解析并提取所需的电影信息             
 *       7）将爬取到的电影信息存到名为 contentQueue 队列中         
 *       8）从 contentQueue 队列中依次取出电影信息，然后存到数据库中。
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月18日 上午8:07:49
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class Dytt8MoiveHelper {
	/** ----------------------------------------------------- Fields start */
	/** 获取爬虫程序抓取入口 */
	private final static String breakoutUrl = "http://www.dytt8.net/html/gndy/dyzz/index.html";
	/** 请求网络线程总数, 线程数最好不要设置太高, 不然会返回很多 400 */
	private final static int theadNum = 5;
	private final static int timeoutMillis = 60000;

	// http://www.dytt8.net/html/gndy/dyzz/index.html
	// http://www.dytt8.net/html/gndy/rihan/index.html
	// http://www.dytt8.net/html/gndy/oumei/index.html
	// http://www.dytt8.net/html/gndy/china/index.html
	// http://www.dytt8.net/html/gndy/jddy/index.html
	/** ----------------------------------------------------- Fields end */

	public static void main(String[] args) {
		// startSpider();
		// getMoiveInforms("http://www.dytt8.net/html/gndy/dyzz/20180416/56714.html");
		getMoivePageUrlList("http://www.dytt8.net/html/gndy/dyzz/index.html");
	}

	public static void startSpider() {
		List<String> floorlist = getPageUrlList();
		TaskQueue taskQueue = new TaskQueue();

		ConcurrentLinkedQueue<String> floorQueue = taskQueue.getFloorQueue();
		for (String item : floorlist) {
			// System.out.println("request url is ### "+item+" ###");
			floorQueue.offer(item);
		}
		int floorWorkThreadNum = 0, topWorkThreadNum = 0;

		while (floorWorkThreadNum <= theadNum) {
			if (taskQueue.isFloorQueueEmpty()) {
				break;
			}
			new FloorWorkThread(taskQueue).start();
			try {
				Thread.sleep(200);
			} catch (InterruptedException floorWorkExp) {
				floorWorkExp.printStackTrace();
			}
			floorWorkThreadNum++;
		}

		while (topWorkThreadNum <= theadNum) {
			new TopWorkThread(taskQueue).start();
			if (taskQueue.isMiddleQueueEmpty()) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException topWorkExp) {
				topWorkExp.printStackTrace();
			}
			topWorkThreadNum++;
		}

		Map<String, Object> item = null;
		while (!taskQueue.isContentQueueEmpty()) {
			item = taskQueue.getContentQueue().poll();
			System.out.println(item);
		}

	}

	/**
	 * @方法描述 : 获取【最新电影】有多少个页面
	 */
	private static int getMaxsize() {
		int optionList = 0;

		try {
			optionList = Jsoup.parse(new URL(breakoutUrl), timeoutMillis)
					.select("div.x > select > option").size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 因首页重复, 正在考虑是否需要要减1,暂时定位不减
		return optionList;
	}

	/**
	 * @方法描述 : 主要功能：目录页url取出
	 */
	public static List<String> getPageUrlList() {
		String request_url_prefix = "http://www.dytt8.net/html/gndy/dyzz/";
		int self = getMaxsize();
		List<String> templist = Lists.newArrayList();
		for (int i = 1; i <= self; i++) {
			templist.add(request_url_prefix + "list_23_" + i + ".html");
		}
		return templist;
	}

	/**
	 * @方法描述 : 获取电影信息的网页链接
	 */
	public static List<String> getMoivePageUrlList(String html) {
		Document doc = null;
		Elements elements = null;
		List<String> templist = null;
		try {
			doc = Jsoup.parse(new URL(html), timeoutMillis);
			elements = doc.select("div.co_content8 >ul > table > tbody > tr > td > b");
			int tempLeng = elements.size();
			templist = Lists.newArrayList();
			for (int i = 0; i < tempLeng; i++) {
				templist.add(elements.get(i).getElementsByTag("a").attr("abs:href"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templist;
	}

	/**
	 * @方法描述 :
	 * 
	 *       <pre>
	 *       解析电影信息页面的内容:
	 *       ◎ 类型		type
	 *       ◎ 译名		trans_name
	 *       ◎ 片名		name        
	 *       ◎ 年代		decade      
	 *       ◎ 国家		conutry     
	 *       ◎ 类别		level       
	 *       ◎ 语言		language    
	 *       ◎ 字幕		subtitles   
	 *       ◎ 上映日期	publish     
	 *       ◎ IMDb评分	IMDB_socre  
	 *       ◎ 豆瓣评分	duban_score
	 *       ◎ 文件格式	format      
	 *       ◎ 视频尺寸	resolution  
	 *       ◎ 文件大小	size        
	 *       ◎ 片长		duration  
	 *       ◎ 导演		director    
	 *       ◎ 主演		actors      
	 *       ◎ 简介		placard     
	 *       ◎ 海报		screenshot  
	 *       ◎ 影片截图	tpurl                                    
	 *       ◎ 下载地址	dytt8_url
	 *       </pre>
	 * 
	 */
	public static List<Map<String, Object>> getMoiveInforms(String html) {
		dirToList();
		return null;
	}

	/** 下载列表 */
	private static List<Map<String, Object>> dirToList() {
		List<Map<String, Object>> itemlist = Lists.newArrayList();
		return itemlist;
	}
}
