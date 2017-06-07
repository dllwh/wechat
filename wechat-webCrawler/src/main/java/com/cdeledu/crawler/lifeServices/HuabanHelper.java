package com.cdeledu.crawler.lifeServices;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.cdeledu.util.application.regex.RegexUtil;
import com.cdeledu.util.database.DataTableHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @类描述: 爬去花瓣网数据
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年6月3日 下午4:45:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HuabanHelper {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(HuabanHelper.class);
	private final static String BASE_URL = "http://huaban.com/";
	private final static String BASE_IMG_URL = "http://img.hb.aicdn.com/";
	private final static String BEAUTY_IMG_URL = BASE_URL + "%s/?max=%s&limit=%s&wfl=1";
	private final static String REGEX_SETTINGS = "\\{\"id\":(.*?), \"name\":(.*?), \"nav_link\":(.*?)}";
	private final static String REGEX_IMG = "\\{\"pin_id\":(\\d+),.+?\"board_id\":(\\d+),.+?\"key\":\"(.*?)\",.\"type\":\"image/(.*?)\",";
	private final static String source = "huaban.com/";

	private static ResourceBundle JDBC = ResourceBundle.getBundle("datasource/jdbc");
	private static String dbUrl = JDBC.getString("database.dbUrl");
	private static String dbUserName = JDBC.getString("database.dbUserName");
	private static String dbPassword = JDBC.getString("database.dbPassword");
	private static String jdbcName = JDBC.getString("database.jdbcName");
	private static String SQL = "INSERT INTO crawler_image (userCode,boardId,url,type,source) VALUES ('%s','%s','%s','%s','%s')";
	private static String ISEXIST = "SELECT COUNT(1) FROM crawler_image WHERE url = '%s'";

	/** SQL执行工具 :实例化查询接口 */
	private static QueryRunner runner = null;
	private static DataTableHelper dataTableHelper = null;
	static {
		dataTableHelper = DataTableHelper.getInstance(dbUrl, dbUserName, dbPassword, jdbcName);
		try {
			runner = dataTableHelper.getQueryRunner();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */

	private static String getRealPath(String category, String pid, Integer limit) {
		if (StringUtils.isNoneBlank(category)) {
			if (limit < 1 || limit > 100) {
				limit = 20;
			}
			while (category.startsWith("/")) {
				category = category.substring(category.indexOf("/") + 1);
			}

			while (category.endsWith("/")) {
				category = category.substring(0, category.lastIndexOf("/"));
			}
			return String.format(BEAUTY_IMG_URL, category, pid, limit);
		}
		return "";
	}

	/**
	 * @方法描述: 解析数据
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static List<Map<String, Object>> analyze(String url) throws Exception {
		List<Map<String, Object>> resultList = Lists.newArrayList();
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(REGEX_IMG);
		Matcher m = r.matcher(new HttpURLConnHelper().sendGetRequest(url));
		Map<String, Object> beautyMap = null;
		String pinId, boardId, imgPath, imgType = "";
		while (m.find()) {
			beautyMap = Maps.newConcurrentMap();
			pinId = m.group(1);
			boardId = m.group(2);
			imgPath = BASE_IMG_URL + m.group(3);
			imgType = m.group(4);
			beautyMap.put("pinId", pinId);// 用户ID
			beautyMap.put("boardId", boardId);// 画板ID
			/** 图片地址后面带有类型_fw554、fw658,无实际意义，猜测是避免缓存 */
			beautyMap.put("imgPath", imgPath);// 图片地址
			beautyMap.put("imgType", imgType); // 图片类型
			beautyMap.put("source", source);
			resultList.add(beautyMap);
		
			if (!isExist(imgPath)) {
				try {
					saveDocument(String.format(SQL, boardId,pinId, imgPath, imgType, source));
				} catch (Exception e) {
					logger.info("解析数据入数据库出现异常: " + e);
				}
			}
		}
		return resultList;
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
			// logger.info("解析数据并保存入数据库:" + inserSql);
		}
		runner.insert(inserSql, new ScalarHandler<Long>());
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 类别信息
	 * @return code 编码
	 * @return name 名称
	 * @return nav_link 链接
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getCategoryInfo() throws Exception {
		String result = new HttpURLConnHelper().sendGetRequest(BASE_URL);
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Map<String, Object> catMap = null;
		JSONObject jsRes = null;
		List<String> tempList = Lists.newArrayList();
		RegexUtil.getList(REGEX_SETTINGS, result, 0, tempList);
		for (String categories : tempList) {
			catMap = Maps.newConcurrentMap();
			jsRes = new JSONObject(categories);
			catMap.put("code", jsRes.getString("id"));
			catMap.put("name", jsRes.getString("name"));
			catMap.put("url", jsRes.getString("nav_link"));
			resultList.add(catMap);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取图片信息
	 * @param code
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getImageInfoByCode(String code, String pid,
			Integer limit) throws Exception {
		return analyze(getRealPath("/favorite/" + code, pid, limit));
	}

	/**
	 * @方法描述: 获取图片信息
	 * @param category
	 *            类别
	 * @param pid
	 * @param limit
	 *            个数
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getImageInfoByCategpry(String category, String pid,
			Integer limit) throws Exception {
		return analyze(getRealPath(category, pid, limit));
	}

	/**
	 * @方法描述: 从人物面板获取图片数据
	 * @param bid
	 * @param pid
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getImageInfoByBoard(String bid, String pid,
			Integer limit) throws Exception {
		return analyze(getRealPath("/boards/" + bid, pid, limit));
	}
	public static void main(String[] args)throws Exception {
		getImageInfoByCategpry("favorite/beauty/", "496615", 100); 
	}
}
