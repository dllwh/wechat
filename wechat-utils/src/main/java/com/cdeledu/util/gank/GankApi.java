package com.cdeledu.util.gank;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.database.DataTableHelper;
import com.cdeledu.util.network.tcp.HttpClientHelper;

public class GankApi {
	private final static String BASE_SHARE_URL = "http://gank.io/api/data/%s/%s/%s";
	private final static ResourceBundle JDBC = ResourceBundle.getBundle("datasource/jdbc");
	private final static String DBURL = JDBC.getString("database.dbUrl");
	private final static String DBUSERNAME = JDBC.getString("database.dbUserName");
	private final static String DBPASSWORD = JDBC.getString("database.dbPassword");
	private final static String JDBCNAME = JDBC.getString("database.jdbcName");
	/** SQL执行工具 :实例化查询接口 */
	private static QueryRunner runner = null;
	private static DataTableHelper dataTableHelper = null;
	/** SQL执行工具:执行SQL语句 */
	private static String SQL = "INSERT INTO web_meizhi(url,remark,publishedAt) VALUES ('%s','%s','%s')";
	private static String ISEXIST = "SELECT COUNT(1) FROM web_meizhi WHERE url = '%s'";
	static {
		dataTableHelper = DataTableHelper.getInstance(DBURL, DBUSERNAME, DBPASSWORD, JDBCNAME);
		try {
			runner = dataTableHelper.getQueryRunner();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 60; i++) {
			getGanSharekData(10, i);
		}
	}

	/**
	 * @方法描述: 分类数据
	 * @param type
	 *            数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
	 * @param num
	 *            请求个数： 数字，大于0
	 * @param page
	 *            第几页：数字，大于0
	 */
	static void getGanSharekData(Integer num, Integer page) throws Exception {
		String result = "";
		try {
			result = new HttpClientHelper()
					.sendGetRequest(String.format(BASE_SHARE_URL, "福利", num, page));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url, inserSql, desc, publishedAt = "";
		if (StringUtils.isNoneBlank(result)) {
			JSONObject json_1 = new JSONObject(result);
			if (json_1.has("error") && !json_1.getBoolean("error")) {
				JSONArray json_2 = json_1.getJSONArray("results");
				JSONObject json_3 = null;
				Integer size = json_2.length();
				for (int i = 0; i < size; i++) {
					json_3 = new JSONObject(json_2.get(i).toString());
					desc = json_3.getString("desc");
					publishedAt = json_3.getString("publishedAt");
					url = json_3.getString("url");
					if (dataTableHelper.getCount(runner, String.format(ISEXIST, url)) < 1) {
						inserSql = String.format(SQL, url, desc, DateUtilHelper
								.formatDateTime(DateUtilHelper.parse(publishedAt, "yyyy-MM-dd")));
						runner.insert(inserSql, new ScalarHandler<String>());
					}
				}
			}
		}
	}

}
