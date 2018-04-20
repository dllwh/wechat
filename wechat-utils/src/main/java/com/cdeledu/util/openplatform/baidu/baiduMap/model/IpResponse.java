package com.cdeledu.util.openplatform.baidu.baiduMap.model;

public class IpResponse extends BaiduMaoApiResult {
	private static final long serialVersionUID = 1L;
	/** 详细地址信息 */
	private String address;
	/** 结构化地址信息 */
	private content content;

	class content {
		/** 简要地址信息 */
		private String address;
		/** 结构化地址信息 */
		private AddressDetail address_detail;
		/** 当前城市中心点 */
		private Point point;

		class AddressDetail {
			/** 城市 */
			private String city;
			/** 百度城市代码 */
			private String city_code;
			/** 区县 */
			private String district;
			/** 省份 */
			private String province;
			/** 街道 */
			private String street;
			/** 门牌号 */
			private String street_number;

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getCity_code() {
				return city_code;
			}

			public void setCity_code(String city_code) {
				this.city_code = city_code;
			}

			public String getDistrict() {
				return district;
			}

			public void setDistrict(String district) {
				this.district = district;
			}

			public String getProvince() {
				return province;
			}

			public void setProvince(String province) {
				this.province = province;
			}

			public String getStreet() {
				return street;
			}

			public void setStreet(String street) {
				this.street = street;
			}

			public String getStreet_number() {
				return street_number;
			}

			public void setStreet_number(String street_number) {
				this.street_number = street_number;
			}

			@Override
			public String toString() {
				return "AddressDetail [city=" + city + ", city_code=" + city_code + ", district="
						+ district + ", province=" + province + ", street=" + street
						+ ", street_number=" + street_number + "]";
			}
		}

		class Point {
			/** 当前城市中心点经度 */
			private String x;
			/** 当前城市中心点纬度 */
			private String y;

			public String getX() {
				return x;
			}

			public void setX(String x) {
				this.x = x;
			}

			public String getY() {
				return y;
			}

			public void setY(String y) {
				this.y = y;
			}

			@Override
			public String toString() {
				return "Point [x=" + x + ", y=" + y + "]";
			}
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public AddressDetail getAddress_detail() {
			return address_detail;
		}

		public void setAddress_detail(AddressDetail address_detail) {
			this.address_detail = address_detail;
		}

		public Point getPoint() {
			return point;
		}

		public void setPoint(Point point) {
			this.point = point;
		}

		@Override
		public String toString() {
			return "content [address=" + address + ", address_detail=" + address_detail + ", point="
					+ point + "]";
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public content getContent() {
		return content;
	}

	public void setContent(content content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n IpResponse [address=" + address + ", content=" + content
				+ "]";
	}

}
