package com.cdeledu.crawler.other.crawlerCity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cdeledu.common.browser.UserAgentType;
import com.cdeledu.util.database.DataTableHelper;
import com.cdeledu.util.network.IpUtilHelper;

/**
 * @类描述: 利用网络爬虫端抓取行政区域数据
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月16日 下午8:08:57
 * @版本: V1.2.3
 * @since: JDK 1.7
 */
public class crawlerCityHelper {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(crawlerCityHelper.class);
	private static ResourceBundle JDBC = ResourceBundle.getBundle("datasource/jdbc");
	private static String dbUrl = JDBC.getString("database.dbUrl");
	private static String dbUserName = JDBC.getString("database.dbUserName");
	private static String dbPassword = JDBC.getString("database.dbPassword");
	private static String jdbcName = JDBC.getString("database.jdbcName");
	/** SQL执行工具 :实例化查询接口 */
	private static QueryRunner runner = null;
	private static DataTableHelper dataTableHelper = null;
	/** SQL执行工具:执行SQL语句 */
	private static String SQL = "INSERT INTO sys_dict_china_city"
			+ "(areaName,areaCode,areaUrl,parentId,areaLevel,areaType)"
			+ " VALUES ('%s', '%s','%s', %s, '%s','%s')";
	private static String ISEXIST = "SELECT COUNT(1) FROM sys_dict_china_city WHERE areaCode = '%s'";
	private static String EACHSQL = "SELECT id,areaUrl FROM sys_dict_china_city"
			+ " WHERE areaLevel = '%s' and areaUrl !=''";

	/** ----------------------------------------------------- Fields end */

	static {
		dataTableHelper = DataTableHelper.getInstance(dbUrl, dbUserName, dbPassword, jdbcName);
		try {
			runner = dataTableHelper.getQueryRunner();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取省份代码
	 * @return
	 */
	private static String getProvinceCode(String val) {

		if (val.indexOf(".") == -1)
			return val;
		val = val.substring(0, val.indexOf(".")) + "000000";
		return (String) val.substring(0, 6);
	}

	/**
	 * @方法描述: 查询是否已经该条数据
	 * @param val
	 * @return
	 */
	private static boolean isExist(String val) {
		int resultNum = 0;
		if (StringUtils.isNotBlank(val)) {
			resultNum = dataTableHelper.getCount(runner, String.format(ISEXIST, val));
		}
		return resultNum > 0 ? true : false;
	}

	/**
	 * 
	 * @方法描述: 将得到的document进行解析 存入数据库
	 * @param sql
	 *            执行sql
	 * @throws SQLException
	 */
	private static void saveDocument(String inserSql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.info("解析数据并保存入数据库:" + inserSql);
		}
		runner.insert(inserSql, new ScalarHandler<Long>()).intValue();
	}

	/**
	 * @方法描述: 地级行政区划单位(地级市、地区、自治州、盟)
	 * @param parentId
	 * @param crawlerUrl
	 */
	private static void saveCityInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);
		Elements eles01 = document.getElementsByAttributeValue("class", "citytr");
		for (Element ele01 : eles01) {
			Elements eles02 = ele01.getElementsByTag("a");
			if (eles02.toString().trim().equals("")) {// 表示没有a标签
				continue;
			}
			String code = eles02.get(0).text(); // 城市代码
			String name = eles02.get(1).text();// 城市名称
			String url = eles02.get(0).absUrl("href");
			String inserSql = String.format(SQL, name, code, url, parentId, 2, "");
			if (!isExist(code)) { // 查找是否已存在,不存在则插入
				try {
					saveDocument(inserSql);
				} catch (Exception e) {
					logger.error("获取地级行政区数据之时出现异常:", e);
					saveCityInfo(parentId, crawlerUrl);
				}
			}
		}
	}

	/**
	 * @方法描述: 县级行政区划单位(市辖区、县级市、县、自治县、旗、自治旗、特区、林区)
	 * @param runner
	 * @param url
	 * @return
	 */
	private static void saveCountyInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);

		Elements eles01 = document.getElementsByAttributeValue("class", "countytr");
		for (Element ele01 : eles01) {
			Elements eles02 = ele01.getElementsByTag("a");
			if (eles02.toString().trim().equals("")) {// 表示没有a标签
				continue;
			}
			String code = eles02.get(0).text();// 县级代码
			String name = eles02.get(1).text();// 县级名称
			String url = eles02.get(0).absUrl("href");

			String inserSql = String.format(SQL, name, code, url, parentId, 3, "");
			if (!isExist(code)) { // 查找是否已存在,不存在则插入
				try {
					saveDocument(inserSql);
				} catch (Exception e) {
					logger.error("获取县级行政区数据之时出现异常:", e);
					saveCountyInfo(parentId, crawlerUrl);
				}
			}
		}
	}

	/**
	 * @方法描述: 乡级行政区划单位(区公所、街道、镇、乡、民族乡、苏木、民族苏木)
	 * @param parentId
	 * @param crawlerUrl
	 */
	private static void saveTownInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);
		Elements eles01 = document.getElementsByAttributeValue("class", "towntr");
		for (Element ele01 : eles01) {
			Elements eles02 = ele01.getElementsByTag("a");
			if (eles01.toString().trim().equals("")) {// 表示没有a标签
				continue;
			}
			String code = eles02.get(0).text();// 乡级行政区代码
			String name = eles02.get(1).text();// 乡级行政区名称
			String url = eles02.get(1).absUrl("href");
			String inserSql = String.format(SQL, name, code, url, parentId, 4, "");
			if (!isExist(code)) { // 查找是否已存在,不存在则插入
				try {
					saveDocument(inserSql);
				} catch (Exception e) {
					logger.error("获取乡级行政区数据之时出现异常:", e);
					saveTownInfo(parentId, crawlerUrl);
				}
			}
		}
	}

	/**
	 * @方法描述: 村级行政单位(村民委员会、居民委员会、类似村民委员会、类似居民委员)
	 * @param parentId
	 * @param crawlerUrl
	 */
	private static void saveVillageInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);
		Elements eles01 = document.getElementsByAttributeValue("class", "villagetr");
		for (Element ele01 : eles01) {
			Elements eles02 = ele01.getElementsByTag("td");
			// 乡级行政区代码
			String code = eles02.get(0).text();
			// 乡级行政区名称
			String name = eles02.get(2).text();
			// 城乡分类代码
			String type = eles02.get(1).text();
			String inserSql = String.format(SQL, name, code, null, parentId, 5, type);
			if (!isExist(code)) { // 查找是否已存在,不存在则插入
				try {
					saveDocument(inserSql);
				} catch (Exception e) {
					saveVillageInfo(parentId, crawlerUrl);
				}
			}
		}
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 通过地址得到document对象
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月17日 下午5:34:26
	 * @param url
	 * @return
	 */
	public static Document getDocument(String url) {
		Connection conn = null;
		Document document = null;
		try {
			conn = Jsoup.connect(url);// 获取连接
			conn.header("Connection", "keep-alive");
			conn.header("User-Agent", UserAgentType.PC_Firefox.getValue());// 配置模拟浏览器
			conn.method(Method.POST);
			conn.timeout(10000);
			document = conn.execute().parse();// 获取响应
			// 表示ip被拦截或者其他情况,反正没有获取document对象
			if (null == document || "".equals(document.toString())) {
				IpUtilHelper.setProxyIp(); // 换代理ip
				getDocument(url);// 继续爬取网页
			}
			return document;
		} catch (Exception e) { // 链接超时等其他情况
			e.printStackTrace();
			IpUtilHelper.setProxyIp(); // 换代理ip
			getDocument(url);// 继续爬取网页
		}
		return getDocument(url);
	}

	/**
	 * @方法描述: 省级行政区(省、自治区、直辖市、特别行政区)
	 * @throws Exception
	 */
	public static void getProvinceInfo() {
		/** 初始解析网页地址 */
		String crawlerBaseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/index.html";
		Document document = getDocument(crawlerBaseUrl);
		Elements eles01 = document.getElementsByAttributeValue("class", "provincetr");
		for (Element ele01 : eles01) {
			for (Element ele02 : ele01.getElementsByTag("a")) {
				String code = getProvinceCode(ele02.attr("href"));// 省份代码
				String name = ele02.text();// 省份名称
				String url = ele02.absUrl("href");// 省份地址
				String inserSql = String.format(SQL, name, code, url, "100000", 1, "");
				if (!isExist(code)) { // 查找是否已存在,不存在则插入
					try {
						saveDocument(inserSql);
					} catch (Exception e) {
						logger.error("获取省级行政区数据之时出现异常:", e);
						continue;
					}
				}
			}
		}
	}

	/**
	 * @方法描述: 地级行政区划单位(地级市、地区、自治州、盟)
	 * @return
	 */
	public static void getCityInfo() {
		String sql = String.format(EACHSQL, 1);
		List<Map<String, Object>> result = null;
		try {
			result = runner.query(sql, new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Map<String, Object> object : result) {
			saveCityInfo((Integer) object.get("id"), (String) object.get("areaUrl"));
		}
	}

	/**
	 * @方法描述: 县级行政区划单位(市辖区、县级市、县、自治县、旗、自治旗、特区、林区)
	 * @param runner
	 * @param url
	 * @return
	 */
	public static void getCountyInfo() {
		String sql = String.format(EACHSQL, 2);
		List<Map<String, Object>> result = null;
		try {
			result = runner.query(sql, new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Map<String, Object> object : result) {
			saveCountyInfo((Integer) object.get("id"), (String) object.get("areaUrl"));
		}
	}

	/**
	 * @方法描述: 乡级行政区划单位(区公所、街道、镇、乡、民族乡、苏木、民族苏木)
	 * @return
	 */
	public static void getTownInfo() {
		String sql = String.format(EACHSQL, 3);
		List<Map<String, Object>> result = null;
		try {
			result = runner.query(sql, new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Map<String, Object> object : result) {
			saveTownInfo((Integer) object.get("id"), (String) object.get("areaUrl"));
		}
	}

	/**
	 * @方法描述: 村级行政单位(村民委员会、居民委员会、类似村民委员会、类似居民委员)
	 */
	public static void getVillageInfo() {
		String sql = String.format(EACHSQL, 4);
		List<Map<String, Object>> result = null;
		try {
			result = runner.query(sql, new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Map<String, Object> object : result) {
			saveVillageInfo((Integer) object.get("id"), (String) object.get("areaUrl"));
		}
	}

	/**
	 * @方法描述: 插入特别地区
	 */
	public static void getSpecialArea(){
		// 台湾省
		if(!isExist("710000")){
			String inserSql = String.format(SQL, "台湾省", "710000", "", "100000", 1, "");
			try {
				saveDocument(inserSql);
			} catch (Exception e) {
				logger.error("插入特别地区【台湾省】出现异常:", e);
			}
		}
		// 香港特别行政区
		if(!isExist("810000")){
			String inserSql = String.format(SQL, "香港特别行政区", "810000", "", "100000", 1, "");
			try {
				saveDocument(inserSql);
			} catch (Exception e) {
				logger.error("插入特别地区【香港特别行政区】出现异常:", e);
			}
		}
		// 澳门特别行政区
		if(!isExist("820000")){
			String inserSql = String.format(SQL, "澳门特别行政区", "820000", "", "100000", 1, "");
			try {
				saveDocument(inserSql);
			} catch (Exception e) {
				logger.error("插入特别地区【澳门特别行政区】出现异常:", e);
			}
		}
		// 钓鱼岛
		if(!isExist("900000")){
			String inserSql = String.format(SQL, "钓鱼岛", "900000", "", "100000", 1, "");
			try {
				saveDocument(inserSql);
			} catch (Exception e) {
				logger.error("插入特别地区【钓鱼岛】出现异常:", e);
			}
		}
	}
	
	public static void main(String[] args) {
		// getProvinceInfo();  // 省级行政区
		// getSpecialArea(); // 插入特别地区
		// getCityInfo(); // 地级行政区划单位
		// getCountyInfo();// 县级行政区划单位
		// getTownInfo(); // 乡级行政区划单位
		// getVillageInfo();// 村级行政单位
	}
}
