package com.cdeledu.util.openplatform.wangsu;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CDN常量
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年3月15日 下午2:56:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class CDNConstHelper {
	/** 基本地址 */
	private static final String BASE_URL = "http://api.direct.chinanetcenter.com/";
	/** 创建导播实例 API */
	public static final String CREATE_URL = BASE_URL + "api/create";
	/** 单音画模式 API */
	public static String SINGLE_URL = BASE_URL + "v1/%s/control/single?videoIndex=%s&audio=%s";
	/** 混合音画模式 API */
	public static String MINGLE_URL = BASE_URL + "v1/%s/control/mingle";
	/** 会议模式 API */
	public static String MEETING_URL = BASE_URL + "v1/%s/control/meeting";
	/** 输入配置 API */
	public static String INPUT_URL = BASE_URL + "v1/%s/source/input/%s";
	/** 输出配置 API */
	public static String OUTPUT_URL = BASE_URL + "v1/%s/source/output";
	/** 停止导播 API */
	public static String STOP_URL = BASE_URL + "v1/%s/config/stop";
	/** 延迟导播 API */
	public static String DELAY_URL = BASE_URL + "v1/%s/config/delay";
	/** 错误回调接口 */
	public static final String CALLBACK_URL = "http://180.97.246.19/direct/callback";
	/** 最大输入流个数 */
	public static final int INPUTSIZE = 4;
}
