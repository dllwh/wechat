package com.cdeledu.util.openplatform.baidu.map.entity;

/**
 * 
 * @title : PlaceApi
 * 
 * @author : 独泪了无痕
 * 
 * @方法描述 :
 *       <p>
 *       Place API 是一类简单的HTTP接口
 *       <p>
 *       用于返回查询某个区域的某类POI数据
 *       <p>
 *       且提供单个POI的详情查询服务
 *       <p>
 * 
 */
public class PlaceApi {

	/**
	 * 状态字段
	 */
	// 本次API访问状态，如果成功返回0，如果失败返回其他数字。
	// private int status;
	// 对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明。
	// private String message;
	// 检索总数，用户请求中设置了page_num字段才会出现total字段。
	// private int total;
	/**
	 * 结果字段(List results)
	 */
	// POI名称
	private String name;
	// POI经纬度坐标
	// private Object location;
	// 经度值
	private float lng;
	// 纬度值
	private float lat;
	// POI地址信息
	private String address = "";
	// POI电话信息
	private String telephone = "";
	// POI唯一标示
	private String uid = "";
	// POI街道id
	private String street_id = "";
	// POI的扩展信息，仅当scope=2时，显示该字段，不同的poi类型，显示的detail_info字段不同。
	// private Object detail_info;
	// 距离中心点的距离
	private int distance;
	// 所属分类，如’hotel’、’cater’。
	private String type = "";
	// 标签
	private String tag = "";
	// POI的详情页
	private String detail_url = "";
	// POI商户的价格
	private String price = "";
	// 营业时间
	private String shop_hours = "";
	// 总体评分
	private String overall_rating = "";
	// 口味评分
	private String taste_rating = "";
	// 服务评分
	private String service_rating = "";
	// 环境评分
	private String environment_rating = "";
	// 星级（设备）评分
	private String facility_rating = "";
	// 卫生评分
	private String hygiene_rating = "";
	// 技术评分
	private String technology_rating = "";
	// 图片数
	private String image_num = "";
	// 团购数
	private int groupon_num;
	// 优惠数
	private int discount_num;
	// 评论数
	private String comment_num = "";
	// 收藏数
	private String favorite_num = "";
	// 签到数
	private String checkin_num = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String streetId) {
		street_id = streetId;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detailUrl) {
		detail_url = detailUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShop_hours() {
		return shop_hours;
	}

	public void setShop_hours(String shopHours) {
		shop_hours = shopHours;
	}

	public String getOverall_rating() {
		return overall_rating;
	}

	public void setOverall_rating(String overallRating) {
		overall_rating = overallRating;
	}

	public String getTaste_rating() {
		return taste_rating;
	}

	public void setTaste_rating(String tasteRating) {
		taste_rating = tasteRating;
	}

	public String getService_rating() {
		return service_rating;
	}

	public void setService_rating(String serviceRating) {
		service_rating = serviceRating;
	}

	public String getEnvironment_rating() {
		return environment_rating;
	}

	public void setEnvironment_rating(String environmentRating) {
		environment_rating = environmentRating;
	}

	public String getFacility_rating() {
		return facility_rating;
	}

	public void setFacility_rating(String facilityRating) {
		facility_rating = facilityRating;
	}

	public String getHygiene_rating() {
		return hygiene_rating;
	}

	public void setHygiene_rating(String hygieneRating) {
		hygiene_rating = hygieneRating;
	}

	public String getTechnology_rating() {
		return technology_rating;
	}

	public void setTechnology_rating(String technologyRating) {
		technology_rating = technologyRating;
	}

	public String getImage_num() {
		return image_num;
	}

	public void setImage_num(String imageNum) {
		image_num = imageNum;
	}

	public int getGroupon_num() {
		return groupon_num;
	}

	public void setGroupon_num(int grouponNum) {
		groupon_num = grouponNum;
	}

	public int getDiscount_num() {
		return discount_num;
	}

	public void setDiscount_num(int discountNum) {
		discount_num = discountNum;
	}

	public String getComment_num() {
		return comment_num;
	}

	public void setComment_num(String commentNum) {
		comment_num = commentNum;
	}

	public String getFavorite_num() {
		return favorite_num;
	}

	public void setFavorite_num(String favoriteNum) {
		favorite_num = favoriteNum;
	}

	public String getCheckin_num() {
		return checkin_num;
	}

	public void setCheckin_num(String checkinNum) {
		checkin_num = checkinNum;
	}
}