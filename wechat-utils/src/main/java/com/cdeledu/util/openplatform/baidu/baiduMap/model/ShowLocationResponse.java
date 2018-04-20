package com.cdeledu.util.openplatform.baidu.baiduMap.model;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 地理编码服务响应结果
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 下午8:37:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ShowLocationResponse extends BaiduMaoApiResult {
	private static final long serialVersionUID = 1L;
	private ShowLocation result;

	class ShowLocation {
		/** 经纬度坐标 */
		private Location location;
		/** 位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点 */
		private Integer precise;
		/** 可信度，描述打点准确度，大于80表示误差小于100m。该字段仅作参考，返回结果准确度主要参考precise参数 */
		private Integer confidence;
		/** 地址类型 */
		private String level;

		class Location {
			/** 纬度值 */
			private Float lat;
			/** 经度值 */
			private Float lng;

			public Float getLat() {
				return lat;
			}

			public void setLat(Float lat) {
				this.lat = lat;
			}

			public Float getLng() {
				return lng;
			}

			public void setLng(Float lng) {
				this.lng = lng;
			}

			@Override
			public String toString() {
				return "Location [lat=" + lat + ", lng=" + lng + "]";
			}
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public Integer getPrecise() {
			return precise;
		}

		public void setPrecise(Integer precise) {
			this.precise = precise;
		}

		public Integer getConfidence() {
			return confidence;
		}

		public void setConfidence(Integer confidence) {
			this.confidence = confidence;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		@Override
		public String toString() {
			return "ShowLocationResponse [location=" + location + ", precise=" + precise
					+ ", confidence=" + confidence + ", level=" + level + "]";
		}
	}

	public ShowLocation getResult() {
		return result;
	}

	public void setResult(ShowLocation result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n ShowLocationResponse [result=" + result + "]";
	}

}
