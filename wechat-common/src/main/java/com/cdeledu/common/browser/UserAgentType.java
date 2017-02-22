package com.cdeledu.common.browser;

/**
 * @类描述:
 *       <ul>
 *       <li>常用浏览器的useragent</li>
 *       <li>不同Agent下看到的内容不一样，比如，京东网站上的手机版网页和pc版网页上的商品优惠不一样</li>
 *       <li>为避免被屏蔽，爬取不同的网站经常要定义和修改useragent值</li>
 *       </ul>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月29日 下午1:01:43
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum UserAgentType {
	/**
	 * PC端的UserAgent
	 */
	/** 火狐浏览器 */
	PC_Firefox("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0"),
	/** Firefox 4.0.1 – MAC */
	PC_Firefox_MAC("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1"),
	/** Firefox 4.0.1 – MAC */
	PC_Firefox_Windows("Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1"),
	/** 谷歌浏览器 */
	PC_Chrome("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36"),
	/** Chrome 17.0 – MAC */
	PC_Chrome_MAC("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"),
	/** pc端 Safari*/
	PC_Safari("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50"),
	/** IE 6.0 */
	PC_IE_6("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)"),
	/** IE 7.0 */
	PC_IE_7("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)"),
	/** IE 8.0 */
	PC_IE_8("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)"),
	/** IE 9.0 */
	PC_IE_9("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0"),
	/** IE 11 */
	PC_IE_11("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; rv:11.0) like Gecko"),

	/**
	 * 移动设备端
	 */
	
	Mobile_Firefox("Mozilla/5.0 (Androdi; Linux armv7l; rv:5.0) Gecko/ Firefox/5.0 fennec/5.0"),
	Mobile_Chrome("Mozilla/5.0 (Linux; U; Android 2.2.1; zh-cn; HTC_Wildfire_A3333 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"),
	/** safari iOS 4.33 – iPhone */
	Mobile_iPhone("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5"),
	/** safari iOS 4.33 – iPod Touch */
	Mobile_iPod_Touch("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5"),
	/** safari iOS 4.33 – iPod Touch */
	Mobile_iPad("Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");

	private String value = "";

	private UserAgentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
