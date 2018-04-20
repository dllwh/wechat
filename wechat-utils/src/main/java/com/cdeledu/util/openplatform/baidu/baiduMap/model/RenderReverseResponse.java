package com.cdeledu.util.openplatform.baidu.baiduMap.model;

import java.util.List;

public class RenderReverseResponse extends BaiduMaoApiResult {
	private static final long serialVersionUID = 1L;
	private RenderReverse result;

	class RenderReverse {
		/** 经纬度坐标 */
		private Location location;

		class Location {
			/** 纬度值 */
			private float lat;
			/** 经度值 */
			private float lng;

			public float getLat() {
				return lat;
			}

			public void setLat(float lat) {
				this.lat = lat;
			}

			public float getLng() {
				return lng;
			}

			public void setLng(float lng) {
				this.lng = lng;
			}

			@Override
			public String toString() {
				return "location [lat=" + lat + ", lng=" + lng + "]";
			}
		}

		/** 结构化地址信息 */
		private String formatted_address;
		/** 可信度，描述打点准确度。[0,100]，大于80表示误差低于100m */
		private String confidence;
		/** 坐标所在商圈信息，如 "人民大学,中关村,苏州街"。最多返回3个。 */
		private String business;
		private AddressComponent addressComponent;

		class AddressComponent {
			/** 国家 */
			private String country;
			/** 省名 */
			private String province;
			/** 城市名 */
			private String city;
			/** 区县名 */
			private String district;
			/** 乡镇名 */
			private String town;
			/** 街道名（行政区划中的街道层级） */
			private String street;
			/** 街道门牌号 */
			private String street_number;
			/** 行政区划代码 */
			private Integer adcode;
			/** 国家代码 */
			private Integer country_code;
			/** 相对当前坐标点的方向，当有门牌号的时候返回数据 */
			private String direction;
			/** 相对当前坐标点的距离，当有门牌号的时候返回数据 */
			private String distance;

			public String getCountry() {
				return country;
			}

			public void setCountry(String country) {
				this.country = country;
			}

			public String getProvince() {
				return province;
			}

			public void setProvince(String province) {
				this.province = province;
			}

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getDistrict() {
				return district;
			}

			public void setDistrict(String district) {
				this.district = district;
			}

			public String getTown() {
				return town;
			}

			public void setTown(String town) {
				this.town = town;
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

			public Integer getAdcode() {
				return adcode;
			}

			public void setAdcode(Integer adcode) {
				this.adcode = adcode;
			}

			public Integer getCountry_code() {
				return country_code;
			}

			public void setCountry_code(Integer country_code) {
				this.country_code = country_code;
			}

			public String getDirection() {
				return direction;
			}

			public void setDirection(String direction) {
				this.direction = direction;
			}

			public String getDistance() {
				return distance;
			}

			public void setDistance(String distance) {
				this.distance = distance;
			}

			@Override
			public String toString() {
				return "AddressComponent [country=" + country + ", province=" + province + ", city="
						+ city + ", district=" + district + ", town=" + town + ", street=" + street
						+ ", street_number=" + street_number + ", adcode=" + adcode
						+ ", country_code=" + country_code + ", direction=" + direction
						+ ", distance=" + distance + "]";
			}
		}

		/** 周边poi数组 */
		private List<Pois> pois;

		class Pois {
			/** 地址信息 */
			private String addr;
			/** 数据来源（已废弃） */
			private String cp;
			/** 和当前坐标点的方向 */
			private String direction;
			/** 离坐标点距离 */
			private Integer distance;
			/** poi名称 */
			private String name;
			/** poi类型，如’ 办公大厦,商务大厦’ */
			private String poiType;
			/** poi坐标{x,y} */
			private Point point;

			class Point {
				private Float x;
				private Float y;

				public Float getX() {
					return x;
				}

				public void setX(Float x) {
					this.x = x;
				}

				public Float getY() {
					return y;
				}

				public void setY(Float y) {
					this.y = y;
				}

				@Override
				public String toString() {
					return "Point [x=" + x + ", y=" + y + "]";
				}
			}

			/** 电话 */
			private String tel;
			/** poi唯一标识 */
			private String uid;
			/** 邮编 */
			private String zip;

			public String getAddr() {
				return addr;
			}

			public void setAddr(String addr) {
				this.addr = addr;
			}

			public String getCp() {
				return cp;
			}

			public void setCp(String cp) {
				this.cp = cp;
			}

			public String getDirection() {
				return direction;
			}

			public void setDirection(String direction) {
				this.direction = direction;
			}

			public Integer getDistance() {
				return distance;
			}

			public void setDistance(Integer distance) {
				this.distance = distance;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getPoiType() {
				return poiType;
			}

			public void setPoiType(String poiType) {
				this.poiType = poiType;
			}

			public Point getPoint() {
				return point;
			}

			public void setPoint(Point point) {
				this.point = point;
			}

			public String getTel() {
				return tel;
			}

			public void setTel(String tel) {
				this.tel = tel;
			}

			public String getUid() {
				return uid;
			}

			public void setUid(String uid) {
				this.uid = uid;
			}

			public String getZip() {
				return zip;
			}

			public void setZip(String zip) {
				this.zip = zip;
			}

			@Override
			public String toString() {
				return "Pois [addr=" + addr + ", cp=" + cp + ", direction=" + direction
						+ ", distance=" + distance + ", name=" + name + ", poiType=" + poiType
						+ ", point=" + point + ", tel=" + tel + ", uid=" + uid + ", zip=" + zip
						+ "]";
			}

		}

		private List<PoiRegions> poiRegions;

		class PoiRegions {
			/** 请求中的坐标与所归属区域面的相对位置关系 */
			private String direction_desc;
			/** 归属区域面名称 */
			private String name;
			/** 归属区域面类型 */
			private String tag;

			public String getDirection_desc() {
				return direction_desc;
			}

			public void setDirection_desc(String direction_desc) {
				this.direction_desc = direction_desc;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getTag() {
				return tag;
			}

			public void setTag(String tag) {
				this.tag = tag;
			}

			@Override
			public String toString() {
				return "PoiRegions [direction_desc=" + direction_desc + ", name=" + name + ", tag="
						+ tag + "]";
			}
		}

		/** 当前位置结合POI的语义化结果描述 */
		private String sematic_description;
		/** 城市id */
		private String cityCode;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public String getFormatted_address() {
			return formatted_address;
		}

		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}

		public String getConfidence() {
			return confidence;
		}

		public void setConfidence(String confidence) {
			this.confidence = confidence;
		}

		public String getBusiness() {
			return business;
		}

		public void setBusiness(String business) {
			this.business = business;
		}

		public AddressComponent getAddressComponent() {
			return addressComponent;
		}

		public void setAddressComponent(AddressComponent addressComponent) {
			this.addressComponent = addressComponent;
		}

		public List<Pois> getPois() {
			return pois;
		}

		public void setPois(List<Pois> pois) {
			this.pois = pois;
		}

		public List<PoiRegions> getPoiRegions() {
			return poiRegions;
		}

		public void setPoiRegions(List<PoiRegions> poiRegions) {
			this.poiRegions = poiRegions;
		}

		public String getSematic_description() {
			return sematic_description;
		}

		public void setSematic_description(String sematic_description) {
			this.sematic_description = sematic_description;
		}

		public String getCityCode() {
			return cityCode;
		}

		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}

		@Override
		public String toString() {
			return "RenderReverse [location=" + location + ", formatted_address="
					+ formatted_address + ", confidence=" + confidence + ", business=" + business
					+ ", addressComponent=" + addressComponent + ", pois=" + pois + ", poiRegions="
					+ poiRegions + ", sematic_description=" + sematic_description + ", cityCode="
					+ cityCode + "]";
		}

	}

	public RenderReverse getResult() {
		return result;
	}

	public void setResult(RenderReverse result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n RenderReverseResponse [result=" + result + "]";
	}
}
