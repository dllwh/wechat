package com.cdeledu.util.mobile;

import javax.servlet.http.HttpServletRequest;

/**
 * @类描述: 移动工具帮助类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月6日 下午12:48:05
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=""></a>
 */
public final class MobileUtilHelper {
	/** ----------------------------------------------------- Fields start */
	/** Wap网关Via头信息中特有的描述信息 */
	private static String[] mobileGateWayHeaders = new String[] {
		"ZXWAP",// 中兴提供的wap网关的via信息
		"chinamobile.com",// 中国移动的诺基亚wap网关
		"monternet.com",// 移动梦网的网关
		"infoX",// 华为提供的wap网关
		"XMS 724Solutions HTG",// 国外电信运营商的wap网关
		"Bytemobile"// 貌似是一个给移动互联网提供解决方案提高网络运行效率的
	};
	/** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
	private static String[] pcHeaders = new String[] {
		"Windows 98", 
		"Windows ME", 
		"Windows 2000",
		"Windows XP",
		"Windows NT", 
		"Ubuntu" 
	};

	/** 手机浏览器的User-Agent里的关键词 */
	private static String[] mobileUserAgents = new String[] {
		"Nokia",//诺基亚
		"SAMSUNG",//三星手机
		"Windows CE",//Windows CE
		"iPhone",//iPhone
		"iPad",//iPad
		"Android",//Android
		"UCWEB",
		"ucweb",
		"IEMobile",//Windows CE手机自带浏览器
		"GoBrowser",//3g GoBrowser
		"WAP2.0"//支持wap 2.0的  
	};
	
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 根据当前请求的特征,判断该请求是否来自手机终端,主要检测特殊的头信息,以及user-Agent这个header
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月6日 下午12:49:45
	 * @param request
	 *            http请求
	 * @return 如果命中手机特征规则，则返回对应的特征字符串
	 */
	public static final boolean isMobileDevice(HttpServletRequest request) {
		boolean result = false;
		boolean pcFlag = false;
		boolean mobileFlag = false;

		String via = request.getHeader("Via");
		String userAgent = request.getHeader("user-agent");

		for (int i = 0; via!=null && !via.trim().equals("") && i < mobileGateWayHeaders.length; i++) {  
            if(via.contains(mobileGateWayHeaders[i])){  
                mobileFlag = true;  
                break;  
            }  
        }  
        for (int i = 0;!mobileFlag && userAgent!=null && !userAgent.trim().equals("") && i < mobileUserAgents.length; i++) {  
            if(userAgent.contains(mobileUserAgents[i])){  
                mobileFlag = true;  
                break;  
            }  
        }  
        for (int i = 0; userAgent!=null && !userAgent.trim().equals("") && i < pcHeaders.length; i++) {  
            if(userAgent.contains(pcHeaders[i])){  
                pcFlag = true;  
            }  
        }  
		if(mobileFlag==true && mobileFlag!=pcFlag){  
			result = true;  
        }  
		
		return result;
	}
}
