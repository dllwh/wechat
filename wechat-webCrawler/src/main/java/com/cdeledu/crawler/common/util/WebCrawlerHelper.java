package com.cdeledu.crawler.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;

import com.cdeledu.common.webCrawler.CrawlType;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.imp.CrawlHandler;
import com.cdeledu.crawler.common.imp.HtmlUnitHandler;
import com.cdeledu.crawler.common.imp.HttpClientHandler;
import com.cdeledu.crawler.common.imp.JsoupHandler;
import com.cdeledu.crawler.common.imp.SeleniumHandler;
import com.cdeledu.crawler.common.imp.UrlConnHandler;

/**
 * @类描述: 数据抓取以及解析通用类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月29日 下午3:03:58
 * @版本: V1.1
 * @since: JDK 1.7
 */
public class WebCrawlerHelper {
	/** ----------------------------------------------------- Fields start */
	private CrawlHandler crawlHandler;
	private CrawlParameter crawlPara;

	public WebCrawlerHelper() {
		crawlPara = new CrawlParameter();
		getInstance();
	}

	public WebCrawlerHelper(CrawlParameter crawlPara) {
		this.crawlPara = crawlPara;
		getInstance();
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private void getInstance() {
		CrawlType type = crawlPara.getType();// 如果此处设置了参数 默认用htmlunit
		
		switch (type) {
		case jsoup:
			crawlHandler = new JsoupHandler();
			break;
		case urlconn:
			crawlHandler = new UrlConnHandler();
			break;
		case httpclient:
			crawlHandler = new HttpClientHandler();
			break;
		case htmlunit:
			crawlHandler = new HtmlUnitHandler();
			break;
		case selenium:
			crawlHandler = new SeleniumHandler();
			break;
		default:
			crawlHandler = new HtmlUnitHandler();
			break;
		}
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 获取请求
	 * @param url
	 * @return
	 */
	public String getSource(String url) {
		return crawlHandler.crawl(url, crawlPara);
	}

	/**
	 * @方法描述: 保存文件
	 * @param doc
	 *            Html文档
	 * @param path
	 *            文件路径
	 * @param code
	 *            编码格式
	 * @return filePaht 保存文件路径
	 * @throws IOException
	 */
	public static String saveHtml(Document doc, String path, String code) throws IOException {
		// 文件保存绝对路径
		String filePath = path + File.separator + System.currentTimeMillis() + "_" + doc.title()
				+ ".htm";
		File file = new File(filePath);
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		fos = new FileOutputStream(file);
		osw = new OutputStreamWriter(fos, code);
		// 写入成功
		osw.write(doc.html());
		IOUtils.closeQuietly(osw);
		IOUtils.closeQuietly(fos);
		return filePath;
	}
}
