package com.cdeledu.util.openplatform.google;

/**
 * @ClassName: GoogleMap
 * @Description: Google地图API
 * @author: 独泪了无痕
 * @date: 2015年12月9日 下午3:42:01
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="http://blog.csdn.net/ithomer/article/details/6598020">
 *      Baidu与Google地图API初探</a>
 */
public class GoogleMap {
	private static double EARTH_RADIUS = 6378.137; // 地球半径
	// 将用角度表示的角转换为近似相等的用弧度表示的角 Math.toRadians

	/**
	 * @方法描述: 谷歌地图计算两个坐标点的距离
	 * @param lng1
	 *            经度1
	 * @param lat1
	 *            纬度1
	 * @param lng2
	 *            经度2
	 * @param lat2
	 *            纬度2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double a = radLat1 - radLat2;
		double b = Math.toRadians(lng1) - Math.toRadians(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	/**
	 * @方法描述: 测试
	 */
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			getDistance(116.403933, 39.914147, 116.403237, 39.927919);
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - b) + "毫秒"); // 耗时：461毫秒
		double dist = getDistance(116.403933, 39.914147, 116.403237, 39.927919);
		System.out.println("两点相距：" + dist + "千米"); // 两点相距：1.0千米

	}
}
