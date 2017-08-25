package com.cdeledu.crawler.SocialNetwork.csdn.csdnHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.cdeledu.util.network.tcp.HttpClientHelper;

public class CsdnHelper {
	/** ----------------------------------------------------- Fields start */
	protected static Logger logger = Logger.getLogger(CsdnHelper.class.getName());

	private static boolean isLogin = false;
	private static String msg = null;
	/** 【特别注意】:登陆信息全部在"httpClient"中保存,所以需要设置为全局变量 */
	private static HttpClientHelper httpClient =HttpClientHelper.getInstance();
	/** CSDN 登录网址 */
	public static final String LOGINURL = "https://passport.csdn.net/account/login?ref=toolbar";
	/** CSDN登录成功后的网址 */
	public static final String LOGINSUCESSURL = "http://my.csdn.net/my/mycsdn";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 登录页面，获取以及给服务器传递必要信息
	 * @param username
	 *            用户名/邮箱/手机号
	 * @param password
	 *            密码
	 */
	public static String loginCsdnPager(String username, String password) {
		String retJSON = "{'status':%s,'msg':'%s'}";

		String html = null;
		// 这个是登录的页面
		try {
			html = httpClient.sendGetRequest(LOGINURL);
		} catch (Exception logEx) {
			logEx.printStackTrace();
		}
		Document doc = Jsoup.parse(html);
		// 获取表单所在的节点
		Element form = doc.select(".user-pass").get(0);
		/**
		 * ① 以下三个是服务器给的标记信息，必须具有该信息登录才有效
		 * lt:该参数可以理解成每个需要登录的用户都有一个流水号。只有有了webflow发放的有效的流水号，
		 * 用户才可以说明是已经进入了webflow流程。
		 * 否则，没有流水号的情况下，webflow会认为用户还没有进入webflow流程，从而会重新进入一次webflow流程，
		 * 从而会重新出现登录界面
		 */
		String lt = form.select("input[name=lt]").get(0).val();
		String execution = form.select("input[name=execution]").get(0).val();
		String _eventId = form.select("input[name=_eventId]").get(0).val();

		/**
		 * 开始构造登录的信息：账号、密码、以及三个标记信息
		 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("lt", lt));
		nvps.add(new BasicNameValuePair("execution", execution));
		nvps.add(new BasicNameValuePair("_eventId", _eventId));

		/**
		 * 开始请求CSDN服务器进行登录操作。一个简单封装，直接获取返回结果
		 */
		String ret = null;
		try {
			ret = httpClient.sendPostRequest(LOGINURL, nvps);
			// ret中会包含以下信息，进行判断即可。
			if (ret.indexOf("redirect_back") > -1) {
				isLogin = true;
				msg = "登陆成功。。。。。";
			} else if (ret.indexOf("登录太频繁") > -1) {
				msg = ("登录太频繁，请稍后再试。。。。。");
			} else {
				msg = ("登陆失败。。。。。");
			}
		} catch (Exception e) {
			msg = ("登陆失败,请稍候重试!");
		}
		// httpClient.destroyClient();

		return String.format(retJSON, isLogin, msg);
	}

}
