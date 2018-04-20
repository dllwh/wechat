package com.cdeledu.util.openplatform.baidu.baiduMap;

import com.cdeledu.util.openplatform.baidu.baiduMap.model.IpResponse;

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
}
