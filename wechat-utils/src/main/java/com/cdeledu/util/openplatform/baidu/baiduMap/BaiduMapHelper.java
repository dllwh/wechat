package com.cdeledu.util.openplatform.baidu.baiduMap;

import com.cdeledu.util.openplatform.baidu.baiduMap.model.IpResponse;
import com.cdeledu.util.openplatform.baidu.baiduMap.model.RenderReverseResponse;
import com.cdeledu.util.openplatform.baidu.baiduMap.model.ShowLocationResponse;
import com.google.gson.JsonSyntaxException;

/**
 * @类描述: 百度地图工具类(调用百度地图接口)
 * @创建者: 独泪了无痕
 * @创建时间: 2015年7月17日 下午1:37:25
 * @更新时间: 2016年10月17日 下午2:08:33
 * @版本: V1.3
 * @since: JDK 1.7
 * @see <a href="developer.baidu.com/map/index.php?title=webapi">百度地图Web服务API
 *      </a>
 */
public class BaiduMapHelper {
	/** 开发者的访问密钥，必填项 */
	private String ak;

	public BaiduMapHelper(String ak) {
		this.ak = ak;
	}

	/**
	 * @方法:利用IP获取大致位置
	 * @创建人:独泪了无痕
	 * @param ip
	 *            IP地址
	 * @return
	 * @throws Exception
	 */
	public IpResponse getlocationByIP(String ip) throws Exception {
		return BaiduMapIPHelper.getlocationByIP(ak, ip);
	}

	/**
	 * @方法:地理编码
	 * @创建人:独泪了无痕
	 * @param address
	 *            待解析的地址。最多支持84个字节
	 * @param city
	 *            地址所在的城市名
	 * @param ak
	 *            开发者的访问密钥
	 * @param callback
	 *            将json格式的返回值通过callback函数返回以实现jsonp功能
	 * @return
	 * @throws JsonSyntaxException
	 * @throws Exception
	 */
	public ShowLocationResponse showLocation(String address, String city, String callback)
			throws Exception {
		return GeocodingAPIHelper.showLocation(ak, address, city, callback);
	}

	/**
	 * @方法:逆地理编码
	 * @创建人:独泪了无痕
	 * @param ak
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @param pois
	 *            是否召回传入坐标周边的poi，0为不召回，1为召回。当值为1时，默认显示周边1000米内的poi。
	 * @param radius
	 *            poi召回半径，允许设置区间为0-1000米，超过1000米按1000米召回
	 * @param callback
	 *            将json格式的返回值通过callback函数返回以实现jsonp功能
	 * @param extensions_road
	 *            当取值为true时，召回坐标周围最近的3条道路数据
	 * @param extensions_town
	 *            当取值为true时，行政区划返回乡镇级数据（仅国内召回乡镇数据）。默认不访问
	 * @param language
	 *            指定召回的新政区划语言类型
	 * @param language_auto
	 *            是否自动填充行政区划。1填充，0不填充。
	 * @param latest_admin
	 *            是否访问最新版行政区划数据（仅对中国数据生效），1（访问），0（不访问）
	 * @throws Exception
	 */
	public RenderReverseResponse renderReverse(float lat, float lng, String callback,
			boolean extensionsRoad, boolean extensionsTown, String language, boolean languageAuto,
			boolean latestAdmin) throws Exception {
		return GeocodingAPIHelper.renderReverse(ak, lat, lng, callback, extensionsRoad,
				extensionsTown, language, languageAuto, latestAdmin);
	}

	public static void main(String[] args) throws Exception {
		// String ak = "tol935uyoOnUnXmTO2ODU15X";
	}
}
