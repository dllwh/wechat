package com.cdeledu.crawler.leisureTourism.mmPictures.taobao;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;

public class GrilPhotoDetailPage {
	/** ----------------------------------------------------- Fields start */
	/** 相册内照片的总页数 */
	private int userID, totalPicPage = 1;
	private String albumID;
	private static String alumUrl = "https://mm.taobao.com/album/json/get_album_photo_list.htm?user_id=%s&album_id=%s&page=%s";
	/** 单页美女图片的链接地址 */
	LinkedList<String> imageSrcs = new LinkedList<String>();

	/** ----------------------------------------------------- Fields end */
	public GrilPhotoDetailPage(int userID, String albumID, int totalPicPage) {
		super();
		this.userID = userID;
		this.albumID = albumID;
		this.totalPicPage = totalPicPage;
	}

	/**
	 * @方法描述: 相册详情页
	 * @param url
	 *            详情页面地址
	 */
	public void getGrilPhotoDetailPage() throws Exception {
		String url = "", result = "", picUrl = "";
		JSONObject _jResult = null;
		JSONArray jsArray = null;
		for (int picCount = 1; picCount <= totalPicPage; picCount++) {
			url = String.format(alumUrl, userID, albumID, picCount);
			try {

				result = HttpURLConnHelper.getInstance().sendPostRequest(url);
				_jResult = new JSONObject(result);
				if (_jResult.getInt("isError") == 0 && _jResult.getInt("isOK") == 1) {
					jsArray = _jResult.getJSONArray("picList");
					for (int i = 0; i < jsArray.length(); i++) {
						picUrl = jsArray.getJSONObject(i).getString("picUrl");
						picUrl = picUrl.substring(0, picUrl.indexOf("-tstar.jpg")) + "-tstar.jpg";
						imageSrcs.add("https:" + picUrl);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
