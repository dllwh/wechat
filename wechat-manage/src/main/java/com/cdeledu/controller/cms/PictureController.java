package com.cdeledu.controller.cms;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.LayuiResponse;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.cms.picture.GankResponse;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.gson.Gson;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 图片管理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月3日 下午8:47:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("pictureController")
public class PictureController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	private static HttpURLConnHelper httpRequest = HttpURLConnHelper.getInstance();

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 妹子图片管理
	 * @return
	 */
	@RequestMapping("meiziPicture")
	public String index(ModelMap modelMap) {
		return "platform/picture/meiziPicture";
	}

	private String getGanRequest(int rows, int page) {
		String result = "http://gank.io/api/data/";
		try {
			return result + URLEncoder.encode("福利", "UTF-8") + "/" + rows + "/" + page;
		} catch (Exception e) {
			return result + rows + "/" + page;
		}
	}

	@ResponseBody
	@RequestMapping(value = "meiziPicture", params = "getGanSharekData")
	public LayuiResponse getGanSharekData(
			@RequestParam(defaultValue = "30", required = true) int rows,
			@RequestParam(defaultValue = "1", required = true) int page) {
		LayuiResponse response = new LayuiResponse();
		String result = "";
		
		try {
			result = httpRequest.sendGetRequest(getGanRequest(rows, page));
			if (StringUtils.isNotBlank(result)) {
				GankResponse gank = new Gson().fromJson(result, GankResponse.class);
				if (gank.ifSuccess()) {
					if(gank.getResults() != null && gank.getResults().size() > 0){
						response.setData(gank.getResults());
						response.setCount((page+1)*rows);
					} else {
						response.setCount(page*rows);
						
					}
				} else {
					response.setCount(page*rows);
				}
			}

		} catch (Exception e) {
			response.setMsg(e.getMessage());
			response.setCount(0);
		}
		return response;
	}
}
