package com.cdeledu.crawler.SocialNetwork.weibo.WeiboHelper;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cn.edu.hfut.dmic.webcollector.net.HttpRequest;

/**
 * @类描述: 利用Selenium获取登陆新浪微博weibo.cn的cookie
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月31日 下午2:26:47
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SinaWeiboCN {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 获取cookie
	 * @param driver
	 * @param sb
	 * @return
	 */
	private static String concatCookie(HtmlUnitDriver driver) {
		Set<Cookie> cookieSet = driver.manage().getCookies();
		driver.close();
		StringBuilder sb = new StringBuilder();
		for (Cookie cookie : cookieSet) {
			sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
		}
		return sb.toString();
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取新浪微博的cookie，这个方法针对weibo.cn有效，对weibo.com无效
	 *        weibo.cn以明文形式传输数据，请使用小号
	 * @param username
	 *            新浪微博用户名
	 * @param password
	 *            新浪微博密码
	 * @return
	 */
	public static String getSinaCookie(String username, String password) throws Exception {
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.get("http://login.weibo.cn/login/");

		/** 手机号/电子邮箱/会员帐号: */
		WebElement mobile = driver.findElementByCssSelector("input[name=mobile]");
		mobile.sendKeys(new CharSequence[] { username });

		/** 密码 */
		WebElement pass = driver.findElementByCssSelector("input[name^=password]");
		pass.sendKeys(new CharSequence[] { password });

		/** 图片验证码 */
		WebElement ele = driver.findElementByCssSelector("img");
		String src = ele.getAttribute("src");
		String cookie = concatCookie(driver);
		HttpRequest request = new HttpRequest(src);
		request.setCookie(cookie);

		String imageCode = "";
		WebElement code = driver.findElementByCssSelector("input[name^=code]");
		code.sendKeys(new CharSequence[] { imageCode });

		/** 记住登录状态，需支持并打开手机的cookie功能。 */
		WebElement rem = driver.findElementByCssSelector("input[name=remember]");
		rem.click();

		/** 登录按钮 */
		WebElement submit = driver.findElementByCssSelector("input[name=submit]");
		submit.click();

		String result = concatCookie(driver);

		if (result.contains("gsid_CTandWM")) {
			return result;
		}
		throw new Exception("weibo login failed");

	}
}
