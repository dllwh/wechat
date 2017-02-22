package com.cdeledu.crawler.common.imp;

import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cdeledu.common.property.PropertyHelperUtils;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.util.device.OsInfo;
import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * @类描述: 使用Selenium来抓取动态加载的页面
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月21日 下午5:14:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SeleniumHandler extends CrawlHandler {
	/** ----------------------------------------------------- Fields start */
	private static String genPath = Thread.currentThread().getContextClassLoader().getResource("")
			.getPath();
	private static Properties props = null;

	static {
		props = PropertyHelperUtils.getProps("/webdriver/crawl.properties");
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private void sleepTime(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述: 获取FireFox驱动
	 * @return
	 */
	private WebDriver getFirefoxDriver() {
		String path = "";
		if (OsInfo.isLinux()) {
			path = props.getProperty("fireFoxDriver_linux");
		} else if (OsInfo.isMac()) {
			path = props.getProperty("fireFoxDriver_win");
		} else if (OsInfo.isWindows()) {
			path = props.getProperty("fireFoxDriver_mac");
		} else {
			path = props.getProperty("fireFoxDriver_win");
		}

		System.getProperties().setProperty("webdriver.firefox.bin", path);
		FirefoxDriver driver = new FirefoxDriver();
		return driver;
	}

	/**
	 * @方法描述: 获取Chrome驱动
	 * @return
	 */
	private WebDriver getChromeDriver() {
		String path = genPath + props.getProperty("chromeDriver");
		System.getProperties().setProperty("webdriver.chrome.driver", path);
		ChromeDriver driver = new ChromeDriver();
		return driver;
	}

	/**
	 * @方法描述: 获取IE驱动
	 * @return
	 */
	private WebDriver getIEDriver() {
		String path = genPath + props.getProperty("ieDriver");

		System.getProperties().setProperty("webdriver.ie.driver", path);
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		InternetExplorerDriver driver = new InternetExplorerDriver(ieCapabilities);
		return driver;
	}

	/**
	 * @方法描述:
	 * @属性:
	 *      <ul>
	 *      <li>getTitle:用于返回当前网页的标题</li>
	 *      <li>getCurrentUrl:用于获取当前网页的URL:</li>
	 *      <li>getText:用于存储某个元素的文本值.例如链接、纯文本等</li>
	 *      <li>isSelected:用于存储复选框或单选框的勾选情况,返回值为true(勾选)或false(未勾选)</li>
	 *      <li>getTagName:用于获取元素的标记名称</li>
	 *      <li>isEnabled:用于存储input等元素的可编辑状态,如果可以编辑,则返回true, 否则返回false</li>
	 *      </ul>
	 * @param crawlPara
	 * @return
	 */
	private WebDriver getDriver(CrawlParameter crawlPara) {
		WebDriver driver = null;
		// 模拟浏览器
		BrowserVersion browser = crawlPara.getBrowse();
		if (browser.isFirefox()) {// 启动firefox
			driver = getFirefoxDriver();
		} else if (browser.isChrome()) {// 启动chrome
			driver = getChromeDriver();
		} else if (browser.isIE()) { // 启动IE
			driver = getIEDriver();
		}

		return driver;
	}

	/** ----------------------------------------------- [私有方法] */

	@Override
	public String crawl(String url, CrawlParameter crawlPara) {
		// 等待数据加载的时间
		// 为了防止服务器封锁，这里的时间要模拟人的动作，随机太短
		long waitLoadBaseTime = 3000;
		int waitLoadRandomTime = 3000;
		Random random = new Random(System.currentTimeMillis());
		WebDriver driver = getDriver(crawlPara);
		// 模拟浏览器访问请求地址:要抓取的网页
		driver.get(url);
		// driver.navigate()
		// 等待页面动态加载完毕
		sleepTime(waitLoadBaseTime + random.nextInt(waitLoadRandomTime));
		String result = driver.getPageSource();
		// 等待页面动态加载完毕
		sleepTime(waitLoadBaseTime + random.nextInt(waitLoadRandomTime));
		// 关闭 Driver 接口
		driver.close();
		// 关闭浏览器
		driver.quit();
		return result;
	}

	public static void main(String[] args) {
		SeleniumHandler sele = new SeleniumHandler();
		CrawlParameter crawlpara = new CrawlParameter();
		crawlpara.setBrowse(BrowserVersion.INTERNET_EXPLORER_11);
		String source = sele.crawl("http://www.ctrip.com/", crawlpara);
		System.out.println(source);
	}
}
